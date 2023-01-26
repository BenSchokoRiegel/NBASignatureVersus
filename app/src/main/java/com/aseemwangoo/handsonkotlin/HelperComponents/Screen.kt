package com.aseemwangoo.handsonkotlin.HelperComponents

sealed class Screen(val route: String){



    object Twitter : Screen("Twitter")
    object Github : Screen("Github")
    object LinkedIn : Screen("LinkedIn")
    object GameOver : Screen("GameOver")
    object StartScreen : Screen("StartScreen")
    object GameSettingScreen : Screen("GameSettingScreen")
    object GameScreen : Screen("GameScreen")






}
