package com.example.weatherfetcher.feature.weather_screen.data

import com.example.weatherfetcher.feature.weather_screen.data.model.WeatherRemouteModel
import retrofit2.Response

class WeatherRemouteSource(private val api: WeatherApi) {

    // TODO add query
   suspend fun  getWeather(): WeatherRemouteModel {
        return api.getWeather("Moscow")
    }
}