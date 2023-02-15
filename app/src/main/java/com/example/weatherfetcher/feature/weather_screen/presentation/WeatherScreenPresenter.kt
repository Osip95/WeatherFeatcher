package com.example.weatherfetcher.feature.weather_screen.presentation


import com.example.weatherfetcher.feature.weather_screen.GetWeatherInteractor
import com.example.weatherfetcher.feature.weather_screen.ui.MainActivity
import com.example.weatherfetcher.feature.weather_screen.ui.WeatherScreenView
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class WeatherScreenPresenter(private val interactor: GetWeatherInteractor) {

    var weatherActivity: WeatherScreenView? = null
    var city = ""
    val errorHandler = CoroutineExceptionHandler { _, _ ->
        weatherActivity?.showError()
    }

    fun onGetTempButtonClicked() {
        if (city == "") {
            weatherActivity?.showErrorCityNotSelected()
            return
        }
        CoroutineScope(Dispatchers.Main).launch(errorHandler) {
            val temp = interactor(city).main.temperature
            weatherActivity?.showTemperature(temp)
        }
    }

    fun onRadioButtonClicked(nameRadioButton: String) {
        city = nameRadioButton
    }

    fun attachView(view: WeatherScreenView) {
        this.weatherActivity = view
    }

    fun detachView() {
        weatherActivity = null
    }
}