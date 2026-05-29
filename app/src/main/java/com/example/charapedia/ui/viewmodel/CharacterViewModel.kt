package com.example.charapedia.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.charapedia.data.network.ApiRepository
import com.example.charapedia.ui.UiState
import com.example.charapedia.ui.models.characters.CharacterUiModel
import com.example.charapedia.data.network.Result
import com.example.charapedia.ui.Event
import com.example.charapedia.ui.models.character.CharacterDetailUiModel
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

    private val _events = MutableSharedFlow<Event>()
    val events: SharedFlow<Event> = _events.asSharedFlow()

    fun getCharacters() {
        getCharactersDbz()
        getCharactersJba()
        getCharactersAot()
    }

    fun getCharacter(malId: Int) {
        getCharacterById(malId)
    }

    private fun loadCharacters(
        fetch: suspend () -> Result<List<CharacterUiModel>>,
        onSuccess: (List<CharacterUiModel>) -> Unit,
        updateLoading: (Boolean) -> Unit
    ) {

        viewModelScope.launch {

            updateLoading(true)

            when (val result = fetch()) {
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

    private fun loadCharacterById(
        malId: Int,
        fetch: suspend (Int) -> Result<CharacterDetailUiModel?>,
        onSuccess: (CharacterDetailUiModel?) -> Unit,
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

    private fun getCharacterById(malId: Int) {
        loadCharacterById(
            malId = malId,
            fetch = { malId: Int ->
                repository.getCharacterById(malId)
            },
            onSuccess = {
                _character.value = it
            },
            updateLoading = { loading ->
                _uiState.update {
                    it.copy(
                        isLoadingCharacter = loading
                    )
                }
            }
        )
    }

    private fun getCharactersDbz() {
        loadCharacters(
            fetch = {
                repository.getCharactersDbz()
            },
            onSuccess = {
                _charactersDbz.value = it
            },
            updateLoading = { loading ->
                _uiState.update {
                    it.copy(
                        isLoadingDbz = loading
                    )
                }
            }
        )
    }

    private fun getCharactersJba() {
        loadCharacters(
            fetch = {
                repository.getCharactersJba()
            },
            onSuccess = {
                _charactersJba.value = it
            },
            updateLoading = { loading ->
                _uiState.update {
                    it.copy(
                        isLoadingJba = loading
                    )
                }
            }
        )
    }

    private fun getCharactersAot() {
        loadCharacters(
            fetch = {
                repository.getCharactersAot()
            },
            onSuccess = {
                _charactersAot.value = it
            },
            updateLoading = { loading ->
                _uiState.update {
                    it.copy(
                        isLoadingAot = loading
                    )
                }
            }
        )
    }

}