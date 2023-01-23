package com.example.nbasignatureversus

import GameOver
import android.content.Intent
import android.net.Uri
import androidx.compose.runtime.*
import androidx.compose.ui.platform.LocalContext
import androidx.core.content.ContextCompat.startActivity
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController


@Composable
fun Navigation() {
    val navController = rememberNavController()
    val sharedViewModel: SharedViewModel = viewModel()
    exampleGame(sharedViewModel)

    NavHost(navController = navController, startDestination = Screen.GameScreen.route) {
        composable(route = Screen.GameScreen.route) {
            GameScreenTopBar(navController,sharedViewModel )

        }
        composable(route = Screen.GameSettingScreen.route){
            Settings(navController = navController, sharedViewModel = sharedViewModel)
        }

        composable(route = Screen.GameOver.route){
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
    sharedViewModel.updatePlayer1(Player("Ben Jasper Riegel",Level.Pro,R.drawable.profil_ben))
    //sharedViewModel.updatePlayer1(Player("Harold Hide The Pain",Level.Rookie,R.drawable.profil_harold))
    //sharedViewModel.updatePlayer2(Player("Marina Doronina",Level.Rookie,R.drawable.profil_marina))
    sharedViewModel.updatePlayer2(Player("Matt",Level.Athlete,R.drawable.profil_matt))




}


