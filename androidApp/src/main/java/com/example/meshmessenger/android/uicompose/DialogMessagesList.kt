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
                    Icon( painter =painterResource(id = R.drawable.back_arrow), contentDescription = "Back", tint = IconsBlue)
                }
                
                Spacer(modifier = Modifier.width(5.dp))
                AsyncImage(
                    model = ImageRequest.Builder(LocalContext.current)
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
                }  ,
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

        ) {
            innerPadding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(PaddingValues(bottom = innerPadding.calculateBottomPadding()))
                .background(BackgroundColor)
        ) {
            items(messagesListExample) { message ->
                MessageCard(message)
            }
        }
    }
}


@Composable
fun MessageCard(message: Message) {
    Row(modifier = Modifier.padding(all = 8.dp), horizontalArrangement = Arrangement.End) {

        Spacer(modifier = Modifier.width(10.dp))

        Column(horizontalAlignment = Alignment.End, modifier = Modifier.fillMaxSize() ) {
            Text(
                text = message.authorName,
                color = Color.Black,                      //автор
                modifier = Modifier.padding(end = 45.dp)
            )

            Spacer(modifier = Modifier.height(4.dp))

            Row(
                modifier = Modifier.fillMaxSize(),
                verticalAlignment = Alignment.Bottom,
                horizontalArrangement = Arrangement.End
            ) {

                Surface(
                    shape = MaterialTheme.shapes.medium,
                    elevation = 5.dp,
                    modifier = Modifier.fillMaxSize(0.8f)
                ) {
                    Text(
                        text = message.text,
                        modifier = Modifier.padding(all = 8.dp),   //текст
                        maxLines = Int.MAX_VALUE
                    )
                }

                Spacer(modifier = Modifier.width(5.dp))

                AsyncImage(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data("https://randart.ru/art/JD99/wallpapers")
                        .crossfade(true)
                        .build(),
                    contentDescription = "Ваша аватарка",
                    modifier = Modifier
                        .size(30.dp)
                        .clip(CircleShape),
                    contentScale = ContentScale.Crop,
                    alignment = Alignment.BottomEnd
                )
            }
        }
    }
}
