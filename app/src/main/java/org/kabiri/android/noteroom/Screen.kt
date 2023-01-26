package org.kabiri.android.noteroom

sealed class Screen(val route: String){
    class WinnerScreen {

    }


    object Twitter : Screen("Twitter")
    object Github : Screen("Github")
    object LinkedIn : Screen("LinkedIn")
    object GameOver : Screen("GameOver")
    object StartScreen : Screen("StartScreen")
    object GameSettingScreen : Screen("GameSettingScreen")
    object GameScreen : Screen("GameScreen")




}

