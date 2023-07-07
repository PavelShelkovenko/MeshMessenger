package com.example.meshmessenger.data

data class Message(
    val id: Int,
    val text: String,
    val time: String,
    val authorName: String,
    val authorSurname: String,
) //добавить статус

val messagesListExample = mutableListOf(
    Message(2,"Привет ", "11:34", "Дмитрий", "Смирнов"),
    Message(2,"Чем занимаешься ? Я пользуюсь приложением MeshApp !", "11:34", "Дмитрий", "Рахимзянов"),

    Message(1," Привет чем занимаешься ? Я пользуюсь приложением MeshApp !", "11:39", "Артур", "Рахимзянов"),
    Message(1,".", "11:36", "Артур", "Рахимзянов"),

    Message(2,"Это очень очень очень очень очень очень очень очень очень очень очень очень очень очень очень большое сообщение", "11:37", "Артур", "Рахимзянов"),
    Message(1,"Привет", "11:38", "Артур", "Рахимзянов"),

    Message(1,"Чем занимаешься ? Я пользуюсь приложением MeshApp !", "11:34", "Артур", "Рахимзянов"),

    Message(2,"    \ud83d\ude11, // Expressionless Face", "11:34", "Кто-то", "Рахимзянов"),
    Message(2,"Привет", "11:35","Кто-то", "Рахимзянов"),
    Message(2,".", "11:36", "Вова", "Рахимзянов"),
)
