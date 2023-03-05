package com.example.weatherfetcher.feature.weather_screen

import com.example.weatherfetcher.base.Either
import com.example.weatherfetcher.base.attempt
import com.example.weatherfetcher.feature.weather_screen.domain.WeatherRepo
import com.example.weatherfetcher.feature.weather_screen.ui.model.WeatherModel


class GetWeatherInteractor (private val weatherRepo: WeatherRepo) {

    suspend operator fun invoke(city:String):Either<Throwable, WeatherModel>  {
      return attempt {  weatherRepo.getWeather(city) }
    }
}