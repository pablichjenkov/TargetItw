package com.target.targetcasestudy.data.usecase

import com.target.targetcasestudy.FakeDataGenerator
import com.target.targetcasestudy.api.DealApiKtx
import com.target.targetcasestudy.api.DealsResponse
import kotlinx.coroutines.test.runTest
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever
import java.io.IOException

class DealCollectionUseCaseTest {

    @Test
    fun `Test DealCollectionUseCase success`() = runTest {

        val dealApiMock = mock<DealApiKtx>().also {
            whenever(it.retrieveDeals()).thenAnswer {
                DealsResponse(FakeDataGenerator.createPartialDealList())
            }
        }

        val dealCollectionUseCase = DealCollectionUseCase(dealApiMock)

        val result = dealCollectionUseCase.getAll()

        assert(result is DealCollectionUseCase.Result.Success)
    }

    @Test
    fun `Test DealCollectionUseCase error`() = runTest {

        val dealApiMock = mock<DealApiKtx>().also {
            whenever(it.retrieveDeals()).thenAnswer {
                throw IOException()
            }
        }

        val dealCollectionUseCase = DealCollectionUseCase(dealApiMock)
        val result = dealCollectionUseCase.getAll()

        assert(result is DealCollectionUseCase.Result.Error)
    }

}