package com.target.targetcasestudy.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.target.targetcasestudy.api.Deal
import com.target.targetcasestudy.data.DealCollectionUseCase
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

    fun requestDealList() {
        viewModelScope.launch(dispatcherContainer.io) {

            _dealListStateFlow.emit(DealListState.Loading)

            when (val result = dealCollectionUseCase.getAll()) {
                is DealCollectionUseCase.Result.Success -> {
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
        class Data(val dealList: List<Deal>) : DealListState()
    }

}

