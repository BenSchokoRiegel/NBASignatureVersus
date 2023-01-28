package com.example.nba_clean_signitature_versus

import androidx.compose.runtime.*
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavController
import com.example.nba_clean_signitature_versus.viewmodel.PlayerViewModel


import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid


import androidx.compose.material.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight

import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.nba_clean_signitature_versus.*

@Composable
fun ShowAllPlayers(navController: NavController,
                   sharedViewModel: SharedViewModel,
                   playerViewModel: PlayerViewModel,
                   isFirst : Boolean
) {

    val noteListState = playerViewModel.noteListFlow.collectAsState(initial = listOf())
    val context = LocalContext.current


    //val playerViewModel: PlayerViewModel = viewModel(factory = PlayerViewModelFactory(context.applicationContext as Application) )

    MaterialTheme() {
        // A surface container using the 'background' color from the theme
        androidx.compose.material.Scaffold(
            topBar = { Topbar(topAndBottomColor, " Choose Player", navController = navController) },
            bottomBar = {
                if (!sharedViewModel.gameScore.hasStarted) {
                    com.example.nba_clean_signitature_versus.BottomNavigation(
                        navController,
                        items =
                        listOf(
                            IconsForGame(R.drawable.icon_settings, "Settings"),
                            IconsForGame(R.drawable.icon_start, "New"),
                            //IconsForGame(R.drawable.icon_reset, "Reset"),
                            IconsForGame(R.drawable.icon_home, "Home"),

                            ), sharedViewModel
                    )
                } else {
                    com.example.nba_clean_signitature_versus.BottomNavigation(
                        navController,
                        items =
                        listOf(
                            IconsForGame(R.drawable.icon_home, "Home"),
                            IconsForGame(R.drawable.icon_continue, "Continue"),
                            IconsForGame(R.drawable.icon_new, "New"),
                            // IconsForGame(R.drawable.icon_reset, "Reset"),
                            IconsForGame(R.drawable.icon_save, "Save"),
                        ), sharedViewModel
                    )
                }


            }
            //bottomBar = { BottomAppBar(backgroundColor = Color.Red, modifier = Modifier.fillMaxHeight()) { Text("Click FloatingAction to add Random elements ") } },
            // floatingActionButton = {
            //FloatingActionButton(onClick = { navController.navigate(Screen.CreateNote.route) }

        ) {
            Rasterlayout1(navController = navController,
                sharedViewModel = sharedViewModel,
                playerViewModel = playerViewModel , isFirst)

        }
    }


}



@OptIn(ExperimentalFoundationApi::class)
@Composable
fun Rasterlayout1(navController: NavController,
                  sharedViewModel: SharedViewModel,
                  playerViewModel: PlayerViewModel, isFirst: Boolean) {


    val noteListState = playerViewModel.noteListFlow.collectAsState(initial = listOf())
    LazyVerticalGrid(
        cells = GridCells.Fixed(2),
        Modifier.padding(10.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp),

        )

    {
        items(noteListState.value.size) { index ->
            val note = noteListState.value[index]

            var background = topAndBottomColor;



            Card(
                modifier = Modifier.clickable {
                    if (isFirst){
                        sharedViewModel.player1.name = note.playername
                        sharedViewModel.player1.level = createLevel(note.playerLevel)
                        sharedViewModel.player1.picture = note.playerBildLocation.toInt()
                    } else {
                        sharedViewModel.player2.name = note.playername
                        sharedViewModel.player2.level = createLevel(note.playerLevel)
                        sharedViewModel.player2.picture = note.playerBildLocation.toInt()
                    }

                    navController.navigate(Screen.GameSettingScreen.route)




                }
            ) {
                Column(
                    modifier = Modifier
                        .background(background)
                        .fillMaxHeight()
                        .fillMaxWidth(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {

                    Row() {
                        Text(
                            note.roomId.toString(),
                            fontWeight = FontWeight.Bold,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis
                        )

                    }
                    Row() {
                        Text(
                            note.playername,
                            fontSize = 22.sp,
                            fontWeight = FontWeight.Bold,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis
                        )

                    }

                    Row() {
                        Text(
                            note.playerLevel, fontSize = 22.sp,
                            maxLines = 1,
                            fontWeight = FontWeight.Bold,
                            overflow = TextOverflow.Ellipsis,

                            )

                    }
                    Row() {


                    }
                    Row(
                        modifier = Modifier
                            .height(55.dp)
                            .fillMaxSize()
                    ) {
                        /*
                      //  var x: Int = note.playerBildLocation
                    //    var a: Int = R.drawable.profil_ben
                  //      var b = R.drawable.profil_ben.toString()
                       var i = Drawable.createFromPath("R.drawable.profil_ben")
                        var xd = Drawable.createFromPath("R.drawable.Doronina")
                     //   var xs = Drawable.createFromPath("R.drawable.profil_ben")
                      //  var x = R.drawable.icon_save.toString()

                        var z = note.playerBildLocation
                   //     var zx = note.playerBildLocation.toString()


                      //  var xs : Int = z.toInt()
                        val painter = painterResource(z.toInt()) ?: painterResource(R.drawable.ic_launcher_foreground)
              //          var paint = painterResource(id = xs)
                        Image(
                        //    bitmap = note.playerBitmap.asImageBitmap(),
                           //  painterResource(z.toInt()),
                        //   painterResource(z.toInt()),
                            painter = painter,
                           // painterResource(i),
                            contentDescription = null,
                            modifier = Modifier
                                .fillMaxWidth()

                        )

    */
                    }
                    Row(
                        modifier = Modifier
                            .height(55.dp)
                            .fillMaxSize()
                    ) {
                        //  var x: Int = note.playerBildLocation

                        Image(
                            //    bitmap = note.playerBitmap.asImageBitmap(),
                            painterResource(R.drawable.icon_delete),
                            // painterResource(i),
                            contentDescription = null,
                            modifier = Modifier
                                .fillMaxWidth()
                                .fillMaxSize()
                                .clickable {
                                    playerViewModel.deletePlayer(note)
                                },
                        )


                    }

                }

            }
        }
    }
}






fun String.makeimportant(): String {

    return "$this!!!"

}


