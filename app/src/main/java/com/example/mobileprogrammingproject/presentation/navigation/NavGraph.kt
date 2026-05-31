package com.example.mobileprogrammingproject.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.mobileprogrammingproject.presentation.ui.screens.*

@Composable
fun AppNavGraph(
    navController: NavHostController,
    modifier: Modifier = Modifier,
    startDestination: String = Screen.SignIn.route

) {
    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier
    ) {
        composable(Screen.AboutUsScreen.route) { AboutUsScreen() }

        composable(
            route = Screen.Dashboard.route,
            arguments = listOf(
                navArgument("firstName") { type = NavType.StringType },
                navArgument("lastName") { type = NavType.StringType },
                navArgument("userId") { type = NavType.IntType },
                navArgument("firebaseUserId") { type = NavType.StringType }
            )
        ) { backStackEntry ->
            val firstName = backStackEntry.arguments?.getString("firstName") ?: ""
            val lastName = backStackEntry.arguments?.getString("lastName") ?: ""
            val userId = backStackEntry.arguments?.getInt("userId") ?: 0
            val firebaseUserId = backStackEntry.arguments?.getString("firebaseUserId") ?: ""
            DashboardScreen(
                firstName = firstName,
                lastName = lastName,
                userId = userId,
                firebaseUserId = firebaseUserId,
                onNavigate = { navController.navigate(it) }
            )
        }

        composable(
            route = Screen.MyPlaylists.route,
            arguments = listOf(
                navArgument("userId") { type = NavType.IntType },
                navArgument("firebaseUserId") { type = NavType.StringType }
            )
        ) { backStackEntry ->
            val userId = backStackEntry.arguments?.getInt("userId") ?: 0
            val firebaseUserId = backStackEntry.arguments?.getString("firebaseUserId") ?: ""
            MyPlaylistsScreen(
                userId = userId,
                firebaseUserId = firebaseUserId,
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

        composable(Screen.Login.route) { LogInScreen(navController) }
        composable(Screen.PlaylistDetails.route) { PlaylistDetailScreen() }
        composable(Screen.PublicPlaylists.route) { PublicPlaylistsScreen() }
        composable(Screen.RecentlyPlayed.route) { RecentlyPlayedScreen() }
        composable(Screen.SignIn.route) { SignInScreen(navController) }
        composable(Screen.SongSearch.route) { SongDetailsScreen() }
    }
}