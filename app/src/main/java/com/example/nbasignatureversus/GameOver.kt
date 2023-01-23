import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavController
import com.example.nbasignatureversus.*
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
            val winner = sharedViewModel.getWinner()
            HeaderSetting(headText = "Player " + winner.name + " Wins the Game")



        }
    }


}