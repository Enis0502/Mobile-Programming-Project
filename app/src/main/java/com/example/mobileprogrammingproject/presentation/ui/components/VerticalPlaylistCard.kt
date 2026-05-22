package com.example.mobileprogrammingproject.presentation.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.mobileprogrammingproject.R
import com.example.mobileprogrammingproject.presentation.data.Playlist
import com.example.mobileprogrammingproject.presentation.data.Song
import androidx.compose.material.icons.filled.*

val testObject2 = Playlist(
    1,
    "Zabranjeno pusenje",
    listOf(Song(1, "Murga drot", "Nele", "3:13"),
        Song(1, "Murga drot", "Nele", "3:13")),
    "12:11")

@Composable
fun VerticalPlaylistCard(playlist: Playlist){
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .width(200.dp)
            .aspectRatio(2f / 3f)
            .background(Color.LightGray,
                shape = RoundedCornerShape(16.dp))
            .padding(8.dp)) {
        Image(
            painter = painterResource(id = R.drawable.playlistcover),
            contentDescription = "Vertical playlist cover",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxWidth()
                .clip(shape = RoundedCornerShape(16.dp))
        )
        Column(modifier = Modifier.fillMaxWidth()) {


            Text(text = playlist.title,
                style = MaterialTheme.typography.bodyLarge,
                fontWeight = FontWeight.Bold)
            Text(text = calculateTotalDuration(playlist))
            LinearProgressIndicator(
                progress = 0.2f,
                modifier = Modifier
                    .fillMaxWidth(),
                color = Color.Black,
                trackColor = Color.White
            )
            Row(modifier = Modifier
                .fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    imageVector = Icons.Filled.KeyboardArrowLeft,
                    contentDescription = "Skip to previous song",
                    modifier = Modifier.size(30.dp)
                )

                Icon(
                    imageVector = Icons.Default.PlayArrow,
                    contentDescription = "Pause Playlist",
                    modifier = Modifier.size(40.dp)
                )

                Icon(
                    imageVector = Icons.Filled.KeyboardArrowRight,
                    contentDescription = "Skip to next song",
                    modifier = Modifier.size(30.dp)
                )
            }
        }
    }

}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun VerticalPlaylistCardPreview(){
    MaterialTheme{
        VerticalPlaylistCard(testObject2)
    }
}