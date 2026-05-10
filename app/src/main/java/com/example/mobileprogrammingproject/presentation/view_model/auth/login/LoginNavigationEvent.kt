package com.example.mobileprogrammingproject.presentation.view_model.auth.login

sealed interface LoginNavigationEvent {
    data object Navigate : LoginNavigationEvent
    data object NavigateBack : LoginNavigationEvent
}