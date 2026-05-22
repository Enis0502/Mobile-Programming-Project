package com.example.mobileprogrammingproject.presentation.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.mobileprogrammingproject.model.data.local.entity.PlaylistEntity
import com.example.mobileprogrammingproject.presentation.navigation.Screen
import com.example.mobileprogrammingproject.presentation.ui.components.SetBackgroundGradient
import com.example.mobileprogrammingproject.presentation.view_model.myPlaylists.MyPlaylistsUiState
import com.example.mobileprogrammingproject.presentation.view_model.myPlaylists.MyPlaylistsViewModel

@Composable
fun MyPlaylistsScreen(
    userId: Int,
    onNavigate: (String) -> Unit = {},
    viewModel: MyPlaylistsViewModel = hiltViewModel()
) {
    val state by viewModel.uiState.collectAsStateWithLifecycle()

    LaunchedEffect(userId) {
        viewModel.loadPlaylists(userId)
    }

    when (state) {
        is MyPlaylistsUiState.Loading -> Text("Loading...")
        is MyPlaylistsUiState.Error -> Text((state as MyPlaylistsUiState.Error).message)
        is MyPlaylistsUiState.Success -> {
            val playlists = (state as MyPlaylistsUiState.Success).playlists
            MyPlaylistsDisplay(
                playlists = playlists,
                onCreatePlaylist = { name, description ->
                    viewModel.createPlaylist(userId, name, description)
                },
                onDeletePlaylist = { viewModel.deletePlaylist(it) },
                onUpdatePlaylist = { viewModel.updatePlaylist(it) },
                onOpenPlaylist = { onNavigate(Screen.PlaylistSongs.createRoute(it.id, it.name)) }
            )
        }
        else -> {}
    }
}

@Composable
fun MyPlaylistsDisplay(
    playlists: List<PlaylistEntity>,
    onCreatePlaylist: (name: String, description: String) -> Unit,
    onDeletePlaylist: (PlaylistEntity) -> Unit,
    onUpdatePlaylist: (PlaylistEntity) -> Unit,
    onOpenPlaylist: (PlaylistEntity) -> Unit
) {
    var showCreateDialog by remember { mutableStateOf(false) }
    var editingPlaylist by remember { mutableStateOf<PlaylistEntity?>(null) }

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
                    PlaylistItemCard(
                        playlist = playlist,
                        onDelete = { onDeletePlaylist(playlist) },
                        onEdit = { editingPlaylist = playlist },
                        onOpen = { onOpenPlaylist(playlist) }
                    )
                }
            }
        }
    }

    if (showCreateDialog) {
        CreatePlaylistDialog(
            onConfirm = { name, description ->
                onCreatePlaylist(name, description)
                showCreateDialog = false
            },
            onDismiss = { showCreateDialog = false }
        )
    }

    editingPlaylist?.let { playlist ->
        EditPlaylistDialog(
            playlist = playlist,
            onConfirm = { updatedName, updatedDescription ->
                onUpdatePlaylist(playlist.copy(name = updatedName, description = updatedDescription))
                editingPlaylist = null
            },
            onDismiss = { editingPlaylist = null }
        )
    }
}

@Composable
fun PlaylistItemCard(
    playlist: PlaylistEntity,
    onDelete: () -> Unit,
    onEdit: () -> Unit,
    onOpen: () -> Unit
) {
    Card( modifier = Modifier.fillMaxWidth(),
        onClick = onOpen) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Text(text = playlist.name, fontWeight = FontWeight.Bold)
                if (playlist.description.isNotBlank()) {
                    Text(
                        text = playlist.description,
                        style = MaterialTheme.typography.bodySmall
                    )
                }
            }
            Row {
                TextButton(onClick = onEdit) {
                    Text("Edit")
                }
                IconButton(onClick = onDelete) {
                    Icon(
                        Icons.Default.Delete,
                        contentDescription = "Delete",
                        tint = MaterialTheme.colorScheme.error
                    )
                }
            }
        }
    }
}

@Composable
fun CreatePlaylistDialog(
    onConfirm: (name: String, description: String) -> Unit,
    onDismiss: () -> Unit
) {
    var name by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }

    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("Create Playlist") },
        text = {
            Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                OutlinedTextField(
                    value = name,
                    onValueChange = { name = it },
                    label = { Text("Name") },
                    singleLine = true
                )
                OutlinedTextField(
                    value = description,
                    onValueChange = { description = it },
                    label = { Text("Description") },
                    singleLine = true
                )
            }
        },
        confirmButton = {
            Button(onClick = { onConfirm(name, description) }) {
                Text("Create")
            }
        },
        dismissButton = {
            Button(
                onClick = onDismiss,
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.secondary
                )
            ) {
                Text("Cancel")
            }
        }
    )
}

@Composable
fun EditPlaylistDialog(
    playlist: PlaylistEntity,
    onConfirm: (name: String, description: String) -> Unit,
    onDismiss: () -> Unit
) {
    var name by remember { mutableStateOf(playlist.name) }
    var description by remember { mutableStateOf(playlist.description) }

    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("Edit Playlist") },
        text = {
            Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                OutlinedTextField(
                    value = name,
                    onValueChange = { name = it },
                    label = { Text("Name") },
                    singleLine = true
                )
                OutlinedTextField(
                    value = description,
                    onValueChange = { description = it },
                    label = { Text("Description") },
                    singleLine = true
                )
            }
        },
        confirmButton = {
            Button(onClick = { onConfirm(name, description) }) {
                Text("Save")
            }
        },
        dismissButton = {
            Button(
                onClick = onDismiss,
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.secondary
                )
            ) {
                Text("Cancel")
            }
        }
    )
}