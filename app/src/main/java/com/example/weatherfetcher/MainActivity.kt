package com.example.weatherfetcher

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val tvHello = findViewById<TextView>(R.id.tvHello)
        tvHello.text = "Hello from code"

        var btnWeather = findViewById<Button>(R.id.btnWeather)
        btnWeather.setOnClickListener{
            Intent(this,WeatherActivity::class.java).also(::startActivity)
        }
    }
}