package com.example.charapedia

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.charapedia.ui.navigation.NavGraph
import com.example.charapedia.ui.theme.CharapediaTheme
import com.example.charapedia.ui.theme.ThemeMode
import com.example.charapedia.ui.viewmodel.PreferencesViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AppRoot()
        }
    }
}

@Composable
fun AppRoot() {

    val vm : PreferencesViewModel = hiltViewModel()
    val mode = vm.themeMode.collectAsState().value

    val isDark = when(mode){
        ThemeMode.SYSTEM -> isSystemInDarkTheme()
        ThemeMode.LIGHT -> false
        ThemeMode.DARK -> true
    }

    CharapediaTheme(
        darkTheme = isDark,
        dynamicColor = false
    ){
        NavGraph()
    }
}
