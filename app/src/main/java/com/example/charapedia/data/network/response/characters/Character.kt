package com.example.charapedia.data.network.response.characters

import com.google.gson.annotations.SerializedName

data class Character(
    @SerializedName("mal_id")
    val malId : Int,
    @SerializedName("images")
    val images : Images,
    @SerializedName("name")
    val name : String
)
