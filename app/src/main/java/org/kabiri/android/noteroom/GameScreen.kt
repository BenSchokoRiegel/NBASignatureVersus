package org.kabiri.android.noteroom

import android.os.Build
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*


import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.*
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.ImageLoader
import coil.compose.rememberAsyncImagePainter
import coil.decode.GifDecoder
import coil.decode.ImageDecoderDecoder
import coil.request.ImageRequest
import coil.size.Size
import org.kabiri.android.noteroom.*
import org.kabiri.android.noteroom.ui.theme.NoteRoomTheme

var defaultPadding = 3.dp
val fontname = FontFamily(Font(R.font.android))
val fon2 = FontFamily(Font(R.font.ankhsanctuary))

val topScreenHide = 10






@Composable
fun GameScreenTopBar(navController: NavController, sharedViewModel: SharedViewModel){
    sharedViewModel.gameScore.hasStarted = true
    NoteRoomTheme(darkTheme = false) {
        // A surface container using the 'background' color from the theme
        Scaffold(
            topBar = { Topbar(sharedViewModel.currentGif.color, " GAME TIME ",navController) },
            // bottomBar = { BottomAppBar(backgroundColor = Color.Red) { Text("Click FloatingAction to add Random elements ") } },
            // floatingActionButton = {
            //FloatingActionButton(onClick = { navController.navigate(Screen.CreateNote.route) }

        ) {
            GameScreen(navController = navController, sharedViewModel = sharedViewModel)
        }
    }


}







@Composable
fun GameScreen(navController: NavController, sharedViewModel: SharedViewModel) {


    Column(
        Modifier
            .background(sharedViewModel.currentGif.color)
            .border(7.dp, Color.Black)
            .fillMaxSize()
    ) {
        TopLayer(
            player1 = sharedViewModel.player1,
            player2 = sharedViewModel.player2,
            gameScore = sharedViewModel.gameScore, color = sharedViewModel.currentGif.color
        )



        Header(sharedViewModel.currentGif.name)
        Row(modifier = Modifier.fillMaxHeight(0.8f)) {
            GifImage(gifLocation = sharedViewModel.currentGif.location)
        }

        createIcons(
            icons =
            listOf(
                IconsForGame(R.drawable.icon_shot_make, "MakeShot"),
                IconsForGame(R.drawable.icon_pause, "Pause"),
                IconsForGame(R.drawable.icon_home, "Home"),
                IconsForGame(R.drawable.icon_miss, "MissShot"),

            ), navController = navController, sharedViewModel = sharedViewModel,
            Modifier
                .background(sharedViewModel.currentGif.color)
               /* .fillMaxSize()*/

            //.border(10.dp,Color.Black)

        )


        /* Button(onClick = {
             print("MakeShoot")
             sharedViewModel.makeShot()
             navController.navigate(Screen.GameScreen.route)
         }) {

         }
         Button(onClick = {
             sharedViewModel.brickShot()
             print("brickShoot")
             navController.navigate(Screen.GameScreen.route)
         }) {
             Text("BrickShoot")
         }*/
    }


}


/*@Composable
fun icon(icon: Int, navController: NavController, sharedViewModel: SharedViewModel) {
    Button(
        onClick = sharedViewModel.makeShot()
                navController . navigate (Screen.GameScreen.route)
    ) {
        Box(
            modifier = Modifier.fillMaxWidth(),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                ImageVector = ImageVector.vectorResource(id = icon)
                        modifier = Modifier
                        . size (18.dp)
                    .align(Alignment.CenterStart),
                contentDescription = "drawable icons",
                tint = Color.Unspecified
            )
        }
    }
}*/







@Composable
fun Header(headText: String) {
    Text(
        text = headText,
        fontSize = 25.sp,
        fontWeight = FontWeight.Bold,
        fontFamily = fon2,
        textAlign = TextAlign.Center,
        style = TextStyle(textDecoration = TextDecoration.Underline),
        modifier = Modifier
            .fillMaxWidth()
            .alpha(1f)
            .padding(defaultPadding)

    )


}

/*
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun Rasterlayout(element :List) {
    LazyVerticalGrid(
        cells = GridCells.Fixed(3), verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp),

        ){



        showPlayer(player = player1, hasBall = gameScore.player1_turn)
        //createScoreBoard(pl1 = gameScore.player1_score, pl2 = gameScore.player2_score, pl1_hasBall = gameScore.player1_turn)
        //showPlayer(player = player2, hasBall = !gameScore.player1_turn)


    }

}*/











@Composable
fun TopLayer(player1: Player, player2: Player, gameScore: GameScore, color: Color) {


    Row(
        modifier = Modifier
            /*.padding(defaultPadding)
          *//*  .border(1.dp, color = Color.Red)*//*
        .padding(defaultPadding)*/
            .border(7.dp, Color.Black)

    ) {

        showPlayer(player = player1, hasBall = gameScore.player1_turn)
        createScoreBoard(
            pl1 = gameScore.player1_score,
            pl2 = gameScore.player2_score,
            pl1_hasBall = gameScore.player1_turn, color
        )
        showPlayer(player = player2, hasBall = !gameScore.player1_turn)
    }


}

