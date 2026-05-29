package com.example.charapedia.data.network.response.characters

import com.google.gson.annotations.SerializedName

data class Images(
    @SerializedName("webp")
    val webp : WebpImage
)
