package com.example.meshmessenger.android.screens.messages.onemessage.private_chat

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.meshmessenger.android.theme.Onest
import com.example.meshmessenger.android.theme.PlaceholderColor
import com.example.meshmessenger.data.Message

@Composable
fun OneMessageOnPrivateChat(message: Message) {

    val isMyMessage = message.id == 1

    Row(
        verticalAlignment = Alignment.Bottom,
        horizontalArrangement = if (isMyMessage) Arrangement.End else Arrangement.Start,
        modifier = Modifier.fillMaxSize()
    ) {

        Surface(
            shape = ShapesOfPrivateTextMessages(isMyMessage = isMyMessage),
            elevation = 5.dp,
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
                        Modifier.padding(start = 15.dp, end = 25.dp, top = 15.dp)
                    } else {
                        Modifier.padding(start = 25.dp, end = 15.dp, top = 15.dp)
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

                Text(
                    text = message.time,
                    maxLines = 1,
                    fontSize = 10.sp,
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
}