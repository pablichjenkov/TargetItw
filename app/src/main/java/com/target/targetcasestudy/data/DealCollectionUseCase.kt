package com.target.targetcasestudy.data

import com.target.targetcasestudy.api.Deal
import com.target.targetcasestudy.api.DealApiKtx
import java.io.IOException
import javax.inject.Inject

class DealCollectionUseCase @Inject constructor(
    private val dealApi: DealApiKtx,
) {

    suspend fun getAll(): Result {

        return try {
            Result.Success(dealApi.retrieveDeals().products)
        } catch (ex: IOException) {
            Result.Error(ex.message?:"No exception Info")
        }

    }

    sealed class Result {
        class Error(val errorMsg: String): Result()
        class Success(val products: List<Deal>): Result()
    }

}