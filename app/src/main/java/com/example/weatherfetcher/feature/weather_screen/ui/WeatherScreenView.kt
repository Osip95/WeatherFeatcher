package com.example.weatherfetcher.feature.weather_screen.ui

import android.widget.Toast
import com.example.weatherfetcher.R

interface WeatherScreenView {
    fun showError()
    fun showTemperature(temperature: String)
    fun showErrorCityNotSelected()
    fun navigateToWindScreen(city: String)
}