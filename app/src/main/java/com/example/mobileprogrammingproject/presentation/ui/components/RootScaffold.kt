package com.example.mobileprogrammingproject.presentation.ui.components

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.mobileprogrammingproject.presentation.navigation.AppNavGraph
import com.example.mobileprogrammingproject.presentation.navigation.Screen
import com.google.firebase.auth.FirebaseAuth

@Composable
fun RootScreen() {
    val navController = rememberNavController()

    val startDestination = remember {
        val currentUser = FirebaseAuth.getInstance().currentUser
        if (currentUser != null) {
            Screen.Dashboard.createRoute(
                firstName = currentUser.displayName?.split(" ")?.firstOrNull() ?: "User",
                lastName = currentUser.displayName?.split(" ")?.lastOrNull() ?: "",
                userId = 0,
                firebaseUserId = currentUser.uid
            )
        } else {
            Screen.SignIn.route
        }
    }

    //routes where nav is hidden
    val authRoutes = setOf(
        Screen.SignIn.route,
        Screen.Login.route
    )

    val currentBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = currentBackStackEntry?.destination?.route
    
    val showBars = currentRoute != null && authRoutes.none { currentRoute.startsWith(it) }

    Scaffold(
        topBar = {
            if (showBars) AppTopBar(navController)
        },
        bottomBar = {
            if (showBars) BottomNavBar(navController)
        }
    ) { paddingValues ->
        AppNavGraph(
            navController = navController,
            startDestination = startDestination,
            modifier = Modifier.padding(paddingValues)
        )
    }
}