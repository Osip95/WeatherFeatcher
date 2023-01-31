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
import com.example.weatherfetcher.feature.weather_screen.ui.WeatherScreenPresenter
import kotlinx.coroutines.*

class MainActivity : AppCompatActivity() {

    private lateinit var presenter: WeatherScreenPresenter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        presenter = WeatherScreenPresenter(
            WeatherInteractor(
                WeatherRepoImpl(
                    WeatherRemouteSource(WeatherApiClient.getApi())
                )
            )
        )

        var weather = ""
        val tvHello = findViewById<TextView>(R.id.tvHello)
        val btnGetTemp = findViewById<Button>(R.id.bt_get_temp)
        val btnGoWindScreen = findViewById<Button>(R.id.bt_go_screen_wind)
        btnGetTemp.setOnClickListener {
            GlobalScope.launch {
                withContext(Dispatchers.Main) {
                    tvHello.text = presenter.getWeather()
                }
            }
        }

        btnGoWindScreen.setOnClickListener{
          startActivity(Intent(this,WindActivity::class.java))
        }


    }
}