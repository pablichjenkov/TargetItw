package com.target.targetcasestudy.di

import com.google.gson.Gson
import com.target.targetcasestudy.api.DealApiKtx
import com.target.targetcasestudy.api.Urls
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object HttpModule {

    @Provides
    @Singleton
    fun providesDealApiKtx(gson: Gson): DealApiKtx {
        return Retrofit.Builder()
            .baseUrl(Urls.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
            .create(DealApiKtx::class.java)
    }

    @Provides
    @Singleton
    fun providesOkHttpClient(
    ) : OkHttpClient {
        return OkHttpClient.Builder()
            //.addInterceptor(loggingInterceptor)
            .connectTimeout(1, TimeUnit.MINUTES)
            .readTimeout(1, TimeUnit.MINUTES)
            .writeTimeout(1, TimeUnit.MINUTES)
            .callTimeout(3, TimeUnit.MINUTES)
            .build()
    }

    @Provides
    @Singleton
    fun providesGson(
    ) : Gson {
        return Gson()
            .newBuilder()
            .setPrettyPrinting()
            .create()
    }

    /*@Provides
    fun providesHttpLoggingInterceptor(
    ) : HttpLoggingInterceptor {
        return HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
    }*/

}