package com.target.targetcasestudy.data.usecase

import android.util.Log
import com.target.targetcasestudy.api.DealApiKtx
import com.target.targetcasestudy.api.DealPartial
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class DealCollectionUseCase @Inject constructor(
    private val dealApi: DealApiKtx,
) : IDealCollectionUseCase {

    override suspend fun getAll(): Result {

        return try {
            Result.Success(dealApi.retrieveDeals().products)
        } catch (ex: IOException) {
            Result.Error(ex.message ?: "No exception Info")
        } catch (ex: HttpException) {
            Log.d("DealCollectionUseCase", "Pablo error ex.message")
            Result.Error(ex.message ?: "No exception Info")
        }

    }

    sealed class Result {
        class Error(val errorMsg: String) : Result()
        class Success(val products: List<DealPartial>) : Result()
    }

}