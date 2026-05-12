package com.example.mobileprogrammingproject.presentation.view_model.playlistDetails

import androidx.lifecycle.ViewModel
import com.example.mobileprogrammingproject.model.playlistsHardcoded
import com.example.mobileprogrammingproject.presentation.data.Playlist
import com.example.mobileprogrammingproject.presentation.navigation.Screen
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class PlaylistDetailsViewModel @Inject constructor() : ViewModel(){
    private val _uiState = MutableStateFlow<PlaylistDetailsUiState>(
        PlaylistDetailsUiState.Success(
            searchQuery = "",
            playlists = playlistsHardcoded
        )
    )

    val uiState: StateFlow<PlaylistDetailsUiState> = _uiState

    fun onSearchQuery(query: String){
        val current = _uiState.value
        if(current is PlaylistDetailsUiState.Success){
            val filtered = playlistsHardcoded.filter{
                it.title.contains(query, ignoreCase = true)
            }
            _uiState.value = current.copy(
                searchQuery = query,
                playlists = filtered
            )
        }

    }
}