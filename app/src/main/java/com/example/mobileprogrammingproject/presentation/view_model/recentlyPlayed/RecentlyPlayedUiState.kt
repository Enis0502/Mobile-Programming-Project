package com.example.mobileprogrammingproject.presentation.view_model.recentlyPlayed

import com.example.mobileprogrammingproject.presentation.data.Playlist

sealed interface RecentlyPlayedUiState {
    data object Init: RecentlyPlayedUiState

    data object Loading: RecentlyPlayedUiState

    data class Success(
        val topPlaylists: List<Playlist>,
        val recentlyPlayed: List<Playlist>
    ): RecentlyPlayedUiState

    data class Error (
        val message: String
    ): RecentlyPlayedUiState
}