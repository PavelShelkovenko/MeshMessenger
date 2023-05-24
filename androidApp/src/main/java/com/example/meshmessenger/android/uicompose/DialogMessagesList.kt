package com.example.meshmessenger.android.uicompose

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.meshmessenger.android.R
import com.example.meshmessenger.data.Message
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.rotate
import coil.transform.CircleCropTransformation
import com.example.meshmessenger.android.theme.*
import com.example.meshmessenger.data.messagesListExample

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun DialogMessagesList(navController: NavController, channelName: String?) {

    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.White),
        topBar = {
            TopAppBar(backgroundColor = Color.White) {
                IconButton(onClick = {
                    navController.navigate("channelListScreen") {
                        popUpTo(0)
                        launchSingleTop = true
                    }
                }) {
                    Icon(
                        painter = painterResource(id = R.drawable.back_arrow),
                        contentDescription = "Back",
                        tint = IconsBlue
                    )
                }

                Spacer(modifier = Modifier.width(5.dp))
                AsyncImage(
                    model = ImageRequest.Builder(LocalContext.current)
                        .transformations(CircleCropTransformation())
                        .data("https://randart.ru/art/JD99/")
                        .crossfade(true)
                        .build(),
                    null,
                    modifier = Modifier
                        .size(40.dp)
                        .clip(CircleShape),
                    contentScale = ContentScale.Crop
                )
                Spacer(modifier = Modifier.width(20.dp))
                Text(channelName!!, fontSize = 20.sp, color = Color.Black, fontFamily = Poppins)

            }
        },
        bottomBar = {
            TextField(
                colors = TextFieldDefaults.textFieldColors(
                    textColor = PrimaryColor,
                    backgroundColor = Color.White,
                    cursorColor = PrimaryColor,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent
                ),
                leadingIcon = {
                    Icon(
                        painter = painterResource(id = R.drawable.paperclip),
                        contentDescription = "",
                        modifier = Modifier
                            .rotate(220f)
                            .clip(CircleShape)
                            .size(30.dp)
                            .clickable {
                                //открыть photo picker
                            }

                    )
                },
                trailingIcon = {
                    Icon(
                        painter = painterResource(id = R.drawable.microphone),
                        contentDescription = null,

                        //tint = PrimaryColor,
                        modifier = Modifier
                            .size(30.dp)
                            .clip(CircleShape)
                            .clickable {
                                //запись звука
                            }

                    )
                },
                placeholder = {
                    Text(text = "Message", color = PlaceholderColor)
                },
                value = "",
                onValueChange = {},
                modifier = Modifier
                    .background(Color.White)
                    .fillMaxWidth()
                    .padding(start = 15.dp, end = 5.dp),

                )
        },

        ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(PaddingValues(bottom = innerPadding.calculateBottomPadding()))
                .background(BackgroundColor)
        ) {
            items(messagesListExample.shuffled()) { message ->
                Spacer(modifier = Modifier.height(2.5.dp))
                if(message.id == 1) { MessageCard(message) }
                else { MessageCard2(message) }
                Spacer(modifier = Modifier.height(2.5.dp))
            }
        }
    }
}



@Composable
fun MessageCard(message: Message) {

    Row(
        modifier = Modifier.fillMaxSize(),
        verticalAlignment = Alignment.Bottom,
        horizontalArrangement = Arrangement.End
    ) {

        Surface(
            shape = MaterialTheme.shapes.medium,
            elevation = 5.dp,
            modifier = Modifier.fillMaxSize(0.7f),
        ) {
            Column(modifier = Modifier.fillMaxSize(), horizontalAlignment = Alignment.End) {
                Text(
                    text = message.authorName,
                    color = PlaceholderColor,                      //автор
                    fontSize = 12.sp,
                    maxLines = 1,
                    modifier = Modifier.padding(top = 1.dp, end = 5.dp)
                )

                Box(contentAlignment = Alignment.CenterStart, modifier = Modifier.fillMaxSize()) {
                    Text(
                        text = message.text,
                        maxLines = Int.MAX_VALUE,
                        modifier = Modifier.padding(start = 5.dp, end = 5.dp, bottom = 1.dp),
                    )
                }

                Row(
                    modifier = Modifier.padding(end = 10.dp),
                    horizontalArrangement = Arrangement.End
                ) {
                    Text(
                        text = message.time,
                        maxLines = 1,
                        fontSize = 10.sp,
                        color = PlaceholderColor,
                        modifier = Modifier.padding(5.dp)
                    )
                    Icon(
                        painter = painterResource(id = R.drawable.check_all),
                        contentDescription = "status icon",
                        modifier = Modifier.size(18.dp)
                    )
                }
            }
        }

        Spacer(modifier = Modifier.width(5.dp))

        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .transformations(CircleCropTransformation())
                .data("https://plus.unsplash.com/premium_photo-1683135218463-12fd419b0b85?crop=entropy&cs=tinysrgb&fit=max&fm=jpg&ixid=M3wxODY2Nzh8MHwxfHJhbmRvbXx8fHx8fHx8fDE2ODQ2NzI1NDF8&ixlib=rb-4.0.3&q=80&w=1080")
                .crossfade(true)
                .build(),
            contentDescription = "Ваша аватарка",
            modifier = Modifier
                .padding(end = 5.dp)
                .size(30.dp)
                .clip(CircleShape)
            ,
            contentScale = ContentScale.Crop,
            alignment = Alignment.BottomEnd
        )
    }
}


@Composable
fun MessageCard2(message: Message) {

    Row(
        modifier = Modifier.fillMaxSize(),
        verticalAlignment = Alignment.Bottom,
        horizontalArrangement = Arrangement.Start
    ) {

        Spacer(modifier = Modifier.width(5.dp))

        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .transformations(CircleCropTransformation())
                .data("https://img-webcalypt.ru/api/img/get_processed_image_by_name/random-person/thumbnails/aXxkXTfcHInasBL51QtqJxejveZniHmxoXQdSgo2tV3TNyIgpUWvInZYnqqRH8Tk.jpg")
                .crossfade(true)
                .build(),
            contentDescription = "Ваша аватарка",
            modifier = Modifier
                .padding(start = 5.dp)
                .size(30.dp)
                .clip(CircleShape)
            ,
            contentScale = ContentScale.Crop,
            alignment = Alignment.BottomStart
        )

        Spacer(modifier = Modifier.width(5.dp))

        Surface(
            shape = MaterialTheme.shapes.medium,
            elevation = 5.dp,
            modifier = Modifier.fillMaxSize(0.8f),
        ) {
            Column(modifier = Modifier.fillMaxSize(), horizontalAlignment = Alignment.Start) {
                Text(
                    text = message.authorName,
                    color = PlaceholderColor,                      //автор
                    fontSize = 12.sp,
                    modifier = Modifier.padding(top = 1.dp, start = 5.dp)
                )

                Box(contentAlignment = Alignment.CenterStart, modifier = Modifier.fillMaxSize()) {
                    Text(
                        text = message.text,
                        modifier = Modifier.padding(start = 5.dp, end = 5.dp, bottom = 1.dp),
                    )
                }

                Row(
                    modifier = Modifier.padding(end = 10.dp),
                    horizontalArrangement = Arrangement.Start
                ) {
                    Text(
                        text = message.time,
                        maxLines = 1,
                        fontSize = 10.sp,
                        color = PlaceholderColor,
                        modifier = Modifier.padding(5.dp)
                    )
                    Icon(
                        painter = painterResource(id = R.drawable.check_all),
                        contentDescription = "status icon",
                        modifier = Modifier.size(18.dp)
                    )
                }
            }
        }
    }
}


