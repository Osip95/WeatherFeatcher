package com.example.weatherfetcher.feature.weather_screen.data

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object WeatherApiClient {
    private val interceptor = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
    private const val BASE_URL = "https://api.openweathermap.org/data/2.5/"
    private val okHttpClient = OkHttpClient.Builder().addInterceptor(interceptor)
        .build()

    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .client(okHttpClient)
        .build()

    private val weatherApi = retrofit.create(WeatherApi::class.java)
    fun getApi(): WeatherApi = weatherApi

}