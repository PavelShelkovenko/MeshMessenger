package com.example.meshmessenger.android.screens.messages

import android.annotation.SuppressLint
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
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
import androidx.compose.ui.text.style.TextAlign
import coil.transform.CircleCropTransformation
import kotlinx.coroutines.launch
import androidx.compose.runtime.getValue
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import com.example.meshmessenger.android.theme.*
import com.example.meshmessenger.presentation.message.MessageViewModel

@OptIn(ExperimentalComposeUiApi::class)
@SuppressLint(
    "UnusedMaterialScaffoldPaddingParameter",
    "MutableCollectionMutableState",
    "CoroutineCreationDuringComposition"
)
@Composable
fun MessagesListScreen(
    navController: NavController,
    chatName: String?,
    pickMedia: ActivityResultLauncher<PickVisualMediaRequest>,
    messageViewModel: MessageViewModel
) {

    val textOfMessage = messageViewModel.textMessage.collectAsState()

    val isEmojiKeyboardEnabled = remember { mutableStateOf(false) }
    val messagesList by messageViewModel.listOfMessages.collectAsState()

    val listState = rememberLazyListState()
    val coroutineScope = rememberCoroutineScope()

    val keyboardController = LocalSoftwareKeyboardController.current

    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.White),
        topBar = {
            TopAppBar(backgroundColor = Color.White) {
                IconButton(onClick = {
                    navController.navigate("chatListScreen") {
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
                    contentDescription = null,
                    modifier = Modifier
                        .size(40.dp)
                        .clip(CircleShape),
                    contentScale = ContentScale.Crop
                )
                Spacer(modifier = Modifier.width(20.dp))
                Text(chatName!!, fontSize = 20.sp, color = Color.Black, fontFamily = Poppins)
            }
        },
        bottomBar = {
            Column(modifier = Modifier.fillMaxWidth()) {

                Row(
                    modifier = Modifier.padding(start = 4.dp),
                    verticalAlignment = Alignment.Bottom
                ) {

                    Box(
                        modifier = Modifier.padding(start = 4.dp, bottom = 4.dp),
                    ) {
                        if (isEmojiKeyboardEnabled.value  ) {
                            IconButton(onClick = {
                                isEmojiKeyboardEnabled.value = false
                                keyboardController?.show()
                            }) {
                                Icon(
                                    painter = painterResource(id = R.drawable.keyboard),
                                    tint = GreyOrdinary,
                                    contentDescription = "emojis picker",
                                    modifier = Modifier
                                        .size(30.dp)
                                        .clip(CircleShape)
                                        .padding(bottom = 4.dp)

                                )
                            }
                        } else {
                            IconButton(onClick = {
                                keyboardController?.hide()
                                isEmojiKeyboardEnabled.value = true
                            }) {
                                Icon(
                                    painter = painterResource(id = R.drawable.emoticon_outline),
                                    contentDescription = "emoji",
                                    tint = GreyOrdinary,
                                    modifier = Modifier
                                        .size(30.dp)
                                        .clip(CircleShape)
                                )
                            }
                        }
                    }
                    TextField(
                        value = textOfMessage.value,
                        onValueChange = { messageViewModel.textMessage.value = it },

                        modifier = Modifier
                            .background(Color.White)
                            .fillMaxWidth(0.75f)
                            .verticalScroll(rememberScrollState())
                            .padding(end = 4.dp),

                        colors = TextFieldDefaults.textFieldColors(
                            textColor = Color.Black,
                            backgroundColor = Color.White,
                            cursorColor = PrimaryColor,
                            focusedIndicatorColor = Color.Transparent,
                            unfocusedIndicatorColor = Color.Transparent
                        ),
                        maxLines = 4,
                        placeholder = {
                            Text(text = "Message", color = PlaceholderColor)
                        },
                    )
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 4.dp),
                        contentAlignment = Alignment.BottomEnd
                    ) {
                        Row {
                            IconButton(onClick = {
                                pickMedia.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageAndVideo))
                            }) {
                                Icon(
                                    painter = painterResource(id = R.drawable.paperclip),
                                    contentDescription = "photo picker",
                                    tint = GreyOrdinary,
                                    modifier = Modifier
                                        .rotate(220f)
                                        .clip(CircleShape)
                                        .size(30.dp)
                                )
                            }
                            if (textOfMessage.value.isBlank()) {
                                IconButton(onClick = { /*TODO*/ }) {
                                    Icon(
                                        painter = painterResource(id = R.drawable.microphone),
                                        contentDescription = null,
                                        tint = GreyOrdinary,

                                        modifier = Modifier
                                            .size(30.dp)
                                            .clip(CircleShape)
                                    )
                                }
                            } else {
                                IconButton(
                                    onClick = {
                                        messageViewModel.sendMessage(
                                            Message(
                                                1, textOfMessage.value,
                                                authorName = "Артур",
                                                time = "12.23",
                                                authorSurname = "Рахимзянов"
                                            )
                                        )
                                    }) {
                                    Icon(
                                        painter = painterResource(id = R.drawable.send),
                                        contentDescription = "send message",
                                        tint = PrimaryColor,
                                        modifier = Modifier
                                            .size(30.dp)
                                            .clip(CircleShape)
                                    )
                                }
                            }
                        }
                    }
                }
                if ( isEmojiKeyboardEnabled.value ) {
                    EmojiPicker(messageViewModel)
                }
            }
        },

        ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(PaddingValues(bottom = innerPadding.calculateBottomPadding()))
                .background(BackgroundColor),
            state = listState
        ) {
            coroutineScope.launch {
                listState.scrollToItem(messagesList.lastIndex)
            }
            items(messagesList) { message ->
                when (chatName) {
                    "Артур Рахимзянов" -> {
                        Spacer(modifier = Modifier.height(1.dp))
                        if (message.id == 1) {
                            MessageCard(message)
                        } else {                    // у каждого есть фото
                            MessageCard2(message)
                        }
                        Spacer(modifier = Modifier.height(1.dp))
                    }
                    "Артур2 Рахимзянов2" -> {
                        Spacer(modifier = Modifier.height(1.dp))
                        OneMessageOnPrivateDialog(message)       //для приватных сообщений
                        Spacer(modifier = Modifier.height(1.dp))
                    }
                    "Дмитрий Кургаев" -> {
                        Spacer(modifier = Modifier.height(1.dp))
                        OneMessageOnPublicChat(message)            //чат
                        Spacer(modifier = Modifier.height(1.dp))
                    }
                    else -> {
                        Spacer(modifier = Modifier.height(1.dp))
                        OneMessageOnPublicChat(message)            //чат
                        Spacer(modifier = Modifier.height(1.dp))
                    }
                }
            }
        }
    }
}

