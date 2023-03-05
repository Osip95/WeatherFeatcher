package com.example.weatherfetcher.feature.weather_screen.domain


import com.example.weatherfetcher.feature.weather_screen.ui.model.WeatherModel

interface WeatherRepo {
   suspend fun getWeather(city:String): WeatherModel
   }