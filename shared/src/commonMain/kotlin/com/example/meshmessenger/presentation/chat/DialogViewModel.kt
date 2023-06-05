package com.example.meshmessenger.presentation.chat

import com.example.meshmessenger.data.Message
import com.example.meshmessenger.data.messagesListExample
import com.example.meshmessenger.domain.database_repository.DatabaseRepository
import dev.icerock.moko.mvvm.flow.CMutableStateFlow
import dev.icerock.moko.mvvm.flow.CStateFlow
import dev.icerock.moko.mvvm.flow.cMutableStateFlow
import dev.icerock.moko.mvvm.flow.cStateFlow
import dev.icerock.moko.mvvm.viewmodel.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow

class DialogViewModel(private val databaseRepository: DatabaseRepository) : ViewModel() {

    val textMessage: CMutableStateFlow<String> = MutableStateFlow("").cMutableStateFlow()
    private val _listOfMessages: CMutableStateFlow<MutableList<Message>> =
        MutableStateFlow(messagesListExample).cMutableStateFlow()
    val listOfMessages: CStateFlow<MutableList<Message>> get() = _listOfMessages.cStateFlow()

    fun sendMessage(message: Message) {
        val list = ArrayList(_listOfMessages.value)
        list.add(message)
        _listOfMessages.value = list
        textMessage.value = ""
    }
}

