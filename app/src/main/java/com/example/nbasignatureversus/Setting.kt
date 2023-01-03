package com.example.nbasignatureversus

import android.content.ClipData
import android.content.ClipData.Item
import android.graphics.drawable.Icon
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.MagnifierStyle.Companion.Default
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*


import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Share
import androidx.compose.runtime.*

import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.TextStyle.Companion.Default
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.nbasignatureversus.ui.theme.NBASIgnatureVersusTheme


val topAndBottomColor: Color = Color.Red

@Composable
fun Settings(navController: NavController, sharedViewModel: SharedViewModel) {


    NBASIgnatureVersusTheme(darkTheme = false) {
        // A surface container using the 'background' color from the theme
        Scaffold(
            topBar = { Topbar("#db711e".color, " Settings ") },
            bottomBar = {
                BottomNavigation(
                    navController,
                    items =
                    listOf(
                        IconsForGame(R.drawable.icon_home, "Home"),
                        IconsForGame(R.drawable.icon_start, "Start"),
                        IconsForGame(R.drawable.icon_save, "Save"),

                        )
                )
            }
            //bottomBar = { BottomAppBar(backgroundColor = Color.Red, modifier = Modifier.fillMaxHeight()) { Text("Click FloatingAction to add Random elements ") } },
            // floatingActionButton = {
            //FloatingActionButton(onClick = { navController.navigate(Screen.CreateNote.route) }

        ) {

            //Preview_MultipleRadioButtons()

            RadioButtonSample()
            //GameSettings()
            //GameScreen(navController = navController, sharedViewModel = sharedViewModel)


        }
    }


}


@Composable
fun GameSettings(sharedViewModel: SharedViewModel) {

    Column() {
        Row() { HeaderSetting("Game Settings") }
        Row(modifier = Modifier.padding(/*30.dp,10.dp*/)) {
            Text(
                text = "Score To Win",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
            )
            RadioButtonSample(list = listOf(
                RadioButtonInfo("ScoreToWin","7"),
                RadioButtonInfo("ScoreToWin","11"),
                RadioButtonInfo("ScoreToWin","15"),
                RadioButtonInfo("scoreToWin","21")



            )
                ,sharedViewModel)


        }
    }
}


class RadioButtonInfo(val name: String, val function: String) {}


@Composable
fun RadioButtonSample(list: List<RadioButtonInfo>, sharedViewModel: SharedViewModel) {
    var currentSavedModel: Int
    // for the default button
    if (list[0].function == "ScoreToWin") {
        currentSavedModel = 0;
        for (i in 0..list.size) {
            if (Integer.decode(list[i].name) == sharedViewModel.gameScore.maxScore) {
                currentSavedModel = i
                break
            }
        }

    } else if (list[0].function == "PlayerOneLevel") {
        currentSavedModel = 0;
        for (i in 0..list.size) {
            if (createLevel( list[i].name) == sharedViewModel.player1.level) {
                currentSavedModel = i
                break
            }
        }

    } else if (list[0].function == "PlayerTwoLevel") {
        currentSavedModel = 0;
        for (i in 0..list.size) {
            if (createLevel( list[i].name) == sharedViewModel.player2.level) {
                currentSavedModel = i
                break
            }
        }

    } else {
        currentSavedModel = 3
    }


    val (selectedOption, onOptionSelected) = remember { mutableStateOf(list[currentSavedModel]) }

    // Inspiered by https://foso.github.io/Jetpack-Compose-Playground/material/radiobutton/
    Row {
        list.forEach { item ->
            Column(
                Modifier
                    //.fillMaxWidth()
                    .selectable(
                        selected = (item == selectedOption),
                        onClick = {
                            onOptionSelected(item)
                        }
                    )
                //.padding(horizontal = 16.dp)
            ) {
                Text(
                    text = item.name,
                    fontWeight = FontWeight.Bold,
                    fontSize = 22.sp,
                    style = MaterialTheme.typography.body1.merge(),
                    modifier = Modifier.padding(start = 16.dp)
                )
                RadioButton(
                    selected = (item == selectedOption),
                    onClick = {
                        onOptionSelected(item)
                        if (item.function == "ScoreToWin") {
                            sharedViewModel.gameScore.maxScore = Integer.decode(item.name)
                        } else if (item.function == "PlayerOneLevel"){
                            sharedViewModel.player1.level = createLevel(item.name)
                        } else if (item.function == "PlayerTwoLevel"){
                            sharedViewModel.player2.level = createLevel(item.name)
                        }

                    }

                )

            }
        }
    }
}





@Composable
fun SegmentedControl(items: List<String>, defaultSelectedItemIndex: Int, any: Any) {

}


