package com.example.meshmessenger.android.screens.channels

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.meshmessenger.android.theme.Onest
import com.example.meshmessenger.android.theme.PlaceholderColor
import com.example.meshmessenger.presentation.channelScreen.Channel


@Composable
fun ChanelItem(channel: Channel, navController: NavController) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(80.dp)
            .background(Color.White)
            .clickable {
                navController.navigate("messagesList/${channel.name + " " + channel.surname}") {
                    launchSingleTop = true
                }
            },
        verticalAlignment = Alignment.CenterVertically

    ) {

        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(channel.imageURL)
                .crossfade(true)
                .build(),
            contentDescription = "avatar image",
            modifier = Modifier
                .padding(all = 10.dp)
                .size(50.dp)
                .clip(CircleShape),
            contentScale = ContentScale.Crop
        )

        Column(
            modifier = Modifier.padding(start = 5.dp, end = 5.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.Start
        ) {
            Text(
                text = channel.name + " " + channel.surname,
                fontFamily = Onest
            )

            Spacer(modifier = Modifier.height(5.dp))

            Text(
                text = channel.lastMessage,
                modifier = Modifier.padding(start = 15.dp),
                fontSize = 14.sp,
                fontFamily = Onest,
                color = PlaceholderColor,
            )
        }
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.CenterEnd
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(end = 15.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.End
            ) {
                Text(
                    text = channel.time,
                    fontFamily = Onest,
                    color = PlaceholderColor,
                    fontSize = 14.sp
                )

            }
        }
    }
    Spacer(
        modifier = Modifier
            .height(0.5.dp)
            .background(PlaceholderColor)
    )
}
