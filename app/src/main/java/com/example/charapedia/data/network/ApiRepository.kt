package com.example.charapedia.data.network

import com.example.charapedia.data.local.dao.CharacterDAO
import com.example.charapedia.data.network.response.characters.CharacterResponse
import com.example.charapedia.ui.models.anime.AnimeDetailUiModel
import com.example.charapedia.ui.models.character.CharacterDetailUiModel
import com.example.charapedia.ui.models.characterItemToEntity
import com.example.charapedia.ui.models.characters.CharacterUiModel
import com.example.charapedia.ui.models.entityToUiModel
import com.example.charapedia.ui.models.toUiModelAnimeDetail
import com.example.charapedia.ui.models.toUiModelDetail
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import retrofit2.Response
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ApiRepository @Inject constructor(
    private val apiClient: ApiClient,
    private val characterDAO: CharacterDAO
) {

    private suspend fun getCharactersResponse(
        animeId: Int
    ): Response<CharacterResponse> {
        return apiClient.getCharacters(animeId)
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

    suspend fun getCharacterById(
        malId: Int
    ): Result<CharacterDetailUiModel> {

        return try {

            val response = apiClient.getCharacterById(malId)

            if(response.isSuccessful) {

                val character = response.body()
                    ?.data
                    ?.toUiModelDetail()

                if(character != null) {

                    Result.Success(character)

                } else {

                    Result.Error(
                        "No se encontró el personaje"
                    )
                }

            } else {

                Result.Error(
                    "Error HTTP ${response.code()}"
                )
            }

        } catch (e: Exception) {

            Result.Error(
                e.message ?: "Error desconocido"
            )
        }
    }

    suspend fun getAnimeById(malId : Int) : Result<AnimeDetailUiModel> {
        return try {

            val response = apiClient.getAnimeById(malId)

            if(response.isSuccessful){
                val anime = response
                    .body()
                    ?.data
                    ?.toUiModelAnimeDetail()

               if (anime != null){
                   Result.Success(anime)
               } else {
                   Result.Error(
                       "No se encontró el anime"
                   )
               }
            } else{
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


}
