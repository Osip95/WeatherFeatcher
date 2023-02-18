package com.example.weatherfetcher.feature.weather_screen.presentation


import com.example.weatherfetcher.feature.weather_screen.GetWeatherInteractor
import com.example.weatherfetcher.feature.weather_screen.ui.WeatherScreenView
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class WeatherScreenPresenter(private val interactor: GetWeatherInteractor) {

    private var weatherScreenView: WeatherScreenView? = null
    private var city = ""
    private val errorHandler = CoroutineExceptionHandler { _, _ ->
        weatherScreenView?.showError()
    }

    fun onGetTempButtonClicked() {
        if (city == "") {
            weatherScreenView?.showErrorCityNotSelected()
            return
        }
        CoroutineScope(Dispatchers.Main).launch(errorHandler) {
            val temp = interactor(city).main.temperature
            weatherScreenView?.showTemperature(temp)
        }
    }

    fun onGoWindScreenClicked() {
        weatherScreenView?.navigateToWindScreen(city)
    }

    fun onCitySelected(nameRadioButton: String) {
        city = nameRadioButton
    }

    fun attachView(view: WeatherScreenView) {
        this.weatherScreenView = view
    }

    fun detachView() {
        weatherScreenView = null
    }
}