package com.example.mobileprogrammingproject.presentation.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.mobileprogrammingproject.R
import com.example.mobileprogrammingproject.presentation.navigation.Screen

//class DashboardOptionsCard {
//}

@Composable
fun DashboardOptionsCard(title: String, onClick: () -> Unit){
    Box(modifier = Modifier
        .clickable{
            onClick()

        }
        .padding(8.dp)
        .fillMaxWidth()
        .height(150.dp)
        .clip(shape = RoundedCornerShape(16.dp))
    )
        {
        Image(
            painter = painterResource(id = R.drawable.dashboardimg),
            contentDescription = "Background image",
            contentScale = ContentScale.Crop,
        )
        Text(text = title,
            color = Color.White,
            modifier = Modifier.padding(16.dp))
//        Card(modifier = modifier
//            .padding(8.dp)
//            .fillMaxWidth()
//            .shadow(elevation = 8.dp)
//        ){
//
//            Text(text = title,
//                modifier = Modifier.padding(16.dp))
//        }
    }

}


@Preview
@Composable
fun DashboardOptionsCardPreview(){
    MaterialTheme {
       // DashboardOptionsCard("Nesta")
    }
}