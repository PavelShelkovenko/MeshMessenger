package com.example.meshmessenger.android.screens.channels

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
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
import com.example.meshmessenger.SharedRes
import com.example.meshmessenger.android.root.stringResource
import com.example.meshmessenger.android.theme.IconsBlue
import com.example.meshmessenger.android.theme.Onest
import com.example.meshmessenger.android.theme.PlaceholderColor
import com.example.meshmessenger.android.theme.White
import com.example.meshmessenger.presentation.channelScreen.Channel
import com.example.meshmessenger.presentation.channelScreen.ChannelViewModel


@Composable
fun ChannelsListScreen(navController: NavController, chatViewModel: ChannelViewModel) {

    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        item {
            TopAppBar(
                backgroundColor = Color.White,
                title = {
                    Text(
                        text = stringResource(id = SharedRes.strings.topAppBar_text),
                        fontSize = 20.sp,
                        color = Color.Black,
                        fontFamily = Onest
                    )
                },
                navigationIcon = {
                    IconButton(onClick = { /*TODO*/ }) {
                        Icon(Icons.Filled.Menu, contentDescription = "Menu", tint = IconsBlue)
                    }
                }
            )
        }
        item { Spacer(modifier = Modifier.height(5.dp)) }
        item { BleUICard(navController) }
        item { Spacer(modifier = Modifier.height(5.dp)) }

        items(chatViewModel.channelsListExample) { channel ->
            ChanelOnly(channel, navController)
        }
    }
}


@Composable
fun ChanelOnly(channel: Channel, navController: NavController) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White)
            .clickable {
                navController.navigate("messagesList/${channel.name + " " + channel.surname}") {
                    launchSingleTop = true
                }
            },

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

        Column(modifier = Modifier.padding(start = 5.dp, end = 5.dp)) {
            Text(
                modifier = Modifier.padding(top = 10.dp),
                text = channel.name + " " + channel.surname,
                fontFamily = Onest
            )

            Spacer(modifier = Modifier.height(5.dp))

            Text(
                text = channel.lastMessage,
                modifier = Modifier.padding(start = 15.dp),
                fontSize = 15.sp,
                fontFamily = Onest,
                color = PlaceholderColor,
            )
        }
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(start = 5.dp, top = 5.dp, end = 5.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.End
        ) {
            Text(
                text = channel.time,
                fontFamily = Onest,
                color = PlaceholderColor,
                fontSize = 15.sp
                )
        }
    }
    Spacer(modifier = Modifier.height(0.5.dp).background(PlaceholderColor))
}


@Composable
fun BleUICard(navController: NavController) {
    Surface(elevation = 20.dp, shape = RoundedCornerShape(8.dp)) {
        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .padding(all = 20.dp)
                .fillMaxWidth(0.95F)
                .background(White)
                .clickable {
                    navController.navigate("ble") {
                        launchSingleTop = true
                    }
                }
        ) {
            Text(
                text = stringResource(id = SharedRes.strings.work_BLE)
            )
        }
    }
}
