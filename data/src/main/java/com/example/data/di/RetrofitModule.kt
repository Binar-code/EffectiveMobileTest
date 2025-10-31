package com.example.data.di

import com.example.data.network.service.ApiService
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val RetrofitModule = module {
    single<Retrofit> {
        Retrofit.Builder()
            .baseUrl("https://drive.usercontent.google.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    single<ApiService> {
        get<Retrofit>().create(ApiService::class.java)
    }
}
