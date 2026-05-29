package com.example.charapedia.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.charapedia.data.network.ApiRepository
import com.example.charapedia.ui.Event
import com.example.charapedia.ui.UiState
import com.example.charapedia.ui.models.anime.AnimeDetailUiModel
import com.example.charapedia.data.network.Result
import dagger.hilt.android.lifecycle.HiltViewModel
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


    companion object {
        private const val DBZ_ID: Int = 813
        private const val JBA_ID: Int = 14719
        private const val AOT_ID: Int = 16498
    }

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

    fun getAnime() {
        getDbzAnime()
        getJbaAnime()
        getAotAnime()
    }
    private fun loadAnimeById(
        malId: Int,
        fetch: suspend (Int) -> Result<AnimeDetailUiModel?>,
        onSuccess: (AnimeDetailUiModel?) -> Unit,
        updateLoading: (Boolean) -> Unit
    ) {

        viewModelScope.launch {

            updateLoading(true)

            when (val result = fetch(malId)) {
                is Result.Success -> {
                    onSuccess(result.data)
                }

                is Result.Error -> {
                    _events.emit(
                        Event.ShowError()
                    )
                }

            }

            updateLoading(false)

        }

    }

    private fun getDbzAnime() {
        loadAnimeById(
            malId = DBZ_ID,
            fetch = { malId ->
                repository.getAnimeById(malId)
            },
            onSuccess = {
                _animeDbz.value = it
            },
            updateLoading = { loading ->
                _uiState.update {
                    it.copy(
                        isLoadingAnimeDbz = loading
                    )

                }
            }

        )
    }

    private fun getJbaAnime() {
        loadAnimeById(
            malId = JBA_ID,
            fetch = { malId ->
                repository.getAnimeById(malId)
            },
            onSuccess = {
                _animeJba.value = it
            },
            updateLoading = { loading ->
                _uiState.update {
                    it.copy(
                        isLoadingAnimeJba = loading
                    )

                }
            }

        )
    }

    private fun getAotAnime() {
        loadAnimeById(
            malId = AOT_ID,
            fetch = { malId ->
                repository.getAnimeById(malId)
            },
            onSuccess = {
                _animeAot.value = it
            },
            updateLoading = { loading ->
                _uiState.update {
                    it.copy(
                        isLoadingAnimeAot = loading
                    )

                }
            }

        )
    }


}