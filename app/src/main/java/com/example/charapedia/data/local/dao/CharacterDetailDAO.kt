package com.example.charapedia.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.charapedia.data.local.entity.CharacterDetailEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface CharacterDetailDAO {

    @Query("SELECT * FROM charactersDetails WHERE id = :id")
    fun getCharacterDetail(id: Int): Flow<CharacterDetailEntity?>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCharacterDetail(characterDetail: CharacterDetailEntity)

}