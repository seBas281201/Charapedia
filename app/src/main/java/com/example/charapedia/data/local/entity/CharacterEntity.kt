package com.example.charapedia.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "characters")
data class CharacterEntity(
    @PrimaryKey val id: Int,
    val animeId : Int,
    val name: String,
    val imageUrl: String,
    val role: String
)
