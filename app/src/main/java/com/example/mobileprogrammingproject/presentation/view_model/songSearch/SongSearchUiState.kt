package com.example.mobileprogrammingproject.presentation.view_model.songSearch

import com.example.mobileprogrammingproject.presentation.data.Song

data class SongSearchUiState (
    val searchQuery: String = "",
    val songList: List<Song> = emptyList()
)
