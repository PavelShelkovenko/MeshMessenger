package com.example.meshmessenger.android.uicompose

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
import com.example.meshmessenger.android.theme.BackgroundColor
import com.example.meshmessenger.android.theme.IconsBlue
import com.example.meshmessenger.android.theme.White
import com.example.meshmessenger.data.Channel
import com.example.meshmessenger.data.channelsListExample

@Composable
fun ChannelListScreen(navController: NavController, channelListExample: ArrayList<Channel>) {

    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        item {
            TopAppBar(
                backgroundColor = Color.White,
                title = { Text(text = "Mesh-App", fontSize = 20.sp, color = Color.Black) },
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

        items(channelListExample) { channel ->
            ChanelOnly(channel, navController)
        }
    }
}



@Composable
fun ChanelOnly(channel: Channel, navController: NavController) {
    Surface(elevation = 20.dp, shape = RoundedCornerShape(8.dp)) {

        Row(
            modifier = Modifier
                .fillMaxWidth(0.95F)
                .background(White)
                .clickable {
                    navController.navigate("messagesList/${channel.name + " " + channel.surname}") {
                        launchSingleTop = true
                    }
                }

        ) {

            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(channel.imageURL)
                    .crossfade(true)
                    .build(),
                null,
                modifier = Modifier
                    .padding(all = 10.dp)
                    .size(50.dp)
                    .clip(CircleShape),
                contentScale = ContentScale.Crop
            )
            Column(modifier = Modifier.padding(all = 5.dp)) {
                Text(channel.name + " " + channel.surname)
                Spacer(modifier = Modifier.height(10.dp))
                Text(channel.lastMessage, modifier = Modifier.padding(start = 15.dp), fontSize = 15.sp )
            }

            Row(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(all = 5.dp),
                verticalAlignment = Alignment.Top,
                horizontalArrangement = Arrangement.End
            ) {
                Text(channelsListExample[0].time)
            }
        }
    }
    Spacer(
        modifier = Modifier
            .height(5.dp)
            .background(BackgroundColor)
    )
}

@Composable
fun BleUICard(navController: NavController) {
    Surface(elevation = 20.dp, shape = RoundedCornerShape(8.dp)) {

        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically ,
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
            Text("Работа с BLE")
        }
    }
}