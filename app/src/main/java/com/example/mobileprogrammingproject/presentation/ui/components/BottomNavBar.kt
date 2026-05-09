package com.example.mobileprogrammingproject.presentation.ui.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.mobileprogrammingproject.presentation.navigation.Screen

@Composable
fun BottomNavBar(navController: NavHostController) {

    val currentBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = currentBackStackEntry?.destination?.route

    NavigationBar {
        NavigationBarItem(
            selected = currentRoute == Screen.Dashboard.route,
            onClick = {
                navController.navigate(Screen.Dashboard.route) {
                    popUpTo(navController.graph.startDestinationId)
                    launchSingleTop = true
                }
            },
            icon = { Icon(Icons.Default.Home, contentDescription = "Dashboard") },
            label = { Text("Dashboard") }
        )

        NavigationBarItem(
            selected = currentRoute == Screen.PlaylistDetails.route,
            onClick = {
                navController.navigate(Screen.PlaylistDetails.route) {
                    popUpTo(navController.graph.startDestinationId)
                    launchSingleTop = true
                }
            },
            icon = { Icon(Icons.Default.List, contentDescription = "Playlist") },
            label = { Text("Playlist") }
        )

        NavigationBarItem(
            selected = currentRoute == Screen.RecentlyPlayed.route,
            onClick = {
                navController.navigate(Screen.RecentlyPlayed.route) {
                    popUpTo(navController.graph.startDestinationId)
                    launchSingleTop = true
                }
            },
            icon = { Icon(Icons.Default.Share, contentDescription = "Recently Played") },
            label = { Text("Recent") }
        )

        NavigationBarItem(
            selected = currentRoute == Screen.AboutUsScreen.route,
            onClick = {
                navController.navigate(Screen.AboutUsScreen.route) {
                    popUpTo(navController.graph.startDestinationId)
                    launchSingleTop = true
                }
            },
            icon = { Icon(Icons.Default.Info, contentDescription = "About") },
            label = { Text("About") }
        )
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun BottomNavBarPreview(){
    MaterialTheme{
        val navController = rememberNavController()
        BottomNavBar(navController)
    }
}