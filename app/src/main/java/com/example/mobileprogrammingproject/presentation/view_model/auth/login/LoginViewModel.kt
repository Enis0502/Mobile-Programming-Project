package com.example.mobileprogrammingproject.presentation.view_model.auth.login

import com.example.mobileprogrammingproject.model.data.local.db.AppDatabase
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
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
    private val repository: UserRepository
) : ViewModel() {
    private val _uiState = MutableStateFlow<LoginUiState>(
        LoginUiState.Init
    )

    val uiState: StateFlow<LoginUiState> = _uiState

    private val _navigationEvent = Channel<LoginNavigationEvent>(
        Channel.Factory.BUFFERED
    )

    val navigationEvent : Flow<LoginNavigationEvent> = _navigationEvent.receiveAsFlow()

    fun onLoginClick(email: String, password: String) {
        viewModelScope.launch {
            _uiState.value = LoginUiState.Loading

            val user = repository.login(email, password)
            if (user != null) {
                _uiState.value = LoginUiState.Success(isLoggedIn = true)
                _navigationEvent.send(
                    LoginNavigationEvent.Navigate(
                        firstName = user.firstName,
                        lastName = user.lastName,
                        userId = user.id
                    )
                )
            } else {
                _uiState.value = LoginUiState.Error(
                    "Invalid email or password"
                )
            }
        }
    }

    fun resetUiState(){
        _uiState.value = LoginUiState.Init
    }
}