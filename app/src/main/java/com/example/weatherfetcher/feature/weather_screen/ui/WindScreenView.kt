package com.example.weatherfetcher.feature.weather_screen.ui

interface WindScreenView {
    fun showError()
    fun showSpeed(speed: String)
    fun showErrorCityNotSelected()
}