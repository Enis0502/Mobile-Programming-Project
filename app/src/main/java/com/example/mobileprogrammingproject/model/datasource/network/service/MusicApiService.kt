package com.example.mobileprogrammingproject.model.datasource.network.service

import com.example.mobileprogrammingproject.model.datasource.network.dto.PlaylistResponseDto
import retrofit2.http.GET
import retrofit2.http.Path

interface DeezerApiService {
    @GET("chart/0/playlists")
    suspend fun getTopPlaylists(): PlaylistResponseDto
    @GET("user/{userId}/playlists")
    suspend fun getPlaylists(
        @Path("userId") userId: Long
    ): PlaylistResponseDto

}