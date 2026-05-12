package com.example.mobileprogrammingproject.presentation.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.ViewModel
import com.example.mobileprogrammingproject.presentation.data.Playlist
import com.example.mobileprogrammingproject.model.playlistsHardcoded
import com.example.mobileprogrammingproject.presentation.ui.components.AppOutlinedTextField
import com.example.mobileprogrammingproject.presentation.ui.components.PlaylistCard
import com.example.mobileprogrammingproject.presentation.ui.components.SetBackgroundGradient
import com.example.mobileprogrammingproject.presentation.view_model.playlistDetails.PlaylistDetailsUiState
import com.example.mobileprogrammingproject.presentation.view_model.playlistDetails.PlaylistDetailsViewModel

@Composable
fun PlaylistDetailScreen(viewModel: PlaylistDetailsViewModel = hiltViewModel()){

    val state by viewModel.uiState.collectAsState()

    when (state) {

        is PlaylistDetailsUiState.Init -> {
            // optional loading screen or empty
        }

        is PlaylistDetailsUiState.Loading -> {
            // show loader if needed
        }

        is PlaylistDetailsUiState.Success -> {
            val successState = state as PlaylistDetailsUiState.Success

            PlaylistDetailDisplay(
                searchQuery = successState.searchQuery,
                onValueChange = { viewModel.onSearchQuery(it) },
                playlists = successState.playlists
            )
        }

        is PlaylistDetailsUiState.Error -> {
            val errorState = state as PlaylistDetailsUiState.Error
            // show error UI if needed
        }
    }
}

@Composable
fun PlaylistDetailDisplay(
    searchQuery: String,
    onValueChange: (String) -> Unit,
    playlists: List<Playlist>
){
    SetBackgroundGradient()
    Card(modifier = Modifier
        .padding(16.dp)
        .fillMaxWidth()) {
        Row(horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth()) {
            Text(text = "Playlists",
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold)
        }
        Column(modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)) {
            AppOutlinedTextField(
                value = searchQuery,
                onValueChange = onValueChange,
                label = "Search for a playlist",
                modifier = Modifier,
                singleLine = true
            )
        }
        HorizontalDivider(modifier = Modifier
            .padding(8.dp))

        LazyColumn (
            modifier = Modifier.fillMaxSize()
        ){
            if(playlists.isEmpty()){
                item(){
                    Text(text = "No playlists found",
                        modifier = Modifier.padding(16.dp))
                }
            }else{
                items(
                    items = playlists,
                    key = {it.id}
                ){
                    playlist ->
                    PlaylistCard(playlist)
                }
            }
        }
    }
}


@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PlaylistDetailScreenPreview(){
    MaterialTheme{
        PlaylistDetailScreen()
    }
}