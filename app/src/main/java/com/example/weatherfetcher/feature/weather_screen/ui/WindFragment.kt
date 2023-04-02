package com.example.weatherfetcher.feature.weather_screen.ui

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.weatherfetcher.R
import com.example.weatherfetcher.feature.weather_screen.presentation.WindViewModel

class WindFragment : Fragment(R.layout.wind_fragment) {
    private lateinit var windViewModel: WindViewModel
    private lateinit var btnGoWeather: Button
    private lateinit var tvSpeedWind: TextView
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        btnGoWeather = view.findViewById(R.id.go_weather)
        tvSpeedWind = view.findViewById(R.id.tvSpeedWind)
        val windSpeed = arguments?.getString(SPEED_WIND_KEY) ?: NOT_FOUND
        windViewModel = WindViewModel(windSpeed)
        windViewModel.viewStateWind.observe(viewLifecycleOwner, ::setTextTv)


        btnGoWeather.setOnClickListener {
            requireActivity().supportFragmentManager.popBackStack()
        }
    }

    private fun setTextTv(speedWind: ViewStateWind) {
        tvSpeedWind.text = speedWind.speedWind
    }
}