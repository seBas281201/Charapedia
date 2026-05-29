package com.example.charapedia.ui

data class UiState(
    val isLoadingDbz: Boolean = false,
    val isLoadingJba: Boolean = false,
    val isLoadingAot: Boolean = false,
    val isLoadingCharacter: Boolean = false,
    val isLoadingAnimeDbz: Boolean = false,
    val isLoadingAnimeJba: Boolean = false,
    val isLoadingAnimeAot: Boolean = false
)
