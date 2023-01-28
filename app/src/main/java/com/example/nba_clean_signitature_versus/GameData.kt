package com.example.nba_clean_signitature_versus

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


val all_pr : List<Int> = listOf<Int>(
    R.drawable.a,
    R.drawable.b,
    R.drawable.c,
    R.drawable.d,
    R.drawable.e,
    R.drawable.f,
    R.drawable.g,
    R.drawable.h,
    R.drawable.i,
    R.drawable.j,
    R.drawable.k,
    R.drawable.l,
    R.drawable.m,
    R.drawable.n,
    R.drawable.o,
    R.drawable.p,
    R.drawable.a,
    R.drawable.q,
    R.drawable.r,
    R.drawable.t,
)

fun getOneIcon():Int{
    var x : Int = all_pr.get((0..all_pr.size-1).random())
    return x

}

