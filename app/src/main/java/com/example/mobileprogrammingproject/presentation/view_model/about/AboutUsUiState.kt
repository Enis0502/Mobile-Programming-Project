package com.example.mobileprogrammingproject.presentation.view_model.about

sealed interface AboutUsUiState{
    data object Init: AboutUsUiState

    data object Loading: AboutUsUiState

    data object Success: AboutUsUiState

    data class Error (
        val message: String
    ): AboutUsUiState
}