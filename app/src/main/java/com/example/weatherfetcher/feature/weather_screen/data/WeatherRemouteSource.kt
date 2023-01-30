package com.example.weatherfetcher.feature.weather_screen.data

import retrofit2.Response

class WeatherRemouteSource(private val api: WeatherApi) {

    // TODO add query
    fun getWeather(): Response<String> {
        return api.getWeather("Moscow")
    }
}