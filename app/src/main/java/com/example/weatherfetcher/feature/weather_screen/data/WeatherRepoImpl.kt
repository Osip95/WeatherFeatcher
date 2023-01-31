package com.example.weatherfetcher.feature.weather_screen.data

class WeatherRepoImpl(private val weatherRemouteSource: WeatherRemouteSource): WeatherRepo {
    override suspend fun getTemperature(): String {
        return weatherRemouteSource.getWeather().main.temperature
    }

    override suspend fun getSpeedWind(): String {
        return weatherRemouteSource.getWeather().wind.speed
    }
}