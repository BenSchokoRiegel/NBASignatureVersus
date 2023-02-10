package com.example.nba_clean_signitature_versus

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import kotlin.random.Random

class SharedViewModel : ViewModel() {


    var player1 by mutableStateOf<Player>(Player("P1_UNKOWN"))


    var player2 by mutableStateOf<Player>(Player("P2_UNKOWN"))


    var gameScore by mutableStateOf<GameScore>(GameScore())


    var gifs by mutableStateOf<List<Gif>>(createAllDiffs()) // Theoretisch hier vielleicht 5 Listen, um das Vorsortieren zu vermeiden.... aber dann beim Start langsam

    var currentGif by mutableStateOf<Gif>(getTheGifForPlayerFirst())

    fun updatePlayer1(newP1: Player) {
        player1 = newP1
    }

    fun updatePlayer2(newP2: Player) {
        player2 = newP2
    }


    fun newGame(){
        gameScore.hasStarted = false
        gameScore.player1_score = 0
        gameScore.player2_score = 0
        gameScore.player1_turn = Random.nextBoolean()
        currentGif = getTheGifForPlayer()

    }



    // function to update if a player missed
    // Player turn change
    fun brickShot() {
        gameScore.player1_turn = !gameScore.player1_turn
        currentGif = getTheGifForPlayer()
    }

    // Player makes the shoot
    // return if Game is Over
    fun makeShot(): Boolean {
        if (gameScore.player1_turn) {
            gameScore.player1_score += 1
        } else {
            gameScore.player2_score += 1
        }
        currentGif = getTheGifForPlayer()
        return gameScore.player1_score >= gameScore.maxScore || gameScore.player2_score >= gameScore.maxScore
    }


    fun getWinner(): Player {

        if (gameScore.player1_score > gameScore.player2_score ) {
            gameFinish()
            return player1
        } else {
            gameFinish()
            return player2
        }

    }

    fun gameFinish(){
        gameScore.player1_score = 0
        gameScore.player2_score = 0
        gameScore.hasStarted = false
    }


    // getting an Gif which is under or equal to the level off the player
    fun getTheGifForPlayer(): Gif {

        val currentPlayerLevel: Level =
            if (this.gameScore.player1_turn) this.player1.level else this.player2.level



        val possibleGif = gifs.filter {
            isLevelExcepted(currentPlayerLevel, it.level) && currentGif != it
        }

        return possibleGif.get(Random.nextInt(0, possibleGif.size))
    }

    fun getTheGifForPlayerFirst(): Gif {

        val currentPlayerLevel: Level =
            if (this.gameScore.player1_turn) this.player1.level else this.player2.level



        val possibleGif = gifs.filter {
            isLevelExcepted(currentPlayerLevel, it.level)
        }

        return possibleGif.get(Random.nextInt(0, possibleGif.size))
    }

    private fun isLevelExcepted(a: Level, b: Level): Boolean {

        if (a == b){
            return true
        } else return a == Level.Athlete && b == Level.Pro


    }


    // compares to Level and return true if the level of a is inside b
    fun compareLevel(a: Level, b: Level): Boolean {

        if (a == Level.Athlete && b == Level.Rookie){
            return false
        }

        var levelList: List<Level> = listOf(Level.Rookie, Level.Pro, Level.Athlete)

        return levelList.indexOf(a) >= levelList.indexOf(b)
    }

    fun checkIfAllReady():Boolean{
        if (this.player1.name != "" && this.player2.name != ""){
            return true
        }
        return false

    }


}
