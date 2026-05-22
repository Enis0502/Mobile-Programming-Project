package com.example.mobileprogrammingproject.presentation.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.example.mobileprogrammingproject.presentation.theme.LightBlue400

@Composable
fun SetBackgroundGradient(){
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
    ) {}
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun SetBackgroundGradientPreview(){
    MaterialTheme{
        SetBackgroundGradient()
    }
}