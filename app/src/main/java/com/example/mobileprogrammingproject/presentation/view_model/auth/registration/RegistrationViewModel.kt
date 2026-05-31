package com.example.mobileprogrammingproject.presentation.view_model.auth.registration

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mobileprogrammingproject.model.data.local.entity.UserEntity
import com.example.mobileprogrammingproject.model.data.repository.mappers.AuthRepository
import com.example.mobileprogrammingproject.model.data.repository.mappers.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegistrationViewModel @Inject constructor(
    private val userRepository: UserRepository,
    private val authRepository: AuthRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow<RegistrationUiState>(RegistrationUiState.Init)
    val uiState: StateFlow<RegistrationUiState> = _uiState

    private val _navigationEvent = Channel<RegistrationNavigationEvent>(Channel.BUFFERED)
    val navigationEvent: Flow<RegistrationNavigationEvent> = _navigationEvent.receiveAsFlow()

    fun onRegisterClick(name: String, lastname: String, email: String, password: String) {
        viewModelScope.launch {
            _uiState.value = RegistrationUiState.Loading

            if (name.isBlank() || lastname.isBlank() || email.isBlank() || password.isBlank()) {
                _uiState.value = RegistrationUiState.Error("All fields are required.")
                return@launch
            }

            //Firebase Auth
            val firebaseResult = authRepository.signUp(email, password)
            if (firebaseResult.isFailure) {
                _uiState.value = RegistrationUiState.Error(
                    firebaseResult.exceptionOrNull()?.message ?: "Registration failed."
                )
                return@launch
            }

            // Save to Room (keeps existing navigation working)
            val user = UserEntity(firstName = name, lastName = lastname, email = email, password = password)
            val newUserId = userRepository.insertUser(user)

            _uiState.value = RegistrationUiState.Success
            _navigationEvent.send(
                RegistrationNavigationEvent.Navigate(
                    firstName = name,
                    lastName = lastname,
                    userId = newUserId.toInt(),
                    firebaseUserId = authRepository.currentUser?.uid ?: ""
                )
            )
        }
    }

    fun resetUiState() {
        _uiState.value = RegistrationUiState.Init
    }
}