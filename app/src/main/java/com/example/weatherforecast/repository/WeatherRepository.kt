package com.example.weatherforecast.repository

import com.example.weatherforecast.data.DataOrException
import com.example.weatherforecast.model.Weather
import com.example.weatherforecast.network.WeatherAPI
import javax.inject.Inject

class WeatherRepository @Inject constructor(private val api: WeatherAPI) {
    suspend fun getWeather(
        cityQuery: String,
        units: String
    )
            : DataOrException<Weather, Boolean, Exception> {
        val response = try{
            api.getWeather(query = cityQuery, units = units)
        } catch (e: Exception) {
            return DataOrException(e = e)
        }
        return DataOrException(data = response)

    }
}