package com.example.charapedia.data.network

import com.example.charapedia.ui.models.anime.AnimeDetailUiModel
import com.example.charapedia.ui.models.character.CharacterDetailUiModel
import com.example.charapedia.ui.models.characters.CharacterUiModel
import com.example.charapedia.ui.models.toUiModel
import com.example.charapedia.ui.models.toUiModelAnimeDetail
import com.example.charapedia.ui.models.toUiModelDetail
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ApiRepository @Inject constructor(
    private val apiClient: ApiClient
) {

    suspend fun getCharactersDbz(): Result<List<CharacterUiModel>> {

        return try {

            val response = apiClient.getCharactersDbz()

            if(response.isSuccessful) {

                val characters = response
                    .body()
                    ?.data
                    ?.map { it.toUiModel() }
                    ?: emptyList()

                Result.Success(characters)

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

    suspend fun getCharactersJba() : Result<List<CharacterUiModel>> {
        return try {

            val response = apiClient.getCharactersJba()

            if(response.isSuccessful){
                val characters = response
                    .body()
                    ?.data
                    ?.map { it.toUiModel() }
                    ?: emptyList()

                Result.Success(characters)

            } else {
                Result.Error(
                    "Error HTTP ${response.code()}"
                )
            }

        } catch (e : Exception) {
            Result.Error(
                e.message ?: "Error desconocido"
            )
        }
    }

    suspend fun getCharactersAot() : Result<List<CharacterUiModel>> {
        return try {

            val response = apiClient.getCharactersAot()

            if (response.isSuccessful){
                val characters = response
                    .body()
                    ?.data
                    ?.map { it.toUiModel() }
                    ?: emptyList()

                Result.Success(characters)
            } else {
                Result.Error(
                    "Error HTTP ${response.code()}"
                )
            }

        } catch ( e : Exception){
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
