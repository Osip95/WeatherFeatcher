package com.example.weatherfetcher.feature.weather_screen.data

import com.example.weatherfetcher.feature.weather_screen.data.model.Weather
import com.example.weatherfetcher.feature.weather_screen.domain.enity.WeatherModel

fun Weather.toDomain()  = WeatherModel(
    this.main.temperature,
    this.wind.speed
)