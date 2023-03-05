package com.example.weatherfetcher.feature.weather_screen.ui

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.weatherfetcher.R
import com.example.weatherfetcher.feature.weather_screen.presentation.WeatherViewModel
import org.koin.androidx.viewmodel.ext.android.activityViewModel

class WindFragment : Fragment(R.layout.wind_fragment) {
    private val weatherViewModel by activityViewModel<WeatherViewModel>()
    private lateinit var btnGoWeather: Button
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        weatherViewModel.viewState.observe(viewLifecycleOwner, ::setTextTv)
        btnGoWeather = view.findViewById(R.id.go_weather)

        btnGoWeather.setOnClickListener {
            activity?.supportFragmentManager?.beginTransaction()
                ?.replace(R.id.conteiner, WeatherFragment())
                ?.commit()
        }
    }

    private fun setTextTv(viewState: ViewState) {
        val tv = view?.findViewById<TextView>(R.id.tvSpeedWind)
        tv?.text = viewState.speedWind
    }
}