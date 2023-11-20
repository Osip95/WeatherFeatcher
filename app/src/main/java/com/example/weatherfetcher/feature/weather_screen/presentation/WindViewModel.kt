package com.example.weatherfetcher.feature.weather_screen.presentation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.weatherfetcher.feature.weather_screen.ui.ViewStateWind

class WindViewModel(val speedWind: String) : ViewModel() {
    val viewStateWind: MutableLiveData<ViewStateWind> =
        MutableLiveData(ViewStateWind(speedWind = speedWind))
}




