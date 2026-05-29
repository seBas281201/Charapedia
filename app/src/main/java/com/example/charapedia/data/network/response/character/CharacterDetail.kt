package com.example.charapedia.data.network.response.character

import com.example.charapedia.data.network.response.characters.Images
import com.google.gson.annotations.SerializedName

data class CharacterDetail(
    @SerializedName("mal_id")
    val malId : Int,
    @SerializedName("images")
    val images : Images,
    @SerializedName("name")
    val name : String,
    @SerializedName("name_kanji")
    val nameKanji : String? = null,
    @SerializedName("about")
    val about : String
)
