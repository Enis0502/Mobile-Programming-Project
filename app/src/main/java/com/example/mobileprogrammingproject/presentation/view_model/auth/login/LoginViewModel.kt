package com.example.mobileprogrammingproject.presentation.view_model.auth.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
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
class LoginViewModel @Inject constructor(
    private val userRepository: UserRepository,
    private val authRepository: AuthRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow<LoginUiState>(LoginUiState.Init)
    val uiState: StateFlow<LoginUiState> = _uiState

    private val _navigationEvent = Channel<LoginNavigationEvent>(Channel.BUFFERED)
    val navigationEvent: Flow<LoginNavigationEvent> = _navigationEvent.receiveAsFlow()

    fun onLoginClick(email: String, password: String) {
        viewModelScope.launch {
            _uiState.value = LoginUiState.Loading

            //Firebase Auth
            val firebaseResult = authRepository.signIn(email, password)
            if (firebaseResult.isFailure) {
                _uiState.value = LoginUiState.Error(
                    firebaseResult.exceptionOrNull()?.message ?: "Invalid email or password."
                )
                return@launch
            }

            //get user data from Room for navigation
            val user = userRepository.login(email, password)
            if (user != null) {
                _uiState.value = LoginUiState.Success(isLoggedIn = true)
                _navigationEvent.send(
                    LoginNavigationEvent.Navigate(
                        firstName = user.firstName,
                        lastName = user.lastName,
                        userId = user.id,
                        firebaseUserId = authRepository.currentUser?.uid ?: ""
                    )
                )
            } else {
                _uiState.value = LoginUiState.Error("User not found locally.")
            }
        }
    }

    fun resetUiState() {
        _uiState.value = LoginUiState.Init
    }
}