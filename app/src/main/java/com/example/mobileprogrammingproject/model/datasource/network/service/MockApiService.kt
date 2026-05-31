package com.example.mobileprogrammingproject.model.datasource.network.service

import com.example.mobileprogrammingproject.model.datasource.network.dto.PlaylistDto
import retrofit2.http.*

interface MockApiService {

    @POST("posts")
    suspend fun createPlaylist(@Body playlist: PlaylistDto): PlaylistDto

    @PUT("posts/{id}")
    suspend fun updatePlaylist(
        @Path("id") id: Long,
        @Body playlist: PlaylistDto
    ): PlaylistDto

    @DELETE("posts/{id}")
    suspend fun deletePlaylist(@Path("id") id: Long)
}