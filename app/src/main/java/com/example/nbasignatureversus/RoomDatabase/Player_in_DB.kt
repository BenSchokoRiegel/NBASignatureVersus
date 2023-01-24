package com.example.nbasignatureversus.RoomDatabase
import androidx.room.Entity
import androidx.room.PrimaryKey


// Maybe UserID noetig??
@Entity(tableName = "player_db_table")
data class Player_in_DB(
    @PrimaryKey(autoGenerate = true)
    val name : String,
    val level : String,
    val picture_id : Int)