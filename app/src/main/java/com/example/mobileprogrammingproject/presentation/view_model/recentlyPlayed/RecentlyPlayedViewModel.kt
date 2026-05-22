package com.example.mobileprogrammingproject.presentation.view_model.recentlyPlayed

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mobileprogrammingproject.model.data.repository.mappers.PlaylistRepository
import com.example.mobileprogrammingproject.presentation.data.Playlist
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RecentlyPlayedViewModel @Inject constructor(
    private val playlistRepository: PlaylistRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow<RecentlyPlayedUiState>(RecentlyPlayedUiState.Loading)
    val uiState: StateFlow<RecentlyPlayedUiState> = _uiState

    init {
        loadPlaylists()
    }

    private fun loadPlaylists() {
        viewModelScope.launch {
            playlistRepository.getAllPlaylists().collect { entities ->
                val playlists = entities.map {
                    Playlist(
                        id = it.id,
                        title = it.name,
                        songList = emptyList(),
                        totalDuration = ""
                    )
                }
                _uiState.value = RecentlyPlayedUiState.Success(
                    topPlaylists = playlists.take(5),
                    recentlyPlayed = playlists.takeLast(10).ifEmpty { playlists }
                )
            }
        }
    }
}