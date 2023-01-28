package org.kabiri.android.noteroom


import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.unit.toSize

import androidx.navigation.NavController
import org.kabiri.android.noteroom.model.NoteEntity
import org.kabiri.android.noteroom.model.PlayerEntity

import org.kabiri.android.noteroom.ui.theme.NoteRoomTheme
import org.kabiri.android.noteroom.viewmodel.PlayerViewModel


val topAndBottomColor: Color = "#db711e".color

val playerPadding = 10.dp


//private lateinit var dataStore : DataStore<Preferences>



@Composable
fun Settings(
    navController: NavController,
    sharedViewModel: SharedViewModel,
    playerViewModel: PlayerViewModel
) {

    val noteListState = playerViewModel.noteListFlow.collectAsState(initial = listOf())
    val context = LocalContext.current
    //val playerViewModel: PlayerViewModel = viewModel(factory = PlayerViewModelFactory(context.applicationContext as Application) )

    NoteRoomTheme(darkTheme = false) {
        // A surface container using the 'background' color from the theme
        androidx.compose.material.Scaffold(
            topBar = { Topbar(topAndBottomColor, " Settings ", navController = navController) },
            bottomBar = {
                if (!sharedViewModel.gameScore.hasStarted) {
                    BottomNavigation(
                        navController,
                        items =
                        listOf(
                            IconsForGame(R.drawable.icon_home, "Home"),
                            IconsForGame(R.drawable.icon_start, "New"),
                            //IconsForGame(R.drawable.icon_reset, "Reset"),
                          //  IconsForGame(R.drawable.icon_save, "Save"),

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
                          //  IconsForGame(R.drawable.icon_save, "Save"),
                        ), sharedViewModel
                    )
                }


            }
            //bottomBar = { BottomAppBar(backgroundColor = Color.Red, modifier = Modifier.fillMaxHeight()) { Text("Click FloatingAction to add Random elements ") } },
            // floatingActionButton = {
            //FloatingActionButton(onClick = { navController.navigate(Screen.CreateNote.route) }

        ) {
            Column(
                Modifier
                    .fillMaxSize()
                    .border(4.dp, Color.Black)
            ) {
                GameSettings(sharedViewModel)
                PlayerSettings(sharedViewModel ,playerViewModel,navController )
            }

        }
    }


}





@Composable
fun PlayerSettings(sharedViewModel: SharedViewModel,  player_view_Model: PlayerViewModel , navController: NavController) {
    Row (
        Modifier
            .border(2.dp, Color.Black)
            .fillMaxWidth()
            .fillMaxHeight()){
        Column(
            Modifier
                .fillMaxHeight()
                .fillMaxWidth(0.5f)
                .border(2.dp, Color.Black)) {
            HeaderSetting(headText = "Player 1")
            Player(sharedViewModel.player1 ,player_view_Model , true,navController)
        }
        Column(
            Modifier
                .fillMaxHeight()
                .border(2.dp, Color.Black)) {
            HeaderSetting(headText = "Player 2")
            Player(sharedViewModel.player2,player_view_Model,false,navController )
        }

    }
}



@Composable
fun save_ordner(){
    Icon(
        painter = painterResource(id = R.drawable.folder),
        contentDescription = null,
        modifier = Modifier
            .alpha(1f)
            .width(75.dp)
            .height(75.dp)
            .padding(10.dp)
            .clickable {
                // navController.navigate(Screen.Twitter.route)
            },
        tint = Color.Black
    )
}


