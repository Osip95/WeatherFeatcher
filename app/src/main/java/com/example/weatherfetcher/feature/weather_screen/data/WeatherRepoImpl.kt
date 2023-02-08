package com.example.weatherfetcher.feature.weather_screen.data

import com.example.weatherfetcher.feature.weather_screen.data.model.Weather
import com.example.weatherfetcher.feature.weather_screen.domain.WeatherRepo

class WeatherRepoImpl(private val weatherRemouteSource: WeatherRemouteSource): WeatherRepo {
    override suspend fun getWeather(): Weather {
        return weatherRemouteSource.getWeather()
    }

}