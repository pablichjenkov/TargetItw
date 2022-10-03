package com.target.targetcasestudy.data.usecase

import android.util.Log
import com.google.gson.Gson
import com.target.targetcasestudy.api.DealApiKtx
import com.target.targetcasestudy.api.DealFull
import com.target.targetcasestudy.data.ApiError
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class FullDealUseCase @Inject constructor(
    private val dealApi: DealApiKtx,
    private val gson: Gson
) : IFullDealUseCase {

    override suspend fun getDeal(dealId: Long): Result {

        return try {
            Result.Success(dealApi.retrieveDeal(dealId))
        } catch (ex: IOException) {
            Result.Error(ApiError("IOException","IoException check your network connection"))
        } catch (ex: HttpException) {
            val errorJsonString = ex.response()?.errorBody()?.string()
            val apiError = gson.fromJson(errorJsonString, ApiError::class.java)
            Result.Error(apiError)
        }

    }

    sealed class Result {
        class Error(val apiError: ApiError): Result()
        class Success(val dealFull: DealFull): Result()
    }

}