// TODO ENTER -> Stop typing
@Composable
fun Player(player: Player, player_view_Model: PlayerViewModel,isFirst:Boolean,navController: NavController){
    Text( "--------------------------------------------------", maxLines = 1)
    var x = LocalContext.current
    var name  by remember { mutableStateOf(TextFieldValue(player.name))}

    Row(Modifier.padding(20.dp,2.dp)){
        //Text("Name : ",fontWeight = FontWeight.Bold )
        TextField(
            value = name,
            onValueChange = { it ->
                name = it
                player.name = name.text
            },
            label = { Text(text = "Your Name") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
            placeholder = { Text(text = "Please Write your name") },
            maxLines = 1,
            singleLine = true
        )


    }
    LevelSetting(player)

    Column(
        horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.fillMaxWidth()){
        Image(
            modifier = Modifier
                .clip(CircleShape)
                //.padding(10.dp, 2.dp
                //.fillMaxWidth()
                .size(75.dp)
                .clickable {
                           player.picture = getOneIcon()
                    navController.navigate(Screen.GameSettingScreen.route)
                },

            painter = painterResource(id = player.picture),
            contentDescription = "Click to Change",

            // contentScale = ContentScale.FillHeight
        )
        Text(text = "Click to change Image")


    }
    Row(modifier = Modifier.padding(10.dp)){
        Icon(
            painter = painterResource(id = R.drawable.icon_save),
            contentDescription = null,
            modifier = Modifier
                .alpha(1f)
                .width(75.dp)
                .height(75.dp)
                .padding(10.dp)
                .clickable {
                  //  player_view_Model.addPlayer(player = PlayerEntity( playername = player.name, playerLevel = player.level.toString(), playerBitmap = getBitmapFromImage(x,player.picture)))
                    player_view_Model.addPlayer(player = PlayerEntity( playername = player.name, playerLevel = player.level.toString(), playerBildLocation = player.picture.toString()))
                    // player_view_Model.addPlayer(PlayerInDB(player.name,player.level.name,player.picture))
                },
            tint = Color.Black
        )


        Icon(
            painter = painterResource(id = R.drawable.icon_pr),
            contentDescription = null,
            modifier = Modifier
                .alpha(1f)
                .width(75.dp)
                .height(75.dp)
                .padding(10.dp)
                .clickable {
                    if (isFirst){
                        navController.navigate(Screen.ShowAllPlayer.route)
                    } else {
                        navController.navigate(Screen.ShowAllPlayer2.route)
                    }
                },
            tint = Color.Black
        )




    }

}


// Inspiriert durch https://www.geeksforgeeks.org/drop-down-menu-in-android-using-jetpack-compose/
@Composable
fun LevelSetting(player:Player){

    // Declaring a boolean value to store
    // the expanded state of the Text Field
    var mExpanded by remember { mutableStateOf(false) }

    // Create a list of cities
    //val mCities = listOf("Delhi", "Mumbai", "Chennai", "Kolkata", "Hyderabad", "Bengaluru", "Pune")
    var allLevel = giveStringLevel()
    //val mCities = listOf(Level.values()[0].toString())

    // Create a string value to store the selected city
    var mSelectedText by remember { mutableStateOf(player.level.name) }

    var mTextFieldSize by remember { mutableStateOf(Size.Zero)}

    // Up Icon when expanded and down icon when collapsed
    val icon = if (mExpanded)
        Icons.Filled.KeyboardArrowUp
    else
        Icons.Filled.KeyboardArrowDown

    Column(Modifier.padding(20.dp,5.dp)) {

        // Create an Outlined Text Field
        // with icon and not expanded
        OutlinedTextField(
            value = mSelectedText,
            onValueChange = {/* mSelectedText = it */},
            modifier = Modifier
                .fillMaxWidth()
                .onGloballyPositioned { coordinates ->
                    // This value is used to assign to
                    // the DropDown the same width
                    mTextFieldSize = coordinates.size.toSize()
                },

            label = {Text("Level")},
            trailingIcon = {
                Icon(icon,"contentDescription",
                    Modifier.clickable { mExpanded = !mExpanded })
            }
        )

        // Create a drop-down menu with list of cities,
        // when clicked, set the Text Field text as the city selected
        DropdownMenu(
            expanded = mExpanded,
            onDismissRequest = { mExpanded = false },
            modifier = Modifier
                .width(with(LocalDensity.current){mTextFieldSize.width.toDp()})
        ) {
            allLevel.forEach { level ->
                DropdownMenuItem(onClick = {
                    mSelectedText = level
                    player.level = createLevel(level)
                    mExpanded = false
                }) {
                    Text(text = level)
                }
            }
        }

    }
}

@Composable
fun GameSettings(sharedViewModel: SharedViewModel) {

    Column(
        Modifier
            .border(2.dp, Color.Black)
            .fillMaxWidth()

    ) {
        Row() {

            if (!sharedViewModel.gameScore.hasStarted) {
                HeaderSetting("Game Settings")
            } else {
                HeaderSetting("Pause" )
            }
        }
        if (sharedViewModel.gameScore.hasStarted) {
            Row(Modifier.padding(horizontal = 10.dp)) {
                Text(text = "Current Score: \t" + sharedViewModel.gameScore.player1_score + " : " + sharedViewModel.gameScore.player2_score,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center,
                )
            }
        }
        var mod = Modifier.padding(10.dp, 5.dp)
        Row(modifier = mod) {
            Text(
                text = "Score To Win:",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
            )


            RadioButtonSample(
                list = listOf(
                    RadioButtonInfo("7", "ScoreToWin"),
                    RadioButtonInfo("11", "ScoreToWin"),
                    RadioButtonInfo("15", "ScoreToWin"),
                    RadioButtonInfo("21", "ScoreToWin")
                ), sharedViewModel
            )



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
            //  print(Integer.decode(list[i].name))
            if (Integer.decode(list[i].name) == sharedViewModel.gameScore.maxScore) {
                currentSavedModel = i
                break
            }
        }

    } else if (list[0].function == "PlayerOneLevel") {
        currentSavedModel = 0;
        for (i in 0..list.size) {
            if (createLevel(list[i].name) == sharedViewModel.player1.level) {
                currentSavedModel = i
                break
            }
        }

    } else if (list[0].function == "PlayerTwoLevel") {
        currentSavedModel = 0;
        for (i in 0..list.size) {
            if (createLevel(list[i].name) == sharedViewModel.player2.level) {
                currentSavedModel = i
                break
            }
        }

    } else {
        currentSavedModel = 3
    }


    val (selectedOption, onOptionSelected) = remember { mutableStateOf(list[currentSavedModel]) }

    // inspiriert by https://foso.github.io/Jetpack-Compose-Playground/material/radiobutton/
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
                    text = safe7(item.name),
                    fontSize = 22.sp,
                    style = MaterialTheme.typography.body1.merge(),
                    modifier = Modifier.padding(start = 13.dp, top = 0.dp, end = 0.dp,0.dp)
                )
                RadioButton(
                    selected = (item == selectedOption),
                    onClick = {
                        onOptionSelected(item)
                        if (item.function == "ScoreToWin") {
                            sharedViewModel.gameScore.maxScore = Integer.decode(item.name)
                        } else if (item.function == "PlayerOneLevel") {
                            sharedViewModel.player1.level = createLevel(item.name)
                        } else if (item.function == "PlayerTwoLevel") {
                            sharedViewModel.player2.level = createLevel(item.name)
                        }

                    }

                )

            }
        }
    }
}

