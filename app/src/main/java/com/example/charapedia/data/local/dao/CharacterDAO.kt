package com.example.charapedia.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.charapedia.data.local.entity.CharacterEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface CharacterDAO {

    @Query(
        "SELECT * FROM characters WHERE animeId = :animeId"
    )
    fun observeCharacters(
        animeId: Int
    ): Flow<List<CharacterEntity>>

    @Insert(
        onConflict = OnConflictStrategy.REPLACE
    )
    suspend fun insertCharacters(
        characters: List<CharacterEntity>
    )

    @Query(
        "SELECT COUNT(*) FROM characters"
    )
    suspend fun getCount(): Int
}