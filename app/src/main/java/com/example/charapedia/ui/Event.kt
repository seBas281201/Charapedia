package com.example.charapedia.ui

sealed interface Event {
    data class ShowError(val message: String = "Algunos recursos no pudieron ser actualizados") : Event
}