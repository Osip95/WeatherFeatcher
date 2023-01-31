package com.example.weatherfetcher

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.example.weatherfetcher.feature.weather_screen.WeatherInteractor
import com.example.weatherfetcher.feature.weather_screen.data.WeatherApiClient
import com.example.weatherfetcher.feature.weather_screen.data.WeatherRemouteSource
import com.example.weatherfetcher.feature.weather_screen.data.WeatherRepoImpl
import com.example.weatherfetcher.feature.weather_screen.data.WindRepoImpl
import com.example.weatherfetcher.feature.weather_screen.ui.WeatherScreenPresenter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class WindActivity : AppCompatActivity() {
    private lateinit var presenter: WeatherScreenPresenter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_wind2)
        presenter = WeatherScreenPresenter(
            WeatherInteractor(
                WindRepoImpl(
                    WeatherRemouteSource(WeatherApiClient.getApi())
                )
            )
        )
        val tvWindSpeed = findViewById<TextView>(R.id.tvWind)
        val btnGetSpeedWind = findViewById<Button>(R.id.btnGetSpeedWind)
        val btnGoWeatherScreen = findViewById<Button>(R.id.btnGoWeatherScreen)
        btnGetSpeedWind.setOnClickListener {
            GlobalScope.launch {
                withContext(Dispatchers.Main) {
                    tvWindSpeed.text = presenter.getWeather()
                }
            }
        }

        btnGoWeatherScreen.setOnClickListener{
            startActivity(Intent(this,MainActivity::class.java))
        }
    }
}