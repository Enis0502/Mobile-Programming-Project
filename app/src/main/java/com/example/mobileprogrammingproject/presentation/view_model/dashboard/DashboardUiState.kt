package com.example.mobileprogrammingproject.presentation.view_model.dashboard

sealed interface DashboardUiState{
    data object Init: DashboardUiState

    data object Loading: DashboardUiState

    data class Success(
        val firstName: String,
        val lastName: String
    ): DashboardUiState

    data class Error(
        val message: String
    )
}