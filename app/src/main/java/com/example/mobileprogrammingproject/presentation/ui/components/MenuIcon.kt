package com.example.mobileprogrammingproject.presentation.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

//class MenuIcon {
//}

@Composable
fun MenuIcon(modifier: Modifier = Modifier){
    Column(modifier = modifier
        .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally) {
        Icon(
            imageVector = Icons.Default.Menu,
            contentDescription = "Menu toggle",
            modifier = modifier.size(50.dp)
        )
    }
}

@Preview
@Composable
fun MenuIconPreview(){
    MenuIcon()
}