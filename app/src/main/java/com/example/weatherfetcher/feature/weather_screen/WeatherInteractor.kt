package com.example.weatherfetcher.feature.weather_screen

import com.example.weatherfetcher.feature.weather_screen.data.WeatherRepo
import com.example.weatherfetcher.feature.weather_screen.data.WeatherRepoImpl

class WeatherInteractor(private val weatherRepo: WeatherRepo) {

    suspend fun getWeather(): String {
        return if (weatherRepo is WeatherRepoImpl)
            weatherRepo.getTemperature()
        else weatherRepo.getSpeedWind()
    }
}