package com.example.weatherfetcher.feature.weather_screen.data

import com.example.weatherfetcher.feature.weather_screen.domain.WeatherRepo
import com.example.weatherfetcher.feature.weather_screen.ui.model.WeatherModel

class WeatherRepoImpl(private val weatherRemouteSource: WeatherRemouteSource) : WeatherRepo {

    override suspend fun getWeather(city: String): WeatherModel = weatherRemouteSource
        .getWeather(city)
        .toDomain()

}