package com.example.charapedia.ui.theme

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.map

val Context.dataStore by preferencesDataStore("theme_preferences")
private val KEY_THEME_MODE = intPreferencesKey("theme_mode")


class ThemePreferences(private val context : Context) {
    suspend fun setThemeMode(mode: ThemeMode) {
        context.dataStore.edit { preferences ->
            preferences[KEY_THEME_MODE] = when(mode) {
                ThemeMode.SYSTEM -> 0
                ThemeMode.LIGHT -> 1
                ThemeMode.DARK -> 2
            }
        }
    }

    val themeModeFlow = context.dataStore.data
        .map { prefs ->
            when(prefs[KEY_THEME_MODE] ?: 0) {
                0 -> ThemeMode.SYSTEM
                1 -> ThemeMode.LIGHT
                2 -> ThemeMode.DARK
                else -> ThemeMode.SYSTEM
            }
        }
}