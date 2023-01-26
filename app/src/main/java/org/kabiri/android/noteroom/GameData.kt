package org.kabiri.android.noteroom

import kotlin.random.Random

enum class Level {
    Rookie,   Pro,  Athlete,
}

fun giveStringLevel():List<String>{
    var result = mutableListOf<String>()
    for (value in Level.values()){
        var x = value.name
        result.add(x)
    }

    return result

}

fun createLevel(name: String): Level {
    if (name == "Pro"){
        return Level.Pro

    } else if (name =="Rookie"){
        return Level.Rookie
    } else if (name == "Athlete"){
        return Level.Athlete
    } else {
        java.lang.Error("not a real Level")
        return Level.Rookie
    }
}




class GameData(gameScore: GameScore, player1: Player, player2: Player){

}

fun gameHasStarted(){

}


class Player(var name: String, var level: Level, var picture: Int) {
    constructor(name:String):this(name, Level.Pro,R.drawable.ic_launcher_foreground)



}



// #TODO Free play modus -> with or without
class GameScore(var player1_score:Int, var player2_score:Int, var player1_turn: Boolean, var maxScore:Int, var hasStarted:Boolean){

    constructor():this(0,0, Random.nextBoolean(),11,hasStarted = false)
}

