package com.example.weatherfetcher.feature.weather_screen.data

import com.example.weatherfetcher.feature.weather_screen.data.model.Weather

class WeatherRemouteSource(private val api: WeatherApi) {
    suspend fun getWeather(city: String): Weather = api.getWeather(city)
}
