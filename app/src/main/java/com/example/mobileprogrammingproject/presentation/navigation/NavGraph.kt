package com.example.mobileprogrammingproject.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.mobileprogrammingproject.presentation.ui.screens.PlaylistDetailScreen
import com.example.mobileprogrammingproject.presentation.ui.screens.SongDetailsScreen
import com.example.mobileprogrammingproject.presentation.ui.screens.AboutUsScreen
import com.example.mobileprogrammingproject.presentation.ui.screens.DashboardDisplay
import com.example.mobileprogrammingproject.presentation.ui.screens.DashboardScreen
import com.example.mobileprogrammingproject.presentation.ui.screens.LogInScreen
import com.example.mobileprogrammingproject.presentation.ui.screens.RecentlyPlayedScreen
import com.example.mobileprogrammingproject.presentation.ui.screens.SignInScreen

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
            route = Screen.Dashboard.route
        ) { backStackEntry ->

            val firstName =
                backStackEntry.arguments?.getString("firstName") ?: ""

            val lastName =
                backStackEntry.arguments?.getString("lastName") ?: ""

            DashboardDisplay(
                firstName = firstName,
                lastName = lastName,
                onNavigate = { navController.navigate(it) }
            )
        }

        composable(Screen.Login.route) {
            LogInScreen(navController)
        }

        composable(Screen.PlaylistDetails.route) {
            PlaylistDetailScreen()
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