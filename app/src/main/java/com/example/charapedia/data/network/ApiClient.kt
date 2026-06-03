package com.example.charapedia.data.network

import com.example.charapedia.data.network.response.animes.AnimeDetailResponse
import com.example.charapedia.data.network.response.character.CharacterDetailResponse
import com.example.charapedia.data.network.response.characters.CharacterResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiClient {

    @GET("anime/{animeId}/characters")
    suspend fun getCharacters(
        @Path("animeId")
        animeId: Int
    ): Response<CharacterResponse>

    @GET("anime/{malId}")
    suspend fun getAnimeById(
        @Path("malId") malId : Int
    ) : Response<AnimeDetailResponse>

    @GET("characters/{malId}")
    suspend fun getCharacterById(
        @Path("malId") malId : Int
    ) : Response<CharacterDetailResponse>
}
