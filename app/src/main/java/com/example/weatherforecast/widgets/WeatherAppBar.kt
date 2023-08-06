package com.example.weatherforecast.widgets

import android.graphics.Paint.Align
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.absolutePadding
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.rounded.MoreVert
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.weatherforecast.navigation.WeatherScreens


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
    val showDialog = remember{
        mutableStateOf(false)
    }
    
    if (showDialog.value){
        ShowSettingDropDownMenu(showDialog = showDialog, navController = navController)
    }

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

                IconButton(onClick = {
                    showDialog.value=true
                    
                }) {
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

@Composable
fun ShowSettingDropDownMenu(showDialog: MutableState<Boolean>,
                            navController: NavController) {
    var expanded by remember {
        mutableStateOf(true)    }

    val items = listOf("About", "Favorites", "Settings")

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentSize(Alignment.TopEnd)
            .absolutePadding(top = 45.dp, right = 20.dp)
    ) {
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            modifier = Modifier
                .width(140.dp)
                .background(Color.White)
        ) {
            items.forEachIndexed { index, text ->
                DropdownMenuItem(onClick = {
                    expanded = false
                    showDialog.value = false
                }, text = {
                    Icon(
                        imageVector = when (text) {
                            "About" -> Icons.Default.Info
                            "Favorites" -> Icons.Default.FavoriteBorder
                            else -> Icons.Default.Settings
                        }, contentDescription = null,
                        tint = Color.LightGray
                    )
                    Text(text = text, modifier = Modifier.padding(start = 30.dp).clickable{

                     navController.navigate(
                         when  (text) {
                             "About" -> WeatherScreens.AboutScreen.name
                             "Favorites" -> WeatherScreens.FavoriteScreen.name
                             else -> WeatherScreens.SettingsScreen.name
                         }
                     )

                    }, fontWeight = FontWeight.W300, textAlign = TextAlign.Center)
                })
            }
        }}}

