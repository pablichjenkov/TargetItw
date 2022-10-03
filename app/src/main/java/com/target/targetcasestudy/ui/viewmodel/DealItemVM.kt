package com.target.targetcasestudy.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.target.targetcasestudy.api.DealFull
import com.target.targetcasestudy.data.ApiError
import com.target.targetcasestudy.data.usecase.FullDealUseCase
import com.target.targetcasestudy.data.usecase.IFullDealUseCase
import com.target.targetcasestudy.di.DispatcherContainer
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class DealItemVM @Inject constructor(
    private val fullDealUseCase: IFullDealUseCase,
    private val dispatcherContainer: DispatcherContainer
) : ViewModel() {

    private val _fullDealStateFlow = MutableStateFlow<SingleDealState>(SingleDealState.Idle)
    val fullDealStateFlow: StateFlow<SingleDealState> get() = _fullDealStateFlow
    private var fullDealInMemoryCache: DealFull? = null

    fun requestFullDeal(dealId: Long) {
        viewModelScope.launch(dispatcherContainer.io) {

            val fullDealInMemoryCacheCopy = fullDealInMemoryCache
            if (fullDealInMemoryCacheCopy != null) {
                _fullDealStateFlow.emit(SingleDealState.Data(fullDealInMemoryCacheCopy))
                return@launch
            }

            _fullDealStateFlow.emit(SingleDealState.Loading)

            when (val result = fullDealUseCase.getDeal(dealId)) {
                is FullDealUseCase.Result.Success -> {
                    fullDealInMemoryCache = result.dealFull
                    _fullDealStateFlow.emit(SingleDealState.Data(result.dealFull))
                }
                is FullDealUseCase.Result.Error -> {
                    _fullDealStateFlow.emit(SingleDealState.Error(result.apiError))
                }
            }

        }
    }

    sealed class SingleDealState {
        object Idle : SingleDealState()
        object Loading : SingleDealState()
        class Error(val apiError: ApiError) : SingleDealState()
        class Data(val fullDeal: DealFull) : SingleDealState()
    }

}