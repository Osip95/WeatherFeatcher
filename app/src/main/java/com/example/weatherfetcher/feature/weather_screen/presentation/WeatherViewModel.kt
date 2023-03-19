package com.example.weatherfetcher.feature.weather_screen.presentation



import androidx.lifecycle.viewModelScope
import com.example.weatherfetcher.base.BaseViewModel
import com.example.weatherfetcher.base.Event
import com.example.weatherfetcher.feature.weather_screen.GetWeatherInteractor
import com.example.weatherfetcher.feature.weather_screen.ui.*
import kotlinx.coroutines.launch

class WeatherViewModel(val interactor: GetWeatherInteractor) : BaseViewModel<ViewState>() {
    override fun initialViewState(): ViewState = ViewState(
        isLoading = false,
        city = "",
        temperature = "temperature",
        speedWind = "",
        readyToGo = false,
        errorVisibility = false,
        errorText = ""
    )


    override fun reduce(event: Event, previousState: ViewState): ViewState? {
        when (event) {

            is UiEvent.OnGoWindScreen -> {
                if (previousState.city == "") return previousState.copy(errorVisibility = true,
                    errorText = "Select city")
                if(previousState.speedWind == "") return previousState.copy(errorVisibility = true,
                errorText = "Get weather data")

                return previousState.copy(readyToGo = true)
            }
            is OnRbClicked -> return previousState.copy(city = event.city,
                errorVisibility = false)
            is UiEvent.OnButtonGetWeatherInform -> {
                if (previousState.city == "") return previousState.copy(errorVisibility = true,
                    errorText = "Select city" )
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
                return previousState.copy(isLoading = true, errorVisibility = false)
            }

            is DataEvent.OnWeatherFetchSucced -> {
                return previousState.copy(isLoading = false,
                    temperature = event.temperature,
                    speedWind = event.speedWind
                )
            }
            else -> return null
        }
    }
}