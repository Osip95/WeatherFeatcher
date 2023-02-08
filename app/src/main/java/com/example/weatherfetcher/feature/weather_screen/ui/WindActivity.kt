package com.example.weatherfetcher.feature.weather_screen.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import com.example.weatherfetcher.CITY
import com.example.weatherfetcher.R
import com.example.weatherfetcher.feature.weather_screen.GetWeatherInteractor
import com.example.weatherfetcher.feature.weather_screen.data.WeatherApiClient
import com.example.weatherfetcher.feature.weather_screen.data.WeatherRemouteSource
import com.example.weatherfetcher.feature.weather_screen.data.WeatherRepoImpl
import com.example.weatherfetcher.feature.weather_screen.presentation.WeatherScreenPresenter
import kotlinx.coroutines.*

class WindActivity : AppCompatActivity() {

    private lateinit var presenter: WeatherScreenPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_wind2)

        val rgCity = findViewById<RadioGroup>(R.id.rgCitiesWind)
        val tvWindSpeed = findViewById<TextView>(R.id.tvWindSpeed)
        val btnGetSpeed = findViewById<Button>(R.id.btnGetSpeedWind)
        val btnGoWeatherScreen = findViewById<Button>(R.id.btnGoWeatherScreen)
        val errorHandler = CoroutineExceptionHandler { _, _ ->
            Toast.makeText(this@WindActivity, "Произошла ошибка", Toast.LENGTH_LONG).show()
        }
        rgCity.setOnCheckedChangeListener { _, checkedId ->
            findViewById<RadioButton>(checkedId)?.apply {
                CITY = text.toString()
            }
        }
        btnGetSpeed.setOnClickListener {
            if (CITY == "") {
                Toast.makeText(this@WindActivity, "Выберите город", Toast.LENGTH_LONG).show()
                return@setOnClickListener
            }
            presenter = WeatherScreenPresenter(
                GetWeatherInteractor(
                    WeatherRepoImpl(
                        WeatherRemouteSource(WeatherApiClient.getApi())
                    )
                )
            )
            CoroutineScope(Dispatchers.Main).launch(errorHandler) {
                tvWindSpeed.text = presenter.getWeather().wind.speed
            }

        }

        btnGoWeatherScreen.setOnClickListener {
            CITY = ""
            startActivity(Intent(this, MainActivity::class.java))
        }
    }
}