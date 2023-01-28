
import androidx.compose.ui.graphics.Color
import com.example.nba_clean_signitature_versus.color


val topAndBottomColor: Color = "#db711e".color

/*
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
                    painter = painterResource(id = sharedViewModel.getWinner().picture),
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

*/