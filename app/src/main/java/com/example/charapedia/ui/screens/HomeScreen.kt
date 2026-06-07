package com.example.charapedia.ui.screens

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.airbnb.lottie.compose.LottieConstants
import com.example.charapedia.R
import com.example.charapedia.ui.Event
import com.example.charapedia.ui.elements.CharacterList
import com.example.charapedia.ui.elements.DefaultTextField
import com.example.charapedia.ui.viewmodel.AnimeViewModel
import com.example.charapedia.ui.viewmodel.CharacterViewModel
import com.example.charapedia.utilities.SectionLoading
import kotlinx.coroutines.flow.collectLatest

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    characterVm : CharacterViewModel = hiltViewModel(),
    animeVm : AnimeViewModel = hiltViewModel(),
    goToCharacterDetail: (Int) -> Unit
) {

    val stateCharacter by characterVm.state.collectAsState()
    val charactersDbz by characterVm.charactersDbz.collectAsState()
    val charactersJba by characterVm.charactersJba.collectAsState()
    val charactersAot by characterVm.charactersAot.collectAsState()
    val charactersFiltered by characterVm.charactersFiltered.collectAsState()
    var query by rememberSaveable {
        mutableStateOf("")
    }

    val stateAnime by animeVm.state.collectAsState()
    val animeDbz by animeVm.animeDbz.collectAsState()
    val animeJba by animeVm.animeJba.collectAsState()
    val animeAot by animeVm.animeAot.collectAsState()



    val snackbarHostState = remember {
        SnackbarHostState()
    }

    LaunchedEffect(characterVm) {
        characterVm.getCharacters()
    }

    LaunchedEffect(animeVm) {
        animeVm.getAnimes()
    }

    LaunchedEffect(Unit) {
        animeVm.events.collectLatest { event ->
            when(event){
                is Event.ShowError -> {
                    snackbarHostState.showSnackbar(
                        message = event.message
                    )
                }
            }
        }
    }


    LaunchedEffect(Unit) {
        characterVm.events.collectLatest { event ->
            when(event){
                is Event.ShowError -> {
                    snackbarHostState.showSnackbar(
                        message = event.message
                    )
                }
            }
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = stringResource(R.string.app_name),
                        style = MaterialTheme.typography.displayMedium
                    )
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    titleContentColor = MaterialTheme.colorScheme.onPrimary
                )
            )
        },
        snackbarHost = {
            SnackbarHost(
                snackbarHostState
            )
        },
        containerColor = MaterialTheme.colorScheme.background
    ) {paddingValues ->

            LazyColumn(
                modifier = Modifier
                    .padding(paddingValues)
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {

                item{
                    DefaultTextField(
                        query = query,
                        onSearch = {
                            query = it
                            characterVm.searchCharacters(it)
                        },
                        onCleanSearch = {
                            query = ""
                        }
                    )
                }

                item {
                    if(charactersFiltered.isNotEmpty() && query.isNotBlank()){
                        CharacterList(
                            characters = charactersFiltered,
                            animeBanner = null,
                            goToCharacterDetail = goToCharacterDetail
                        )
                    } else {
                        Text(
                            text = stringResource(R.string.HomeScreen_Item_Title_Dbz),
                            style = MaterialTheme.typography.titleMedium,
                            textAlign = TextAlign.Left,
                            modifier = Modifier
                                .fillMaxWidth()
                        )

                        Spacer(modifier = Modifier.height(8.dp))

                        if(stateCharacter.isLoadingDbz || stateAnime.isLoadingDbz ){
                            SectionLoading(
                                iterations = LottieConstants.IterateForever
                            )
                        } else {

                            CharacterList(
                                characters = charactersDbz,
                                animeBanner = animeDbz,
                                goToCharacterDetail = goToCharacterDetail
                            )

                        }

                        Spacer(modifier = Modifier.height(40.dp))


                        Text(
                            text = stringResource(R.string.HomeScreen_Item_Title_Jba),
                            style = MaterialTheme.typography.titleMedium,
                            textAlign = TextAlign.Left,
                            modifier = Modifier
                                .fillMaxWidth()
                        )

                        Spacer(modifier = Modifier.height(8.dp))

                        if(stateCharacter.isLoadingJba || stateAnime.isLoadingJba ){
                            SectionLoading(
                                iterations = LottieConstants.IterateForever
                            )
                        } else {

                            CharacterList(
                                characters = charactersJba,
                                animeBanner = animeJba,
                                goToCharacterDetail = goToCharacterDetail
                            )

                        }

                        Spacer(modifier = Modifier.height(40.dp))

                        Text(
                            text = stringResource(R.string.HomeScreen_Item_Title_Aot),
                            style = MaterialTheme.typography.titleMedium,
                            textAlign = TextAlign.Left,
                            modifier = Modifier
                                .fillMaxWidth()
                        )

                        Spacer(modifier = Modifier.height(8.dp))

                        if(stateCharacter.isLoadingAot || stateAnime.isLoadingAot ){
                            SectionLoading(
                                iterations = LottieConstants.IterateForever
                            )
                        } else {

                            CharacterList(
                                characters = charactersAot,
                                animeBanner = animeAot,
                                goToCharacterDetail = goToCharacterDetail
                            )

                        }


                        Spacer(modifier = Modifier.height(8.dp))

                    }
                }


            }

    }

}