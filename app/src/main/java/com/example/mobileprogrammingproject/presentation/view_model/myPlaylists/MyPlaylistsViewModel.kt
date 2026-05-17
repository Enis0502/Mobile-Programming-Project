package com.example.mobileprogrammingproject.presentation.view_model.myPlaylists

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mobileprogrammingproject.model.data.local.entity.PlaylistEntity
import com.example.mobileprogrammingproject.model.data.repository.mappers.PlaylistRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MyPlaylistsViewModel @Inject constructor(
    private val repository: PlaylistRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow<MyPlaylistsUiState>(MyPlaylistsUiState.Loading)
    val uiState: StateFlow<MyPlaylistsUiState> = _uiState

    fun loadPlaylists(userId: Int) {
        viewModelScope.launch {
            repository.getPlaylistsByUser(userId).collect { playlists ->
                _uiState.value = MyPlaylistsUiState.Success(playlists = playlists)
            }
        }
    }

    fun createPlaylist(userId: Int, name: String, description: String) {
        if (name.isBlank()) {
            _uiState.value = MyPlaylistsUiState.Error("Playlist name cannot be empty")
            return
        }
        viewModelScope.launch {
            repository.insertPlaylist(
                PlaylistEntity(userId = userId, name = name, description = description)
            )
        }
    }

    fun deletePlaylist(playlist: PlaylistEntity) {
        viewModelScope.launch {
            repository.deletePlaylist(playlist)
        }
    }

    fun updatePlaylist(playlist: PlaylistEntity) {
        viewModelScope.launch {
            repository.updatePlaylist(playlist)
        }
    }
}