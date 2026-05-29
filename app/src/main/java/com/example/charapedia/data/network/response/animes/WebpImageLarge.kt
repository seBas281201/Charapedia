package com.example.charapedia.data.network.response.animes

import com.google.gson.annotations.SerializedName

data class WebpImageLarge(
    @SerializedName("large_image_url")
    val imageUrl: String,
)
