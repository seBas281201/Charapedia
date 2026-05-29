package com.example.charapedia.data.network.response.animes

import com.google.gson.annotations.SerializedName

data class Images(
    @SerializedName("webp")
    val webp : WebpImageLarge
)
