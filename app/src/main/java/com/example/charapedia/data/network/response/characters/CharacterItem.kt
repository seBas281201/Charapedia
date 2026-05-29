package com.example.charapedia.data.network.response.characters

import com.google.gson.annotations.SerializedName

data class CharacterItem(
    @SerializedName("character")
    val character: Character,
    @SerializedName("role")
    val role : String
)
