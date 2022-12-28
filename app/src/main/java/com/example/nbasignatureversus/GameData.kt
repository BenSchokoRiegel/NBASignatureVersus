package com.example.nbasignatureversus

enum class Level {
    Pro, ADVANCED, ROOKIE, Master,
}



class GameData(gameScore: GameScore,player1:Player,player2: Player){

}




class Player(val name: String,  val level: Level, var picture: Int) {
    constructor(name:String):this(name,Level.Pro,R.drawable.ic_launcher_foreground)



}


class GameScore(var player1_score:Int, var player2_score:Int, var player1_turn: Boolean, val maxScore:Int){

    constructor():this(0,0,true,11)
}