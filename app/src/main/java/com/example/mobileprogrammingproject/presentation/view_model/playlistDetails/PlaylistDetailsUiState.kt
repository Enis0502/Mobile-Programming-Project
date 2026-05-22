package com.example.mobileprogrammingproject.presentation.view_model.playlistDetails

import com.example.mobileprogrammingproject.presentation.data.Playlist

sealed interface PlaylistDetailsUiState{
    data object Init: PlaylistDetailsUiState

    data class Success(
        val searchQuery: String = "",
        val playlists: List<Playlist> = emptyList()
    ): PlaylistDetailsUiState

    data object Loading: PlaylistDetailsUiState

    data class Error(
        val message: String
    ): PlaylistDetailsUiState
}