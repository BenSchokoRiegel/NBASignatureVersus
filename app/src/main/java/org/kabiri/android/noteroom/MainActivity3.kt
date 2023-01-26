package org.kabiri.android.noteroom

import android.os.Bundle
import android.widget.ImageView
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview


class MainActivity3 : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Navigation()
        }
    }
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContent {
//            NBASIgnatureVersusTheme {
//                // A surface container using the 'background' color from the theme
//                Surface(
//                    modifier = Modifier.fillMaxSize(),
//                    color = MaterialTheme.colors.background
//                ) {
//                    Greeting("Android")
//                }
//            }
//        }
//    }
}

/*
@Composable
fun Greeting(name: String) {
    val imageView : ImageView

    //Image(painter = painterResource(id = R.drawable.durant), contentDescription = null)
    Text(text = "Hello $name!")
}



@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    NBASIgnatureVersusTheme {
        Greeting("Android")
    }
}
*/
