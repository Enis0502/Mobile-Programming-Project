package com.example.mobileprogrammingproject.presentation.view_model.playlistSongs

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mobileprogrammingproject.model.data.local.entity.SongEntity
import com.example.mobileprogrammingproject.model.data.repository.mappers.PlaylistSongRepository
import com.example.mobileprogrammingproject.model.data.repository.mappers.SongRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PlaylistSongsViewModel @Inject constructor(
    private val playlistSongRepository: PlaylistSongRepository,
    private val songRepository: SongRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow<PlaylistSongsUiState>(PlaylistSongsUiState.Loading)
    val uiState: StateFlow<PlaylistSongsUiState> = _uiState

    private val _allSongs = MutableStateFlow<List<SongEntity>>(emptyList())
    val allSongs: StateFlow<List<SongEntity>> = _allSongs

    fun loadPlaylistSongs(playlistId: Int) {
        viewModelScope.launch {
            playlistSongRepository.getPlaylistWithSongs(playlistId).collect { playlistWithSongs ->
                _uiState.value = PlaylistSongsUiState.Success(
                    songs = playlistWithSongs.songs
                )
            }
        }
    }

    fun loadAllSongs() {
        viewModelScope.launch {
            songRepository.getAllSongs().collect { songs ->
                _allSongs.value = songs
            }
        }
    }

    fun addSongToPlaylist(playlistId: Int, songId: Int) {
        viewModelScope.launch {
            playlistSongRepository.addSongToPlaylist(playlistId, songId)
        }
    }

    fun removeSongFromPlaylist(playlistId: Int, songId: Int) {
        viewModelScope.launch {
            playlistSongRepository.removeSongFromPlaylist(playlistId, songId)
        }
    }
}