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
import com.example.nba_clean_signitature_versus.viewmodel.PlayerViewModel

//import androidx.camera.core.ImageCapture
//import androidx.camera.core.ImageCaptureConfig
//import androidx.compose.Composable
import java.io.File
import java.util.concurrent.ExecutorService


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
   // val x : CameraStart by

    private lateinit var outputDirectory: File
    private lateinit var cameraExecutor: ExecutorService

    private var shouldShowCamera: MutableState<Boolean> = mutableStateOf(false)

    private lateinit var photoUri: Uri
    private var shouldShowPhoto: MutableState<Boolean> = mutableStateOf(false)


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


