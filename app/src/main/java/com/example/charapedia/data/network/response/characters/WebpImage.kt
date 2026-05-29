package com.example.charapedia.data.network.response.characters

import com.google.gson.annotations.SerializedName

data class WebpImage(
    @SerializedName("image_url")
    val imageUrl: String,
)
