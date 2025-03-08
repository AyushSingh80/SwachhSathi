package com.example.swachhsathi.data.network

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object RetrofitInstance {
    private val client = OkHttpClient.Builder()
        .addInterceptor { chain ->
            val request = chain.request().newBuilder().build()
            chain.proceed(request)
        }
        .build()

    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(" https://swatchasathi.pythonanywhere.com/")
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val api: ApiService by lazy { retrofit.create(ApiService::class.java) }
}
