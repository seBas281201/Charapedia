package com.example.charapedia.ui.models

import com.example.charapedia.data.network.response.animes.AnimeDetail
import com.example.charapedia.data.network.response.character.CharacterDetail
import com.example.charapedia.data.network.response.characters.CharacterItem
import com.example.charapedia.ui.models.anime.AnimeDetailUiModel
import com.example.charapedia.ui.models.character.CharacterDetailUiModel
import com.example.charapedia.ui.models.characters.CharacterUiModel

fun CharacterItem.toUiModel() : CharacterUiModel {
    return CharacterUiModel(
        id = character.malId,
        name = character.name,
        imageUrl = character.images.webp.imageUrl,
        role = role
    )
}

fun CharacterDetail.toUiModelDetail() : CharacterDetailUiModel{
    return CharacterDetailUiModel(
        id = malId,
        name = name,
        imageUrl = images.webp.imageUrl,
        nameKanji = nameKanji,
        about = about
    )
}

fun AnimeDetail.toUiModelAnimeDetail() : AnimeDetailUiModel {
    return AnimeDetailUiModel(
        malId = malId,
        imageUrl = images.webp.imageUrl
    )
}