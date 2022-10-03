package com.target.targetcasestudy.data.usecase

interface IFullDealUseCase {
    suspend fun getDeal(dealId: Long): FullDealUseCase.Result
}