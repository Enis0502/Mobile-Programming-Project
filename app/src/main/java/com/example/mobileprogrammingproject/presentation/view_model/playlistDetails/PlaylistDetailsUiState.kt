package com.example.mobileprogrammingproject.presentation.view_model.playlistDetails

import com.example.mobileprogrammingproject.presentation.data.Playlist

data class PlaylistDetailsUiState(
    val searchQuery: String = "",
    val playlists: List<Playlist> = emptyList()
)