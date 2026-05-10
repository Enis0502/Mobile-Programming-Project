package com.example.mobileprogrammingproject.presentation.view_model.songSearch

import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.lifecycle.ViewModel
import com.example.mobileprogrammingproject.model.songs
import com.example.mobileprogrammingproject.presentation.data.Song
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class SongSearchViewModel @Inject constructor(): ViewModel() {
    private val _uiState = MutableStateFlow(
        SongSearchUiState(
            songList = songs
        )
    )

    val uiState: StateFlow<SongSearchUiState> = _uiState

    fun searchSongs(query : String){
        _uiState.value = _uiState.value.copy(
            searchQuery = query
        )
    }

    fun filterSongs(): List<Song> {
        return _uiState.value.songList.filter {
            it.title.contains(_uiState.value.searchQuery, ignoreCase = true)
        }
    }
}