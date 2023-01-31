package com.example.nba_clean_signitature_versus

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.IconButton

import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold

import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController


@Composable
fun S_Screen(navController: NavController, sharedViewModel: SharedViewModel) {

    MaterialTheme() {
        // A surface container using the 'background' color from the theme
        Scaffold(
            topBar = { Topbar(topAndBottomColor, " Start Playing ", navController = navController) },
            bottomBar = {

                    BottomNavigation(
                        navController,
                        items =
                        listOf(
                            //IconsForGame(R.drawable.icon_settings, "Settings"),
                            IconsForGame(R.drawable.icon_continue, "New"),
                            //IconsForGame(R.drawable.icon_reset, "Reset"),
                            IconsForGame(R.drawable.icon_help, "help"),
                            ), sharedViewModel
                    )
            }


        ) {

            show_start_icons(navController = navController)
        }
    }

}


@Composable
fun show_start_icons(navController: NavController){
    Column(
        Modifier
            .fillMaxSize()
            .padding(0.dp, 0.dp),  horizontalAlignment = Alignment.CenterHorizontally

    ){
        Row( Modifier
            .fillMaxWidth().padding(bottom = 0.dp), horizontalArrangement = Arrangement.Center){
            Image(
                painter = painterResource(id = R.drawable.start_screen_top_n),
                contentDescription = null,

            )
        }
        Row(
            Modifier
                .fillMaxWidth()
                .padding(bottom = 80.dp), horizontalArrangement = Arrangement.Center){
            IconButton(onClick = { navController.navigate(Screen.GameSettingScreen.route) }) {
                Image(
                    painter = painterResource(id = R.drawable.start_screen_bottom),
                    contentDescription = null,
                )
            }


        }


    }



}


