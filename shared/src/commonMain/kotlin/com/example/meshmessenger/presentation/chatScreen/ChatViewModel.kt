package com.example.meshmessenger.presentation.chatScreen

import com.example.meshmessenger.data.Message
import com.example.meshmessenger.data.messagesListExample
import com.example.meshmessenger.domain.database_repository.DatabaseRepository
import com.example.meshmessenger.domain.utils.toCommonStateFlow
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update

class ChatViewModel(
    private val databaseRepository: DatabaseRepository,
    coroutineScope: CoroutineScope? ) {

    private val viewModelScope = coroutineScope ?: CoroutineScope(Dispatchers.Main)

    private val _state = MutableStateFlow(ChatState())
    val state = _state.stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(5000),
        ChatState()
    ).toCommonStateFlow()

    init {
        _state.update {
            it.copy(
                messagesList = messagesListExample
            )
        }
    }

    fun onEvent(event: ChatEvent) {
        when(event) {
            is ChatEvent.TextChanged -> {
                _state.update {
                    it.copy( textOfMessage = event.textChanged )
                }
            }

            is ChatEvent.MessageSend -> {
                sendMessage(event.message)
            }
        }
    }



    private fun sendMessage(message: Message) {

        _state.value.messagesList.add(message)
        _state.update {
            it.copy(textOfMessage = "")
        }
    }
}

