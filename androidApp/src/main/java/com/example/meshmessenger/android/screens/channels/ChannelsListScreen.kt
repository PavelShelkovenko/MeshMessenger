package com.example.meshmessenger.android.screens.channels


import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.meshmessenger.SharedRes
import com.example.meshmessenger.android.root.stringResource
import com.example.meshmessenger.android.theme.IconsBlue
import com.example.meshmessenger.android.theme.Onest
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
            ChanelItem(channel, navController)
        }
    }
}





