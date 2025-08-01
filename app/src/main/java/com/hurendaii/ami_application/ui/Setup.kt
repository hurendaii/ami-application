package com.hurendaii.ami_application.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.Alignment
import com.hurendaii.ami_application.util.Logger

@Composable
fun SetupScreen(
    amiViewModel: AmiViewModel,
    currentFriendName: String,
    onNameSubmitted: () -> Unit
) {
    var newFriendName by remember { mutableStateOf(currentFriendName) }
    val successMessage = amiViewModel.successMessage

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
                    amiViewModel.setName(newFriendName)
                    Logger.d("SetupScreen submit name = $newFriendName")
                    Logger.d("AmiViewModel name after setName: ${amiViewModel.amiModel.name}")
                    onNameSubmitted()
                }
            },
            enabled = newFriendName.isNotBlank(),
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Submit")
        }

        if (successMessage.isNotBlank()) {
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = successMessage,
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.primary
            )
            // Optional: Automatically clear the message after 2 seconds
            LaunchedEffect(successMessage) {
                kotlinx.coroutines.delay(2000)
                amiViewModel.clearSuccessMessage()
            }
        }
    }
}

