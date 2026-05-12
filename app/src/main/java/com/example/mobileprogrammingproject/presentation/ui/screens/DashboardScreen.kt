package com.example.mobileprogrammingproject.presentation.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.compose.rememberNavController
import com.example.mobileprogrammingproject.presentation.ui.components.SetBackgroundGradient
import com.example.mobileprogrammingproject.presentation.navigation.Screen
import com.example.mobileprogrammingproject.presentation.ui.components.DashboardOptionsCard
import com.example.mobileprogrammingproject.presentation.view_model.auth.login.LoginUiState
import com.example.mobileprogrammingproject.presentation.view_model.dashboard.DashboardUiState
import com.example.mobileprogrammingproject.presentation.view_model.dashboard.DashboardViewModel

@Composable
fun DashboardScreen(
    onNavigate: (String) -> Unit,
    viewModel: DashboardViewModel = hiltViewModel()
) {

    val state by viewModel.uiState.collectAsStateWithLifecycle()

    when(state) {

        is DashboardUiState.Loading -> {
            Text("Loading...")
        }

        is DashboardUiState.Success -> {

            val successState = state as DashboardUiState.Success

            DashboardDisplay(
                onNavigate = onNavigate,
                firstName = successState.firstName,
                lastName = successState.lastName
            )
        }

        else -> {}
    }
}
@Composable
fun DashboardDisplay(
    onNavigate: (String) -> Unit,
    firstName: String,
    lastName: String
) {

    SetBackgroundGradient()

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

        item{
            DashboardOptionsCard("View All Playlists", onClick = {
                onNavigate(Screen.PlaylistDetails.route)
            })
        }

        item{
            DashboardOptionsCard("Search For a Song", onClick = {
                onNavigate(Screen.SongSearch.route)
            })
        }

        item{
            DashboardOptionsCard("Log In", onClick = {
                onNavigate(Screen.Login.route)
            })
        }

        item{
            DashboardOptionsCard("Sign In", onClick = {
                onNavigate(Screen.SignIn.route)
            })
        }
    }
}


@Preview(showBackground = true, showSystemUi = true)
@Composable
fun DashboardScreenPreview(){
    MaterialTheme{
        DashboardDisplay(onNavigate = {}, "Enis", "Bulbulusic")
    }

}