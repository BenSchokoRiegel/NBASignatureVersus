package com.example.nbasignatureversus

import androidx.compose.runtime.*
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument

@Composable
fun Navigation() {
    val navController = rememberNavController()
    val sharedViewModel: SharedViewModel = viewModel()
    exampleGame(sharedViewModel)

    NavHost(navController = navController, startDestination = Screen.GameScreen.route) {
        composable(route = Screen.GameScreen.route) {
            GameScreen(navController,sharedViewModel )

        }
        composable(route = Screen.GameSettingScreen.route){
            Settings(navController = navController, sharedViewModel = sharedViewModel)
        }

//        composable(route = Screen.CreateNote.route) {
//            CreateNote(navController)
//        }

    }
}

// an Example app to test the Composable for profil and Scrore
fun exampleGame(sharedViewModel: SharedViewModel){
    sharedViewModel.updatePlayer1(Player("Ben Jasper Riegel",Level.Pro,R.drawable.profil_ben))
    //sharedViewModel.updatePlayer1(Player("Harold Hide The Pain",Level.ROOKIE,R.drawable.profil_harold))
    //sharedViewModel.updatePlayer2(Player("Marina Doronina",Level.ROOKIE,R.drawable.profil_marina))
    sharedViewModel.updatePlayer2(Player("Matt",Level.Master,R.drawable.profil_matt))




}


