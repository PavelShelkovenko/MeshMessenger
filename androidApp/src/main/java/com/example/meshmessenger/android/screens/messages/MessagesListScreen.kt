package com.example.meshmessenger.android.screens.messages

import android.annotation.SuppressLint
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import coil.transform.CircleCropTransformation
import com.example.meshmessenger.SharedRes
import com.example.meshmessenger.android.R
import com.example.meshmessenger.android.root.stringResource
import com.example.meshmessenger.android.screens.messages.onemessage.private_chat.OneMessageOnPrivateChat
import com.example.meshmessenger.android.screens.messages.onemessage.public_chat.OneMessageOnPublicChat
import com.example.meshmessenger.android.theme.*
import com.example.meshmessenger.data.Message
import com.example.meshmessenger.presentation.message.MessageViewModel
import kotlinx.coroutines.launch

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
                            Text(text = stringResource(id = SharedRes.strings.message), color = PlaceholderColor)
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
                    "Для приватных чатов" -> {
                        Spacer(modifier = Modifier.height(1.dp))
                        OneMessageOnPrivateChat(message)
                        Spacer(modifier = Modifier.height(1.dp))
                    }
                    "Для публичных чатов" -> {
                        Spacer(modifier = Modifier.height(1.dp))
                        OneMessageOnPublicChat(message)
                        Spacer(modifier = Modifier.height(1.dp))
                    }
                    else -> {
                        Spacer(modifier = Modifier.height(1.dp))
                        OneMessageOnPublicChat(message)
                        Spacer(modifier = Modifier.height(1.dp))
                    }
                }
            }
        }
    }
}


@Composable
fun roundedCornerShapeDefine(id: Int): RoundedCornerShape {
    return if (id == 1) {
        RoundedCornerShapeForLocalUser
    } else {
        RoundedCornerShapeForAnotherUser
    }
}