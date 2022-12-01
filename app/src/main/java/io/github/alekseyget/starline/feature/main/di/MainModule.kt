package io.github.alekseyget.starline.feature.main.di

import android.content.Context
import dagger.Module
import dagger.Provides
import io.github.alekseyget.starline.utils.RetryInterceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import java.io.File
import javax.inject.Named

@Module
class MainModule {

    @Named("cacheDir")
    @Provides
    fun provideCacheDirectory(context: Context): File = context.cacheDir

    @Provides
    fun provideOkHttpClient(
        loggingInterceptor: HttpLoggingInterceptor,
        retryInterceptor: RetryInterceptor
    ): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .addInterceptor(retryInterceptor)
            .build()
    }

    @Provides
    fun provideLoggingInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor().apply {
            setLevel(HttpLoggingInterceptor.Level.BODY)
        }
    }

    @Provides
    fun provideRetryInterceptor(): RetryInterceptor {
        return RetryInterceptor(attemptsCount = 3)
    }

}