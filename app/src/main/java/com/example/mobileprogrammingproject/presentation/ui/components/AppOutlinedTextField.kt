package com.example.mobileprogrammingproject.presentation.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun AppOutlinedTextField(
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    modifier: Modifier = Modifier,
    singleLine: Boolean = true,
    isPassword: Boolean = false)
{
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        label = {Text(label)},
        singleLine = true,
        modifier = modifier.fillMaxWidth(),
        visualTransformation = if(isPassword){
            PasswordVisualTransformation()
        }else{
            VisualTransformation.None
        }
    )
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun SongSearchFieldPreview(){
    MaterialTheme{

    }
}