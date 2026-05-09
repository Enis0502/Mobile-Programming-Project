package com.example.mobileprogrammingproject.presentation.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Card
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.mobileprogrammingproject.R
import com.example.mobileprogrammingproject.presentation.ui.components.SetBackgroundGradient


@Composable
fun AboutUsScreen(){
    SetBackgroundGradient()
    Column() {
        Box(modifier = Modifier
            .clip(shape = RoundedCornerShape(0.dp, 0.dp, 16.dp, 16.dp))
            .fillMaxWidth()
            .background(brush = Brush.linearGradient(colors=listOf(Color.Blue, Color(0xFF90CAF9))
            ))
            .wrapContentHeight()){
            Column(modifier = Modifier
                .fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally) {
                Image(
                    painter = painterResource(id = R.drawable.logoimage),
                    contentDescription = "Logo image"
                )

                Text(text = "Music Organiser",
                    style = MaterialTheme.typography.headlineLarge,
                    fontWeight = FontWeight.Bold)
                Text(text = "Organize. Discover. Enjoy",
                    style = MaterialTheme.typography.bodyLarge
                )
                Spacer(modifier = Modifier.height(16.dp))
                Text(text = "Version 1.0.0")
                Spacer(modifier = Modifier.height(16.dp))
            }
        }
        Column(modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth()) {

            HorizontalDivider(modifier = Modifier.padding(0.dp, 16.dp, 0.dp, 16.dp))

            Card(modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth()
                .shadow(elevation = 8.dp)) {
                Box(modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .background(color = Color.Blue)){
                    Row(modifier = Modifier
                        .fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically) {
                        Box(modifier = Modifier
                            .padding(16.dp)
                            .clip(shape = CircleShape)
                            .background(color = Color.White)){
                            Icon(
                                imageVector = Icons.Default.Person,
                                contentDescription = "Profile picture",
                                modifier = Modifier.size(50.dp)
                            )
                        }
                        Spacer(modifier = Modifier.width(16.dp))

                        Column() {
                            Text(text = "Developed by Enis Bulbulusic",
                                style = MaterialTheme.typography.titleMedium,
                                color = Color.White
                            )
                            Text(text = "Android developer and music enthusiast",
                                style = MaterialTheme.typography.bodyMedium,
                                color = Color.White
                            )
                        }
                    }
                }

                Text(text = "This app is created to simplify your playlist management and make music management effortless",
                    modifier = Modifier.padding(8.dp))

            }
        }
    }


}

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun AboutUsScreenPreview(){
    MaterialTheme{
       AboutUsScreen()
    }
}