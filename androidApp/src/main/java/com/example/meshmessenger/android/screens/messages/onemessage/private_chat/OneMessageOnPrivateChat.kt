package com.example.meshmessenger.android.screens.messages.onemessage.private_chat

import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.meshmessenger.android.R
import com.example.meshmessenger.android.screens.messages.roundedCornerShapeDefine
import com.example.meshmessenger.android.theme.PlaceholderColor
import com.example.meshmessenger.data.Message

@Composable
fun OneMessageOnPrivateChat(message: Message) {
    Row(
        verticalAlignment = Alignment.Bottom,
        horizontalArrangement = if (message.id == 1) Arrangement.End else Arrangement.Start, //
        modifier = Modifier.fillMaxSize() //пришлось задать весь размер иначе witdhIn не работает, а считает слева
    ) {

        Surface(
            shape = roundedCornerShapeDefine(id = message.id),
            elevation = 5.dp,
            modifier = Modifier
                .wrapContentWidth()
                .widthIn(
                    max = 340.dp,
                    min = 80.dp
                )    //чтобы задать макс и мин длину, через fraction не работает
                .padding(
                    bottom = 15.dp,
                    start = 15.dp,
                    end = 15.dp
                ), //чтобы фотка была ниже чем текст сообщения
        ) {
            Column(
                modifier = Modifier.wrapContentWidth(),
                horizontalAlignment = if (message.id == 1) Alignment.End else Alignment.Start, //
                verticalArrangement = Arrangement.Center
            ) {

                Box(
                    contentAlignment = if (message.id == 1) Alignment.CenterEnd else Alignment.CenterStart,
                    modifier = Modifier.wrapContentWidth()
                ) {
                    Text(
                        text = message.text,
                        maxLines = Int.MAX_VALUE,
                        modifier = Modifier.padding(start = 5.dp, end = 5.dp, top = 16.dp)
                    )
                }

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
                        }
                    )
                    Spacer(modifier = Modifier.width(2.dp))
                    Icon(
                        painter = painterResource(id = R.drawable.check_all),
                        contentDescription = "status icon",
                        modifier = Modifier
                            .size(16.dp)
                            .padding()
                    )
                }
            }
        }
    }
}