@Composable
fun EmojiPicker(messageViewModel: MessageViewModel) {
    LazyVerticalGrid(
        modifier = Modifier.height(150.dp),
        columns = GridCells.Adaptive(minSize = 42.dp)
    ) {
        items(emojis) { emoji ->
            Text(
                modifier = Modifier
                    .clip(CircleShape)
                    .clickable(onClick = {
                        messageViewModel.textMessage.value = messageViewModel.textMessage.value + emoji
                    })
                    .sizeIn(minWidth = 42.dp, minHeight = 42.dp)
                    .padding(8.dp),
                text = emoji,
                style = LocalTextStyle.current.copy(
                    fontSize = 18.sp,
                    textAlign = TextAlign.Center
                )
            )
        }
    }
}


@Composable
fun OneMessageOnPrivateDialog(message: Message) {
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
                            modifier = Modifier.padding(end = 10.dp, top = 2.dp)
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
                        }
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
                        }
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


@Composable
fun roundedCornerShapeDefine(id: Int): RoundedCornerShape {
    return if (id == 1) {
        RoundedCornerShape(
            topEnd = 16.dp,
            topStart = 16.dp,
            bottomEnd = 0.dp,
            bottomStart = 16.dp
        )
    } else {
        RoundedCornerShape(
            topEnd = 16.dp,
            topStart = 16.dp,
            bottomEnd = 16.dp,
            bottomStart = 0.dp
        )
    }
}


