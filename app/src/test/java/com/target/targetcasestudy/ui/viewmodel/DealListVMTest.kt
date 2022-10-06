package com.target.targetcasestudy.ui.viewmodel

import com.target.targetcasestudy.FakeDataGenerator
import com.target.targetcasestudy.data.usecase.DealCollectionUseCase
import com.target.targetcasestudy.data.usecase.IDealCollectionUseCase
import com.target.targetcasestudy.di.DispatcherContainer
import com.target.targetcasestudy.newUnconfinedTestDispatcher
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.runTest
import org.junit.Test

internal class DealListVMTest {
    @Test
    fun `Test state historical when calling DealListVM_requestDealList`() = runTest {
        val testDispatcher = newUnconfinedTestDispatcher()
        val valueHistory = mutableListOf<DealListVM.DealListState>()

        val collectionDealUseCase = object : IDealCollectionUseCase {
            override suspend fun getAll(): DealCollectionUseCase.Result {
                // Pretend a delay to avoid conflation in the StateFlow so Loading event does not
                // get override with Data
                delay(20)
                return DealCollectionUseCase.Result.Success(FakeDataGenerator.createPartialDealList())
            }
        }

        val dispatcherContainer = DispatcherContainer(
            main = testDispatcher,
            default = testDispatcher,
            io = testDispatcher,
            unconfined = testDispatcher
        )

        val dealListVM = DealListVM(
            collectionDealUseCase,
            dispatcherContainer
        )

        val job = launch(testDispatcher) {
            dealListVM.dealListStateFlow.collect {
                valueHistory.add(it)
            }
        }

        dealListVM.requestDealList()

        testDispatcher.scheduler.advanceUntilIdle()
        job.cancel()

        assert(valueHistory.size == 3)

        println("Pablo::" + valueHistory)

        assert(valueHistory[0] is DealListVM.DealListState.Idle)
        assert(valueHistory[1] is DealListVM.DealListState.Loading)
        assert(valueHistory[2] is DealListVM.DealListState.Data)
    }

}