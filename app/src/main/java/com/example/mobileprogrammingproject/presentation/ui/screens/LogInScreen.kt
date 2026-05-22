package com.example.mobileprogrammingproject.presentation.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.example.mobileprogrammingproject.R
import com.example.mobileprogrammingproject.presentation.navigation.Screen
import com.example.mobileprogrammingproject.presentation.theme.LightBlue400
import com.example.mobileprogrammingproject.presentation.ui.components.AppOutlinedTextField
import com.example.mobileprogrammingproject.presentation.view_model.auth.login.LoginNavigationEvent
import com.example.mobileprogrammingproject.presentation.view_model.auth.login.LoginUiState
import com.example.mobileprogrammingproject.presentation.view_model.auth.login.LoginViewModel
import kotlinx.coroutines.launch

@Composable
fun LogInScreen(navController: NavController, viewModel: LoginViewModel = hiltViewModel()){
    val snackBarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()

    val uiState by viewModel.uiState.collectAsState()
    var emailInput by remember { mutableStateOf("")}
    var passwordInput by remember { mutableStateOf("")}

    val isFormValid by remember (emailInput, passwordInput){
        derivedStateOf {
            emailInput.isNotBlank() && passwordInput.isNotBlank()
        }
    }

    LaunchedEffect(Unit) {
        viewModel.navigationEvent.collect { event ->
            when(event){
                is LoginNavigationEvent.Navigate -> {
                    navController.navigate(
                        Screen.Dashboard.createRoute(event.firstName, event.lastName, event.userId)
                    )
                }
                else -> {}
            }
        }
    }

    LaunchedEffect(uiState) {
        when(uiState){
            is LoginUiState.Error -> {
                snackBarHostState.showSnackbar(
                    (uiState as LoginUiState.Error).message
                )
                viewModel.resetUiState()
            }
            else -> {}
        }
    }

    LogInDisplay(
        emailInput = emailInput,
        passwordInput = passwordInput,
        onEmailChange = {emailInput = it},
        onPasswordChange = {passwordInput = it},
        isFormValid = isFormValid,
        snackBarHostState = snackBarHostState,
        onLoginClick = {
            viewModel.onLoginClick(emailInput, passwordInput)
        }
    )
}

@Composable
fun LogInDisplay(
    emailInput: String,
    passwordInput: String,
    onEmailChange: (String) -> Unit,
    onPasswordChange: (String) -> Unit,
    isFormValid: Boolean,
    snackBarHostState: SnackbarHostState,
    onLoginClick: () -> Unit
){
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        LightBlue400,
                        Color.White
                    )
                )
            )
    ) {
        SnackbarHost(
            hostState = snackBarHostState,
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(16.dp)
        )
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(
                modifier = Modifier
                    .padding(top = 100.dp, bottom = 24.dp)
                    .fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = painterResource(id = R.drawable.logoimage),
                    contentDescription = "Logo image"
                )
            }

            Text(
                text = "Log in Music Organizer",
                style = MaterialTheme.typography.headlineLarge,
                fontWeight = FontWeight.Bold
            )
            Column(modifier = Modifier
                .fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally) {
                AppOutlinedTextField(
                    value = emailInput,
                    onValueChange = onEmailChange,
                    label = "Enter your email",
                    modifier = Modifier,
                )
                Spacer(modifier = Modifier
                    .fillMaxWidth()
                    .height(16.dp))
                AppOutlinedTextField(
                    value = passwordInput,
                    onValueChange = onPasswordChange,
                    label = "Enter your password",
                    modifier = Modifier,
                    isPassword = true
                )
                Spacer(modifier = Modifier
                    .fillMaxWidth()
                    .height(16.dp))

                Button(
                    modifier = Modifier.fillMaxWidth(),
                    enabled = isFormValid,
                    onClick = onLoginClick
                ) {
                    Text("Log in")
                }

                Text(text = "Don't have an account? Sign in here.")
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun LogInScreenPreview(){
    MaterialTheme{

        //LogInScreen()
    }
}