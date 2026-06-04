package com.example.charapedia.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.charapedia.data.local.entity.AnimeEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface AnimeDAO {
    @Query("SELECT * FROM animes")
    fun observeAnimes(): Flow<List<AnimeEntity>>

    @Insert
    suspend fun insertAnimes(animes: List<AnimeEntity>)
}