fun safe7(name: String): String {
    if (name == "7") {
        return "07"
    } else {
        return name
    }
}


@Composable
fun SegmentedControl(items: List<String>, defaultSelectedItemIndex: Int, any: Any) {

}


@Composable
fun Topbar(backgroundColor: Color, topBarText: String,navController: NavController) {

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


                Icon(
                    painter = painterResource(id = R.drawable.twitter),
                    contentDescription = null,
                    modifier = Modifier
                        .alpha(1f)
                        .clickable {
                            navController.navigate(Screen.Twitter.route)
                        },
                    tint = Color.Black
                )

                Icon(
                    painter = painterResource(id = R.drawable.github_mark),
                    contentDescription = null,
                    modifier = Modifier
                        .alpha(1f)
                        .clickable {
                            navController.navigate(Screen.Github.route)

                        },
                    tint = Color.Black
                )
                Icon(
                    painter = painterResource(id = R.drawable.linkedin),
                    contentDescription = null,
                    modifier = Modifier
                        .alpha(1f)
                        .clickable {
                            navController.navigate(Screen.LinkedIn.route)

                        },
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






/**
// Inspiration by https://handyopinion.com/show-alert-dialog-with-an-input-field-edittext-in-android-kotlin/
fun showdialog(){
val builder: AlertDialog.Builder = android.app.AlertDialog.Builder(this)
builder.setTitle("Title")

// Set up the input
val input = EditText(this)
// Specify the type of input expected; this, for example, sets the input as a password, and will mask the text
input.setHint("Enter Text")
input.inputType = InputType.TYPE_CLASS_TEXT
builder.setView(input)

// Set up the buttons
builder.setPositiveButton("OK", DialogInterface.OnClickListener { dialog, which ->
// Here you get get input text from the Edittext
var m_Text = input.text.toString()
})
builder.setNegativeButton("Cancel", DialogInterface.OnClickListener { dialog, which -> dialog.cancel() })

builder.show()
}
 */