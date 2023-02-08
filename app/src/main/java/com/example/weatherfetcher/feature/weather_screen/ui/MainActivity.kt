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

class MainActivity : AppCompatActivity() {

    private lateinit var presenter: WeatherScreenPresenter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val rgCity = findViewById<RadioGroup>(R.id.radioGroupСities)
        val tvTemperature = findViewById<TextView>(R.id.tvTemperature)
        val btnGetTemp = findViewById<Button>(R.id.bt_get_temp)
        val btnGoWindScreen = findViewById<Button>(R.id.bt_go_screen_wind)
        val errorHandler = CoroutineExceptionHandler { _, _ ->
            Toast.makeText(this@MainActivity, "Произошла ошибка", Toast.LENGTH_LONG).show()
        }
        rgCity.setOnCheckedChangeListener { _, checkedId ->
            findViewById<RadioButton>(checkedId)?.apply {
                CITY = text.toString()
            }
        }
        btnGetTemp.setOnClickListener {
            if (CITY == "") {
                Toast.makeText(this@MainActivity, "Выберите город", Toast.LENGTH_LONG).show()
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
                tvTemperature.text = presenter.getWeather().main.temperature
            }

        }

        btnGoWindScreen.setOnClickListener {
            CITY = ""
            startActivity(Intent(this, WindActivity::class.java))
        }


    }
}