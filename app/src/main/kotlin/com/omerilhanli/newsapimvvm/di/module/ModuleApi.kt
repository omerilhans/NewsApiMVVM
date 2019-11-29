package com.omerilhanli.newsapimvvm.di.module

import com.fasterxml.jackson.databind.ObjectMapper
import com.omerilhanli.newsapimvvm.BuildConfig
import com.omerilhanli.newsapimvvm.api.Api
import com.omerilhanli.newsapimvvm.api.constant.ApiConst
import dagger.Module
import dagger.Provides
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.CallAdapter
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.jackson.JacksonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
class ModuleApi {

    /*           * from top to bottom
                 ______
                 = == =
                   ||
                   ||
                  \\//
                   \/
    */

    @Provides
    @Singleton
    fun provideInterceptor(): Interceptor {
        val loggingInterceptor = HttpLoggingInterceptor()
        if (BuildConfig.DEBUG) {// HelperLogger, Debug Mode
            loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        } else {// No logging, Release Mode
            loggingInterceptor.level = HttpLoggingInterceptor.Level.NONE
        }
        return loggingInterceptor
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(interceptor: Interceptor): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(interceptor)
            .connectTimeout(ApiConst.KEY_TIMEOUT, TimeUnit.SECONDS)
            .writeTimeout(ApiConst.KEY_TIMEOUT, TimeUnit.SECONDS)
            .readTimeout(ApiConst.KEY_TIMEOUT, TimeUnit.SECONDS)
            .build()
    }

    @Provides
    @Singleton
    fun provideConverterFactory(): Converter.Factory {
        return JacksonConverterFactory.create(ObjectMapper())
    }

    @Provides
    @Singleton
    fun provideCallAdapterFactory(): CallAdapter.Factory {
        return RxJava2CallAdapterFactory.create()
    }

    @Provides
    @Singleton
    fun provideRetrofit(
        client: OkHttpClient,
        converterFactory: Converter.Factory,
        callAdapterFactory: CallAdapter.Factory
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .client(client)
            .addConverterFactory(converterFactory)
            .addCallAdapterFactory(callAdapterFactory)
            .build()
    }

    @Provides
    @Singleton
    fun provideApi(retrofit: Retrofit): Api {
        return retrofit.create(Api::class.java)
    }


    @Provides
    @Singleton
    fun provideApiKey(): String {
        return BuildConfig.NEWS_API_KEY
    }
}
