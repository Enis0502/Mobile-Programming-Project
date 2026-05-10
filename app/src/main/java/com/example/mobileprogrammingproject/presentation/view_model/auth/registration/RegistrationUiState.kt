package com.example.mobileprogrammingproject.presentation.view_model.auth.registration

import android.os.Message

sealed interface RegistrationUiState{
    data object Init: RegistrationUiState

    data object Loading : RegistrationUiState

    data object Success: RegistrationUiState

    data class Error(
        val message : String
    ): RegistrationUiState

}