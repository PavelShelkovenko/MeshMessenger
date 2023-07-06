package com.example.meshmessenger.android.screens.chats

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
import com.example.meshmessenger.android.theme.BackgroundColor
import com.example.meshmessenger.android.theme.IconsBlue
import com.example.meshmessenger.android.theme.Onest
import com.example.meshmessenger.android.theme.White
import com.example.meshmessenger.presentation.chat.Channel
import com.example.meshmessenger.presentation.chat.ChatViewModel

@Composable
fun ChatsListScreen(navController: NavController, chatViewModel: ChatViewModel) {

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
            ChanelOnly(channel, navController, chatViewModel)
        }
    }
}



@Composable
fun ChanelOnly(channel: Channel, navController: NavController, chatViewModel: ChatViewModel) {
    Surface {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.White)
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
                contentDescription =  null,
                modifier = Modifier
                    .padding(all = 10.dp)
                    .size(50.dp)
                    .clip(CircleShape),
                contentScale = ContentScale.Crop
            )
            Column(modifier = Modifier.padding(all = 5.dp)) {
                Text(
                    text = channel.name + " " + channel.surname,
                    fontFamily = Onest

                )
                Spacer(modifier = Modifier.height(10.dp))
                Text(
                    text = channel.lastMessage,
                    modifier = Modifier.padding(start = 15.dp),
                    fontSize = 15.sp,
                    fontFamily = Onest
                )
            }

            Row(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(all = 5.dp),
                verticalAlignment = Alignment.Top,
                horizontalArrangement = Arrangement.End
            ) {
                Text(
                    text = chatViewModel.channelsListExample[0].time,
                    fontFamily = Onest
                )
            }
        }
    }
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
            Text(
                text = stringResource(id = SharedRes.strings.work_BLE)
            )
        }
    }
}