package com.example.weatherfetcher.feature.weather_screen.data

import com.example.weatherfetcher.API_KEY
import com.example.weatherfetcher.CITY
import com.example.weatherfetcher.feature.weather_screen.data.model.Weather
import retrofit2.http.GET
import retrofit2.http.Query


interface WeatherApi {

    @GET("weather")
   suspend fun getWeather(
        @Query("q") query: String = CITY,
        @Query("appid") apiKey: String = API_KEY
    ) : Weather



}