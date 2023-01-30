package com.example.nba_clean_signitature_versus

sealed class Screen(val route: String){
    class WinnerScreen {

    }


    object Twitter : Screen("Twitter")
    object Github : Screen("Github")
    object LinkedIn : Screen("LinkedIn")
    object GameOver : Screen("GameOver")
    object Winner : Screen("Winner")

    object StartScreen : Screen("StartScreen")
    object GameSettingScreen : Screen("GameSettingScreen")
    object GameScreen : Screen("GameScreen")
    object ShowAllPlayer : Screen("ShowAllPlayer")
    object ShowAllPlayer2 : Screen("ShowAllPlayer2")

    object ChoosePlayer1 : Screen("ChoosePlayer1")
    object ChoosePlayer2 : Screen("ChoosePlayer2")

    object Camera : Screen("Camera")

    object Video : Screen("Video")



}

