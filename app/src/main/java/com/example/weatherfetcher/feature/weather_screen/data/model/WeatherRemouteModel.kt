package com.example.weatherfetcher.feature.weather_screen.data.model

import com.google.gson.annotations.SerializedName

data class WeatherRemouteModel(
    @SerializedName("main")
    val main: WeatherMainRemoteModel,
    @SerializedName("wind")
    val wind: WindRemouteModel
)
