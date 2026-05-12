package com.example.mobileprogrammingproject.presentation.view_model.recentlyPlayed

import androidx.lifecycle.ViewModel
import com.example.mobileprogrammingproject.model.playlistsHardcoded
import com.example.mobileprogrammingproject.presentation.data.Playlist
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class RecentlyPlayedViewModel @Inject constructor(): ViewModel() {
    //val allPlaylists = playlistsHardcoded
    //    val topPlaylists = allPlaylists.take(5)
    //    val recentlyPlayed = allPlaylists.takeLast(10)
    private val _uiState = MutableStateFlow<RecentlyPlayedUiState>(
        RecentlyPlayedUiState.Success(
            topPlaylists = setTopPlaylists(),
            recentlyPlayed = setRecentlyPlayedPlaylists()
        )
    )

    val uiState: StateFlow<RecentlyPlayedUiState> = _uiState

    fun setTopPlaylists(): List<Playlist>{
        return playlistsHardcoded.take(5)
    }

    fun setRecentlyPlayedPlaylists(): List<Playlist>{
        return playlistsHardcoded.takeLast(10)
    }
}