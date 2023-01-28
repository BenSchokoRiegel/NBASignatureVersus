import android.media.Image
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.Role.Companion.Image
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.navigation.NavController
import com.example.nba_clean_signitature_versus.*


@Composable
fun Winner_Screen(navController: NavController, sharedViewModel: SharedViewModel) {


    MaterialTheme() {
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
            EndScreen(sharedViewModel = sharedViewModel,"s")


        }
    }


}



@Composable
fun EndScreen(sharedViewModel: SharedViewModel, d : String) {
    val configuration = LocalConfiguration.current
   // val screenWidth = configuration.screenWidthDp.dp
    //val screenHeight = configuration.screenHeightDp.dp

  //  val pl1:Player = sharedViewModel.player1
  //  val pl2:Player = sharedViewModel.player2
 //   val gameScore:GameScore = sharedViewModel.gameScore
 //   val color: Color = "#FFFFFF".color
    ConstraintLayout() {
        val (title, description) = createRefs()
        Box(
            modifier = Modifier
                .padding(0.dp, 50.dp)
                //    .background(color = Red)
                //.fillMaxWidth(0.4f)
                .constrainAs(title) {
                    top.linkTo(parent.top)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    width = Dimension.fillToConstraints
                },
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = painterResource(id = sharedViewModel.getWinner().picture),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth(0.30514f)
                    .fillMaxHeight(0.161538f)

            )
        }

        Box(
            modifier = Modifier
           //     .padding(end = 4.dp)
             //   .background(Color.Magenta)
          //      .padding(bottom = 5.dp, start = 8.dp, end = 16.dp, top = 4.dp)
                .constrainAs(description) {
                    top.linkTo(title.top  )
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    bottom.linkTo(parent.bottom)
                }
        ) {
            Image(
                painterResource(id = R.drawable.finale_winning),
                contentDescription = "",
                //contentScale = ContentScale.FillBounds, // or some other scale
                modifier = Modifier.fillMaxWidth()
            )
        }
      //  TopLayer(player1 = pl1, player2 = pl2 , gameScore = gameScore, color = color)
    }
}

