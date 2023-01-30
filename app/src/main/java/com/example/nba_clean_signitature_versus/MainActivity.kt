package com.example.nba_clean_signitature_versus




import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import dagger.hilt.android.AndroidEntryPoint


import android.net.Uri
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.graphics.Color
import com.example.nba_clean_signitature_versus.viewmodel.PlayerViewModel

//import androidx.camera.core.ImageCapture
//import androidx.camera.core.ImageCaptureConfig
//import androidx.compose.Composable
import java.io.File
import java.util.concurrent.ExecutorService






@AndroidEntryPoint
class MainActivity : ComponentActivity() {

val playerViewModel: PlayerViewModel by viewModels()
   // val x : CameraStart by




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
          // org.kabiri.android.noteroom.camera_save.MainActivity()

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
    MaterialTheme() {
        Greeting("Android")
    }
}


