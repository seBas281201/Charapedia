package com.example.charapedia.ui.elements

import androidx.compose.animation.AnimatedContent
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.airbnb.lottie.compose.LottieConstants
import com.example.charapedia.ui.models.anime.AnimeDetailUiModel
import com.example.charapedia.ui.models.characters.CharacterUiModel
import com.example.charapedia.utilities.SectionLoading

@Composable
fun AnimeSection(
    title: String,
    isLoading: Boolean,
    characters: List<CharacterUiModel>,
    animeBanner: AnimeDetailUiModel?,
    goToCharacterDetail: (Int) -> Unit
) {

    Text(
        text = title,
        style = MaterialTheme.typography.titleMedium,
        modifier = Modifier.fillMaxWidth()
    )

    Spacer(modifier = Modifier.height(8.dp))

    AnimatedContent(
        targetState = isLoading,
        label = "anime_section"
    ) { loading ->

        if (loading) {

            SectionLoading(
                iterations = LottieConstants.IterateForever
            )

        } else {

            CharacterList(
                characters = characters,
                animeBanner = animeBanner,
                goToCharacterDetail = goToCharacterDetail
            )

        }
    }

    Spacer(modifier = Modifier.height(40.dp))

}