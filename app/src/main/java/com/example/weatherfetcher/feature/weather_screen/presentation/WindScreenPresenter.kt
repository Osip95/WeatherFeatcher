package com.example.weatherfetcher.feature.weather_screen.presentation


import com.example.weatherfetcher.feature.weather_screen.GetWeatherInteractor
import com.example.weatherfetcher.feature.weather_screen.ui.WindActivity
import com.example.weatherfetcher.feature.weather_screen.ui.WindScreenView
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class WindScreenPresenter(private val interactor: GetWeatherInteractor, private val city: String) {

    private var windScreenView: WindScreenView? = null
    private val errorHandler = CoroutineExceptionHandler { _, _ ->
        windScreenView?.showError()
    }

    fun onGoWeatherScreenClicked() {
        windScreenView?.navigateToWeatherScreen()
    }

    fun attachView(view: WindActivity) {
        this.windScreenView = view
        getWindSpeed()
    }

    private fun getWindSpeed() {
        if (city == "") {
            windScreenView?.showErrorCityNotSelected()
            return
        }
        CoroutineScope(Dispatchers.Main).launch(errorHandler) {
            val speedWind = interactor(city).wind.speed
            windScreenView?.showSpeed(speedWind)
        }
    }

    fun detachView() {
        windScreenView = null
    }
}