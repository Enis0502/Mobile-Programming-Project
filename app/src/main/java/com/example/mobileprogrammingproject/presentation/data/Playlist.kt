package com.example.mobileprogrammingproject.presentation.data


data class Playlist(
    val id: Int,
    val title: String,
    val songList: List<Song>,
    val totalDuration: String
)