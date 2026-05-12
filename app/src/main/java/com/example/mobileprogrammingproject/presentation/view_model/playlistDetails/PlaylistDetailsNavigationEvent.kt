package com.example.mobileprogrammingproject.presentation.view_model.playlistDetails

sealed interface PlaylistDetailsNavigationEvent {
    data object Navigate: PlaylistDetailsNavigationEvent
    data object NavigateBack: PlaylistDetailsNavigationEvent
}