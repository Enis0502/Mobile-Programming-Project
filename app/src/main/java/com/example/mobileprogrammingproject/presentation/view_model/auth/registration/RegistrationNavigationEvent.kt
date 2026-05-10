package com.example.mobileprogrammingproject.presentation.view_model.auth.registration

sealed interface RegistrationNavigationEvent{
    data object Navigate: RegistrationNavigationEvent
    data object NavigateBack: RegistrationNavigationEvent
}

