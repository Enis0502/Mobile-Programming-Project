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
    private val _uiState = MutableStateFlow(
        PlaylistDetailsUiState(
            playlists = playlistsHardcoded
        )
    )

    val uiState: StateFlow<PlaylistDetailsUiState> = _uiState

    fun onSearchQuery(query: String){
        _uiState.value = _uiState.value.copy(
            searchQuery = query
        )
    }

    fun getFilteredPlaylists(): List<Playlist>{
        return _uiState.value.playlists.filter {
            it.title.contains(_uiState.value.searchQuery, ignoreCase = true)
        }
    }
}