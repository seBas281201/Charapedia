package com.example.charapedia.ui.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.charapedia.data.AnimeId
import com.example.charapedia.data.network.ApiRepository
import com.example.charapedia.ui.Event
import com.example.charapedia.ui.UiState
import com.example.charapedia.ui.models.anime.AnimeDetailUiModel
import com.example.charapedia.data.network.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AnimeViewModel @Inject constructor(
    private val repository: ApiRepository
) : ViewModel() {


    private val _uiState = MutableStateFlow(UiState())
    val state: StateFlow<UiState> = _uiState.asStateFlow()

    private val _animeDbz = MutableStateFlow<AnimeDetailUiModel?>(null)
    val animeDbz: StateFlow<AnimeDetailUiModel?> = _animeDbz.asStateFlow()

    private val _animeJba = MutableStateFlow<AnimeDetailUiModel?>(null)
    val animeJba: StateFlow<AnimeDetailUiModel?> = _animeJba.asStateFlow()

    private val _animeAot = MutableStateFlow<AnimeDetailUiModel?>(null)
    val animeAot: StateFlow<AnimeDetailUiModel?> = _animeAot.asStateFlow()

    private val _events = MutableSharedFlow<Event>()
    val events: SharedFlow<Event> = _events.asSharedFlow()


    fun getAnimes() {
        observeAllAnimes()
        refreshAllAnimes()
    }
    private fun observeAnimes(
        malId : Int,
        onUpdate : (AnimeDetailUiModel?) -> Unit
    ) {
        viewModelScope.launch {
            repository
                .observeAnime(malId)
                .collect { anime ->

                    Log.d(
                        "ANIME_FLOW",
                        "id=$malId anime=$anime"
                    )
                    onUpdate(anime)
                }
        }

    }

    private fun refreshAnimes(
        malId: Int,
        updateLoading: (Boolean) -> Unit
    ) {
        viewModelScope.launch {

            updateLoading(true)

            when (repository.refreshAnime(malId)) {
                is Result.Success -> {}
                is Result.Error -> {
                    _events.emit(
                        Event.ShowError()
                    )
                }
            }

            updateLoading(false)

        }

    }

    private fun observeAllAnimes(){
        observeAnimes(AnimeId.DBZ.id) {
            _animeDbz.value = it
        }

        observeAnimes(AnimeId.JBA.id) {
            _animeJba.value = it
        }

        observeAnimes(AnimeId.AOT.id) {
            _animeAot.value = it
        }

    }

    private fun refreshAllAnimes(){
        viewModelScope.launch {
            refreshAnimes(AnimeId.DBZ.id) { loading ->
                _uiState.update { state ->
                    state.copy(
                        isLoadingDbz = loading
                    )
                }
            }

            delay(1000)

            refreshAnimes(AnimeId.JBA.id) { loading ->
                _uiState.update { state ->
                    state.copy(
                        isLoadingJba = loading
                    )
                }
            }

            delay(1000)

            refreshAnimes(AnimeId.AOT.id) { loading ->
                _uiState.update { state ->
                    state.copy(
                        isLoadingAot = loading
                    )
                }
            }
        }
    }


}