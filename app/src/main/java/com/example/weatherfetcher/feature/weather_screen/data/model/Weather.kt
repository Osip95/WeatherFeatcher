package com.example.weatherfetcher.feature.weather_screen.data.model

import com.google.gson.annotations.SerializedName


data class Weather(
    @SerializedName("main")
    val main: WeatherMain,
    val wind: WeatherWind
)
