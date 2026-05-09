package com.example.mobileprogrammingproject.presentation.navigation

sealed class Screen(val route: String){
    data object AboutUsScreen: Screen("about_us_screen")
    data object Dashboard : Screen("dashboard_screen/{firstName}/{lastName}") {
        fun createRoute(firstName: String, lastName: String) =
            "dashboard_screen/$firstName/$lastName"
    }
    data object Login: Screen("login_screen")
    data object PlaylistDetails: Screen("playlist_details_screen")
    data object PlaylistScreen: Screen("playlist_screen")
    data object RecentlyPlayed: Screen("recently_played_screen")
    data object SignIn: Screen("sign_in_screen")
    data object SongSearch: Screen("song_search_screen")
}