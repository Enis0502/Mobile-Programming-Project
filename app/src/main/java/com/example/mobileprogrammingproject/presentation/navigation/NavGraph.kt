package com.example.mobileprogrammingproject.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.mobileprogrammingproject.presentation.ui.screens.AboutUsScreen
import com.example.mobileprogrammingproject.presentation.ui.screens.DashboardScreen
import com.example.mobileprogrammingproject.presentation.ui.screens.LogInScreen
import com.example.mobileprogrammingproject.presentation.ui.screens.MyPlaylistsScreen
import com.example.mobileprogrammingproject.presentation.ui.screens.PlaylistDetailScreen
import com.example.mobileprogrammingproject.presentation.ui.screens.RecentlyPlayedScreen
import com.example.mobileprogrammingproject.presentation.ui.screens.SignInScreen
import com.example.mobileprogrammingproject.presentation.ui.screens.SongDetailsScreen
import com.example.mobileprogrammingproject.presentation.ui.screens.PlaylistSongsScreen

@Composable
fun AppNavGraph(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = navController,
        startDestination = Screen.SignIn.route,
        modifier = modifier
    ) {
        composable(Screen.AboutUsScreen.route) {
            AboutUsScreen()
        }

        composable(
            route = Screen.Dashboard.route,
            arguments = listOf(
                navArgument("firstName") { type = NavType.StringType },
                navArgument("lastName") { type = NavType.StringType },
                navArgument("userId") { type = NavType.IntType }
            )
        ) { backStackEntry ->
            val firstName = backStackEntry.arguments?.getString("firstName") ?: ""
            val lastName = backStackEntry.arguments?.getString("lastName") ?: ""
            val userId = backStackEntry.arguments?.getInt("userId") ?: 0
            DashboardScreen(
                firstName = firstName,
                lastName = lastName,
                userId = userId,
                onNavigate = { navController.navigate(it) }
            )
        }

        composable(
            route = Screen.PlaylistSongs.route,
            arguments = listOf(
                navArgument("playlistId") { type = NavType.IntType },
                navArgument("playlistName") { type = NavType.StringType }
            )
        ) { backStackEntry ->
            val playlistId = backStackEntry.arguments?.getInt("playlistId") ?: 0
            val playlistName = backStackEntry.arguments?.getString("playlistName") ?: ""
            PlaylistSongsScreen(playlistId = playlistId, playlistName = playlistName)
        }

        composable(Screen.Login.route) {
            LogInScreen(navController)
        }

        composable(Screen.PlaylistDetails.route) {
            PlaylistDetailScreen()
        }

        composable(
            route = Screen.MyPlaylists.route,
            arguments = listOf(
                navArgument("userId") { type = NavType.IntType }
            )
        ) { backStackEntry ->
            val userId = backStackEntry.arguments?.getInt("userId") ?: 0
            MyPlaylistsScreen(
                userId = userId,
                onNavigate = { navController.navigate(it) }
            )
        }

        composable(Screen.RecentlyPlayed.route) {
            RecentlyPlayedScreen()
        }

        composable(Screen.SignIn.route) {
            SignInScreen(navController)
        }

        composable(Screen.SongSearch.route) {
            SongDetailsScreen()
        }
    }
}