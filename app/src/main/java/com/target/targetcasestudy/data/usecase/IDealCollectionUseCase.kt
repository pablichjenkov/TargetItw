package com.target.targetcasestudy.data.usecase

interface IDealCollectionUseCase {
    suspend fun getAll(): DealCollectionUseCase.Result
}