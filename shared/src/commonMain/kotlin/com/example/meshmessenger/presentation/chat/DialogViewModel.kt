package com.example.meshmessenger.presentation.chat

import com.example.meshmessenger.data.Message
import com.example.meshmessenger.data.messagesListExample
import com.example.meshmessenger.domain.chat.Chat
import com.example.meshmessenger.domain.chat_members.ChatMembers
import com.example.meshmessenger.domain.database_repository.DatabaseRepository
import com.example.meshmessenger.domain.user.User
import dev.icerock.moko.mvvm.flow.CMutableStateFlow
import dev.icerock.moko.mvvm.flow.CStateFlow
import dev.icerock.moko.mvvm.flow.cMutableStateFlow
import dev.icerock.moko.mvvm.flow.cStateFlow
import dev.icerock.moko.mvvm.viewmodel.ViewModel
import kotlinx.coroutines.*
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
        CoroutineScope(Dispatchers.Unconfined).launch {
            databaseRepository.deleteAllMessages()
            databaseRepository.insertMessage(com.example.meshmessenger.domain.message.Message(9,"1",1,"1text", 1, 3, "dd", null))
            databaseRepository.insertMessage(com.example.meshmessenger.domain.message.Message(10,"1",1,"2text", 1, 2, "dd", null))
            databaseRepository.insertMessage(com.example.meshmessenger.domain.message.Message(11,"1",1,"3text", 1, 1, "dd", null))
            databaseRepository.insertMessage(com.example.meshmessenger.domain.message.Message(12,"1",1,"1text", 1, 4, "dd", null))

            databaseRepository.insertMessage(com.example.meshmessenger.domain.message.Message(5,"1",2,"1text", 1, 3, "dd", null))
            databaseRepository.insertMessage(com.example.meshmessenger.domain.message.Message(6,"1",2,"2text", 1, 2, "dd", null))
            databaseRepository.insertMessage(com.example.meshmessenger.domain.message.Message(7,"1",2,"3text", 1, 1, "dd", null))
            databaseRepository.insertMessage(com.example.meshmessenger.domain.message.Message(8,"1",2,"1text", 1, 4, "dd", null))

            println(databaseRepository.getAllMessagesFromOneChat(1))
            println(databaseRepository.getAllMessages())
        }
    }
}

