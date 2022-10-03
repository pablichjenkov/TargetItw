package com.target.targetcasestudy.ui

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn
import dagger.hilt.android.EntryPointAccessors
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Inject

class ViewModelFactory @Inject constructor(
    @ApplicationContext private val applicationContext: Context
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {

        val hiltEntryPoint =
            EntryPointAccessors.fromApplication(
                applicationContext,
                ViewModelFactoryHiltEntryPoint::class.java
            )

        return when (modelClass) {

            DealListVM::class.java -> {
                hiltEntryPoint.provideDealListVM() as T
            }

            else -> throw ClassNotFoundException()
        }

    }

}

@EntryPoint
@InstallIn(SingletonComponent::class)
interface ViewModelFactoryHiltEntryPoint {
    fun provideDealListVM(): DealListVM
}