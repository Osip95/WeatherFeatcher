package com.example.weatherfetcher.feature.weather_screen.ui

import com.example.weatherfetcher.feature.weather_screen.WeatherInteractor

class WeatherScreenPresenter(val iteractor: WeatherInteractor) {
   suspend fun getWeather(): String{
        return iteractor.getWeather()
    }
}