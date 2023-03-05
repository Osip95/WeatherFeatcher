package com.example.weatherfetcher.feature.weather_screen.ui

import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import com.example.weatherfetcher.R
import com.example.weatherfetcher.feature.weather_screen.presentation.WeatherViewModel
import org.koin.androidx.viewmodel.ext.android.activityViewModel

class WeatherFragment : Fragment(R.layout.weather_fragment) {
    private lateinit var rgCity: RadioGroup
    private lateinit var tvTemperature: TextView
    private lateinit var btnGetWeatherInform: Button
    private lateinit var btnGoWindScreen: Button
    private lateinit var progressBar: ProgressBar
    private val weatherViewModel: WeatherViewModel by activityViewModel()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        rgCity = view.findViewById(R.id.radioGroupСities)
        tvTemperature = view.findViewById(R.id.tvTemperature)
        btnGetWeatherInform = view.findViewById(R.id.bt_get_weather_inform)
        btnGoWindScreen = view.findViewById(R.id.bt_go_screen_wind)
        progressBar = view.findViewById(R.id.progrses_bar)

        weatherViewModel.viewState.observe(viewLifecycleOwner, ::render)

        btnGetWeatherInform.setOnClickListener {
            if (weatherViewModel.viewState.value?.city == "") {
                showErrorCityNotSelected()
                return@setOnClickListener
            }
            weatherViewModel.processUIEvent(UiEvent.OnButtonGetTemperature)
        }
        rgCity.setOnCheckedChangeListener { _, checkedId ->
            view.findViewById<RadioButton>(checkedId)?.apply {
                when (this.id) {
                    R.id.rbMoscow -> weatherViewModel.processUIEvent(UiEvent.OnRbMoscow)
                    R.id.rbSaintPetersburg -> weatherViewModel.processUIEvent(UiEvent.OnRbSaintPetersburg)
                    R.id.rbOmsk -> weatherViewModel.processUIEvent(UiEvent.OnRbOmsk)
                }
            }
        }

        btnGoWindScreen.setOnClickListener {
            if (weatherViewModel.viewState.value?.city == "") {
                showErrorCityNotSelected()
                return@setOnClickListener
            }
            if (weatherViewModel.viewState.value?.speedWind == "") {
                showErrorDataNotReceived()
                return@setOnClickListener
            }
            activity?.supportFragmentManager?.beginTransaction()
                ?.replace(R.id.conteiner, WindFragment())
                ?.commit()
        }
    }

    private fun render(viewState: ViewState) {
        when (viewState.city) {
            "Moscow" -> rgCity.check(R.id.rbMoscow)
            "Saint Petersburg" -> rgCity.check(R.id.rbSaintPetersburg)
            "Omsk" -> rgCity.check(R.id.rbOmsk)
        }
        progressBar.isVisible = viewState.isLoading
        tvTemperature.text = viewState.temperature
    }

   private fun showErrorCityNotSelected() {
        Toast.makeText(activity, R.string.no_city_selected, Toast.LENGTH_LONG).show()
    }

   private fun showErrorDataNotReceived() {
        Toast.makeText(activity, R.string.no_weather_data, Toast.LENGTH_LONG).show()
    }


}
