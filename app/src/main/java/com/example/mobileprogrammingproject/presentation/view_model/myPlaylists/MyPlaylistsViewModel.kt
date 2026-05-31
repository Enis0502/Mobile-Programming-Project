package com.example.mobileprogrammingproject.presentation.view_model.myPlaylists

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mobileprogrammingproject.model.data.repository.mappers.FirestorePlaylist
import com.example.mobileprogrammingproject.model.data.repository.mappers.FirestoreRepository
import com.example.mobileprogrammingproject.model.datasource.network.dto.PlaylistDto
import com.example.mobileprogrammingproject.model.datasource.network.service.MockApiService
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MyPlaylistsViewModel @Inject constructor(
    private val firestoreRepository: FirestoreRepository,
    private val mockApiService: MockApiService      // ← add this

) : ViewModel() {

    private val _uiState = MutableStateFlow<MyPlaylistsUiState>(MyPlaylistsUiState.Loading)
    val uiState: StateFlow<MyPlaylistsUiState> = _uiState

    fun loadPlaylists(userId: String) {
        viewModelScope.launch {
            firestoreRepository.getPlaylistsForUser(userId).collect { playlists ->
                _uiState.value = MyPlaylistsUiState.Success(playlists)
            }
        }
    }

    fun createPlaylist(userId: String, name: String, description: String) {
        if (name.isBlank()) {
            _uiState.value = MyPlaylistsUiState.Error("Playlist name cannot be empty")
            return
        }
        viewModelScope.launch {
            //Firestore
            val result = firestoreRepository.createPlaylist(userId, name, description)
            if (result.isFailure) {
                _uiState.value = MyPlaylistsUiState.Error(
                    result.exceptionOrNull()?.message ?: "Failed to create playlist"
                )
                return@launch
            }
            // Retrofit POST
            try {
                val response = mockApiService.createPlaylist(
                    PlaylistDto(id = 0L, title = name, picture = null)
                )
                android.util.Log.d("MockAPI", "POST success: ${response.title}")
            } catch (e: Exception) {
                android.util.Log.e("MockAPI", "POST failed: ${e.message}")
            }
        }
    }

    fun updatePlaylist(playlist: FirestorePlaylist, newName: String, newDescription: String) {
        viewModelScope.launch {
            //Firestore
            val result = firestoreRepository.updatePlaylist(playlist.id, newName, newDescription)
            if (result.isFailure) {
                _uiState.value = MyPlaylistsUiState.Error(
                    result.exceptionOrNull()?.message ?: "Failed to update playlist"
                )
                return@launch
            }
            // Retrofit PUT
            try {
                val response = mockApiService.updatePlaylist(
                    id = 1L,
                    playlist = PlaylistDto(id = 1L, title = newName, picture = null)
                )
                android.util.Log.d("MockAPI", "PUT success: ${response.title}")
            } catch (e: Exception) {
                android.util.Log.e("MockAPI", "PUT failed: ${e.message}")
            }
        }
    }

    fun deletePlaylist(playlist: FirestorePlaylist) {
        viewModelScope.launch {
            // Real data Firestore
            val result = firestoreRepository.deletePlaylist(playlist.id)
            if (result.isFailure) {
                _uiState.value = MyPlaylistsUiState.Error(
                    result.exceptionOrNull()?.message ?: "Failed to delete playlist"
                )
                return@launch
            }
            // Retrofit DELETE
            try {
                mockApiService.deletePlaylist(id = 1L)
                android.util.Log.d("MockAPI", "DELETE success")
            } catch (e: Exception) {
                android.util.Log.e("MockAPI", "DELETE failed: ${e.message}")
            }
        }
    }
}