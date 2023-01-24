import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavController
import com.example.nbasignatureversus.*
import com.example.nbasignatureversus.R
import com.example.nbasignatureversus.ui.theme.NBASIgnatureVersusTheme

val topAndBottomColor: Color = "#db711e".color

@Composable
fun GameOver(navController: NavController, sharedViewModel: SharedViewModel) {


    NBASIgnatureVersusTheme(darkTheme = false) {
        // A surface container using the 'background' color from the theme
        Scaffold(
            topBar = { Topbar(topAndBottomColor, " Game Over ",navController) },
            bottomBar = {
                    BottomNavigation(
                        navController,
                        items =
                        listOf(
                            IconsForGame(R.drawable.icon_home, "Home"),
                            IconsForGame(R.drawable.icon_settings, "Settings"),
                            //IconsForGame(R.drawable.icon_new, "New"),
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
            WinnerScreen(sharedViewModel = sharedViewModel)


        }
    }


}


@Composable
fun WinnerScreen(sharedViewModel: SharedViewModel){
    val winner = sharedViewModel.getWinner()
    HeaderSetting(headText = "Player " + winner.name + " Wins the Game")

    Column(modifier = Modifier.padding(0.dp,50.dp)) {
        Row(){
            Text("")
        }

        Row() {


            ConstraintLayout(
                modifier = Modifier
                    .fillMaxWidth()
                    .paint(
                        painterResource(id = R.drawable.background_test),
                        contentScale = ContentScale.FillWidth
                    )
            ) {
                Text(text = "s")
                //HeaderSetting(headText = "Player " + winner.name + " Wins the Game")
                Image(
                    modifier = Modifier
                        .padding(defaultPadding)
                        .size(50.dp)
                        .clip(CircleShape),
                    painter = painterResource(id = winner.picture),
                    contentDescription = "",
                    contentScale = ContentScale.FillHeight
                )
            }
        }
    }






}