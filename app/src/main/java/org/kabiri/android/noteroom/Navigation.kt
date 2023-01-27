package org.kabiri.android.noteroom

import GameOver
import android.app.Activity
import android.content.Context
import android.content.ContextWrapper
import android.content.Intent
import android.content.pm.ActivityInfo
import android.net.Uri
import androidx.compose.runtime.*
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import org.kabiri.android.noteroom.viewmodel.PlayerViewModel


@Composable
fun Navigation(playerViewModel : PlayerViewModel) {

    val navController = rememberNavController()
    val sharedViewModel: SharedViewModel = viewModel()
    exampleGame(sharedViewModel)

    NavHost(navController = navController, startDestination = Screen.GameSettingScreen.route) {

        composable(route = Screen.ShowAllPlayer.route) {
            Screen()
            ShowAllPlayers(navController,sharedViewModel,playerViewModel, isFirst = true)

        }
        composable(route = Screen.ShowAllPlayer2.route) {
            Screen()
            ShowAllPlayers(navController,sharedViewModel,playerViewModel, isFirst = false)

        }

        composable(route = Screen.GameScreen.route) {
            Screen()
            GameScreenTopBar(navController,sharedViewModel )

        }
        composable(route = Screen.GameSettingScreen.route){
            Screen()
            Settings(navController = navController, sharedViewModel = sharedViewModel,playerViewModel)
        }

        composable(route = Screen.GameOver.route){
            Screen()
            GameOver(navController = navController, sharedViewModel = sharedViewModel)
        }

        composable(route = Screen.LinkedIn.route){
            val context = LocalContext.current
            val intent = remember { Intent(Intent.ACTION_VIEW, Uri.parse("https://www.linkedin.com/in/ben-riegel-220b33173/")) }
            context.startActivity(intent)
        }

        composable(route = Screen.Twitter.route){
            val context = LocalContext.current
            val intent = remember { Intent(Intent.ACTION_VIEW, Uri.parse("https://twitter.com/DrSchokoriegel")) }
            context.startActivity(intent)
        }

        composable(route = Screen.Github.route){
            val context = LocalContext.current
            val intent = remember { Intent(Intent.ACTION_VIEW, Uri.parse("https://github.com/BenSchokoRiegel?tab=overview&from=2023-01-01&to=2023-01-23")) }
            context.startActivity(intent)
        }
//        composable(route = Screen.CreateNote.route) {
//            CreateNote(navController)
//        }

    }
}



// an Example app to test the Composable for profil and Scrore
fun exampleGame(sharedViewModel: SharedViewModel){
    //sharedViewModel.updatePlayer1(Player("Ben Jasper Riegel", Level.Pro,R.drawable.profil_ben))
  //  sharedViewModel.updatePlayer1(Player("Harold Hide The Pain",Level.Rookie,R.drawable.profil_harold))
  //  sharedViewModel.updatePlayer2(Player("Marina Doronina",Level.Rookie,R.drawable.profil_marina))
    //sharedViewModel.updatePlayer2(Player("Matt", Level.Athlete,R.drawable.profil_matt))
    sharedViewModel.updatePlayer1(Player("Harold Hide The Pain",Level.Rookie,R.drawable.profil_harold))
    sharedViewModel.updatePlayer2(Player("Marina Doronina",Level.Rookie,R.drawable.profil_marina))
}


// Quelle : https://stackoverflow.com/questions/69230049/how-to-force-orientation-for-some-screens-in-jetpack-compose

@Composable
fun LockScreenOrientation(orientation: Int) {
    val context = LocalContext.current
    DisposableEffect(Unit) {
        val activity = context.findActivity() ?: return@DisposableEffect onDispose {}
        val originalOrientation = activity.requestedOrientation
        activity.requestedOrientation = orientation
        onDispose {
            // restore original orientation when view disappears
            activity.requestedOrientation = originalOrientation
        }
    }
}
// Quelle : https://stackoverflow.com/questions/69230049/how-to-force-orientation-for-some-screens-in-jetpack-compose
fun Context.findActivity(): Activity? = when (this) {
    is Activity -> this
    is ContextWrapper -> baseContext.findActivity()
    else -> null
}
// Inspiriert by : https://stackoverflow.com/questions/69230049/how-to-force-orientation-for-some-screens-in-jetpack-compose
@Composable
fun Screen() {
    LockScreenOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT)
}

