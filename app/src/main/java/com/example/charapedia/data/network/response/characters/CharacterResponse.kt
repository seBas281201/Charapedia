package com.example.charapedia.data.network.response.characters

import com.google.gson.annotations.SerializedName

data class CharacterResponse(

    @SerializedName("data")
    val data : List<CharacterItem>

)
