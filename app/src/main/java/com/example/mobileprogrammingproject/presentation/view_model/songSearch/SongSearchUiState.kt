package com.example.mobileprogrammingproject.presentation.view_model.songSearch

import com.example.mobileprogrammingproject.presentation.data.Song

sealed interface SongSearchUiState{
    data object Init: SongSearchUiState

    data object Loading: SongSearchUiState

    data class Success (
        val searchQuery: String = "",
        val songList: List<Song> = emptyList()
    ): SongSearchUiState

    data class Error (
        val message: String
    ): SongSearchUiState
}