@Composable
fun Topbar(backgroundColor: Color, topBarText: String) {

    val textStyleBody1 = MaterialTheme.typography.body1
    var textStyle by remember { mutableStateOf(textStyleBody1) }
    var readyToDraw by remember { mutableStateOf(false) }


    TopAppBar(
        //modifier = Modifier.padding(4.dp, 4.dp),
        backgroundColor = backgroundColor,
        navigationIcon = {
            IconButton(onClick = {}) {
                Icon(Icons.Default.Info, "Info")
            }
        },
        actions = {

            //Icon(Icons.Default.Info, "Info")
            //Icon(imageVector = ImageVector., "Info")
            Row(
                Modifier.padding(10.dp, 10.dp, 0.dp, 10.dp),
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {


                androidx.compose.material.Icon(
                    painter = painterResource(id = R.drawable.twitter),
                    contentDescription = null,
                    modifier = Modifier
                        .alpha(1f)
                        .clickable { },
                    tint = Color.Black
                )

                androidx.compose.material.Icon(
                    painter = painterResource(id = R.drawable.github_mark),
                    contentDescription = null,
                    modifier = Modifier
                        .alpha(1f)
                        .clickable { },
                    tint = Color.Black
                )
                androidx.compose.material.Icon(
                    painter = painterResource(id = R.drawable.linkedin),
                    contentDescription = null,
                    modifier = Modifier
                        .alpha(1f)
                        .clickable { },
                    tint = Color.Black
                )
            }
        },

        title = {
            // border = "#D8B09C".color
            // s C75F2A
            // E3E2E2
            /*Text(text = "NBA Signature Versus ",
                style = textStyle,
                maxLines = 1,
                softWrap = false,
                Modifier.background(color = "#E3E2E2".color, shape = RoundedCornerShape(10.dp))
                    .border(1.dp, "#D8B09C".color, shape = RoundedCornerShape(10.dp))
                    .fillMaxWidth().drawWithContent{
                        if (readyToDraw) drawContent()
                    },
                onTextLayout = { textLayoutResult ->
                    if (textLayoutResult.didOverflowHeight) {
                        textStyle = textStyle.copy(fontSize = textStyle.fontSize * 0.9)
                    } else {
                        readyToDraw = true
                    }
                }
            )*/
            // Inspriert durch https://stackoverflow.com/questions/63971569/androidautosizetexttype-in-jetpack-compose
            Text(
                text = topBarText,
                style = textStyle,
                maxLines = 1,
                softWrap = false,
                fontSize = 20.sp,

                color = "#C75F2A".color,
                modifier = Modifier
                    .background(color = "#E3E2E2".color, shape = RoundedCornerShape(10.dp))
                    // .fillMaxWidth()
                    //.fillMaxHeight(0.75f)
                    .border(2.dp, "#D8B09C".color, shape = RoundedCornerShape(10.dp))
                    .drawWithContent {
                        if (readyToDraw) drawContent()
                    },
                onTextLayout = { textLayoutResult ->
                    if (textLayoutResult.didOverflowWidth) {
                        textStyle = textStyle.copy(fontSize = textStyle.fontSize * 0.9)
                    } else {
                        readyToDraw = true
                    }
                }
            )
            /*Image(
                painter = painterResource(id = R.drawable.header),
                contentDescription = null, modifier = Modifier.fillMaxSize()
                    )*/
        }

    )
}


@Composable
fun HeaderSetting(headText: String) {
    Text(
        text = headText,
        fontSize = 25.sp,
        fontWeight = FontWeight.Bold,
        textAlign = TextAlign.Center,
        style = TextStyle(textDecoration = TextDecoration.Underline),
        modifier = Modifier
            .fillMaxWidth()
            .alpha(1f)
            .padding(defaultPadding)

    )


}

@Composable
fun BottomBar() {
    BottomAppBar(
        contentColor = topAndBottomColor,
        content = {

        }
    )
}

// Inspieriert durch https://medium.com/geekculture/bottom-navigation-in-jetpack-compose-android-9cd232a8b16
@Composable
fun BottomNavigation(navController: NavController, items: List<IconsForGame>) {
    androidx.compose.material.BottomNavigation(
        backgroundColor = Color.White,
        contentColor = Color.Black,
        modifier = Modifier
            .fillMaxHeight(0.2f)
            .padding(10.dp)
    ) {
        items.forEach { item ->
            BottomNavigationItem(
                icon = {
                    Icon(
                        painterResource(id = item.location),
                        contentDescription = item.function
                    )
                },
//                label = {
//                    Text(
//                        text = item.function,
//                        fontSize = 9.sp
//                    )
//                },
                selectedContentColor = Color.Black,
                unselectedContentColor = Color.Black.copy(0.4f),
                alwaysShowLabel = true,
                selected = true,
                onClick = {
                    /* TODO */
                },
                modifier = Modifier.padding(20.dp)
            )
        }
    }


}




