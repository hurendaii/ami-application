package com.hurendaii.ami_application.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.Alignment

@Composable
fun SetupScreen(
    currentFriendName: String,
    onNameSubmitted: (String) -> Unit
) {
    var newFriendName by remember { mutableStateOf(currentFriendName) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(32.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Name your friend",
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        TextField(
            value = newFriendName,
            onValueChange = { newFriendName = it },
            label = { Text("Friend's name") },
            singleLine = true,
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(24.dp))

        Button(
            onClick = {
                if (newFriendName.isNotBlank()) {
                    onNameSubmitted(newFriendName)
                }
            },
            enabled = newFriendName.isNotBlank(),
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Submit")
        }
    }
}