@Composable
fun createScoreBoard(pl1: Int, pl2: Int, pl1_hasBall: Boolean, color: Color) {
    val scoreBoardPadding = 10.dp
    val configuration = LocalConfiguration.current
    val maxWi = configuration.screenWidthDp.dp - configuration.screenWidthDp.dp.div(2.8f) * 2
    val maxHi = configuration.screenHeightDp.dp.div(topScreenHide)
    Row(
        modifier = Modifier

            .width(maxWi)
            .height(maxHi)
            .border(1.dp, color = Color.Black)
            .background(Color.Black)
            .padding(scoreBoardPadding)
            .border(1.dp, color = Color.Black)
            .background(color)
        /*.border(1.dp, color = Color.Red)
        .padding(defaultPadding)
        .border(1.dp, color = Color.Transparent)*/
        //.fillMaxSize(0.33f)

    ) {
        Column(
            modifier = Modifier

                .fillMaxWidth()


        ) {
            Spacer(modifier = Modifier.height(6.dp))
            Text(
                text = "SCORE",
                fontWeight = FontWeight.Bold,
                fontFamily = fontname,
                textAlign = TextAlign.Center,
                fontSize = 15.sp,
                maxLines = 1,
                modifier = Modifier
                    .fillMaxWidth()
            )
            //Text(text = "", fontSize = 6.sp)
            Spacer(modifier = Modifier.height(6.dp))
            Text(
                text = "$pl1 : $pl2",
                fontWeight = FontWeight.Bold,
                fontFamily = fon2,
                textAlign = TextAlign.Center,
                fontSize = 25.sp,
                maxLines = 1,
                modifier = Modifier
                    .fillMaxWidth()


            )

            /*if (pl1_hasBall){
                Row(){

                    Text(text = pl1.toString(), fontWeight = FontWeight.Bold, fontFamily = fon2, style = TextStyle(textDecoration = TextDecoration.Underline))
                    Text(text = " : ", fontWeight = FontWeight.Bold, fontFamily = fon2,)
                    Text(text = pl2.toString(), fontWeight = FontWeight.Bold, fontFamily = fon2)
                }
            } else {

                Row(modifier = Modifier.align(alignment = CenterHorizontally)){
                    Text(text = "\t $pl1", fontWeight = FontWeight.Bold, fontFamily = fon2)
                    Text(text = " : ", fontWeight = FontWeight.Bold, fontFamily = fon2)
                    Text(text = pl2.toString(), fontWeight = FontWeight.Bold, fontFamily = fon2,style = TextStyle(textDecoration = TextDecoration.Underline))
                }*/
        }

    }

}


@Composable
fun showPlayer(player: Player, hasBall: Boolean) {

    val configuration = LocalConfiguration.current
    val maxWi = configuration.screenWidthDp.dp.div(2.8f)
    val maxHi = configuration.screenHeightDp.dp.div(topScreenHide)


    var spezMod: Modifier
    if (hasBall) {
        spezMod = Modifier
            .background(Color.Red)
            .width(maxWi)
            .height(maxHi)
            .padding(defaultPadding)
            .border(1.dp, color = Color.Red)
            .background(Color.Red)
            .padding(defaultPadding)
            .border(1.dp, Color.Red)


    } else {
        spezMod = Modifier
            .width(maxWi)
            .height(maxHi)
            .padding(defaultPadding)
            .padding(defaultPadding)/*
            .border(1.dp, color = Color.Red)

            .padding(defaultPadding)
            .border(1.dp, color = Color.Transparent)*/

    }
    Row(
        modifier = spezMod
    ) {
        Image(
            modifier = Modifier
                .padding(defaultPadding)
                .size(50.dp)
                .clip(CircleShape),
            painter = painterResource(id = player.picture),
            contentDescription = "",
            contentScale = ContentScale.FillHeight
        )
        Column(
            modifier = Modifier
                .padding(defaultPadding)
            //  .fillMaxWidth(0.1f)

        ) {
            Text(
                player.name,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                fontWeight = FontWeight.Bold,
            )
            Text(
                player.level.toString(),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                fontWeight = FontWeight.Bold,
            )

        }
    }
}


// bzw data = R.drawable.durant
// Quelle https://stackoverflow.com/questions/60229555/adding-gif-into-jetpack-compose
@Composable
fun GifImage(
    gifLocation: Int,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current
    val imageLoader = ImageLoader.Builder(context)
        .components {
            if (Build.VERSION.SDK_INT >= 28) {
                add(ImageDecoderDecoder.Factory())
            } else {
                add(GifDecoder.Factory())
            }
        }
        .build()
    Image(
        painter = rememberAsyncImagePainter(
            ImageRequest.Builder(context).data(data = gifLocation).apply(block = {
                size(Size.ORIGINAL)
            }).build(), imageLoader = imageLoader
        ),
        contentDescription = null,
        modifier = modifier
            .fillMaxWidth()
            .fillMaxHeight()

    )
}