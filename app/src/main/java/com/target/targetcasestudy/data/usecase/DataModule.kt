package com.target.targetcasestudy.data.usecase

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class DataModule {

    @Binds
    abstract fun providesFullDealUseCase(fullDealUseCase: FullDealUseCase) : IFullDealUseCase

    @Binds
    abstract fun providesDealCollectionUseCase(
        dealCollectionUseCase: DealCollectionUseCase
    ) : IDealCollectionUseCase

}