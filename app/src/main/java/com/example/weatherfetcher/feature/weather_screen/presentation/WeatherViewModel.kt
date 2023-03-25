package com.example.weatherfetcher.feature.weather_screen.presentation




import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.weatherfetcher.base.BaseViewModel
import com.example.weatherfetcher.base.Event
import com.example.weatherfetcher.base.SingleLiveEvent
import com.example.weatherfetcher.feature.weather_screen.GetWeatherInteractor
import com.example.weatherfetcher.feature.weather_screen.ui.*
import kotlinx.coroutines.launch

class WeatherViewModel(val interactor: GetWeatherInteractor) : BaseViewModel<ViewState>() {
    private val _goWindEvent = SingleLiveEvent<String>()
    val goWindEvent: MutableLiveData<String> = _goWindEvent
    override fun initialViewState(): ViewState = ViewState(
        isLoading = false,
        city = "",
        temperature = "temperature",
        speedWind = "",
        errorCode = ErrorСodes.NO_ERROR
    )


    override fun reduce(event: Event, previousState: ViewState): ViewState? {
        when (event) {

            is UiEvent.OnGoWindScreen -> {
                if (previousState.city == "") return previousState.copy(errorCode = ErrorСodes.CITY_ERROR)
                if(previousState.speedWind == "") return previousState.copy(errorCode = ErrorСodes.DATA_ERROR)
                goWindEvent.value = previousState.speedWind
                return previousState.copy()
            }
            is OnRbClicked -> return previousState.copy(city = event.city)
            is UiEvent.OnButtonGetWeatherInform -> {
                if (previousState.city == "") return previousState.copy(errorCode = ErrorСodes.CITY_ERROR)
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
                return previousState.copy(isLoading = true, errorCode = ErrorСodes.NO_ERROR)
            }
            is DataEvent.OnWeatherFetchFailed -> {
                return previousState.copy(errorCode = ErrorСodes.ERROR_NETWORK, isLoading = false)
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