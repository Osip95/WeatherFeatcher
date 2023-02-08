package com.example.weatherfetcher.feature.weather_screen.presentation

import com.example.weatherfetcher.feature.weather_screen.GetWeatherInteractor
import com.example.weatherfetcher.feature.weather_screen.data.model.Weather


class WeatherScreenPresenter(val interactor: GetWeatherInteractor) {
   suspend fun getWeather(): Weather{
       return interactor()
    }
}