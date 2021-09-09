package com.example.userlist.di

import android.content.Context
import android.content.pm.PackageManager
import com.example.userlist.other.Constants.BASE_URL
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {


    @Singleton
    @Provides
    fun provideHttpClient(githubToken: String): OkHttpClient {
        val okHttpClientBuilder = OkHttpClient.Builder()
            .callTimeout(60, TimeUnit.SECONDS)
            .readTimeout(60, TimeUnit.SECONDS)
        val httpLoggingInterceptor = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }

        okHttpClientBuilder.addInterceptor { chain ->
            chain.proceed(
                chain.request().newBuilder()
                    .addHeader("Authorization", githubToken)
                    .addHeader("Accept", "application/json")
                    .build()
            )
        }

        okHttpClientBuilder.addInterceptor(httpLoggingInterceptor)
        return okHttpClientBuilder.build()
    }


    @Singleton
    @Provides
    fun provideRetrofit(client: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .client(client)
            .build()
    }

    @Singleton
    @Provides
    fun provideGitHubToken(@ApplicationContext applicationContext: Context): String {
        var ai = applicationContext.packageManager
            .getApplicationInfo(applicationContext.packageName, PackageManager.GET_META_DATA)
        val key = ai.metaData["githubToken"].toString()
        return key

    }
}