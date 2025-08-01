package com.hurendaii.ami_application.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.hurendaii.ami_application.R
import com.hurendaii.ami_application.navigation.Screen
import androidx.compose.runtime.*
import kotlinx.coroutines.delay
import java.text.SimpleDateFormat
import java.util.*
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import com.hurendaii.ami_application.util.Logger
import androidx.compose.runtime.LaunchedEffect


@Composable
fun CasaScreen(
    amiViewModel: AmiViewModel,
    friendName: String,
    onNavigate: (String) -> Unit
) {
    LaunchedEffect(amiViewModel.amiModel.name) {
        Logger.d("CasaScreen AmiModel name = ${amiViewModel.amiModel.name}")
    }


    val amiModel by remember { derivedStateOf { amiViewModel.amiModel } }

    // Use amiModel.name for display, reactive to changes
    Text("Welcome to Casa, ${amiModel.name}!")

    val successMessage = amiViewModel.successMessage
    val timeFormatter = remember { SimpleDateFormat("HH:mm", Locale.getDefault()) }
    val digitalFont = FontFamily(Font(R.font.ds_digital, FontWeight.Normal))
    var currentTime by remember { mutableStateOf(timeFormatter.format(Date())) }

    LaunchedEffect(Unit) {
        while (true) {
            currentTime = timeFormatter.format(Date())
            delay(1_000L)
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        // 🟦 TOP MESSAGE BAR (NEW)
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.TopCenter)
        ) {
            // Show the success message if present
            if (successMessage.isNotBlank()) {
                Text(
                    text = successMessage,
                    style = MaterialTheme.typography.bodyLarge,
                    color = MaterialTheme.colorScheme.primary,
                    modifier = Modifier.padding(bottom = 8.dp)
                )
                // Clear after 2 seconds
                LaunchedEffect(successMessage) {
                    delay(2000)
                    amiViewModel.clearSuccessMessage()
                }
            }
        }

        Column(
            modifier = Modifier
                .fillMaxSize(),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            // 🟦 TOP BAR
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 8.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(horizontalAlignment = Alignment.Start) {
                    Button(onClick = { onNavigate(Screen.Shop.createRoute(friendName)) }) {
                        Text("Shop")
                    }
                    Text(
                        text = currentTime,
                        fontFamily = digitalFont,
                        fontSize = MaterialTheme.typography.headlineMedium.fontSize,
                        textAlign = TextAlign.Start,
                        modifier = Modifier.padding(top = 4.dp)
                    )
                }

                Button(onClick = { onNavigate(Screen.Profile.createRoute(friendName)) }) {
                    Text("Profile")
                }
                Button(onClick = { onNavigate(Screen.Health.createRoute(friendName)) }) {
                    Text("Health")
                }
            }

            // 🟩 CHARACTER DISPLAY
            Box(
                modifier = Modifier
                    .fillMaxHeight(0.6f)
                    .fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = painterResource(id = R.drawable.stickman_first_pose),
                    contentDescription = friendName,
                    modifier = Modifier.fillMaxHeight()
                )
            }

            // 🟥 BOTTOM BAR
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Button(onClick = { onNavigate(Screen.Games.createRoute(friendName)) }) {
                    Text("Games")
                }
                Button(onClick = { onNavigate(Screen.Track.createRoute(friendName)) }) {
                    Text("Track")
                }
                Button(onClick = { onNavigate(Screen.Settings.createRoute(friendName)) }) {
                    Text("Settings")
                }
            }
        }

        // 🟨 MIDDLE RIGHT FEED BUTTON
        Box(
            modifier = Modifier
                .fillMaxSize(),
            contentAlignment = Alignment.CenterEnd
        ) {
            Button(
                onClick = {
                    // Feed action via ViewModel (shows success message!)
                    amiViewModel.feed()
                }
            ) {
                Text("Feed")
            }
        }

        // 💰 CURRENCY METER
        Column(
            modifier = Modifier
                .align(Alignment.TopEnd)
                .padding(top = 64.dp, end = 8.dp)
        ) {
            Text("Currency 1: 0", style = MaterialTheme.typography.bodySmall)
            Text("Currency 2: 0", style = MaterialTheme.typography.bodySmall)
        }
    }
}

