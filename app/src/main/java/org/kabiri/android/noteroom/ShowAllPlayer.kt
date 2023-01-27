package org.kabiri.android.noteroom

import androidx.compose.runtime.*
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavController
import org.kabiri.android.noteroom.ui.theme.NoteRoomTheme
import org.kabiri.android.noteroom.viewmodel.PlayerViewModel




import android.content.ClipData
import android.graphics.drawable.Drawable
import android.graphics.fonts.Font
import androidx.compose.ui.graphics.Color
import android.os.Bundle
import android.text.style.StyleSpan
import android.text.SpannableString
import android.text.TextDirectionHeuristic

import android.view.RoundedCorner
import android.widget.TextView
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid


import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.Role.Companion.Image
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight

import androidx.compose.ui.text.font.Typeface
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import org.kabiri.android.noteroom.model.NoteEntity
import org.kabiri.android.noteroom.model.PlayerEntity
import org.kabiri.android.noteroom.ui.home.NotePopup



import java.util.Locale.filter
import kotlin.random.Random
import kotlin.random.Random.Default.nextInt

@Composable
fun ShowAllPlayers(navController: NavController,
                   sharedViewModel: SharedViewModel,
                   playerViewModel: PlayerViewModel,
                   isFirst : Boolean
) {

    val noteListState = playerViewModel.noteListFlow.collectAsState(initial = listOf())
    val context = LocalContext.current


    //val playerViewModel: PlayerViewModel = viewModel(factory = PlayerViewModelFactory(context.applicationContext as Application) )

    NoteRoomTheme(darkTheme = false) {
        // A surface container using the 'background' color from the theme
        androidx.compose.material.Scaffold(
            topBar = { Topbar(topAndBottomColor, " Choose Player", navController = navController) },
            bottomBar = {
                if (!sharedViewModel.gameScore.hasStarted) {
                    BottomNavigation(
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
                    BottomNavigation(
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


