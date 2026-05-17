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
import androidx.compose.material3.Card
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
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
import com.example.mobileprogrammingproject.model.data.local.entity.SongEntity
import com.example.mobileprogrammingproject.presentation.ui.components.SetBackgroundGradient
import com.example.mobileprogrammingproject.presentation.view_model.playlistSongs.PlaylistSongsUiState
import com.example.mobileprogrammingproject.presentation.view_model.playlistSongs.PlaylistSongsViewModel

// STATEFUL
@Composable
fun PlaylistSongsScreen(
    playlistId: Int,
    playlistName: String,
    viewModel: PlaylistSongsViewModel = hiltViewModel()
) {
    val state by viewModel.uiState.collectAsStateWithLifecycle()
    val allSongs by viewModel.allSongs.collectAsStateWithLifecycle()

    LaunchedEffect(playlistId) {
        viewModel.loadPlaylistSongs(playlistId)
        viewModel.loadAllSongs()
    }

    when (state) {
        is PlaylistSongsUiState.Loading -> Text("Loading...")
        is PlaylistSongsUiState.Error -> Text((state as PlaylistSongsUiState.Error).message)
        is PlaylistSongsUiState.Success -> {
            val songs = (state as PlaylistSongsUiState.Success).songs
            PlaylistSongsDisplay(
                playlistName = playlistName,
                songs = songs,
                allSongs = allSongs,
                onAddSong = { viewModel.addSongToPlaylist(playlistId, it.id) },
                onRemoveSong = { viewModel.removeSongFromPlaylist(playlistId, it.id) }
            )
        }
        else -> {}
    }
}

// STATELESS
@Composable
fun PlaylistSongsDisplay(
    playlistName: String,
    songs: List<SongEntity>,
    allSongs: List<SongEntity>,
    onAddSong: (SongEntity) -> Unit,
    onRemoveSong: (SongEntity) -> Unit
) {
    var showAddDialog by remember { mutableStateOf(false) }

    SetBackgroundGradient()

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(onClick = { showAddDialog = true }) {
                Icon(Icons.Default.Add, contentDescription = "Add Song")
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
                    text = playlistName,
                    style = MaterialTheme.typography.headlineMedium,
                    fontWeight = FontWeight.Bold
                )
            }
            if (songs.isEmpty()) {
                item {
                    Text(
                        text = "No songs yet. Tap + to add songs.",
                        modifier = Modifier.padding(top = 16.dp)
                    )
                }
            } else {
                items(items = songs, key = { it.id }) { song ->
                    SongItemCard(
                        song = song,
                        onRemove = { onRemoveSong(song) }
                    )
                }
            }
        }
    }

    if (showAddDialog) {
        AddSongDialog(
            allSongs = allSongs,
            currentSongs = songs,
            onAddSong = {
                onAddSong(it)
                showAddDialog = false
            },
            onDismiss = { showAddDialog = false }
        )
    }
}

@Composable
fun SongItemCard(
    song: SongEntity,
    onRemove: () -> Unit
) {
    Card(modifier = Modifier.fillMaxWidth()) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Text(text = song.title, fontWeight = FontWeight.Bold)
                Text(text = song.artist, style = MaterialTheme.typography.bodySmall)
                Text(text = song.duration, style = MaterialTheme.typography.bodySmall)
            }
            IconButton(onClick = onRemove) {
                Icon(
                    Icons.Default.Delete,
                    contentDescription = "Remove",
                    tint = MaterialTheme.colorScheme.error
                )
            }
        }
    }
}

@Composable
fun AddSongDialog(
    allSongs: List<SongEntity>,
    currentSongs: List<SongEntity>,
    onAddSong: (SongEntity) -> Unit,
    onDismiss: () -> Unit
) {
    var searchQuery by remember { mutableStateOf("") }
    val currentSongIds = currentSongs.map { it.id }.toSet()

    val filteredSongs = allSongs.filter { song ->
        song.id !in currentSongIds &&
                (song.title.contains(searchQuery, ignoreCase = true) ||
                        song.artist.contains(searchQuery, ignoreCase = true))
    }

    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("Add Songs") },
        text = {
            Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                OutlinedTextField(
                    value = searchQuery,
                    onValueChange = { searchQuery = it },
                    label = { Text("Search songs") },
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth()
                )
                LazyColumn(
                    modifier = Modifier.fillMaxWidth(),
                    verticalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    if (filteredSongs.isEmpty()) {
                        item { Text("No songs found") }
                    } else {
                        items(items = filteredSongs, key = { it.id }) { song ->
                            Card(
                                modifier = Modifier.fillMaxWidth(),
                                onClick = { onAddSong(song) }
                            ) {
                                Column(modifier = Modifier.padding(12.dp)) {
                                    Text(text = song.title, fontWeight = FontWeight.Bold)
                                    Text(
                                        text = song.artist,
                                        style = MaterialTheme.typography.bodySmall
                                    )
                                }
                            }
                        }
                    }
                }
            }
        },
        confirmButton = {
            Button(onClick = onDismiss) { Text("Close") }
        }
    )
}