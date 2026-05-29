package com.example.charapedia.ui.models.character

data class CharacterDetailUiModel(
    val id: Int,
    val name: String,
    val imageUrl: String,
    val nameKanji: String?,
    val about: String
)
