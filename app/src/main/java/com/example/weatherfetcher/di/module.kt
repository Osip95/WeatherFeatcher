package com.example.weatherfetcher.di

import android.util.Log
import com.example.weatherfetcher.feature.weather_screen.GetWeatherInteractor
import com.example.weatherfetcher.feature.weather_screen.data.WeatherApi
import com.example.weatherfetcher.feature.weather_screen.data.WeatherRemouteSource
import com.example.weatherfetcher.feature.weather_screen.data.WeatherRepoImpl
import com.example.weatherfetcher.feature.weather_screen.domain.WeatherRepo
import com.example.weatherfetcher.feature.weather_screen.presentation.WeatherViewModel
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


private const val BASE_URL = "https://api.openweathermap.org/data/2.5/"

val weatherModule = module {

    single<HttpLoggingInterceptor> {
        HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)  }

    single<OkHttpClient> {
        OkHttpClient.Builder().addInterceptor(get<HttpLoggingInterceptor>()).build() }

    single<Retrofit> {  Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .client(get())
        .build() }

    single<WeatherApi> {get<Retrofit>().create(WeatherApi::class.java)}

    single<WeatherRemouteSource> { WeatherRemouteSource(get()) }

    single<WeatherRepo> { WeatherRepoImpl(get()) }

    single<GetWeatherInteractor> { GetWeatherInteractor(get()) }

    viewModel { WeatherViewModel(get()) }



}