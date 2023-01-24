package com.example.nbasignatureversus.RoomDatabase

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface Player_in_DB_Dao {


    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addPlayer(player: Player_in_DB)

    @Query(value = "SELECT * FROM player_db_table ORDER BY ASC")
    fun readAllData() : LiveData<List<Player_in_DB>>



}