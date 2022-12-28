package com.example.nbasignatureversus

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.material.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

class IconsForGame(val location: Int, val function: String) {}


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
                    sharedViewModel.makeShot()
                    navController.navigate(Screen.GameScreen.route)
                } else if (item.function == "Home") {
                    //#TODO
                } else if (item.function == "MissShot") {
                    sharedViewModel.brickShot()
                    print("BrickShoot")
                    navController.navigate(Screen.GameScreen.route)
                } else if (item.function == "Settings") {
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