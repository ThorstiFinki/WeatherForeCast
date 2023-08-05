package com.example.weatherforecast.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.weatherforecast.screens.main.MainScreen
import com.example.weatherforecast.screens.main.MainViewModel
import com.example.weatherforecast.screens.search.SearchScreen
import com.example.weatherforecast.screens.splash.WeatherSplashScreen

@Composable
fun WeatherNavigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = WeatherScreens.SplashScreen.name){
     composable(WeatherScreens.SplashScreen.name){
         WeatherSplashScreen(navController = navController)
     }
    //example: www.google.com/cityname="Würselen"
     val route = WeatherScreens.MainScreen.name
     composable("$route/{city}",
     arguments= listOf(
         navArgument(name="city"){
             type = NavType.StringType
         })
     ){
         navBack ->
         navBack.arguments?.getString("city").let {city ->

             val mainViewModel = hiltViewModel<MainViewModel>()
             MainScreen(navController = navController, mainViewModel,
             city = city)
         }
     }

        composable(WeatherScreens.SearchScreen.name){
            SearchScreen(navController = navController)
        }

    }
}