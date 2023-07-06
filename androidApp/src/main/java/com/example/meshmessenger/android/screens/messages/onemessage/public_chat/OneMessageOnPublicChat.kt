package com.example.meshmessenger.android.screens.messages.onemessage.public_chat

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import coil.transform.CircleCropTransformation
import com.example.meshmessenger.android.R
import com.example.meshmessenger.android.screens.messages.roundedCornerShapeDefine
import com.example.meshmessenger.android.theme.Onest
import com.example.meshmessenger.android.theme.PlaceholderColor
import com.example.meshmessenger.data.Message

@Composable
fun OneMessageOnPublicChat(message: Message) {
    Row(
        verticalAlignment = Alignment.Bottom,
        horizontalArrangement = if (message.id == 1) Arrangement.End else Arrangement.Start, //
        modifier = Modifier.fillMaxSize() //пришлось задать весь размер иначе witdhIn не работает, а считает слева
    ) {
        if (message.id != 1) {
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

        Surface(
            shape = roundedCornerShapeDefine(id = message.id),
            elevation = 5.dp,

            modifier = Modifier
                .wrapContentWidth()
                .widthIn(max = 300.dp, min = 80.dp)
                .padding(end = 15.dp, bottom = 15.dp, start = 5.dp)
        ) {

            Spacer(modifier = Modifier.width(5.dp))

            Column(
                modifier = Modifier.wrapContentWidth(),
                horizontalAlignment = if (message.id == 1) Alignment.End else Alignment.Start,
                verticalArrangement = Arrangement.Center
            ) {
                if (message.id != 1) {
                    Box(
                        modifier = Modifier
                            .wrapContentWidth()
                            .padding(start = 10.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = message.authorName,
                            maxLines = 1,
                            fontSize = 10.sp,
                            color = PlaceholderColor,
                            modifier = Modifier.padding(end = 10.dp, top = 2.dp),
                            fontFamily = Onest,

                            )
                    }
                }

                Box(
                    contentAlignment = if (message.id == 1) Alignment.CenterEnd else Alignment.CenterStart,
                    modifier = Modifier.wrapContentWidth()
                ) {
                    Text(
                        text = message.text,
                        maxLines = Int.MAX_VALUE,
                        modifier = if (message.id == 1) {
                            Modifier.padding(start = 5.dp, end = 5.dp, top = 16.dp)
                        } else {
                            Modifier.padding(start = 5.dp, end = 5.dp, top = 5.dp)
                        },
                        fontFamily = Onest,

                        )
                }

                Spacer(modifier = Modifier.height(4.dp))

                Row(
                    modifier = if (message.id == 1) {
                        Modifier
                            .wrapContentWidth()
                            .padding(end = 2.dp)
                    } else {
                        Modifier
                            .wrapContentWidth()
                            .padding(start = 2.dp)
                    },
                    horizontalArrangement = if (message.id == 1) {
                        Arrangement.End
                    } else {
                        Arrangement.Start
                    }
                ) {
                    Text(
                        text = message.time,
                        maxLines = 1,
                        fontSize = 10.sp,
                        color = PlaceholderColor,
                        modifier = if (message.id == 1) {
                            Modifier.padding(end = 2.dp)
                        } else {
                            Modifier.padding(start = 2.dp)
                        },
                        fontFamily = Onest,

                        )

                    Spacer(modifier = Modifier.width(2.dp))

                    Icon(
                        painter = painterResource(id = R.drawable.check_all),
                        contentDescription = "status icon",
                        modifier = Modifier.size(16.dp)
                    )
                }
            }
        }
    }
}