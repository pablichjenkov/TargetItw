package com.target.targetcasestudy.ui.viewmodel

import com.target.targetcasestudy.FakeDataGenerator
import com.target.targetcasestudy.data.usecase.FullDealUseCase
import com.target.targetcasestudy.data.usecase.IFullDealUseCase
import com.target.targetcasestudy.di.DispatcherContainer
import com.target.targetcasestudy.newUnconfinedTestDispatcher
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.runTest
import org.junit.Test

internal class DealItemVMTest {
    @Test
    fun `Test state historical when calling DealItemVM_requestFullDeal`() = runTest {
        val testDispatcher = newUnconfinedTestDispatcher()
        val valueHistory = mutableListOf<DealItemVM.SingleDealState>()

        val fullDealUseCase = object : IFullDealUseCase {
            override suspend fun getDeal(dealId: Long): FullDealUseCase.Result {
                // Pretend a delay to avoid conflation in the StateFlow so Loding event does not
                // get override with Data
                delay(20)
                return FullDealUseCase.Result.Success(FakeDataGenerator.createDealFull(0L))
            }
        }

        val dispatcherContainer = DispatcherContainer(
            main = testDispatcher,
            default = testDispatcher,
            io = testDispatcher,
            unconfined = testDispatcher
        )

        val dealItemVM = DealItemVM(
            fullDealUseCase,
            dispatcherContainer
        )

        val job = launch(testDispatcher) {
            dealItemVM.fullDealStateFlow.collect {
                valueHistory.add(it)
            }
        }

        dealItemVM.requestFullDeal(0L)

        testDispatcher.scheduler.advanceUntilIdle()
        job.cancel()

        assert(valueHistory.size == 3)

        println("Pablo::" + valueHistory)

        assert(valueHistory[0] is DealItemVM.SingleDealState.Idle)
        assert(valueHistory[1] is DealItemVM.SingleDealState.Loading)
        assert(valueHistory[2] is DealItemVM.SingleDealState.Data)
    }

}