package com.example.weatherfetcher.feature.weather_screen.data

import com.example.weatherfetcher.feature.weather_screen.data.model.Weather

class WeatherRemouteSource(private val api: WeatherApi) {

    // TODO add query
    suspend fun getWeather(): Weather = api.getWeather()
}
