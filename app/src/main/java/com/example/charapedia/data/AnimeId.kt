package com.example.charapedia.data

sealed class AnimeId(val id: Int){
    data object DBZ : AnimeId(813)
    data object JBA : AnimeId(14719)
    data object AOT : AnimeId(16498)
}