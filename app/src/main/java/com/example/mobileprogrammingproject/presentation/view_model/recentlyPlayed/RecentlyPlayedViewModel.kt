package com.example.mobileprogrammingproject.presentation.view_model.recentlyPlayed

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mobileprogrammingproject.model.data.repository.mappers.FirestoreRepository
import com.example.mobileprogrammingproject.presentation.data.Playlist
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RecentlyPlayedViewModel @Inject constructor(
    private val firestoreRepository: FirestoreRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow<RecentlyPlayedUiState>(RecentlyPlayedUiState.Loading)
    val uiState: StateFlow<RecentlyPlayedUiState> = _uiState

    init {
        loadPlaylists()
    }

    private fun loadPlaylists() {
        val userId = FirebaseAuth.getInstance().currentUser?.uid ?: return
        viewModelScope.launch {
            firestoreRepository.getPlaylistsForUser(userId).collect { firestorePlaylists ->
                val playlists = firestorePlaylists.mapIndexed { index, fp ->
                    Playlist(
                        id = index,
                        title = fp.name,
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