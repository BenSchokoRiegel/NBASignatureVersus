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
    val txtState = rememberSaveable { mutableStateOf("") }

    var pl: MutableState<PlayerEntity?> = rememberSaveable { mutableStateOf(null) }


    val noteIdState: MutableState<Long?> = rememberSaveable { mutableStateOf(null) }
    val popupState =
        rememberSaveable { mutableStateOf(org.kabiri.android.noteroom.PopupState.Close) }
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
                      //  var x: Int = note.playerBildLocation
                        var a: Int = R.drawable.profil_ben
                        var b = R.drawable.profil_ben.toString()
                        var i = Drawable.createFromPath("R.drawable.profil_ben")
                      //  var x = R.drawable.icon_save.toString()
                        var z = note.playerBildLocation.toString()
                        var xs : Int = z.toInt()
                        Image(
                        //    bitmap = note.playerBitmap.asImageBitmap(),
                             painterResource(z.toInt()),
                           // painterResource(i),
                            contentDescription = null,
                            modifier = Modifier
                                .fillMaxWidth()

                        )


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
                                .fillMaxSize().clickable {
                                    playerViewModel.deletePlayer(note)
                                },
                        )


                    }

                }

            }
        }
    }
}




@OptIn(ExperimentalFoundationApi::class, ExperimentalFoundationApi::class)
@Composable
fun Rasterlayout(navController: NavController,
                 sharedViewModel: SharedViewModel,
                 playerViewModel: PlayerViewModel, isFirst: Boolean) {
    val txtState = rememberSaveable { mutableStateOf("") }

    var pl : MutableState<PlayerEntity?> = rememberSaveable { mutableStateOf(null) }


    val noteIdState: MutableState<Long?> = rememberSaveable { mutableStateOf(null) }
    val popupState = rememberSaveable { mutableStateOf(org.kabiri.android.noteroom.PopupState.Close) }
    val noteListState = playerViewModel.noteListFlow.collectAsState(initial = listOf())
    LazyVerticalGrid(
        cells = GridCells.Fixed(3),  Modifier.padding(10.dp),verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp),

        )

    {
        items(noteListState.value.size) { index ->
                val note = noteListState.value[index]

            var background = topAndBottomColor;



            Card(
                modifier = Modifier.clickable {
                 //   popupState.value = org.kabiri.android.noteroom.PopupState.Open
                    //noteIdState.value = note.roomId
                 //   pl.value = noteListState.value[index]
                    if (isFirst){
                        sharedViewModel.player1.name = note.playername
                        sharedViewModel.player1.level = createLevel(note.playerLevel)
                    } else {
                        sharedViewModel.player2.name = note.playername
                        sharedViewModel.player2.level = createLevel(note.playerLevel)
                    }

                    navController.navigate(Screen.GameSettingScreen.route)

                }
            ) {
                Column(
                    modifier = Modifier
                        .background(background)
                        .fillMaxHeight()
                        .fillMaxWidth()
                ) {

                    Row() {
                        Text(note.roomId.toString(),
                            fontWeight = FontWeight.Bold,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis)

                    }
                    Row() {
                        Text(note.playername,
                            fontWeight = FontWeight.Bold,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis)

                    }
                    Row() {
                        Text(
                            note.playerLevel,
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

                            Image(

                               // painterResource(id = note.playerBildLocation),
                                painterResource(id = R.drawable.icon_settings),
                                contentDescription = null,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .fillMaxSize(),
                            )


                    }

                }

            }
        }
    }
    when (popupState.value) {
        org.kabiri.android.noteroom.PopupState.Open -> {
            NotePopup2(
                onClickDismiss = {
                    popupState.value = org.kabiri.android.noteroom.PopupState.Close
                    pl.value?.let { playerViewModel.deletePlayer(it) }

                },
                onClickSave = {
                    if (isFirst) {
                        /*sharedViewModel.player1.name = pl.value!!.playername
                        sharedViewModel.player1.level = createLevel(pl.value!!.playername)
                        sharedViewModel.player1.picture = pl.value!!.playerBildLocation*/
                        //sharedViewModel.player1.name = "thomas"
                      //  sharedViewModel.player1.level = createLevel("Pro")
                      //  sharedViewModel.player1.picture = R.drawable.icon_settings
                      //  goaway(navController)

                    } else{
                        sharedViewModel.player2.name = pl.value!!.playername
                        sharedViewModel.player2.level = createLevel(pl.value!!.playername)
                       // sharedViewModel.player2.picture = pl.value!!.
                    }
                    popupState.value = org.kabiri.android.noteroom.PopupState.Close
                 //  navController.navigate(Screen.GameSettingScreen.route)


                }
            )
        }
        org.kabiri.android.noteroom.PopupState.Edit -> {
            NotePopup2(
                txtState = txtState,
                onClickDismiss = {
         //           popupState.value = org.kabiri.android.noteroom.PopupState.Close
                    popupState.value = org.kabiri.android.noteroom.PopupState.Close
                },
                onClickSave = {
     //               playerViewModel.deletePlayer(player = PlayerEntity(noteIdState.value,"","",2))
                 /*   homeViewModel.updateNote(
                        note = NoteEntity(
                            roomId = noteIdState.value,
                            text = it
                        )
                    )
                    */

                    popupState.value = org.kabiri.android.noteroom.PopupState.Close
                }
            )
        }
        org.kabiri.android.noteroom.PopupState.Close -> {
        }
    }

}


fun String.makeimportant(): String {

    return "$this!!!"

}


/**
 *
 * Copyright © 2022 Ali Kabiri
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software
 * and associated documentation files (the “Software”), to deal in the Software without restriction,
 * including without limitation the rights to use, copy, modify, merge, publish, distribute,
 * sublicense, and/or sell copies of the Software, and to permit persons to whom the Software
 * is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or
 * substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED “AS IS”, WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED,
 * INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR
 * PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE
 * FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE,
 * ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

private enum class PopupState {
    Open, Close, Edit
}

@Composable
fun NotePopup2(
    txtState: MutableState<String> = rememberSaveable { mutableStateOf("")},
    onClickSave: (String) -> Unit,
    onClickDismiss: () -> Unit,
) {
    Dialog(onDismissRequest = onClickDismiss) {
        Column(
            modifier = Modifier
                .clip(RoundedCornerShape(8.dp))
                .background(color = MaterialTheme.colorScheme.background)
        ) {
            BasicTextField(
                modifier = Modifier
                    .padding(start = 16.dp, end = 16.dp, top = 24.dp),
                value = txtState.value,
                onValueChange = { txt: String ->
                    txtState.value = txt
                },
            )

            Row(
                modifier = Modifier.padding(start = 16.dp, end = 16.dp, bottom = 16.dp, top = 16.dp)
            ) {
                androidx.compose.material3.Button(onClick = onClickDismiss) {
                    androidx.compose.material3.Text(text = stringResource(id = R.string.screen_home_popup_button_dismiss))
                }
                Spacer(modifier = Modifier.width(8.dp))
                androidx.compose.material3.Button(onClick = { onClickSave(txtState.value) }) {
                    androidx.compose.material3.Text(text = stringResource(id = R.string.screen_home_popup_button_save))
                }
            }
        }
    }
}
