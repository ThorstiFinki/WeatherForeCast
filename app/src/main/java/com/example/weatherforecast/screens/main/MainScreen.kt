package com.example.weatherforecast.screens.main

import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.produceState
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.weatherforecast.data.DataOrException
import com.example.weatherforecast.model.Weather
import com.example.weatherforecast.widgets.WeatherAppBar
import java.lang.Exception

@Composable
fun MainScreen(navController: NavController,
                mainViewModel: MainViewModel = hiltViewModel()){


    val weatherData = produceState<DataOrException<Weather, Boolean, Exception>>(
        initialValue = DataOrException(loading=true)){
        value = mainViewModel.getWeatherData(city = "Berlin")
    }.value

    if(weatherData.loading == true) {
        CircularProgressIndicator()
    }else if (weatherData.data != null){
        MainScaffold(weather= weatherData.data!!, navController )

    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScaffold(weather:Weather, navController: NavController){
    Scaffold(topBar = {
WeatherAppBar(title = "Hallo irgendwas")
    }) {
        MainContent(data = weather)
    }


}

@Composable
fun MainContent(data: Weather) {
    Text(text = data.city.name)

}
