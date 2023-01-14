package com.example.nbasignatureversus

sealed class Screen(val route: String){
    class WinnerScreen {

    }

    object GameOver : Screen("GameOver")
    object StartScreen : Screen("StartScreen")
    object GameSettingScreen : Screen("GameSettingScreen")
    object GameScreen : Screen("GameScreen")




}

