package com.example.mobileprogrammingproject.presentation.ui.screens
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
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
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.mobileprogrammingproject.R
import com.example.mobileprogrammingproject.presentation.navigation.Screen
import com.example.mobileprogrammingproject.presentation.theme.LightBlue400
import com.example.mobileprogrammingproject.presentation.ui.components.AppOutlinedTextField
import com.example.mobileprogrammingproject.presentation.view_model.auth.login.LoginUiState
import com.example.mobileprogrammingproject.presentation.view_model.auth.registration.RegistrationNavigationEvent
import com.example.mobileprogrammingproject.presentation.view_model.auth.registration.RegistrationUiState
import com.example.mobileprogrammingproject.presentation.view_model.auth.registration.RegistrationViewModel
import kotlinx.coroutines.launch

@Composable
fun SignInScreen(navController: NavController, viewModel: RegistrationViewModel = hiltViewModel()){
    val snackBarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()

    val uiState by viewModel.uiState.collectAsState()
    var name by remember { mutableStateOf("") }
    var lastName by remember { mutableStateOf("") }
    var emailInput by remember { mutableStateOf("")}
    var passwordInput by remember { mutableStateOf("")}

    val validForm by remember (name, lastName, emailInput, passwordInput) {
        derivedStateOf {
            name.isNotBlank() &&
            lastName. isNotBlank() &&
            emailInput.isNotBlank() &&
            passwordInput.isNotBlank()
        }
    }

    LaunchedEffect(Unit) {
        viewModel.navigationEvent.collect { event ->
            when(event){
                is RegistrationNavigationEvent.Navigate -> {
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
            is RegistrationUiState.Error -> {
                snackBarHostState.showSnackbar(
                    (uiState as RegistrationUiState.Error).message
                )
                viewModel.resetUiState()
            }
            else -> {}
        }
    }

    SignInDisplay(
        snackBarHostState = snackBarHostState,
        name = name,

        lastName = lastName,
        emailInput = emailInput,
        passwordInput = passwordInput,
        isValidForm = validForm,
        onNameChange = {name = it},
        onLastNameChange = {lastName = it},
        onEmailChange = {emailInput = it},
        onPasswordChange = {passwordInput = it},
        onSignInClick = {
            viewModel.onRegisterClick(name, lastName, emailInput, passwordInput)
        }
    )

}

@Composable
fun SignInDisplay(
    snackBarHostState: SnackbarHostState,
    name: String,
    lastName: String,
    emailInput: String,
    passwordInput: String,
    isValidForm: Boolean,
    onNameChange: (String) -> Unit,
    onLastNameChange: (String) -> Unit,
    onEmailChange: (String) -> Unit,
    onPasswordChange: (String) -> Unit,
    onSignInClick: () -> Unit
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
                text = "Sign in Music Organizer",
                style = MaterialTheme.typography.headlineLarge,
                fontWeight = FontWeight.Bold
            )
            Column(modifier = Modifier
                .fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally) {
                Row(modifier = Modifier
                    .fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    AppOutlinedTextField(
                        value = name,
                        onValueChange = onNameChange,
                        label = "First name",
                        modifier = Modifier.weight(1f)
                    )
                    AppOutlinedTextField(
                        value = lastName,
                        onValueChange = onLastNameChange,
                        label = "Last name",
                        modifier = Modifier.weight(1f)
                    )
                }
                Spacer(modifier = Modifier
                    .fillMaxWidth()
                    .height(16.dp))

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

                Button(modifier = Modifier.fillMaxWidth(),
                    enabled = isValidForm,
                    onClick = onSignInClick,
                ){
                    Text("Sign in")
                }

                Text(text = "Already have an account? Log in here.")
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun SignInScreenPreview(){
    MaterialTheme{
        //SignInScreen()
    }
}