package com.example.mobileprogrammingproject.presentation.view_model.playlistSongs

import com.example.mobileprogrammingproject.model.data.local.entity.SongEntity

sealed interface PlaylistSongsUiState {
    data object Init : PlaylistSongsUiState
    data object Loading : PlaylistSongsUiState
    data class Success(val songs: List<SongEntity>) : PlaylistSongsUiState
    data class Error(val message: String) : PlaylistSongsUiState
}