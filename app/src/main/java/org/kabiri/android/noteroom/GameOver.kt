import android.graphics.Paint
import android.graphics.Paint.Align
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

import org.kabiri.android.noteroom.*
import org.kabiri.android.noteroom.R
import org.kabiri.android.noteroom.ui.theme.NoteRoomTheme
import java.util.*


val topAndBottomColor: Color = "#db711e".color


@Composable
fun GameOver(navController: NavController, sharedViewModel: SharedViewModel) {


    NoteRoomTheme(darkTheme = false) {
        // A surface container using the 'background' color from the theme
        Scaffold(
            topBar = { Topbar(topAndBottomColor, " Game Over ", navController) },
            bottomBar = {
                BottomNavigation(
                    navController,
                    items =
                    listOf(

                        IconsForGame(R.drawable.icon_home, "Home"),
                        IconsForGame(R.drawable.icon_settings, "Settings"),
                        IconsForGame(R.drawable.icon_new, "New"),
                        IconsForGame(R.drawable.icon_replay, "New"),

                        ), sharedViewModel
                )
            }
            //bottomBar = { BottomAppBar(backgroundColor = Color.Red, modifier = Modifier.fillMaxHeight()) { Text("Click FloatingAction to add Random elements ") } },
            // floatingActionButton = {
            //FloatingActionButton(onClick = { navController.navigate(Screen.CreateNote.route) }

        ) {
            //val winner = sharedViewModel.getWinner()
            //HeaderSetting(headText = "Player " + winner.name + " Wins the Game")
            WinnerScreen(sharedViewModel = sharedViewModel,3)


        }
    }


}

// Inspieriert durch https://stackoverflow.com/questions/69039866/how-to-add-a-background-image-in-jetpack-compose-to-a-compose-layout
@Composable
fun WinnerScreen(sharedViewModel: SharedViewModel, d : Int) {
    Box(modifier = Modifier.fillMaxWidth()) {
        Image(
            painterResource(id = R.drawable.victory_screen),
            contentDescription = "",
            //contentScale = ContentScale.FillBounds, // or some other scale
            modifier = Modifier.matchParentSize()
        )
        CompositionLocalProvider(LocalContentAlpha provides ContentAlpha.medium) {
            IconButton(
                onClick = { /*...*/ },
                modifier = Modifier
                    .padding(horizontal = 100.dp, vertical = 100.dp)
            ) {
                Image(
                    painter = painterResource(id = sharedViewModel.player2.picture),
                    contentDescription = null,
                    modifier = Modifier
                        .height(400.dp)
                        .width(600.dp)
                        .padding(32.dp),
                )
            }
        }
    }

}


@Composable
fun WinnerScreen(sharedViewModel: SharedViewModel, d : Boolean){

    Surface(color = Color.White) {
        Image(painter = painterResource(id = sharedViewModel.getWinner().picture),
            contentDescription = null,)


    }



}

@Composable
fun WinnerScreen(sharedViewModel: SharedViewModel) {





    val winner = sharedViewModel.getWinner()
    HeaderSetting(headText = "Player " + winner.name + " Wins the Game")

    Column(modifier = Modifier.padding(0.dp, 50.dp)) {
        Row() {
            Text("")
        }

        Row() {

            Image(
                painter = painterResource(id = sharedViewModel.getWinner().picture),
                contentDescription = null,
                modifier = Modifier
                    .height(600.dp)
                    .width(600.dp)
                    .paint(
                        painter = painterResource(R.drawable.victory_screen),
                       contentScale = ContentScale.Inside,
                        sizeToIntrinsics = false
                    )
                    .padding(32.dp),
            )


        }


    }
}

