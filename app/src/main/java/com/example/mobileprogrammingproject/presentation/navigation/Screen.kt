package com.example.mobileprogrammingproject.presentation.navigation

sealed class Screen(val route: String){
    data object AboutUsScreen: Screen("about_us_screen")
    object Dashboard : Screen("dashboard_screen/{firstName}/{lastName}/{userId}") {
        fun createRoute(firstName: String, lastName: String, userId: Int) =
            "dashboard_screen/$firstName/$lastName/$userId"
    }
    data object Login: Screen("login_screen")
    data object PlaylistDetails: Screen("playlist_details_screen")
    data object PlaylistScreen : Screen("playlist_screen")

    data object MyPlaylists : Screen("my_playlists_screen/{userId}") {
        fun createRoute(userId: Int) = "my_playlists_screen/$userId"
    }
    data object PlaylistSongs : Screen("playlist_songs_screen/{playlistId}/{playlistName}") {
        fun createRoute(playlistId: Int, playlistName: String) =
            "playlist_songs_screen/$playlistId/${playlistName.replace("/", " ")}"
    }
    data object RecentlyPlayed: Screen("recently_played_screen")
    data object SignIn: Screen("sign_in_screen")
    data object SongSearch: Screen("song_search_screen")
}