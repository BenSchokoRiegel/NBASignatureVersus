package com.aseemwangoo.handsonkotlin

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.aseemwangoo.handsonkotlin.HelperComponents.SharedViewModel
import com.aseemwangoo.handsonkotlin.Screen
import com.aseemwangoo.handsonkotlin.defaultPadding
import topAndBottomColor




public class IconsForGame(val location: Int, val function: String) {}







// Inspieriert durch https://medium.com/geekculture/bottom-navigation-in-jetpack-compose-android-9cd232a8b16
@Composable
fun BottomNavigation(
    navController: NavController,
    items: List<IconsForGame>,
    sharedViewModel: SharedViewModel
) {
    androidx.compose.material.BottomNavigation(
        backgroundColor = topAndBottomColor,
        contentColor = Color.Black,
        elevation = 0.dp,
        modifier = Modifier
            .fillMaxHeight(0.2f)
            .padding(10.dp)
    ) {
        items.forEach { item ->
            BottomNavigationItem(
                icon = {
                    Icon(
                        painterResource(id = item.location),
                        contentDescription = item.function
                    )
                },
//                label = {
//                    Text(
//                        text = item.function,
//                        fontSize = 9.sp
//                    )
//                },
                selectedContentColor = Color.Black,
                unselectedContentColor = Color.Black.copy(0.4f),
                alwaysShowLabel = true,
                selected = true,
                onClick = {
                    if (item.function == "Continue") {
                        navController.navigate(Screen.GameScreen.route)

                    } else if (item.function == "New") {
                        sharedViewModel.newGame()
                        navController.navigate(Screen.GameScreen.route)

                    } else if (item.function == "Save") {
                        // #TODO
                    } else if (item.function == "Home") {
                        //#TODO
                    }else if (item.function == "Settings") {
                        navController.navigate(Screen.GameSettingScreen.route)
                    }
                },
                modifier = Modifier.padding(20.dp)
            )
        }
    }


}


@Composable
fun HeaderSetting(headText: String) {
    Text(
        text = headText,
        fontSize = 25.sp,
        fontWeight = FontWeight.Bold,
        textAlign = TextAlign.Center,
        style = TextStyle(textDecoration = TextDecoration.Underline),
        modifier = Modifier
            .fillMaxWidth()
            .alpha(1f)
            .padding(defaultPadding)

    )


}




@OptIn(ExperimentalFoundationApi::class)
@Composable
fun createIcons(
    icons: List<IconsForGame>,
    navController: NavController,
    sharedViewModel: SharedViewModel,
    mod: Modifier
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(20.dp),
        modifier = mod.fillMaxSize().border(defaultPadding, Color.Black).padding(10.dp)

    ) {
        val configuration = LocalConfiguration.current
        val maxWi = configuration.screenWidthDp.dp.div(4f) - 20.dp

        for (item in icons){
            /* val wi = maxWi
                     if (item.function == "MakeShot")*/
//fillMaxHeight(0.25f).fillMaxWidth(0.25f)
            IconButton(onClick = {
                if (item.function == "MakeShot") {
                    print("MakeShoot")
                    val gameover : Boolean = sharedViewModel.makeShot()
                    if (gameover){
                        navController.navigate(Screen.GameOver.route)
                    } else {
                        navController.navigate(Screen.GameScreen.route)
                    }
                } else if (item.function == "Home") {
                    //#TODO
                } else if (item.function == "MissShot") {
                    sharedViewModel.brickShot()
                    print("BrickShoot")
                    navController.navigate(Screen.GameScreen.route)
                } else if (item.function == "Pause") {
                    navController.navigate(Screen.GameSettingScreen.route)
                }
            }) {
                Image(
                    painter = painterResource(id = item.location),
                    contentDescription = null, modifier = Modifier.width(maxWi)/*modifier = Modifier.padding(
                    *//*    horizontal = 16.dp,*//*
                        vertical = 4.dp
                    )*/
                )
            }
        }
    }
}