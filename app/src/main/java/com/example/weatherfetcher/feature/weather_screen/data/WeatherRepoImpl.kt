package com.example.weatherfetcher.feature.weather_screen.data

class WeatherRepoImpl(private val weatherRemouteSource: WeatherRemouteSource): WeatherRepo {
    override fun getTemperature(): String {
        return weatherRemouteSource.getWeather().message()
    }
}