package com.example.mobileprogrammingproject.presentation.view_model.auth.registration

sealed interface RegistrationNavigationEvent {
    data class Navigate(
        val firstName: String,
        val lastName: String,
        val userId: Int,
        val firebaseUserId: String
    ) : RegistrationNavigationEvent
}