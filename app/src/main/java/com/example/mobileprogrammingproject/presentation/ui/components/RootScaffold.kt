package com.example.mobileprogrammingproject.presentation.ui.components

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.example.mobileprogrammingproject.presentation.navigation.AppNavGraph

@Composable
fun RootScreen() {

    val navController = rememberNavController()

    Scaffold(
        topBar = {
            AppTopBar(navController)
        },
        bottomBar = {
            BottomNavBar(navController)
        }
    ) { paddingValues ->

        AppNavGraph(
            navController = navController,
            modifier = Modifier.padding(paddingValues)
        )
    }
}