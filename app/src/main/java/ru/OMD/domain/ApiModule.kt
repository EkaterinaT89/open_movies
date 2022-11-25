package ru.OMD.domain

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object ApiModule {
    @Provides
    @Singleton
    fun provideApiService(): MovieApi {
        return retrofit(okhttp(loggingInterceptor()))
            .create(MovieApi::class.java)
    }
}