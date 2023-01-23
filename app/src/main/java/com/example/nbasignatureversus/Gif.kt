package com.example.nbasignatureversus


import android.graphics.Color.parseColor
import android.icu.text.CaseMap.Title
import androidx.compose.ui.graphics.Color


fun createAllDiffs(): List<Gif> {

    /**
    val l = mutableListOf<Gif>()
    val a1 = Gif("Durant-Crossover_PullUp", R.drawable.durant_against_giannes, Level.Rookie)
    val a2 = Gif(
        "Lebron-DoubleBackStep",
        R.drawable.leborn_3_backstep,
        Level.Advanced
    ) // "One Dribble \n looking down \n DoubleBackStep "
    val a3 = Gif("Duncan_BankShot", R.drawable.duncan_bankshot, Level.Rookie)
    val a4 =
        Gif("kareem_abdul_jabbar_hookshot", R.drawable.kareem_abdul_jabbar_hookshot, Level.Rookie)
    var a5: Gif =
        Gif("curry_behindtheback_backstep", R.drawable.curry_behindtheback_backstep, Level.Pro)
 */

    // Rookie -> 11

    // Pro -> 9
    // Athlete 4

    return listOf(
        Gif("Durant-Crossover_PullUp", R.drawable.durant_against_giannes, Level.Rookie,"#453ec4".color),
        Gif("Lebron-DoubleBackStep", R.drawable.leborn_3_backstep, Level.Pro,"#ebaf3b".color),
        Gif("Duncan_BankShot", R.drawable.duncan_bankshot, Level.Rookie, "#956c7a".color),
        Gif("kareem Hookshot", R.drawable.kareem_abdul_jabbar_hookshot, Level.Rookie,"#522287".color),
        Gif("curry_behindtheback_backstep", R.drawable.curry_behindtheback_backstep, Level.Pro,"#efa452".color),
        Gif("Jorden-Fade-Away", R.drawable.mj_fadeaway, Level.Pro,"#ffffff".color),

    Gif("Chamberlain Backwards Shoot", R.drawable.chamberlain_backwards_shoot, Level.Rookie,"#ffffff".color),
    Gif("Kobe Running Pullup", R.drawable.koby_running_pullup, Level.Rookie,"#956c7a".color),
    Gif("Kobe Gamewinner", R.drawable.kobe_lebron_intro_big, Level.Rookie,"#fdc027".color),
    Gif("Buttler Turnaroundshot", R.drawable.buttler, Level.Rookie,"#63b2b9".color),
    Gif("Kawhi One Hand Dunk", R.drawable.kawhi_dunk, Level.Athlete,"#B73C3C".color),
    Gif("Lebron Tomahawk Dunk", R.drawable.lebron_tomahawk_dunk, Level.Athlete,"#EFB433".color),
    Gif("Paython 2 TwoHand Dunk", R.drawable.gary_payton, Level.Athlete,"#39218D".color),
    Gif("Rose English-Finish", R.drawable.drose_roto, Level.Pro,"#ffffff".color),

        Gif("Joker!!! Choose One", R.drawable.tracy13_full, Level.Pro,"#301C3F".color),
        Gif("Harden Step Back", R.drawable.harden_step_back, Level.Pro,"#8A7A7A".color),
        Gif("Dame Side Step", R.drawable.damethree_square, Level.Pro,"#CDBBA7".color),
        Gif("Dirk One Leg Fade", R.drawable.dirkfade_1x1, Level.Rookie,"#988F89".color),
        Gif("Luka between Back Step back", R.drawable.lukastepback_1x1_v3, Level.Pro,"#7664C8".color),
        Gif("MJ Fade Away", R.drawable.mj_fadeaway, Level.Pro,"#A83131".color),
        Gif("Shaq square Up Dunk", R.drawable.shaq_dunk_square, Level.Athlete,"#C8802C".color),
        Gif("Curry Deep three", R.drawable.stephthree_square, Level.Rookie,"#4E6EB5".color),


         //Gif("Joker!!! Choose One", R.drawable.suns, Level.Rookie,"#e13427".color),


                // tray cc867c
        Gif("Gamewinner_Kawhi",R.drawable.gamewinner_kawhi,Level.Rookie,"#ffffff".color),
        Gif("Tray-Pull-Up",R.drawable.tray_pull_up, Level.Rookie,"#cc867c".color),
        Gif("Ginóbili-Euro_Step", R.drawable.g_eurostep,Level.Rookie,"#959595".color)

    )
}



class Gif(val name: String,val location: Int, val level: Level, val color: Color) {
    constructor(name:String): this(name,0,Level.Rookie)
    constructor(s: String, lo: Int, l: Level):this(s,lo,l,Color.White)
}

// Quelle  https://www.youtube.com/watch?v=zlqXzE0al5s
val String.color
    get() = Color(parseColor(this))