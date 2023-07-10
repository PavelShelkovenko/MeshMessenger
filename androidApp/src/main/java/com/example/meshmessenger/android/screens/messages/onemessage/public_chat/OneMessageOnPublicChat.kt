package com.example.meshmessenger.android.screens.messages.onemessage.public_chat

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import coil.transform.CircleCropTransformation
import com.example.meshmessenger.android.screens.messages.onemessage.private_chat.ShapesOfPrivateTextMessages
import com.example.meshmessenger.android.theme.Onest
import com.example.meshmessenger.android.theme.PlaceholderColor
import com.example.meshmessenger.data.Message

@Composable
fun OneMessageOnPublicChat(message: Message) {
    val isMyMessage = message.id == 1

    Row(
        verticalAlignment = Alignment.Bottom,
        horizontalArrangement = if (isMyMessage) Arrangement.End else Arrangement.Start,
        modifier = Modifier.fillMaxSize()
    ) {

        if (!isMyMessage) {
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .transformations(CircleCropTransformation())
                    .data("https://images.unsplash.com/photo-1639091435585-508ff02f0b07?crop=entropy&cs=tinysrgb&fit=max&fm=jpg&ixid=M3wxODY2Nzh8MHwxfHJhbmRvbXx8fHx8fHx8fDE2ODUwMjU2Nzd8&ixlib=rb-4.0.3&q=80&w=1080")
                    .crossfade(true)
                    .build(),
                contentDescription = "Ваша аватарка",
                modifier = Modifier
                    .padding(start = 5.dp)
                    .size(30.dp)
                    .clip(CircleShape),
                contentScale = ContentScale.Crop,
                alignment = Alignment.BottomStart
            )
        }

        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = if (isMyMessage) Alignment.End else Alignment.Start,
        ) {
            Surface(
                shape = ShapesOfPrivateTextMessages(isMyMessage = isMyMessage),
                modifier = Modifier
                    .wrapContentWidth()
                    .widthIn(max = 320.dp, min = 60.dp)
                    .padding(all = 2.dp)
            ) {
                Column(
                    modifier = Modifier.background(Color.White),
                    horizontalAlignment = if (isMyMessage) Alignment.End else Alignment.Start,
                    verticalArrangement = Arrangement.Center
                ) {
                    Box(
                        contentAlignment = if (isMyMessage) Alignment.CenterEnd else Alignment.CenterStart,
                        modifier = if (isMyMessage) {
                            Modifier.padding(start = 15.dp, end = 25.dp, top = 15.dp, bottom = 15.dp )
                        } else {
                            Modifier.padding(start = 25.dp, end = 15.dp, top = 15.dp, bottom = 15.dp)
                        }
                    ) {
                        Text(
                            text = message.text,
                            maxLines = Int.MAX_VALUE,
                            modifier = Modifier.wrapContentWidth(), //размер зависит от текста
                            fontFamily = Onest,
                            color = Color.Black
                        )
                    }
                }
            }

            Text(
                text = message.time,
                maxLines = 1,
                fontSize = 14.sp,
                color = PlaceholderColor,
                modifier = Modifier.padding(start = 15.dp, end = 15.dp),

                textAlign = if (isMyMessage) {
                    TextAlign.End
                } else {
                    TextAlign.Start
                },
                fontFamily = Onest
            )

        }

    }
}