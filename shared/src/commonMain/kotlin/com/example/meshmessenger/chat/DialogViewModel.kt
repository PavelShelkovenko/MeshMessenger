package com.example.meshmessenger.chat

import dev.icerock.moko.mvvm.flow.CMutableStateFlow
import dev.icerock.moko.mvvm.flow.cMutableStateFlow
import dev.icerock.moko.mvvm.viewmodel.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow

class DialogViewModel: ViewModel() {
    val textMessage: CMutableStateFlow<String> = MutableStateFlow("").cMutableStateFlow()

    fun messageChange(){

    }
}