package com.example.recipecompose.presentation.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.example.recipecompose.R

@Composable
fun WelcomeScreen(
    navHostController: NavController,
    modifier: Modifier = Modifier
) {
    WelcomeContent(
        onClick = {
            navHostController.navigate("HomeScreen")
        },
        modifier = modifier
    )
}

@Composable
private fun WelcomeContent(
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Box(modifier = modifier.fillMaxSize()) {
        Image(
            painter = painterResource(R.drawable.onboarding),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = 42.dp),
            verticalArrangement = Arrangement.Bottom,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text ="Let's\nCooking",
                color = Color.White,
                fontSize = 40.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
            )
            Spacer(Modifier.height(20.dp))
            Text("Find best recipes for cooking", color = Color.White)
            Spacer(Modifier.height(20.dp))
            Button(
                shape = RoundedCornerShape(4.dp),
                contentPadding = PaddingValues(horizontal = 32.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Red
                ),
                onClick = onClick
            ) {
                Text("Start Cooking")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun WelcomePreview() {
    WelcomeContent(
        onClick = {}
    )
}