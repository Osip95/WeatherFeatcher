package com.example.weatherfetcher.feature.weather_screen.ui


import com.example.weatherfetcher.base.Event


data class ViewState(
    val isLoading: Boolean,
    val city: String,
    val temperature: String,
    val speedWind:String,
    var errorCode: ErrorСodes
)

data class ViewStateWind(val speedWind: String)

sealed class UiEvent(): Event {
    object OnButtonGetWeatherInform : UiEvent()
    object OnGoWindScreen: UiEvent()

}
class OnRbClicked(var city: String = ""): UiEvent()




sealed class DataEvent: Event {
    data class OnWeatherFetchSucced(val temperature: String, val speedWind: String) : DataEvent()
    data class OnWeatherFetchFailed(val error: Throwable): DataEvent()
}