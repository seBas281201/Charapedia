package com.example.charapedia.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "charactersDetails")
data class CharacterDetailEntity(
    @PrimaryKey val id: Int,
    val name: String,
    val imageUrl: String,
    val nameKanji: String,
    val about: String
)
