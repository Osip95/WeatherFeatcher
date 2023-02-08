package com.example.weatherfetcher.feature.weather_screen.domain

import com.example.weatherfetcher.feature.weather_screen.data.model.Weather

interface WeatherRepo {
   suspend fun getWeather(): Weather
   }