package com.salvatore.cinemates.network

import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory

object NetworkApiService {
    private val LOGGING_TAG = "--ApiService"

    fun searchApiCall() = Retrofit.Builder()
        .baseUrl(NetworkConstants.BASE_URL)
        .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
        .addConverterFactory(NetworkClientHelper.jacksonConverter)
        .client(NetworkClientHelper.client)
        .build()
        .create(SearchApiService::class.java)!!
}