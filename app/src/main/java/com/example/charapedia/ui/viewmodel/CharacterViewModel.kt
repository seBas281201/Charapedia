package com.example.charapedia.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.charapedia.data.AnimeId
import com.example.charapedia.data.network.ApiRepository
import com.example.charapedia.ui.UiState
import com.example.charapedia.ui.models.characters.CharacterUiModel
import com.example.charapedia.data.network.Result
import com.example.charapedia.ui.Event
import com.example.charapedia.ui.models.character.CharacterDetailUiModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CharacterViewModel @Inject constructor(
    private val repository: ApiRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(UiState())
    val state: StateFlow<UiState> = _uiState.asStateFlow()

    private val _charactersDbz = MutableStateFlow<List<CharacterUiModel>>(emptyList())
    val charactersDbz: StateFlow<List<CharacterUiModel>> = _charactersDbz.asStateFlow()

    private val _charactersJba = MutableStateFlow<List<CharacterUiModel>>(emptyList())
    val charactersJba: StateFlow<List<CharacterUiModel>> = _charactersJba.asStateFlow()

    private val _charactersAot = MutableStateFlow<List<CharacterUiModel>>(emptyList())
    val charactersAot: StateFlow<List<CharacterUiModel>> = _charactersAot.asStateFlow()

    private val _character = MutableStateFlow<CharacterDetailUiModel?>(null)
    val character: StateFlow<CharacterDetailUiModel?> = _character.asStateFlow()

    private val _query = MutableStateFlow("")

    @OptIn(ExperimentalCoroutinesApi::class)
    val charactersFiltered: StateFlow<List<CharacterUiModel>> = _query.flatMapLatest { query ->
        if(query.isBlank()){
            flowOf(emptyList())
        } else {
            repository.searchCharacters(query)
        }
    }.stateIn(
        scope = viewModelScope,
        started = kotlinx.coroutines.flow.SharingStarted.WhileSubscribed(5000),
        initialValue = emptyList()
    )


    private val _events = MutableSharedFlow<Event>()
    val events: SharedFlow<Event> = _events.asSharedFlow()

    fun getCharacters() {
        observeAllCharacters()
        refreshAllCharacters()
    }

    fun getCharacter(malId: Int) {
        observeCharacter(malId)
        refreshCharacter(malId)
    }

    fun searchCharacters(query: String) {
        _query.value = query
    }

    private fun observeCharacters(
        animeId: Int,
        onUpdate: (List<CharacterUiModel>) -> Unit
    ) {

        viewModelScope.launch {

            repository
                .observeCharacters(animeId)
                .collect { characters ->

                    onUpdate(characters)

                }

        }

    }

    private fun observeCharacter(
        malId: Int
    ){
        viewModelScope.launch {
            repository
                .observeCharacter(malId)
                .collect { character ->
                    _character.value = character
                }
        }
    }

    private fun refreshCharacter(
        malId: Int
    ) {

        viewModelScope.launch {
            _uiState.update {
                it.copy(
                    isLoadingCharacter = true
                )
            }

            when(
                repository.refreshCharacter(malId)
            ) {
                is Result.Success -> {}
                is Result.Error -> {
                    _events.emit(
                        Event.ShowError()
                    )
                }
            }

            _uiState.update {
                it.copy(
                    isLoadingCharacter = false
                )
            }

        }

    }

    private fun observeAllCharacters() {

        observeCharacters(AnimeId.DBZ.id) {
            _charactersDbz.value = it
        }

        observeCharacters(AnimeId.JBA.id) {
            _charactersJba.value = it
        }

        observeCharacters(AnimeId.AOT.id) {
            _charactersAot.value = it
        }

    }

    private fun refreshAllCharacters() {
        viewModelScope.launch {
            _uiState.update {
                it.copy(
                    isLoadingDbz = true,
                    isLoadingJba = true,
                    isLoadingAot = true
                )
            }

            val resultDbz = repository.refreshCharacters(AnimeId.DBZ.id)
            val resultJba = repository.refreshCharacters(AnimeId.JBA.id)
            val resultAot = repository.refreshCharacters(AnimeId.AOT.id)

            _uiState.update {
                it.copy(
                    isLoadingDbz = false,
                    isLoadingJba = false,
                    isLoadingAot = false
                )
            }

            if(resultDbz is Result.Error || resultJba is Result.Error || resultAot is Result.Error){
                _events.emit(
                    Event.ShowError()
                )
            }

        }

    }

}