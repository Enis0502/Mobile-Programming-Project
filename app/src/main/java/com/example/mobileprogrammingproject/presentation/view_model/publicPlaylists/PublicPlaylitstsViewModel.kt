package com.example.mobileprogrammingproject.presentation.view_model.publicPlaylists

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mobileprogrammingproject.model.data.repository.mappers.PlaylistRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PublicPlaylistsViewModel @Inject constructor(
    private val repository: PlaylistRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow<PublicPlaylistsUiState>(PublicPlaylistsUiState.Loading)
    val uiState: StateFlow<PublicPlaylistsUiState> = _uiState

    init {
        loadTopPlaylists()
    }

    fun loadTopPlaylists() {
        viewModelScope.launch {
            _uiState.value = PublicPlaylistsUiState.Loading
            try {
                val response = repository.getTopPlaylists()
                _uiState.value = PublicPlaylistsUiState.Success(
                    playlists = response.data ?: emptyList()
                )
            } catch (e: Exception) {
                _uiState.value = PublicPlaylistsUiState.Error(e.message ?: "Failed to load playlists")
            }
        }
    }
}