package com.example.mobileprogrammingproject.presentation.view_model.myPlaylists

import com.example.mobileprogrammingproject.model.data.repository.mappers.FirestorePlaylist

sealed interface MyPlaylistsUiState {
    data object Init : MyPlaylistsUiState
    data object Loading : MyPlaylistsUiState
    data class Success(val playlists: List<FirestorePlaylist>) : MyPlaylistsUiState
    data class Error(val message: String) : MyPlaylistsUiState
}