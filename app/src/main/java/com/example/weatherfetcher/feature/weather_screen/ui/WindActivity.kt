package com.example.weatherfetcher.feature.weather_screen.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import com.example.weatherfetcher.R
import com.example.weatherfetcher.feature.weather_screen.GetWeatherInteractor
import com.example.weatherfetcher.feature.weather_screen.data.WeatherApiClient
import com.example.weatherfetcher.feature.weather_screen.data.WeatherRemouteSource
import com.example.weatherfetcher.feature.weather_screen.data.WeatherRepoImpl
import com.example.weatherfetcher.feature.weather_screen.presentation.WindScreenPresenter


class WindActivity : AppCompatActivity(), WindScreenView {

    private lateinit var tvWindSpeed: TextView
    private lateinit var btnGoWeatherScreen: Button
    private lateinit var windPresenter: WindScreenPresenter
    private lateinit var city: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_wind)
        val interactor = GetWeatherInteractor(
            WeatherRepoImpl(
                WeatherRemouteSource(
                    WeatherApiClient.getApi())))

        tvWindSpeed = findViewById(R.id.tvWindSpeed)
        btnGoWeatherScreen = findViewById(R.id.btnGoWeatherScreen)
        city = intent.getStringExtra(CITY).toString()
        windPresenter = WindScreenPresenter(interactor,city)
        windPresenter.attachView(this)


        btnGoWeatherScreen.setOnClickListener {
            windPresenter.onGoWeatherScreenClicked()
        }
    }

    override fun showError() {
        Toast.makeText(this, R.string.error_message, Toast.LENGTH_LONG).show()
    }

    override fun showSpeed(speed: String) {
        tvWindSpeed.text = speed
    }

    override fun showErrorCityNotSelected() {
        Toast.makeText(this, R.string.error_message, Toast.LENGTH_LONG).show()
    }

    override fun navigateToWeatherScreen() {
        startActivity(Intent(this, MainActivity::class.java))
    }

    override fun onDestroy() {
        windPresenter.detachView()
        super.onDestroy()
    }
}