@file:OptIn(ExperimentalMaterial3Api::class)
@file:Suppress("OPT_IN_IS_NOT_ENABLED")

package org.kabiri.android.noteroom.ui.home

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import org.kabiri.android.noteroom.R
import org.kabiri.android.noteroom.model.NoteEntity
import org.kabiri.android.noteroom.viewmodel.HomeViewModelAbstract
import org.kabiri.android.noteroom.viewmodel.PlayerViewModel

@Composable
fun testDataBase(playerViewModel: PlayerViewModel) {
    val noteListState = playerViewModel.noteListFlow.collectAsState(initial = listOf())


    Scaffold {
        LazyColumn {
            items(noteListState.value.size) { index ->
                val note = noteListState.value[index]
                Box(modifier = Modifier
                    .fillMaxWidth()
                    .clickable {

                    }
                    .pointerInput(Unit) {
                        detectTapGestures(
                            onLongPress = {
                                // delete the note on long click

                            }
                        )
                    }
                    .height(54.dp)
                ) {
                    Text(
                        modifier = Modifier
                            .align(Alignment.CenterStart)
                            .padding(start = 16.dp, end = 16.dp),
                        text = "id = " + note.roomId.toString() + " "+  note.playername +" "+ note.playerLevel,
                        maxLines = 1,
                    )


                    Spacer(
                        modifier = Modifier
                            .height(0.5.dp)
                            .fillMaxWidth()
                            .background(color = Color.Gray.copy(alpha = 0.54f))
                            .align(Alignment.BottomCenter)
                    )
                }
            }
            item {
                Box(modifier = Modifier.fillMaxWidth()) {
                    Button(
                        modifier = Modifier.align(Alignment.Center),
                        onClick = {

                        }) {
                        Text(text = stringResource(id = R.string.screen_home_button_add_note))
                    }
                }
            }
        }
    }

}