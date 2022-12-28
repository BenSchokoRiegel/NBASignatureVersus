package com.example.nbasignatureversus

sealed class Screen(val route: String){
    object StartScreen : Screen("StartScreen")
    object GameSettingScreen : Screen("GameSettingScreen")
    object GameScreen : Screen("GameScreen")




}

