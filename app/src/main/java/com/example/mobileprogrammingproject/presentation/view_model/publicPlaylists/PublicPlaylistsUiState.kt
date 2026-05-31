package com.example.mobileprogrammingproject.presentation.view_model.publicPlaylists
import com.example.mobileprogrammingproject.model.datasource.network.dto.PlaylistDto

sealed interface PublicPlaylistsUiState {
    data object Loading : PublicPlaylistsUiState
    data class Success(val playlists: List<PlaylistDto>) : PublicPlaylistsUiState
    data class Error(val message: String) : PublicPlaylistsUiState
}