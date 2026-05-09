package com.example.mobileprogrammingproject.presentation.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.rememberNavController
import com.example.mobileprogrammingproject.presentation.ui.components.SetBackgroundGradient
import com.example.mobileprogrammingproject.presentation.navigation.Screen

@Composable
fun DashboardScreen(
    onNavigate: (String) -> Unit,
    firstName: String,
    lastName: String
) {

    SetBackgroundGradient()

    val dashboardItems = listOf(
        "View All Playlists",
        "Search For a Song",
        "Log In",
        "Sign Up"
    )

    LazyColumn (
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {

        item {
            Text(
                text = "Music Organizer",
                style = MaterialTheme.typography.headlineLarge,
                fontWeight = FontWeight.Bold
            )
        }
        item {
            Text(text = "Welcome, $firstName $lastName")
        }
        items(dashboardItems) { item ->
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable {
                        when (item) {
                            "View All Playlists" ->
                                onNavigate(Screen.PlaylistDetails.route)

                            "Search For a Song" ->
                                onNavigate(Screen.SongSearch.route)

                            "Log In" ->
                                onNavigate(Screen.Login.route)

                            "Sign Up" ->
                                onNavigate(Screen.SignIn.route)
                        }
                    }
            ) {
                Text(
                    text = item,
                    modifier = Modifier
                        .padding(20.dp),
                    style = MaterialTheme.typography.titleMedium
                )
            }
        }
    }
}


@Preview(showBackground = true, showSystemUi = true)
@Composable
fun DashboardScreenPreview(){
    MaterialTheme{
        val navController = rememberNavController()
    }

}