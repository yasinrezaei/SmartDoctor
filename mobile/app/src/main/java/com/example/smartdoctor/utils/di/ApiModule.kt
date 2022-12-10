package com.example.smartdoctor.utils.di

import com.example.smartdoctor.data.server.ApiService
import com.example.smartdoctor.utils.Constants.BASE_URL
import com.example.smartdoctor.utils.Constants.CONNECTION_TIMEOUT
import com.example.smartdoctor.utils.Constants.NAMED_BODY
import com.example.smartdoctor.utils.Constants.NAMED_HEADER
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Named
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object ApiModule {

    @Provides
    @Singleton
    fun provideBaseUrl() = BASE_URL

    @Provides
    @Singleton
    fun provideConnectionTimeout() = CONNECTION_TIMEOUT

    @Provides
    @Singleton
    fun provideGson():Gson = GsonBuilder().setLenient().create()

    @Provides
    @Singleton
    @Named(NAMED_HEADER)
    fun provideHeaderInterceptor() = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.HEADERS
    }
    @Provides
    @Singleton
    @Named(NAMED_BODY)
    fun provideBodyInterceptor() = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }


    @Provides
    @Singleton
    fun provideClient(time:Long, @Named(NAMED_HEADER) header: HttpLoggingInterceptor, @Named(NAMED_BODY) body: HttpLoggingInterceptor) =
        OkHttpClient.Builder()
            .addInterceptor(header)
            .addInterceptor(body)
            .connectTimeout(time, TimeUnit.SECONDS)
            .readTimeout(time, TimeUnit.SECONDS)
            .writeTimeout(time, TimeUnit.SECONDS)
            .retryOnConnectionFailure(true)
            .build()

    @Provides
    @Singleton
    fun provideRetrofit(baseUrl:String,gson:Gson,client: OkHttpClient): ApiService =
        Retrofit.Builder().baseUrl(baseUrl).client(client).addConverterFactory(GsonConverterFactory.create(gson)).build().create(ApiService::class.java)



}