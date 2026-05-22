package com.example.mobileprogrammingproject.presentation.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.mobileprogrammingproject.R
import com.example.mobileprogrammingproject.presentation.data.Song


@Composable
fun SongDetails(song: Song, isExpanded: Boolean, onExpandClick: () -> Unit){
    Column(modifier = Modifier
        .padding(8.dp, 0.dp, 8.dp, 8.dp)
        .fillMaxWidth()
        .clip(shape = RoundedCornerShape(16.dp))
        .background(color = Color.Gray)
        ){
        Row(modifier = Modifier
            .padding(8.dp)) {
            Image(
                painter = painterResource(id = R.drawable.imperia),
                contentDescription = "Song Cover",
                modifier = Modifier
                    .size(80.dp)
                    .clip(shape = RoundedCornerShape(16.dp))
            )
            Spacer(modifier = Modifier
                .width(16.dp)
                .height(80.dp))
            Column() {
                Text(text = song.title,
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold)
                Text(text = song.artist)
                Row(modifier = Modifier
                    .fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically) {
                    Text(text = song.duration)
                    Spacer(modifier = Modifier.weight(1f))
                    Icon(
                        imageVector = Icons.Default.KeyboardArrowDown,
                        contentDescription = "Expand song card",
                        modifier = Modifier
                            .size(40.dp)
                            .clickable{onExpandClick()}
                    )
                }
            }
        }
        if (isExpanded) {
            Column(
                modifier = Modifier.padding(8.dp)
            ) {
                Text(text = "Lyrics to be added later... Do not want to hardcode it")
            }
        }
    }
}

val obj = Song(1,"jala", "aaa", "2.2")

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun SongDetailsPreview() {

    var expanded by remember { mutableStateOf(false) }

    MaterialTheme {
        SongDetails(
            song = obj,
            isExpanded = expanded,
            onExpandClick = { expanded = !expanded }
        )
    }
}