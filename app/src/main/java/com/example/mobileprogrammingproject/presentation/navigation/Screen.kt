package com.example.mobileprogrammingproject.presentation.navigation

sealed class Screen(val route: String) {
    data object AboutUsScreen : Screen("about_us_screen")
    object Dashboard : Screen("dashboard_screen/{firstName}/{lastName}/{userId}/{firebaseUserId}") {
        fun createRoute(firstName: String, lastName: String, userId: Int, firebaseUserId: String) =
            "dashboard_screen/$firstName/$lastName/$userId/$firebaseUserId"
    }
    data object Login : Screen("login_screen")
    data object PlaylistDetails : Screen("playlist_details_screen")
    data object PlaylistScreen : Screen("playlist_screen")
    data object MyPlaylists : Screen("my_playlists_screen/{userId}/{firebaseUserId}") {
        fun createRoute(userId: Int, firebaseUserId: String) =
            "my_playlists_screen/$userId/$firebaseUserId"
    }
    data object PlaylistSongs : Screen("playlist_songs_screen/{playlistId}/{playlistName}") {
        fun createRoute(playlistId: Int, playlistName: String) =
            "playlist_songs_screen/$playlistId/${playlistName.replace("/", " ")}"
    }
    data object PublicPlaylists : Screen("public_playlists_screen")
    data object RecentlyPlayed : Screen("recently_played_screen")
    data object SignIn : Screen("sign_in_screen")
    data object SongSearch : Screen("song_search_screen")
}