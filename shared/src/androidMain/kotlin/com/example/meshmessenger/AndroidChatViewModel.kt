package com.example.meshmessenger

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.meshmessenger.domain.database_repository.DatabaseRepository
import com.example.meshmessenger.presentation.chatScreen.ChatEvent
import com.example.meshmessenger.presentation.chatScreen.ChatViewModel


class AndroidChatViewModel(
    private val databaseRepository: DatabaseRepository
) : ViewModel() {

    private val viewModel by lazy {
        ChatViewModel(
            databaseRepository = databaseRepository,
            coroutineScope = viewModelScope
        )
    }

    val state = viewModel.state

    fun onEvent(event: ChatEvent){
        return viewModel.onEvent(event)
    }
}