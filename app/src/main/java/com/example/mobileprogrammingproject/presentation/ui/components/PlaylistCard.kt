package com.example.mobileprogrammingproject.presentation.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.mobileprogrammingproject.R
import com.example.mobileprogrammingproject.presentation.data.Playlist
import com.example.mobileprogrammingproject.presentation.data.Song

//class PlaylistCard {
//}


fun convertDurationsInSeconds(song: Song): Int{
    var songDurationInSeconds = 0
    val separated = song.duration.split(":")
    songDurationInSeconds += separated[0].toInt()*60 + separated[1].toInt()
    return songDurationInSeconds
}

fun calculateTotalDuration(playlist: Playlist): String{
    var totalPlaylistDuration = 0

    playlist.songList.forEach {
        song ->  totalPlaylistDuration += convertDurationsInSeconds(song)}

    val minutes = totalPlaylistDuration / 60
    val seconds = totalPlaylistDuration % 60

    val returnValue = "$minutes:$seconds"
    return returnValue
}

val testObject = Playlist(
    1,
    "Zabranjeno pusenje",
    listOf(Song(1, "Murga drot", "Nele", "3:13"),
        Song(1, "Murga drot", "Nele", "3:13")),
    "12:11")
@Composable
fun PlaylistCard(playlist: Playlist){
    Column(modifier = Modifier
        .fillMaxWidth()
        .padding(8.dp)
        .background(color = Color.LightGray,
            shape = RoundedCornerShape(16.dp))
        .height(80.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center) {

        Row(horizontalArrangement = Arrangement.SpaceBetween,

            modifier = Modifier.fillMaxWidth()
                .padding(8.dp)) {
            Image(
                painter = painterResource(id = R.drawable.playlistcover),
                contentDescription = "Playlist Cover",
                modifier = Modifier
                    .clip(shape = RoundedCornerShape(16.dp)
                    )
            )

            Text(text = playlist.title,)
            Text(text = calculateTotalDuration(playlist))
        }
    }
}


@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PlaylistCardPreview(){
    MaterialTheme{
        PlaylistCard(testObject)
    }
}