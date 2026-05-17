package com.example.mobileprogrammingproject.presentation.view_model.auth.registration

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mobileprogrammingproject.model.data.local.entity.UserEntity
import com.example.mobileprogrammingproject.model.data.repository.mappers.UserRepository
import com.example.mobileprogrammingproject.presentation.view_model.auth.login.LoginUiState
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
    private val repository: UserRepository
) : ViewModel() {
    private val _uiState = MutableStateFlow<RegistrationUiState>(
        RegistrationUiState.Init
    )

    val uiState : StateFlow<RegistrationUiState> = _uiState

    private val _navigationEvent = Channel<RegistrationNavigationEvent>(
        Channel.BUFFERED
    )

    val navigationEvent : Flow<RegistrationNavigationEvent> = _navigationEvent.receiveAsFlow()


    fun onRegisterClick(
        name: String,
        lastname: String,
        email: String,
        password: String
    ) {
        viewModelScope.launch {
            _uiState.value = RegistrationUiState.Loading

            if (name.isNotBlank() && lastname.isNotBlank() &&
                email.isNotBlank() && password.isNotBlank()
            ) {
                val user = UserEntity(
                    firstName = name,
                    lastName = lastname,
                    email = email,
                    password = password
                )

                val newUserId = repository.insertUser(user)
                _uiState.value = RegistrationUiState.Success

                _navigationEvent.send(
                    RegistrationNavigationEvent.Navigate(
                        firstName = name,
                        lastName = lastname,
                        userId = newUserId.toInt()
                    )
                )
            } else {
                _uiState.value = RegistrationUiState.Error(
                    "Registration failed, all fields required."
                )
            }
        }
    }

    fun resetUiState(){
        _uiState.value = RegistrationUiState.Init
    }
}