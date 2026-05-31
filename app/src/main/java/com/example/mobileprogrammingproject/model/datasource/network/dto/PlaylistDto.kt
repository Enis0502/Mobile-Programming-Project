package com.example.mobileprogrammingproject.model.datasource.network.dto

import com.google.gson.annotations.SerializedName

data class PlaylistDto(
    val id: Long,
    val title: String,
    @SerializedName("picture_medium") val picture: String?
)