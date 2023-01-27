package org.kabiri.android.noteroom

import android.content.res.Resources.Theme
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Scaffold
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.navigation.NavController
import org.kabiri.android.noteroom.ui.theme.NoteRoomTheme


@Composable
fun S_Screen(navController: NavController, sharedViewModel:SharedViewModel) {

    NoteRoomTheme(darkTheme = false) {
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
            .fillMaxWidth()
            .padding(25.dp, 25.dp)

    ){
        Row( Modifier
            .fillMaxWidth()){
            Image(
                painter = painterResource(id = R.drawable.start_screen_top),
                contentDescription = null,

            )
        }
        Row(
            Modifier
                .fillMaxWidth()
                .padding(bottom = 25.dp)){
            IconButton(onClick = { navController.navigate(Screen.GameSettingScreen.route) }) {
                Image(
                    painter = painterResource(id = R.drawable.start_screen_bottom),
                    contentDescription = null,
                )
            }


        }


    }



}


