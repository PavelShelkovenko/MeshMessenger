package com.example.meshmessenger.android.screens.messages.onemessage.private_chat

import android.util.Log

import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.meshmessenger.android.screens.TAG
import com.example.meshmessenger.android.theme.PlaceholderColor
import com.example.meshmessenger.data.Message

@Composable
fun OneMessageOnPrivateChat(message: Message) {

    val isMymessage = message.id == 1

    Row(
        verticalAlignment = Alignment.Bottom,
        horizontalArrangement = if (isMymessage) Arrangement.End else Arrangement.Start, //
        modifier = Modifier.fillMaxSize() //пришлось задать весь размер иначе witdhIn не работает, а считает слева
    ) {

        Surface(
            shape = if (isMymessage) {
                Log.e(TAG, "first")
                ShapesOfPrivateTextMessages(25f, isLastMessage = true, isMyMessage = true)
            } else {
                Log.e(TAG, "second")
                ShapesOfPrivateTextMessages(25f, isLastMessage = true, isMyMessage = false)
            },
            elevation = 5.dp,
            modifier = Modifier
                .wrapContentWidth()
                .widthIn(max = 320.dp, min = 60.dp)    //чтобы задать макс и мин длину, через fraction не работает
                .padding(all = 2.dp),
        ) {
            Column(
                modifier = if (isMymessage) {
                    Modifier.wrapContentWidth()
                } else {
                    Modifier.wrapContentWidth()
                },
                horizontalAlignment = if (isMymessage) Alignment.End else Alignment.Start, //
                verticalArrangement = Arrangement.Center
            ) {

                Box(
                    contentAlignment = if (isMymessage) Alignment.CenterEnd else Alignment.CenterStart,
                    modifier = if(isMymessage) {
                        Modifier.wrapContentWidth().padding(start = 5.dp, end = 15.dp)
                    } else {
                        Modifier.wrapContentWidth().padding(start = 15.dp, end = 5.dp)
                    }
                ) {
                    Text(
                        text = message.text,
                        maxLines = Int.MAX_VALUE,
                        modifier = Modifier.padding(start = 5.dp, end = 5.dp, top = 16.dp)
                    )
                }

                Row(
                    modifier = if (isMymessage) {
                        Modifier.wrapContentWidth()
                    } else {
                        Modifier.wrapContentWidth()
                    },
                    horizontalArrangement = if (isMymessage) {
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
                        modifier = if (isMymessage) {
                            Modifier.padding(start = 15.dp , end = 15.dp)
                        } else {
                            Modifier.padding(end = 15.dp, start = 15.dp)
                        }
                    )
                }
            }
        }
    }
}