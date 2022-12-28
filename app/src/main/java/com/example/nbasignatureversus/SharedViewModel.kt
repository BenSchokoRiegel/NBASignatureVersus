package com.example.nbasignatureversus

import android.app.Person
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


    // compares to Level and return true if the level of a is inside b
    fun isLevelExcepted(a: Level, b: Level): Boolean {

        if (a == Level.Master && b == Level.ROOKIE){
            return false
        }

        var levelList: List<Level> = listOf(Level.ROOKIE, Level.ADVANCED, Level.Pro, Level.Master)

        return levelList.indexOf(a) >= levelList.indexOf(b)


    }


}
