package com.example.weatherforecast.widgets

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import com.bawp.jetweatherforecast.utils.formatDate
import com.bawp.jetweatherforecast.utils.formatDateTime
import com.bawp.jetweatherforecast.utils.formatDecimals
import com.example.weatherforecast.R
import com.example.weatherforecast.model.WeatherItem
import com.example.weatherforecast.screens.main.WeatherStateImage


@Composable
fun HumidityWindPressureRow(weather: WeatherItem){
    Row(modifier = Modifier
        .padding(12.dp)
        .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween) {
        Row(modifier = Modifier.padding(4.dp)) {
            Icon(painter = painterResource(id = R.drawable.humidity),
                contentDescription = "humidity Icon",
                modifier = Modifier.size(20.dp))
            Text(text = "${weather.humidity}%", style = MaterialTheme.typography.titleSmall)
        }

        Row(modifier = Modifier.padding(4.dp)) {
            Icon(painter = painterResource(id = R.drawable.pressure),
                contentDescription = "pressure Icon",
                modifier = Modifier.size(20.dp))
            Text(text = "${weather.pressure}psi", style = MaterialTheme.typography.titleSmall)
        }

        Row(modifier = Modifier.padding(4.dp)) {
            Icon(painter = painterResource(id = R.drawable.wind),
                contentDescription = "wind Icon",
                modifier = Modifier.size(20.dp))
            Text(text = "${weather.speed}m/h", style = MaterialTheme.typography.titleSmall)
        }

    }
}

@Composable
fun SunsetSunRiseRow(weather : WeatherItem){
    Row(modifier = Modifier
        .fillMaxWidth()
        .padding(top = 15.dp, bottom = 6.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically) {
        Row {
            Image(painter = painterResource(id = R.drawable.sunrise),
                contentDescription ="sunrise",
                modifier = Modifier.size(30.dp))
            Text(text = formatDateTime(weather.sunrise),
                style= MaterialTheme.typography.titleSmall)
        }

        Row {
            Image(painter = painterResource(id = R.drawable.sunset),
                contentDescription ="sunset",
                modifier = Modifier.size(30.dp))
            Text(text = formatDateTime(weather.sunset),
                style= MaterialTheme.typography.titleSmall)
        }
    }
}

@Composable
fun WeatherDetailRow(weather: WeatherItem){
    val imageUrl = "https://openweathermap.org/img/wn/${weather.weather[0].icon}.png"

    Surface(modifier = Modifier
        .padding(3.dp)
        .fillMaxWidth(),
        shape = CircleShape.copy(topEnd = CornerSize(6.dp)),
        color = Color.White) {
        Row(modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween) {
            Text(text = formatDate(weather.dt)
                .split(",")[0],
                modifier = Modifier.padding(start=5.dp))
            WeatherStateImage(imageUrl = imageUrl)
            Surface(modifier= Modifier.padding(0.dp),
                shape = CircleShape,
                color= Color(0xFFFFC400)
            ) {
                Text(weather.weather[0].description,
                    modifier= Modifier.padding(4.dp),
                    style= MaterialTheme.typography.labelMedium)

            }
            Text(text = buildAnnotatedString {
                withStyle(style= SpanStyle(
                    color = Color.Blue.copy(alpha = 0.7f),
                    fontWeight = FontWeight.SemiBold)
                ){
                    append(formatDecimals(weather.temp.max) +"°")
                }
                withStyle(
                    style = SpanStyle(
                        color= Color.LightGray)
                ){
                    append(formatDecimals(weather.temp.min) +"°")
                }
            })

        }

    }





}
