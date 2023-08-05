package com.example.weatherforecast.widgets

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.rounded.MoreVert
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController


@OptIn(ExperimentalMaterial3Api::class)
//@Preview
@Composable
fun WeatherAppBar(
    title: String = "Title",
    icon: ImageVector? = null,
    isMainScreen: Boolean = true,
    elevation: Dp = 0.dp, //no effect. use tonalElevation in Surface!
    navController: NavController,
    onActionClicked: () -> Unit = {},
    onButtonClicked: () -> Unit = {}
) {
    Surface(
        modifier = Modifier,
        tonalElevation = 20.dp,
        shadowElevation = 20.dp,
        contentColor = Color.Transparent,
        color = MaterialTheme.colorScheme.background
    ) {
    TopAppBar(
        scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior(),
        colors = TopAppBarDefaults.mediumTopAppBarColors(
            MaterialTheme.colorScheme.onPrimary
        ),

        title = {
            Text(

                text = title, style = TextStyle(
                    fontWeight = FontWeight.Bold,
                    fontSize = 15.sp,
                  //  color = MaterialTheme.colorScheme.onPrimary
                )
            )
        },
        actions = {
            if (isMainScreen) {
                IconButton(onClick = {
                    onActionClicked.invoke()
                }) {
                    Icon(
                        imageVector = Icons.Default.Search, contentDescription = "search icon"
                    )
                }

                IconButton(onClick = {}) {
                    Icon(
                        imageVector = Icons.Rounded.MoreVert, contentDescription = "More icon"
                    )
                }
            } else Box {}
        },
        navigationIcon = {
            if(icon!=null){
                Icon(imageVector = icon ,
                    contentDescription = "null",
                //tint= MaterialTheme.colorScheme.onSecondary,
                modifier = Modifier.clickable {
                    onButtonClicked.invoke()
                })
            }
        }
    )}

}
