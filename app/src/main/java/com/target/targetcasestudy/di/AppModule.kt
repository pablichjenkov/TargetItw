package com.target.targetcasestudy.di

import com.target.targetcasestudy.ui.ViewModelFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    /*@Provides
    @Singleton
    fun providesApplicationContext() : Application {
        return application
    }*/

    @Provides
    @Singleton
    fun providesDispatcherContainers(): DispatcherContainer {
        return DispatcherContainer()
    }

}