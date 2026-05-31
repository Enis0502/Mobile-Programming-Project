package com.example.mobileprogrammingproject.presentation.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.mobileprogrammingproject.model.data.repository.mappers.FirestorePlaylist
import com.example.mobileprogrammingproject.presentation.ui.components.CreatePlaylistDialog
import com.example.mobileprogrammingproject.presentation.ui.components.EditFirestorePlaylistDialog
import com.example.mobileprogrammingproject.presentation.ui.components.FirestorePlaylistCard
import com.example.mobileprogrammingproject.presentation.ui.components.SetBackgroundGradient
import com.example.mobileprogrammingproject.presentation.view_model.myPlaylists.MyPlaylistsUiState
import com.example.mobileprogrammingproject.presentation.view_model.myPlaylists.MyPlaylistsViewModel

@Composable
fun MyPlaylistsScreen(
    userId: Int,
    firebaseUserId: String,
    onNavigate: (String) -> Unit = {},
    viewModel: MyPlaylistsViewModel = hiltViewModel()
) {
    val state by viewModel.uiState.collectAsStateWithLifecycle()

    LaunchedEffect(firebaseUserId) {
        viewModel.loadPlaylists(firebaseUserId)
    }

    when (state) {
        is MyPlaylistsUiState.Loading -> {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator()
            }
        }
        is MyPlaylistsUiState.Error -> {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Text(
                    text = (state as MyPlaylistsUiState.Error).message,
                    color = MaterialTheme.colorScheme.error
                )
            }
        }
        is MyPlaylistsUiState.Success -> {
            val playlists = (state as MyPlaylistsUiState.Success).playlists
            MyPlaylistsDisplay(
                playlists = playlists,
                onCreatePlaylist = { name, description ->
                    viewModel.createPlaylist(firebaseUserId, name, description)
                },
                onDeletePlaylist = { viewModel.deletePlaylist(it) },
                onUpdatePlaylist = { playlist, name, desc ->
                    viewModel.updatePlaylist(playlist, name, desc)
                }
            )
        }
        else -> {}
    }
}

@Composable
fun MyPlaylistsDisplay(
    playlists: List<FirestorePlaylist>,
    onCreatePlaylist: (name: String, description: String) -> Unit,
    onDeletePlaylist: (FirestorePlaylist) -> Unit,
    onUpdatePlaylist: (FirestorePlaylist, String, String) -> Unit
) {
    var showCreateDialog by remember { mutableStateOf(false) }
    var editingPlaylist by remember { mutableStateOf<FirestorePlaylist?>(null) }

    SetBackgroundGradient()

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(onClick = { showCreateDialog = true }) {
                Icon(Icons.Default.Add, contentDescription = "Create Playlist")
            }
        },
        containerColor = MaterialTheme.colorScheme.background.copy(alpha = 0f)
    ) { padding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            item {
                Text(
                    text = "My Playlists",
                    style = MaterialTheme.typography.headlineMedium,
                    fontWeight = FontWeight.Bold
                )
            }
            if (playlists.isEmpty()) {
                item {
                    Text(
                        text = "No playlists yet. Tap + to create one.",
                        modifier = Modifier.padding(top = 16.dp)
                    )
                }
            } else {
                items(items = playlists, key = { it.id }) { playlist ->
                    FirestorePlaylistCard(
                        playlist = playlist,
                        onDelete = { onDeletePlaylist(playlist) },
                        onEdit = { editingPlaylist = playlist }
                    )
                }
            }
        }
    }

    if (showCreateDialog) {
        CreatePlaylistDialog(
            onConfirm = { name, description ->
                onCreatePlaylist(name, description)
            },
            onDismiss = { }
        )
    }

    editingPlaylist?.let { playlist ->
        EditFirestorePlaylistDialog(
            playlist = playlist,
            onConfirm = { name, desc ->
                onUpdatePlaylist(playlist, name, desc)
            },
            onDismiss = { }
        )
    }
}


