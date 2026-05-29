package com.example.charapedia.data.network.response.animes

import com.google.gson.annotations.SerializedName

data class AnimeDetailResponse(
    @SerializedName("data")
    val data : AnimeDetail
)
