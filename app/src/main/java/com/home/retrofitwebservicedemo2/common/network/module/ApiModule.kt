package com.home.retrofitwebservicedemo2.common.network.module

import com.home.retrofitwebservicedemo2.common.network.Api
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
object ApiModule {

    @JvmStatic
    @Provides // 提供被依賴的對象
    @Singleton // 表明這個被依賴的對像在應用的生命週期裡只有一個實例
    fun provideApi(okHttpClient: OkHttpClient, baseUrl: String): Api {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(MoshiConverterFactory.create())
            .client(okHttpClient)
            .build().create(Api::class.java)
    }
}
