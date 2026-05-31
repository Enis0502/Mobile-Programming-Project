package com.example.mobileprogrammingproject.presentation.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.mobileprogrammingproject.model.datasource.network.dto.PlaylistDto
import com.example.mobileprogrammingproject.presentation.ui.components.PublicPlaylistCard
import com.example.mobileprogrammingproject.presentation.ui.components.SetBackgroundGradient
import com.example.mobileprogrammingproject.presentation.view_model.publicPlaylists.PublicPlaylistsUiState
import com.example.mobileprogrammingproject.presentation.view_model.publicPlaylists.PublicPlaylistsViewModel

@Composable
fun PublicPlaylistsScreen(
    viewModel: PublicPlaylistsViewModel = hiltViewModel()
) {
    val state by viewModel.uiState.collectAsStateWithLifecycle()

    SetBackgroundGradient()

    when (state) {
        is PublicPlaylistsUiState.Loading -> {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator()
            }
        }
        is PublicPlaylistsUiState.Error -> {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(
                        text = (state as PublicPlaylistsUiState.Error).message,
                        color = MaterialTheme.colorScheme.error
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Button(onClick = { viewModel.loadTopPlaylists() }) {
                        Text("Retry")
                    }
                }
            }
        }
        is PublicPlaylistsUiState.Success -> {
            val playlists = (state as PublicPlaylistsUiState.Success).playlists
            PublicPlaylistsDisplay(playlists = playlists)
        }
    }
}

@Composable
fun PublicPlaylistsDisplay(playlists: List<PlaylistDto>) {
    Scaffold(
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
                    text = "Public Playlists",
                    style = MaterialTheme.typography.headlineMedium,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = "Top charts from Deezer",
                    style = MaterialTheme.typography.bodySmall,
                    modifier = Modifier.padding(bottom = 8.dp)
                )
            }
            items(items = playlists, key = { it.id }) { playlist ->
                PublicPlaylistCard(playlist = playlist)
            }
        }
    }
}
