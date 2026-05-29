package com.example.charapedia.data.network.response.animes

import com.google.gson.annotations.SerializedName

class AnimeDetail(
    @SerializedName("mal_id")
    val malId : Int,
    @SerializedName("images")
    val images : Images
)
