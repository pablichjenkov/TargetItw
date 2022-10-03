package com.target.targetcasestudy.api

import retrofit2.http.GET
import retrofit2.http.Path

interface DealApiKtx {
  @GET("${Urls.BASE_URL}/deals")
  suspend fun retrieveDeals(): DealsResponse

  @GET("${Urls.BASE_URL}/deals/{dealId}")
  suspend fun retrieveDeal(@Path("dealId") dealId: Long): DealFull
}
