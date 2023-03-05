package com.example.weatherfetcher.feature.weather_screen.presentation

import androidx.lifecycle.viewModelScope
import com.example.weatherfetcher.base.BaseViewModel
import com.example.weatherfetcher.base.Event
import com.example.weatherfetcher.feature.weather_screen.GetWeatherInteractor
import com.example.weatherfetcher.feature.weather_screen.ui.DataEvent
import com.example.weatherfetcher.feature.weather_screen.ui.UiEvent
import com.example.weatherfetcher.feature.weather_screen.ui.ViewState
import kotlinx.coroutines.launch

class WeatherViewModel(val interactor: GetWeatherInteractor) : BaseViewModel<ViewState>() {
    override fun initialViewState(): ViewState = ViewState(
        isLoading = false,
        city = "",
        temperature = "temperature",
        speedWind = ""
    )


    override fun reduce(event: Event, previousState: ViewState): ViewState? {
        when (event) {
            is UiEvent.OnRbMoscow -> return previousState.copy(city = "Moscow")
            is UiEvent.OnRbSaintPetersburg -> return previousState.copy(city = "Saint Petersburg")
            is UiEvent.OnRbOmsk -> return previousState.copy(city = "Omsk")
            is UiEvent.OnButtonGetTemperature -> {

                viewModelScope.launch {
                    interactor(previousState.city).fold(
                        onError = {
                            processDataEvent(DataEvent.OnWeatherFetchFailed(error = it))
                        },
                        onSuccess = {
                            processDataEvent(DataEvent.OnWeatherFetchSucced(temperature = it.temperature,
                                speedWind = it.speedWind))
                        }
                    )
                }
                return previousState.copy(isLoading = true)
            }

            is DataEvent.OnWeatherFetchSucced -> {
                return previousState.copy(isLoading = false,
                    temperature = event.temperature,
                    speedWind = event.speedWind)
            }
            else -> return null
        }
    }
}