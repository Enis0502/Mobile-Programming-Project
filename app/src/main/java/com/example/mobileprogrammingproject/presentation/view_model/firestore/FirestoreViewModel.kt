package com.example.mobileprogrammingproject.presentation.view_model.firestore

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mobileprogrammingproject.model.data.repository.mappers.FirestorePlaylist
import com.example.mobileprogrammingproject.model.data.repository.mappers.FirestoreRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

sealed interface FirestoreUiState {
    data object Loading : FirestoreUiState
    data class Success(val playlists: List<FirestorePlaylist>) : FirestoreUiState
    data class Error(val message: String) : FirestoreUiState
}

@HiltViewModel
class FirestoreViewModel @Inject constructor(
    private val firestoreRepository: FirestoreRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow<FirestoreUiState>(FirestoreUiState.Loading)
    val uiState: StateFlow<FirestoreUiState> = _uiState

    fun loadPlaylists(userId: String) {
        viewModelScope.launch {
            firestoreRepository.getPlaylistsForUser(userId).collect { playlists ->
                _uiState.value = FirestoreUiState.Success(playlists)
            }
        }
    }

    fun createPlaylist(userId: String, name: String, description: String) {
        if (name.isBlank()) {
            _uiState.value = FirestoreUiState.Error("Playlist name cannot be empty")
            return
        }
        viewModelScope.launch {
            val result = firestoreRepository.createPlaylist(userId, name, description)
            if (result.isFailure) {
                _uiState.value = FirestoreUiState.Error(
                    result.exceptionOrNull()?.message ?: "Failed to create playlist"
                )
            }
        }
    }

    fun updatePlaylist(id: String, name: String, description: String) {
        viewModelScope.launch {
            val result = firestoreRepository.updatePlaylist(id, name, description)
            if (result.isFailure) {
                _uiState.value = FirestoreUiState.Error(
                    result.exceptionOrNull()?.message ?: "Failed to update playlist"
                )
            }
        }
    }

    fun deletePlaylist(id: String) {
        viewModelScope.launch {
            val result = firestoreRepository.deletePlaylist(id)
            if (result.isFailure) {
                _uiState.value = FirestoreUiState.Error(
                    result.exceptionOrNull()?.message ?: "Failed to delete playlist"
                )
            }
        }
    }
}