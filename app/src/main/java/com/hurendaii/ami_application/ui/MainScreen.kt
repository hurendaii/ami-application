package com.hurendaii.ami_application.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun MainScreen(
    onEnterClicked: () -> Unit
) {
    Column(
        modifier = Modifier.fillMaxSize().padding(32.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Ami",
            fontSize = 36.sp,
            modifier = Modifier.padding(bottom = 8.dp)
        )

        Text(
            text = "Your friend throughout web3",
            fontSize = 18.sp,
            modifier = Modifier.padding(bottom = 48.dp)
        )

        Button(onClick = onEnterClicked) {
            Text(text = "Enter")
        }
    }
}
