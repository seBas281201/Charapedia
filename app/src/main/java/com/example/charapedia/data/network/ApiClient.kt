package com.example.charapedia.data.network

import com.example.charapedia.data.network.response.animes.AnimeDetailResponse
import com.example.charapedia.data.network.response.character.CharacterDetailResponse
import com.example.charapedia.data.network.response.characters.CharacterResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiClient {

    @GET("anime/813/characters")
    suspend fun getCharactersDbz() : Response<CharacterResponse>

    @GET("anime/14719/characters")
    suspend fun getCharactersJba() : Response<CharacterResponse>

    @GET("anime/16498/characters")
    suspend fun getCharactersAot() : Response<CharacterResponse>

    @GET("anime/{malId}")
    suspend fun getAnimeById(
        @Path("malId") malId : Int
    ) : Response<AnimeDetailResponse>

    @GET("characters/{malId}")
    suspend fun getCharacterById(
        @Path("malId") malId : Int
    ) : Response<CharacterDetailResponse>
}
