package com.example.weatherfetcher.feature.weather_screen.ui

import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import com.example.weatherfetcher.R
import com.example.weatherfetcher.feature.weather_screen.presentation.WeatherViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class WeatherFragment : Fragment(R.layout.weather_fragment) {
    private lateinit var rgCity: RadioGroup
    private lateinit var tvTemperature: TextView
    private lateinit var btnGetWeatherInform: Button
    private lateinit var btnGoWindScreen: Button
    private lateinit var progressBar: ProgressBar
    private lateinit var tvError: TextView
    private val weatherViewModel: WeatherViewModel by viewModel<WeatherViewModel>()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        rgCity = view.findViewById(R.id.radioGroupСities)
        tvTemperature = view.findViewById(R.id.tvTemperature)
        btnGetWeatherInform = view.findViewById(R.id.bt_get_weather_inform)
        btnGoWindScreen = view.findViewById(R.id.bt_go_screen_wind)
        progressBar = view.findViewById(R.id.progrses_bar)
        tvError = view.findViewById(R.id.tv_error)

        weatherViewModel.viewState.observe(viewLifecycleOwner, ::render)
        weatherViewModel.goWindEvent.observe(viewLifecycleOwner, ::goToWindScreen)

        btnGetWeatherInform.setOnClickListener {
            weatherViewModel.processUIEvent(UiEvent.OnButtonGetWeatherInform)
        }
        rgCity.setOnCheckedChangeListener { _, checkedId ->
            view.findViewById<RadioButton>(checkedId)?.apply {
                val buttonText = text.toString()
                weatherViewModel.processUIEvent(OnRbClicked(buttonText))
            }
        }

        btnGoWindScreen.setOnClickListener {
            weatherViewModel.processUIEvent(UiEvent.OnGoWindScreen)


        }
    }

    private fun render(viewState: ViewState) {
        when (viewState.city) {
            getString(R.string.moscow) -> rgCity.check(R.id.rbMoscow)
            getString(R.string.saint_petersburg) -> rgCity.check(R.id.rbSaintPetersburg)
            getString(R.string.omsk) -> rgCity.check(R.id.rbOmsk)
            else -> Unit
        }
        tvError.text = when (viewState.errorCode) {
            ErrorСodes.NO_ERROR -> ""
            ErrorСodes.WIND_SPEED_EMPTY -> getString(R.string.no_weather_data)
            ErrorСodes.EMPTY_CITY -> getString(R.string.no_city_selected)
            ErrorСodes.WEATHER_FETCH_ERROR -> getString(R.string.error_network)
        }
        progressBar.isVisible = viewState.isLoading
        tvTemperature.text = viewState.temperature
    }

    private fun goToWindScreen(speedWind: String) {
        val bundle = Bundle().apply { putString(SPEED_WIND_KEY, speedWind) }
        val windFragment = WindFragment().apply { arguments = bundle }
        requireActivity().supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container_view, windFragment).addToBackStack(null)
            .commit()
    }


}
