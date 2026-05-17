package com.example.mobileprogrammingproject.presentation.view_model.songSearch

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mobileprogrammingproject.model.data.repository.mappers.SongRepository
import com.example.mobileprogrammingproject.presentation.data.Song
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@OptIn(FlowPreview::class)
@HiltViewModel
class SongSearchViewModel @Inject constructor(
    private val repository: SongRepository
) : ViewModel() {

    private val _searchQuery = MutableStateFlow("")

    private val _uiState = MutableStateFlow<SongSearchUiState>(
        SongSearchUiState.Success(searchQuery = "", songList = emptyList())
    )

    val uiState: StateFlow<SongSearchUiState> = _uiState

    init {
        viewModelScope.launch {
            _searchQuery
                .debounce(300)
                .flatMapLatest { query ->
                    if (query.isEmpty()) repository.getAllSongs()
                    else repository.getFilteredSongs(query)
                }
                .collect { songs ->
                    _uiState.value = SongSearchUiState.Success(
                        searchQuery = _searchQuery.value,
                        songList = songs.map {
                            Song(id = it.id, title = it.title, artist = it.artist, duration = it.duration)
                        }
                    )
                }
        }
    }

    fun searchSongs(query: String) {
        _searchQuery.value = query
    }
}