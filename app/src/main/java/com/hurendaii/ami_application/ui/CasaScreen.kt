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

@Composable
fun CasaScreen(
    friendName: String,
    onNavigate: (String) -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
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
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Button(onClick = { onNavigate(Screen.Shop.createRoute(friendName)) }) {
                    Text("Shop")
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
            Button(onClick = { onNavigate(Screen.Feed.createRoute(friendName)) }) {
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
