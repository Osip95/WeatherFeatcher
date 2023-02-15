package com.example.weatherfetcher.feature.weather_screen.data


import com.example.weatherfetcher.feature.weather_screen.data.model.Weather
import retrofit2.http.GET
import retrofit2.http.Query


interface WeatherApi {
    @GET("weather")
   suspend fun getWeather(
        @Query("q") query:String,
        @Query("appid") apiKey: String = "58b397ef6b2ac4874ee79550212748d0"
    ) : Weather

}