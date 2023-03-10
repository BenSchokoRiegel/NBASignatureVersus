package com.example.nba_clean_signitature_versus


import Winner_Screen
import android.app.Activity
import android.content.Context
import android.content.ContextWrapper
import android.content.Intent
import android.content.pm.ActivityInfo
import android.net.Uri
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.compose.runtime.*
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.viewinterop.AndroidView
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.nba_clean_signitature_versus.viewmodel.PlayerViewModel


@Composable
fun Navigation(playerViewModel : PlayerViewModel) {

    val navController = rememberNavController()
    val sharedViewModel: SharedViewModel = viewModel()
 //   exampleGame(sharedViewModel)

    NavHost(navController = navController, startDestination = Screen.StartScreen.route) {
  //  NavHost(navController = navController, startDestination = Screen.Winner.route) {

        composable(route = Screen.StartScreen.route) {
            Screen()
            S_Screen(navController,sharedViewModel)

        }
        composable(route = Screen.ShowAllPlayer.route) {
            Screen()
            ShowAllPlayers(navController,sharedViewModel,playerViewModel, isFirst = true)

        }
        composable(route = Screen.ShowAllPlayer2.route) {
            Screen()
            ShowAllPlayers(navController,sharedViewModel,playerViewModel, isFirst = false)

        }

        composable(route = Screen.ChoosePlayer1.route) {
            Screen()
            ChooseProfil(navController,sharedViewModel,playerViewModel, isFirst = true)

        }
        composable(route = Screen.ChoosePlayer2.route) {
            Screen()
            ChooseProfil(navController,sharedViewModel,playerViewModel, isFirst = false)

        }

        composable(route = Screen.GameScreen.route) {
            Screen()
            GameScreenTopBar(navController,sharedViewModel )

        }
        composable(route = Screen.GameSettingScreen.route){
            Screen()
            Settings(navController = navController, sharedViewModel = sharedViewModel,playerViewModel)
        }



        composable(route = Screen.LinkedIn.route){
          //  val context = LocalContext.current
         //   val intent = remember { Intent(Intent.ACTION_VIEW, Uri.parse("https://www.linkedin.com/in/ben-riegel-220b33173/")) }
           // context.startActivity(intent)
            WebV(context = LocalContext.current, url = "https://de.linkedin.com/in/ben-riegel-220b33173")
        }

        composable(route = Screen.Twitter.route){
         //   val context = LocalContext.current
         //   val intent = remember { Intent(Intent.ACTION_VIEW, Uri.parse("https://twitter.com/DrSchokoriegel")) }
       //     context.startActivity(intent)
            WebV(context = LocalContext.current, url = "https://twitter.com/DrSchokoriegel")
        }
        composable(route = Screen.Video.route){
            val context = LocalContext.current
            val intent = remember { Intent(Intent.ACTION_VIEW, Uri.parse("https://youtu.be/ivaNKgt_EUg")) }
            context.startActivity(intent)
      //      WebV(context = LocalContext.current, url = "https://youtu.be/ivaNKgt_EUg")
        }


        composable(route = Screen.Github.route){
         //   val context = LocalContext.current
         //   val intent = remember { Intent(Intent.ACTION_VIEW, Uri.parse("https://github.com/BenSchokoRiegel?tab=overview&from=2023-01-01&to=2023-01-23")) }
          //  context.startActivity(intent)
            WebV(context = LocalContext.current, url = "https://github.com/BenSchokoRiegel")
        }

        composable(route = Screen.Winner.route){
            Screen()
            Winner_Screen(navController = navController, sharedViewModel = sharedViewModel)
        }
//        composable(route = Screen.CreateNote.route) {
//            CreateNote(navController)
//        }

    }
}
// Quelle Gruppe 2
@Composable
fun WebV(context: Context,url:String){
     AndroidView(factory = {
         WebView(context).apply {
             webViewClient = WebViewClient()
             loadUrl(url)
         }

     })
}



// an Example app to test the Composable for profil and Scrore
fun exampleGame(sharedViewModel: SharedViewModel){
  //  sharedViewModel.updatePlayer1(Player("Ben Riegel", Level.Pro,R.drawable.q))
  //  sharedViewModel.updatePlayer1(Player("Harold Hide The Pain",Level.Rookie,R.drawable.profil_harold))
 //   sharedViewModel.updatePlayer2(Player("Marina",Level.Rookie,R.drawable.profil_marina))
  //  sharedViewModel.updatePlayer2(Player("Matt", Level.Athlete,R.drawable.profil_matt))
  //  sharedViewModel.updatePlayer1(Player("Harold Hide The Pain",Level.Rookie,R.drawable.profil_harold))
  //  sharedViewModel.updatePlayer2(Player("Marina ",Level.Rookie,R.drawable.profil_marina))
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

