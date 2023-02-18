package com.example.weatherfetcher.feature.weather_screen

import com.example.weatherfetcher.feature.weather_screen.domain.WeatherRepo
import com.example.weatherfetcher.feature.weather_screen.data.model.Weather


class GetWeatherInteractor (private val weatherRepo: WeatherRepo) {

    suspend operator fun invoke(city:String): Weather {
      return  weatherRepo.getWeather(city)
    }
}