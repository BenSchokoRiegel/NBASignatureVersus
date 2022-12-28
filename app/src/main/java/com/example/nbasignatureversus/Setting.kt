package com.example.nbasignatureversus

import android.content.ClipData
import android.graphics.drawable.Icon
import androidx.compose.foundation.Image
import androidx.compose.foundation.MagnifierStyle.Companion.Default
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*


import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Share

import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle.Companion.Default
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.nbasignatureversus.ui.theme.NBASIgnatureVersusTheme


val topAndBottomColor: Color = Color.Red

@Composable
fun Settings(navController: NavController, sharedViewModel: SharedViewModel) {

    NBASIgnatureVersusTheme(darkTheme = false) {
        // A surface container using the 'background' color from the theme
        Scaffold(
            topBar = { Topbar() },
           // bottomBar = { BottomAppBar(backgroundColor = Color.Red) { Text("Click FloatingAction to add Random elements ") } },
            // floatingActionButton = {
            //FloatingActionButton(onClick = { navController.navigate(Screen.CreateNote.route) }

        ) {
            GameScreen(navController = navController, sharedViewModel = sharedViewModel)
        }
    }


}
@Preview
@Composable
fun Topbar() {

    TopAppBar(
        //modifier = Modifier.padding(4.dp, 4.dp),
        backgroundColor = "#db711e".color,
        navigationIcon = {
            IconButton(onClick = {}) {
                Icon(Icons.Default.Info, "Info")
            }
        },
        actions = {

            //Icon(Icons.Default.Info, "Info")
            //Icon(imageVector = ImageVector., "Info")
            Row(Modifier.padding(10.dp,10.dp,0.dp,10.dp),horizontalArrangement = Arrangement.spacedBy(10.dp)) {


                androidx.compose.material.Icon(
                    painter = painterResource(id = R.drawable.twitter),
                    contentDescription = null,
                    modifier = Modifier.alpha(1f).clickable { },
                    tint = Color.Black
                )

                androidx.compose.material.Icon(
                    painter = painterResource(id = R.drawable.github_mark),
                    contentDescription = null,
                    modifier = Modifier.alpha(1f).clickable {  },
                    tint = Color.Black
                )
                androidx.compose.material.Icon(
                    painter = painterResource(id = R.drawable.linkedin),
                    contentDescription = null,
                    modifier = Modifier.alpha(1f).clickable {  },
                    tint = Color.Black
                )
            }
        },
        title = {
            Image(
                painter = painterResource(id = R.drawable.header),
                contentDescription = null, /*modifier = Modifier.padding(
                    *//*    horizontal = 16.dp,*//*
                        vertical = 4.dp
                    )*/
            )
        }

    )
}



@Composable
fun ActionItemSpec(s: String, call: Any, alwaysShow: Any, function: () -> Unit) {

}

@Composable
fun BottomBar() {
    BottomAppBar(
        contentColor = topAndBottomColor,
        content = {

        }
    )


}