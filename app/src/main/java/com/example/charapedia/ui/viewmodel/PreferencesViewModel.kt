package com.example.charapedia.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.charapedia.ui.theme.ThemeMode
import com.example.charapedia.ui.theme.ThemePreferences
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PreferencesViewModel @Inject constructor(
    private val themePreferences: ThemePreferences
) : ViewModel() {

    private val _themeMode = MutableStateFlow(ThemeMode.SYSTEM)
    val themeMode: StateFlow<ThemeMode> = _themeMode


    init{
        viewModelScope.launch {
            themePreferences.themeModeFlow.collect {
                _themeMode.value = it
            }
        }
    }

    fun updateTheme(mode : ThemeMode){
        viewModelScope.launch {
            themePreferences.setThemeMode(mode)
        }
    }

}