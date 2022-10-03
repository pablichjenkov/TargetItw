package com.target.targetcasestudy.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.target.targetcasestudy.api.DealPartial
import com.target.targetcasestudy.data.usecase.DealCollectionUseCase
import com.target.targetcasestudy.di.DispatcherContainer
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class DealListVM @Inject constructor(
    private val dealCollectionUseCase: DealCollectionUseCase,
    private val dispatcherContainer: DispatcherContainer
) : ViewModel() {

    private val _dealListStateFlow = MutableStateFlow<DealListState>(DealListState.Idle)
    val dealListStateFlow: StateFlow<DealListState> get() = _dealListStateFlow
    private var productInMemoryCache: List<DealPartial>? = null

    fun requestDealList() {
        viewModelScope.launch(dispatcherContainer.io) {

            val productInMemoryCacheCopy = productInMemoryCache
            if (productInMemoryCacheCopy != null) {
                _dealListStateFlow.emit(DealListState.Data(productInMemoryCacheCopy))
                return@launch
            }

            _dealListStateFlow.emit(DealListState.Loading)

            when (val result = dealCollectionUseCase.getAll()) {
                is DealCollectionUseCase.Result.Success -> {
                    productInMemoryCache = result.products
                    _dealListStateFlow.emit(DealListState.Data(result.products))
                }
                is DealCollectionUseCase.Result.Error -> {
                    _dealListStateFlow.emit(DealListState.Error(result.errorMsg))
                }
            }

        }
    }

    sealed class DealListState {
        object Idle : DealListState()
        object Loading : DealListState()
        class Error(val errorMsg: String) : DealListState()
        class Data(val dealList: List<DealPartial>) : DealListState()
    }

}

