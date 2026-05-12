package com.example.mobileprogrammingproject.presentation.view_model.dashboard

import androidx.compose.runtime.MutableState
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class DashboardViewModel @Inject constructor() : ViewModel(){
    private val _uiState = MutableStateFlow<DashboardUiState>(
        DashboardUiState.Loading
    )

    val uiState: StateFlow<DashboardUiState> = _uiState

    init {
        loadUser()
    }

    private fun loadUser(){
        _uiState.value = DashboardUiState.Success(
            firstName = "Enis",
            lastName =  "Bulbulusic"
        )
    }

}