@Composable
fun MessageCard(message: Message) {

    Row(
        verticalAlignment = Alignment.Bottom,
        horizontalArrangement = Arrangement.End,
        modifier = Modifier.fillMaxSize() //пришлось задать весь размер иначе witdhIn не работает, а считает слева
    ) {

        Surface(
            shape = RoundedCornerShape(
                topEnd = 16.dp,
                topStart = 16.dp,
                bottomEnd = 0.dp,
                bottomStart = 16.dp
            ),
            elevation = 5.dp,
            modifier = Modifier
                .wrapContentWidth()
                .widthIn(
                    max = 300.dp,
                    min = 60.dp,

                    )    //чтобы задать макс и мин длину, через fraction не работает
                .padding(bottom = 15.dp) //чтобы фотка была ниже чем текст сообщения
        ) {
            Column(
                modifier = Modifier.wrapContentWidth(),
                horizontalAlignment = Alignment.End,
                verticalArrangement = Arrangement.Center
            ) {

                Box(
                    contentAlignment = Alignment.CenterEnd,
                    modifier = Modifier.wrapContentWidth()
                ) {
                    Text(
                        text = message.text,
                        maxLines = Int.MAX_VALUE,
                        modifier = Modifier.padding(start = 5.dp, end = 5.dp, top = 16.dp)
                    )
                }

                Row(
                    modifier = Modifier
                        .wrapContentWidth()
                        .padding(end = 2.dp),
                    horizontalArrangement = Arrangement.End
                ) {
                    Text(
                        text = message.time,
                        maxLines = 1,
                        fontSize = 10.sp,
                        color = PlaceholderColor,
                        modifier = Modifier.padding(end = 2.dp)
                    )
                    Icon(
                        painter = painterResource(id = R.drawable.check_all),
                        contentDescription = "status icon",
                        modifier = Modifier.size(16.dp)
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
                .clip(CircleShape),
            contentScale = ContentScale.Crop,
            //alignment = Alignment.BottomEnd
        )
    }
}

@Composable
fun MessageCard2(message: Message) {

    Row(
        verticalAlignment = Alignment.Bottom,
        horizontalArrangement = Arrangement.Start, //
        modifier = Modifier.fillMaxSize() //пришлось задать весь размер иначе witdhIn не работает, а считает слева
    ) {

        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .transformations(CircleCropTransformation())
                .data("https://images.unsplash.com/photo-1639091435585-508ff02f0b07?crop=entropy&cs=tinysrgb&fit=max&fm=jpg&ixid=M3wxODY2Nzh8MHwxfHJhbmRvbXx8fHx8fHx8fDE2ODUwMjU2Nzd8&ixlib=rb-4.0.3&q=80&w=1080")
                .crossfade(true)
                .build(),
            contentDescription = "Ваша аватарка",
            modifier = Modifier
                .padding(start = 5.dp)   //
                .size(30.dp)
                .clip(CircleShape),
            contentScale = ContentScale.Crop,
            alignment = Alignment.BottomStart
        )

        Spacer(modifier = Modifier.width(5.dp))

        Surface(
            shape = RoundedCornerShape(
                topEnd = 16.dp,
                topStart = 16.dp,
                bottomEnd = 16.dp,   //
                bottomStart = 0.dp
            ),
            elevation = 5.dp,
            modifier = Modifier
                .wrapContentWidth()
                .widthIn(
                    max = 300.dp,
                    min = 60.dp
                )    //чтобы задать макс и мин длину, через fraction не работает
                .padding(bottom = 15.dp), //чтобы фотка была ниже чем текст сообщения
        ) {
            Column(
                modifier = Modifier.wrapContentWidth(),
                horizontalAlignment = Alignment.Start, //
                verticalArrangement = Arrangement.Center
            ) {

                Box(
                    contentAlignment = Alignment.CenterEnd,
                    modifier = Modifier.wrapContentWidth()
                ) {
                    Text(
                        text = message.text,
                        maxLines = Int.MAX_VALUE,
                        modifier = Modifier.padding(start = 5.dp, end = 5.dp, top = 16.dp)
                    )
                }

                Row(
                    modifier = Modifier
                        .wrapContentWidth()
                        .padding(start = 2.dp),
                    horizontalArrangement = Arrangement.Start
                ) {
                    Text(
                        text = message.time,
                        maxLines = 1,
                        fontSize = 10.sp,
                        color = PlaceholderColor,
                        modifier = Modifier.padding(end = 2.dp)
                    )
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