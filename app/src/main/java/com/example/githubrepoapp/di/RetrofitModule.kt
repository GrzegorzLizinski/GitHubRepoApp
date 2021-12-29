package com.example.githubrepoapp.di

import com.example.githubrepoapp.BuildConfig
import com.example.githubrepoapp.api.RepoGitHubApi
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RetrofitModule {

    @Provides
    fun provideBaseUrl() = BuildConfig.BASE_URL

    @Provides
    @Singleton
    fun provideOkHttp(): OkHttpClient.Builder {
        return OkHttpClient.Builder()
    }

    @Provides
    @Singleton
    fun provideRetrofit(
        httpClient: OkHttpClient.Builder,
        BASE_URL: String
    ): Retrofit { //add logger
        val logging =
            HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
        httpClient.addInterceptor(logging)
        //add retro builder
        val retroBuilder = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
        retroBuilder.client(httpClient.build())
        //create retrofit - only this instance would be used in the entire application
        return retroBuilder.build()
    }
    @Provides
    @Singleton
    fun provideRetrofitService(retrofit: Retrofit): RepoGitHubApi {
        return retrofit.create(RepoGitHubApi::class.java)
    }
}
