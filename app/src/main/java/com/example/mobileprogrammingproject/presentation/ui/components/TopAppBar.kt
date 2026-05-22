package com.example.mobileprogrammingproject.presentation.ui.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.material3.*
import androidx.compose.runtime.getValue
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.mobileprogrammingproject.presentation.navigation.Screen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppTopBar(navController: NavHostController) {

    val currentBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = currentBackStackEntry?.destination?.route

    val showBackButton = currentRoute != Screen.Dashboard.route

    TopAppBar(
        title = {
            Text(
                text = when (currentRoute) {
                    Screen.Dashboard.route -> "Dashboard"
                    Screen.PlaylistScreen.route -> "Playlists"
                    Screen.RecentlyPlayed.route -> "Recently Played"
                    Screen.AboutUsScreen.route -> "About Us"
                    Screen.Login.route -> "Login"
                    Screen.SignIn.route -> "Sign Up"
                    Screen.SongSearch.route -> "Search"
                    else -> ""
                }
            )
        },
        navigationIcon = {
            if (showBackButton) {
                IconButton(onClick = {
                    navController.popBackStack()
                }) {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = "Back"
                    )
                }
            }
        }
    )
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun TopAppBarPreview(){
    MaterialTheme {
        val navController = rememberNavController()
        AppTopBar(navController)
    }
}