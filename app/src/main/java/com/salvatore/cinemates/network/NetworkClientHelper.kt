package com.salvatore.cinemates.network

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.converter.jackson.JacksonConverterFactory
import java.security.KeyManagementException
import java.security.NoSuchAlgorithmException
import java.util.concurrent.TimeUnit

object NetworkClientHelper {
    private var networkClient: OkHttpClient? = null
    private var jacksonJsonConverter: JacksonConverterFactory? = null

    val client: OkHttpClient
        @Throws(NoSuchAlgorithmException::class, KeyManagementException::class)
        get() {
            if (networkClient == null) {
                val networkInterceptor = HttpLoggingInterceptor()
                networkInterceptor.level = HttpLoggingInterceptor.Level.BODY

                networkClient = OkHttpClient.Builder()
                    .connectTimeout(15000, TimeUnit.MILLISECONDS)
                    .readTimeout(20000, TimeUnit.MILLISECONDS)
                    .addInterceptor(networkInterceptor)
                    .build()
            }

            return networkClient!!
        }

    val jacksonConverter: JacksonConverterFactory
        get() {
            if (jacksonJsonConverter == null) {
                jacksonJsonConverter = JacksonConverterFactory.create()
            }

            return jacksonJsonConverter!!
        }
}