package com.example.charapedia.data.network.response.character

import com.google.gson.annotations.SerializedName

data class CharacterDetailResponse(
    @SerializedName("data")
    val data : CharacterDetail
)
