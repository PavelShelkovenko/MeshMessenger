package com.example.meshmessenger.chat

import com.example.meshmessenger.data.messagesListExample
import dev.icerock.moko.mvvm.flow.CMutableStateFlow
import dev.icerock.moko.mvvm.flow.cMutableStateFlow
import dev.icerock.moko.mvvm.viewmodel.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import com.example.meshmessenger.data.Message
import dev.icerock.moko.mvvm.flow.CStateFlow
import dev.icerock.moko.mvvm.flow.cStateFlow

class DialogViewModel: ViewModel() {
    val textMessage: CMutableStateFlow<String> = MutableStateFlow("").cMutableStateFlow()
    private val _listOfMessages: CMutableStateFlow<MutableList<Message>> =
                MutableStateFlow(messagesListExample).cMutableStateFlow()
    val listOfMessages: CStateFlow<MutableList<Message>> get() = _listOfMessages.cStateFlow()

    fun sendMessage(message: Message){
        _listOfMessages.value.add(message)
        textMessage.value = ""
    }
}

