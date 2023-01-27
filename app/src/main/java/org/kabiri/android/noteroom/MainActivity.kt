package org.kabiri.android.noteroom

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
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
         //   Navigation(playerViewModel = playerViewModel)

        }
    }
}

@Composable
fun takeAndSavePhoto() {
    val imageCapture = ImageCapture(imageCaptureConfig)
    val image = imageCapture.takePicture()

    // Save the image to the "drawable" folder
    val file = File(context.getDrawable(R.drawable.your_file_name).toString())
    image.save(file, ImageFormat.JPEG)
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