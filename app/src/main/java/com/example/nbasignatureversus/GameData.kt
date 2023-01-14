package com.example.nbasignatureversus

import kotlin.random.Random

enum class Level {
    Pro, ADVANCED, ROOKIE, Master,
}

fun createLevel(name: String): Level {
    if (name == "PRO"){
        return Level.Pro
    } else if (name == "ADVANCED"){
        return Level.ADVANCED
    } else if (name =="ROOKIE"){
        return Level.ROOKIE
    } else if (name == "MASTER"){
        return Level.Master
    } else {
        java.lang.Error("not a real Level")
        return Level.ROOKIE
    }
}




class GameData(gameScore: GameScore,player1:Player,player2: Player){

}

fun gameHasStarted(){

}


class Player(var name: String, var level: Level, var picture: Int) {
    constructor(name:String):this(name,Level.Pro,R.drawable.ic_launcher_foreground)



}




class GameScore(var player1_score:Int, var player2_score:Int, var player1_turn: Boolean, var maxScore:Int, var hasStarted:Boolean){

    constructor():this(0,0, Random.nextBoolean(),11,hasStarted = false)
}