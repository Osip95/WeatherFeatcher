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
import com.example.weatherfetcher.feature.weather_screen.presentation.WeatherScreenPresenter



class MainActivity : AppCompatActivity(), WeatherScreenView {

    private lateinit var weatherPresenter: WeatherScreenPresenter
    private lateinit var rgCity: RadioGroup
    private lateinit var tvTemperature: TextView
    private lateinit var btnGetTemp: Button
    private lateinit var btnGoWindScreen: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val interactor = GetWeatherInteractor(
            WeatherRepoImpl(
                WeatherRemouteSource(
                    WeatherApiClient.getApi())))

        rgCity = findViewById(R.id.radioGroupСities)
        tvTemperature = findViewById(R.id.tvTemperature)
        btnGetTemp = findViewById(R.id.bt_get_temp)
        btnGoWindScreen = findViewById(R.id.bt_go_screen_wind)
        weatherPresenter = WeatherScreenPresenter(interactor)
        weatherPresenter.attachView(this)

        rgCity.setOnCheckedChangeListener { _, checkedId ->
            findViewById<RadioButton>(checkedId)?.apply {
                weatherPresenter.onRadioButtonClicked(this.text.toString())
            }
        }

        btnGetTemp.setOnClickListener {
            weatherPresenter.onGetTempButtonClicked()
        }

        btnGoWindScreen.setOnClickListener {
            val intent = Intent(this, WindActivity::class.java)
            intent.putExtra("city", weatherPresenter.city)
            startActivity(intent)
        }
    }


    override fun showErrorCityNotSelected() {
        Toast.makeText(this, R.string.no_city_selected, Toast.LENGTH_LONG).show()
    }

    override fun showError() {
        Toast.makeText(this, R.string.error_message, Toast.LENGTH_LONG).show()
    }

    override fun showTemperature(temperature: String) {
        tvTemperature.text = temperature
    }

    override fun onDestroy() {
        weatherPresenter.detachView()
        super.onDestroy()
    }
}