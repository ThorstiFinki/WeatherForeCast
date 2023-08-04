package com.example.weatherforecast.screens.main

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.produceState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.rememberImagePainter
import com.example.weatherforecast.data.DataOrException
import com.example.weatherforecast.model.Weather
import com.example.weatherforecast.widgets.WeatherAppBar
import java.lang.Exception

@Composable
fun MainScreen(navController: NavController,
                mainViewModel: MainViewModel = hiltViewModel()){


    val weatherData = produceState<DataOrException<Weather, Boolean, Exception>>(
        initialValue = DataOrException(loading=true)){
        value = mainViewModel.getWeatherData(city = "Würselen")
    }.value

    if(weatherData.loading == true) {
        CircularProgressIndicator()
    }else if (weatherData.data != null){
        MainScaffold(weather= weatherData.data!!, navController )

    }
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScaffold(weather:Weather, navController: NavController){
    Scaffold(topBar = {
WeatherAppBar(
    title = weather.city.name + " ,${weather.city.country}",
    icon= Icons.Default.ArrowBack,
    navController = navController,
        elevation= 5.dp){
    Log.d("TAG", "MainScaffold: Button Clicked")
}
    }) {
        MainContent(data = weather)
    }


}

@Composable
fun MainContent(data: Weather) {
    val imageUrl = "https://openweathermap.org/img/wn/${data!!.list[0].weather[0].icon}.png"


  Column(modifier = Modifier
      .padding(4.dp)
      .fillMaxWidth(),
  verticalArrangement = Arrangement.Center,
  horizontalAlignment = Alignment.CenterHorizontally) {
      Spacer(modifier = Modifier.padding(35.dp))
      Text(text = "Nov 29",
      style = MaterialTheme.typography.bodyMedium,
     // color = MaterialTheme.colorScheme.onSecondary,
      fontWeight = FontWeight.SemiBold,
          modifier = Modifier.padding(6.dp))

      Surface(modifier = Modifier
          .padding(4.dp)
          .size(200.dp),
      shape = CircleShape,
      color= Color(0xFFFFC400)
      ) {
          Column(verticalArrangement = Arrangement.Center,
          horizontalAlignment = Alignment.CenterHorizontally) {
              WeatherStateImage(imageUrl=imageUrl)
              Text(text = "54", style= MaterialTheme.typography.bodyLarge,
              fontWeight = FontWeight.ExtraBold)
              Text(text = "Snow", fontStyle = FontStyle.Italic)
          }

      }
  }

}

@Composable
fun WeatherStateImage(imageUrl: String) {
    Image(painter = rememberImagePainter(imageUrl),
        contentDescription = "icon image",
    modifier = Modifier.size(80.dp))

}
