package ru.OMD.domain

import okhttp3.logging.HttpLoggingInterceptor
import ru.OMD.BuildConfig

fun loggingInterceptor() = HttpLoggingInterceptor()
    .apply {
        if (BuildConfig.DEBUG) {
            level = HttpLoggingInterceptor.Level.BODY
        }
    }