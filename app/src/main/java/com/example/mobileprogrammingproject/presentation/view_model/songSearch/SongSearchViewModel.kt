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
    private val _uiState = MutableStateFlow<SongSearchUiState>(
        SongSearchUiState.Success(
            searchQuery = "",
            songList = emptyList()
        )
    )

    val uiState: StateFlow<SongSearchUiState> = _uiState

    fun searchSongs(query: String){
        val current = _uiState.value
        if(current is SongSearchUiState.Success){
            val filtered = songs.filter {
                it.title.contains(query, ignoreCase = true)
            }

            _uiState.value = current.copy(
                searchQuery = query,
                songList = filtered
            )
        }
    }
}