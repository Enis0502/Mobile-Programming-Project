package com.example.mobileprogrammingproject.presentation.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.mobileprogrammingproject.model.playlistsHardcoded
import com.example.mobileprogrammingproject.presentation.data.Playlist
import com.example.mobileprogrammingproject.presentation.ui.components.SetBackgroundGradient
import com.example.mobileprogrammingproject.presentation.ui.components.VerticalPlaylistCard
import com.example.mobileprogrammingproject.presentation.view_model.recentlyPlayed.RecentlyPlayedUiState
import com.example.mobileprogrammingproject.presentation.view_model.recentlyPlayed.RecentlyPlayedViewModel

@Composable
fun RecentlyPlayedScreen(viewModel: RecentlyPlayedViewModel = hiltViewModel()){
    val state by viewModel.uiState.collectAsStateWithLifecycle()

    when (state){
        is RecentlyPlayedUiState.Success -> {
            val successState = state as RecentlyPlayedUiState.Success

            RecentlyPlayedDisplay(
                topPlaylists = successState.topPlaylists,
                recentlyPlayed = successState.recentlyPlayed
            )
        }

        is RecentlyPlayedUiState.Loading -> {
            CircularProgressIndicator()
        }

        is RecentlyPlayedUiState.Error -> {
            Text((state as RecentlyPlayedUiState.Error).message)
        }

        else -> {
            //no op
        }
    }
}

@Composable
fun RecentlyPlayedDisplay(
    topPlaylists: List<Playlist>,
    recentlyPlayed: List<Playlist>
){
    Box(modifier = Modifier.fillMaxSize()){
        SetBackgroundGradient()
        LazyColumn(
            modifier = Modifier.fillMaxSize()
        ) {
            item{
                Text(
                    text = "Top Playlists",
                    style = MaterialTheme.typography.headlineLarge,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(8.dp,24.dp, 8.dp, 8.dp)
                )
            }
            item{
                LazyRow(
                    modifier = Modifier.padding(8.dp),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    items(topPlaylists) { playlist ->
                        VerticalPlaylistCard(playlist)
                    }
                }
            }
            item {
                Text(text = "Recently Played",
                    style = MaterialTheme.typography.headlineLarge,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(8.dp,24.dp, 8.dp, 8.dp)
                )
            }
            item {
                LazyRow(
                    modifier = Modifier.padding(8.dp),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    items(recentlyPlayed){
                        playlist -> VerticalPlaylistCard(playlist)
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun RecentlyPlayedScreenPreview(){
    MaterialTheme{
        RecentlyPlayedScreen()
    }
}