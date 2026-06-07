package com.example.charapedia.data.network

import android.util.Log
import com.example.charapedia.data.local.dao.AnimeDAO
import com.example.charapedia.data.local.dao.CharacterDAO
import com.example.charapedia.data.local.dao.CharacterDetailDAO
import com.example.charapedia.data.network.response.animes.AnimeDetailResponse
import com.example.charapedia.data.network.response.character.CharacterDetailResponse
import com.example.charapedia.data.network.response.characters.CharacterResponse
import com.example.charapedia.ui.models.anime.AnimeDetailUiModel
import com.example.charapedia.ui.models.animeDetailToEntity
import com.example.charapedia.ui.models.character.CharacterDetailUiModel
import com.example.charapedia.ui.models.characterDetailToEntity
import com.example.charapedia.ui.models.characterItemToEntity
import com.example.charapedia.ui.models.characters.CharacterUiModel
import com.example.charapedia.ui.models.entityToUiModel
import com.example.charapedia.ui.models.entityToUiModelAnimeDetail
import com.example.charapedia.ui.models.entityToUiModelDetail
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import retrofit2.Response
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ApiRepository @Inject constructor(
    private val apiClient: ApiClient,
    private val characterDAO: CharacterDAO,
    private val characterDetailDAO: CharacterDetailDAO,
    private val animeDAO: AnimeDAO
) {

    private suspend fun getCharactersResponse(
        animeId: Int
    ): Response<CharacterResponse> {
        return apiClient.getCharacters(animeId)
    }

    private suspend fun getCharacterResponse(
        malId: Int
    ): Response<CharacterDetailResponse> {
        return apiClient.getCharacterById(malId)
    }

    private suspend fun getAnimeResponse(
        malId: Int
    ): Response<AnimeDetailResponse> {
        return apiClient.getAnimeById(malId)
    }

    fun observeCharacter(
        malId: Int
    ): Flow<CharacterDetailUiModel?>{

        return characterDetailDAO
            .getCharacterDetail(malId)
            .map { entity ->
                entity?.entityToUiModelDetail()
            }


    }

    fun observeAnime(
        malId : Int
    ) : Flow<AnimeDetailUiModel?>{
        return animeDAO
            .observeAnimes(malId)
            .map { entity ->
                entity?.entityToUiModelAnimeDetail()
            }
    }

    fun observeCharacters(
        animeId: Int
    ): Flow<List<CharacterUiModel>> {

        return characterDAO
            .observeCharacters(animeId)
            .map { entities ->
                entities.map { it.entityToUiModel() }
            }
    }

    fun searchCharacters(query: String): Flow<List<CharacterUiModel>> {

        return characterDAO
            .searchCharacters(query)
            .map { entities ->
                entities.map { it.entityToUiModel() }
            }

    }

    suspend fun refreshCharacters(
        animeId: Int
    ) : Result<Unit> {
        return try {

            val response = getCharactersResponse(animeId)

            if (response.isSuccessful){
                val characters  = response
                    .body()
                    ?.data
                    ?.map { it.characterItemToEntity(animeId) }
                    ?: emptyList()

                characterDAO.insertCharacters(characters)
                Result.Success(Unit)
            } else {
                Result.Error(
                    "Error HTTP ${response.code()}"
                )
            }

        } catch (e : Exception){
            Result.Error(
                e.message ?: "Error desconocido"
            )
        }
    }

    suspend fun refreshCharacter(
        malId: Int
    ) : Result<Unit> {
        return try {
            val response = getCharacterResponse(malId)



            if(response.isSuccessful){
                val character = response
                    .body()
                    ?.data
                    ?.characterDetailToEntity()

                if(character != null){
                    characterDetailDAO.insertCharacterDetail(character)
                    Log.d(
                        "ANIME_API_SUCCESS",
                        "${response.code()} -> ${response.body()}"
                    )
                    Result.Success(Unit)
                } else {
                    Result.Error(
                        "No se encontró el personaje"
                    )
                }

            } else {
                Log.d(
                    "ANIME_API",
                    "Error ${response.code()} -> ${response.errorBody()?.string()}"
                )
                Result.Error(
                    "Error HTTP ${response.code()}"
                )
            }

        } catch (e : Exception){
            Result.Error(
                e.message ?: "Error desconocido"
            )
        }
    }

    suspend fun refreshAnime(
        malId : Int
    ): Result<Unit> {
        return try {

            val result = getAnimeResponse(malId)

            if(result.isSuccessful){
                val anime = result
                    .body()
                    ?.data
                    ?.animeDetailToEntity()

                if (anime != null){
                    animeDAO.insertAnimes(anime)
                    Result.Success(Unit)
                } else {
                    Result.Error(
                        "No se encontró el anime"
                    )
                }

            } else {
                Result.Error(
                    "Error HTTP ${result.code()}"
                )
            }

        } catch (e : Exception){
            Result.Error(
                e.message ?: "Error desconocido"
            )
        }
    }

}
