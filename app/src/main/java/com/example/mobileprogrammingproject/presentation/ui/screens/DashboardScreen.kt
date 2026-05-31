package com.example.mobileprogrammingproject.presentation.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.mobileprogrammingproject.presentation.navigation.Screen
import com.example.mobileprogrammingproject.presentation.ui.components.DashboardOptionsCard
import com.example.mobileprogrammingproject.presentation.ui.components.SetBackgroundGradient
import com.example.mobileprogrammingproject.presentation.view_model.dashboard.DashboardUiState
import com.example.mobileprogrammingproject.presentation.view_model.dashboard.DashboardViewModel
import com.google.firebase.auth.FirebaseAuth

@Composable
fun DashboardScreen(
    firstName: String,
    lastName: String,
    userId: Int,
    firebaseUserId: String = "",
    onNavigate: (String) -> Unit,
    viewModel: DashboardViewModel = hiltViewModel()
) {
    val state by viewModel.uiState.collectAsStateWithLifecycle()

    LaunchedEffect(firstName, lastName, userId) {
        viewModel.loadUser(firstName, lastName, userId)
    }

    when (state) {
        is DashboardUiState.Loading -> Text("Loading...")
        is DashboardUiState.Success -> {
            val successState = state as DashboardUiState.Success
            DashboardDisplay(
                onNavigate = onNavigate,
                firstName = successState.firstName,
                lastName = successState.lastName,
                userId = successState.userId,
                firebaseUserId = firebaseUserId
            )
        }
        else -> {}
    }
}

@Composable
fun DashboardDisplay(
    onNavigate: (String) -> Unit,
    firstName: String,
    lastName: String,
    userId: Int = 0,
    firebaseUserId: String = ""
) {
    SetBackgroundGradient()

    LazyColumn(
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
        item {
            DashboardOptionsCard("My Playlists", onClick = {
                onNavigate(Screen.MyPlaylists.createRoute(userId, firebaseUserId))
            })
        }
        item {
            DashboardOptionsCard("Public Playlists", onClick = {
                onNavigate(Screen.PublicPlaylists.route)
            })
        }
        item {
            DashboardOptionsCard("View All Playlists", onClick = {
                onNavigate(Screen.PlaylistDetails.route)
            })
        }
        item {
            DashboardOptionsCard("Search For a Song", onClick = {
                onNavigate(Screen.SongSearch.route)
            })
        }
        item {
            DashboardOptionsCard("Recently Played", onClick = {
                onNavigate(Screen.RecentlyPlayed.route)
            })
        }
        item {
            DashboardOptionsCard("Logout", onClick = {
                FirebaseAuth.getInstance().signOut()
                onNavigate(Screen.SignIn.route)
            }, isDestructive = true)
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun DashboardScreenPreview() {
    MaterialTheme {
        DashboardDisplay(onNavigate = {}, "Enis", "Bulbulusic")
    }
}