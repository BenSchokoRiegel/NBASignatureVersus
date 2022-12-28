package com.example.nbasignatureversus


import android.graphics.Color.parseColor
import android.icu.text.CaseMap.Title
import androidx.compose.ui.graphics.Color


fun createAllDiffs(): List<Gif> {

    val l = mutableListOf<Gif>()
    val a1 = Gif("Durant-Crossover_PullUp", R.drawable.durant_against_giannes, Level.ROOKIE)
    val a2 = Gif(
        "Lebron-DoubleBackStep",
        R.drawable.leborn_3_backstep,
        Level.ADVANCED
    ) // "One Dribble \n looking down \n DoubleBackStep "
    val a3 = Gif("Duncan_BankShot", R.drawable.duncan_bankshot, Level.ROOKIE)
    val a4 =
        Gif("kareem_abdul_jabbar_hookshot", R.drawable.kareem_abdul_jabbar_hookshot, Level.ROOKIE)
    var a5: Gif =
        Gif("curry_behindtheback_backstep", R.drawable.curry_behindtheback_backstep, Level.Pro)



    return listOf(
        Gif("Durant-Crossover_PullUp", R.drawable.durant_against_giannes, Level.ADVANCED,"#453ec4".color),
        Gif("Lebron-DoubleBackStep", R.drawable.leborn_3_backstep, Level.ADVANCED,"#ebaf3b".color),
        Gif("Duncan_BankShot", R.drawable.duncan_bankshot, Level.ROOKIE, "#956c7a".color),
        Gif("kareem Hookshot", R.drawable.kareem_abdul_jabbar_hookshot, Level.ROOKIE,"#522287".color),
        Gif("curry_behindtheback_backstep", R.drawable.curry_behindtheback_backstep, Level.Pro,"#efa452".color),
        Gif("Jorden-Fade-Away", R.drawable.mj_fadeaway, Level.ADVANCED,"#ffffff".color),

    Gif("Chamberlain Backwards Shoot", R.drawable.chamberlain_backwards_shoot, Level.ROOKIE,"#ffffff".color),
    Gif("Kobe Running Pullup", R.drawable.koby_running_pullup, Level.ROOKIE,"#956c7a".color),
    Gif("Kobe Gamewinner", R.drawable.kobe_lebron_intro_big, Level.ROOKIE,"#fdc027".color),
    Gif("Buttler Turnaroundshot", R.drawable.buttler, Level.ROOKIE,"#63b2b9".color),
    Gif("Kawhi One Hand Dunk", R.drawable.kawhi_dunk, Level.Master,"#B73C3C".color),
    Gif("Lebron Tomahawk Dunk", R.drawable.lebron_tomahawk_dunk, Level.Master,"#EFB433".color),
    Gif("Paython 2 TwoHand Dunk", R.drawable.gary_payton, Level.Master,"#39218D".color),
    Gif("rose English-Finish", R.drawable.drose_roto, Level.ADVANCED,"#ffffff".color),
       // Gif("Joker!!! Choose One", R.drawable.suns, Level.ROOKIE,"#e13427".color),
        Gif("Joker!!! Choose One", R.drawable.tracy13_full, Level.Pro,"#301C3F".color),
        Gif("Harden Step Back", R.drawable.harden_step_back, Level.Pro,"#8A7A7A".color),
        Gif("Dame Side Step", R.drawable.damethree_square, Level.ADVANCED,"#CDBBA7".color),
        Gif("Dirk One Leg Fade", R.drawable.dirkfade_1x1, Level.ROOKIE,"#988F89".color),
        Gif("Luka between Back Step back", R.drawable.lukastepback_1x1_v3, Level.Pro,"#7664C8".color),
        Gif("MJ Fade Away", R.drawable.mj_fadeaway, Level.ROOKIE,"#A83131".color),
        Gif("Shaq square Up Dunk", R.drawable.shaq_dunk_square, Level.Master,"#C8802C".color),
        Gif("Curry Deep three", R.drawable.stephthree_square, Level.ADVANCED,"#4E6EB5".color),


    )
}



class Gif(val name: String,val location: Int, val level: Level, val color: Color) {
    constructor(name:String): this(name,0,Level.ROOKIE)
    constructor(s: String, lo: Int, l: Level):this(s,lo,l,Color.White)
}

// Quelle  https://www.youtube.com/watch?v=zlqXzE0al5s
val String.color
    get() = Color(parseColor(this))