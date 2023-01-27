package org.kabiri.android.noteroom

import org.kabiri.android.noteroom.ui.theme.NoteRoomTheme


import android.graphics.ImageFormat
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.camera.core.ImageCapture
import androidx.camera.core.impl.ImageCaptureConfig
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import dagger.hilt.android.AndroidEntryPoint
import org.kabiri.android.noteroom.ui.home.HomeScreen
import org.kabiri.android.noteroom.ui.home.testDataBase
import org.kabiri.android.noteroom.ui.theme.NoteRoomTheme
import org.kabiri.android.noteroom.viewmodel.HomeViewModel
import org.kabiri.android.noteroom.viewmodel.PlayerViewModel


import android.content.Context
//import androidx.camera.core.ImageCapture
//import androidx.camera.core.ImageCaptureConfig
//import androidx.compose.Composable
import java.io.File
import java.io.FileOutputStream
import java.io.IOException





/*
@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val playerViewModel: PlayerViewModel by viewModels()
        val homeViewModel: HomeViewModel by viewModels()

        setContent {
            NoteRoomTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    HomeScreen(
                        homeViewModel = homeViewModel
                    )
                }
            }
        }
    }
}
*/





@AndroidEntryPoint
class MainActivity : ComponentActivity() {

val playerViewModel: PlayerViewModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Navigation(playerViewModel = playerViewModel)

        }
    }
}






@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    NoteRoomTheme {
        Greeting("Android")
    }
}


