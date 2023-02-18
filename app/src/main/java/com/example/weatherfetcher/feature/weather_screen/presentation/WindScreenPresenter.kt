package com.example.weatherfetcher.feature.weather_screen.presentation


import com.example.weatherfetcher.feature.weather_screen.GetWeatherInteractor
import com.example.weatherfetcher.feature.weather_screen.ui.WindActivity
import com.example.weatherfetcher.feature.weather_screen.ui.WindScreenView
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class WindScreenPresenter(private val interactor: GetWeatherInteractor, private val city: String) {

    private var windActivity: WindScreenView? = null
    private val errorHandler = CoroutineExceptionHandler { _, _ ->
        windActivity?.showError()
    }

    private fun getWindSpeed() {
        if (city == "") {
            windActivity?.showErrorCityNotSelected()
            return
        }
        CoroutineScope(Dispatchers.Main).launch(errorHandler) {
            val speedWind = interactor(city).wind.speed
            windActivity?.showSpeed(speedWind)
        }
    }

    fun onGoWeatherScreenClicked() {
        windActivity?.navigateToWeatherScreen()
    }

    fun attachView(view: WindActivity) {
        this.windActivity = view
        getWindSpeed()
    }

    fun detachView() {
        windActivity = null
    }
}