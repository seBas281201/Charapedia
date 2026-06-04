package com.example.charapedia.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.charapedia.data.local.entity.AnimeEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface AnimeDAO {
    @Query("SELECT * FROM animes WHERE id = :malId")
    fun observeAnimes(
        malId: Int
    ): Flow<AnimeEntity?>

    @Insert
    suspend fun insertAnimes(animes: AnimeEntity)
}