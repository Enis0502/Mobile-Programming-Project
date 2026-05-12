package com.example.mobileprogrammingproject.presentation.view_model.about

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class AboutUsViewModel @Inject constructor(): ViewModel() {
    private val _uiState = MutableStateFlow<AboutUsUiState>(
        AboutUsUiState.Success
    )

    val uiState: StateFlow<AboutUsUiState> = _uiState
}