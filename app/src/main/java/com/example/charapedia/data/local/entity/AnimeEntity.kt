package com.example.charapedia.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "animes")
data class AnimeEntity(
    @PrimaryKey
    val id: Int,
    val images: String
)
