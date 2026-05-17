package com.example.mobileprogrammingproject.presentation.view_model.auth.login

sealed interface LoginNavigationEvent {
    data class Navigate(
        val firstName: String,
        val lastName: String,
        val userId: Int
    ) : LoginNavigationEvent
}