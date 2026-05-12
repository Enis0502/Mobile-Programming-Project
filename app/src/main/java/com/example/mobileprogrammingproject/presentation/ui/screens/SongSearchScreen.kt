package com.example.mobileprogrammingproject.presentation.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Card
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.mobileprogrammingproject.presentation.data.Song
import com.example.mobileprogrammingproject.model.songs
import com.example.mobileprogrammingproject.presentation.ui.components.AppOutlinedTextField
import com.example.mobileprogrammingproject.presentation.ui.components.SetBackgroundGradient
import com.example.mobileprogrammingproject.presentation.ui.components.SongDetails
import com.example.mobileprogrammingproject.presentation.view_model.playlistDetails.PlaylistDetailsUiState
import com.example.mobileprogrammingproject.presentation.view_model.songSearch.SongSearchUiState
import com.example.mobileprogrammingproject.presentation.view_model.songSearch.SongSearchViewModel
import kotlinx.coroutines.launch

@Composable
fun SongDetailsScreen(viewModel: SongSearchViewModel = hiltViewModel()){
    val listState = rememberLazyListState()
    val scope = rememberCoroutineScope()

    val state by viewModel.uiState.collectAsState()

    var expandedSongId by remember { mutableStateOf<Int?>(null) }

    when(state){
        is SongSearchUiState.Init -> {

        }
        is SongSearchUiState.Success -> {
            val successState = state as SongSearchUiState.Success
            Scaffold(
                floatingActionButton = {
                    FloatingActionButton(
                        onClick = {
                            scope.launch {
                                listState.animateScrollToItem(0)
                            }
                        }
                    ) {
                        Icon(
                            imageVector = Icons.Default.KeyboardArrowUp,
                            contentDescription = "Scroll to top",
                            modifier = Modifier.size(30.dp)
                        )
                    }
                }
            ) { padding ->

                SongDetailsDisplay(
                    searchQuery = successState.searchQuery ,
                    onValueChange = { viewModel.searchSongs(it) },
                    displayedSongs = successState.songList,
                    isExpanded = expandedSongId,
                    onExpandClick = { id ->
                        expandedSongId = if (expandedSongId == id) null else id
                    },
                    listState = listState,
                    onScrollToTop = {
                        scope.launch {
                            listState.animateScrollToItem(0)
                        }
                    },
                    modifier = Modifier.padding(padding)
                )
            }
        }else -> {}
    }

}

@Composable
fun SongDetailsDisplay(
    searchQuery: String,
    onValueChange: (String) -> Unit,
    displayedSongs: List<Song>,
    isExpanded: Int?,
    onExpandClick: (Int) -> Unit,
    listState: LazyListState,
    onScrollToTop : () -> Unit,
    modifier: Modifier = Modifier,
){
    SetBackgroundGradient()
    Card(modifier = Modifier
        .padding(16.dp, 24.dp)
        .fillMaxWidth()
        .shadow(elevation = 8.dp)) {
        Row(horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth()) {
            Text(text = "Playlist Name",
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold)
        }
        Column(modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)) {
            AppOutlinedTextField(
                value = searchQuery,
                onValueChange = onValueChange,
                label = "Search for a song",
                modifier = Modifier,
                singleLine = true
            )
        }
        HorizontalDivider(modifier = Modifier
            .padding(8.dp))

        LazyColumn(
            modifier = Modifier.fillMaxWidth(),
            state = listState) {
            if (displayedSongs.isEmpty()){
                item{
                    Text(text = "No songs found.")
                }
            }else{
                items(
                    items = displayedSongs,
                    key = { it.id }
                ) { displayedSong ->

                    SongDetails(
                        song = displayedSong,
                        isExpanded = isExpanded == displayedSong.id,
                        onExpandClick = { onExpandClick(displayedSong.id) }
                    )
                }
            }
        }
    }
}


@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PlaylistDetailsCardPreview(){
    MaterialTheme{
        SongDetailsScreen()
    }
}