package com.example.weatherfetcher.feature.weather_screen.domain


import com.example.weatherfetcher.feature.weather_screen.GetWeatherInteractor
import com.example.weatherfetcher.feature.weather_screen.data.WeatherApiClient
import com.example.weatherfetcher.feature.weather_screen.data.WeatherRemouteSource
import com.example.weatherfetcher.feature.weather_screen.data.WeatherRepoImpl
import kotlinx.coroutines.*


class WeatherModel(private val errorHandler: CoroutineExceptionHandler) {
    private lateinit var temp: String
    private lateinit var speed: String
    var interactor: GetWeatherInteractor = GetWeatherInteractor(
        WeatherRepoImpl(
            WeatherRemouteSource(
                WeatherApiClient.getApi())))

    fun getTemp(city: String): String {
        runBlocking {
            CoroutineScope(Dispatchers.Main).launch(errorHandler) {
                temp = interactor(city).main.temperature
            }.join()
        }
        return temp
    }

    fun getSpeedWind(city: String): String {
        runBlocking {
            CoroutineScope(Dispatchers.Main).launch(errorHandler) {
                speed = interactor(city).wind.speed
            }.join()
        }
        return speed
    }
}