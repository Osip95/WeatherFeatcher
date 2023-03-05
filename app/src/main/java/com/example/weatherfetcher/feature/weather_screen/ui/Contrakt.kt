package com.example.weatherfetcher.feature.weather_screen.ui

import com.example.weatherfetcher.base.Event


data class ViewState(
    val isLoading: Boolean,
    val city: String,
    val temperature: String,
    val speedWind:String
)

sealed class UiEvent(): Event {
    object OnButtonGetTemperature : UiEvent()
    object OnRbMoscow: UiEvent()
    object OnRbSaintPetersburg: UiEvent()
    object OnRbOmsk: UiEvent()
}

sealed class DataEvent: Event {
    data class OnWeatherFetchSucced(val temperature: String, val speedWind: String) : DataEvent()
    data class OnWeatherFetchFailed(val error: Throwable): DataEvent()
}