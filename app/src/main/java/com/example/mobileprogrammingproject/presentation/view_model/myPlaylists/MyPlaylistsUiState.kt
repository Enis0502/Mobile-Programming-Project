package com.example.mobileprogrammingproject.presentation.view_model.myPlaylists

import com.example.mobileprogrammingproject.model.data.local.entity.PlaylistEntity

sealed interface MyPlaylistsUiState {
    data object Init : MyPlaylistsUiState
    data object Loading : MyPlaylistsUiState
    data class Success(val playlists: List<PlaylistEntity>) : MyPlaylistsUiState
    data class Error(val message: String) : MyPlaylistsUiState
}