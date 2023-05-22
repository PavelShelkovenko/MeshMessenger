package com.example.meshmessenger.data

data class Message(
    val id: Int,
    val text: String,
    val time: String,
    val authorName: String,
    val authorSurname: String,
) //добавить статус

val messagesListExample = arrayListOf(
    Message(1,"Первое сообщение", "11:34", "Артур", "Рахимзянов"),
    Message(1,"Первое сообщение2", "11:35","Артур", "Рахимзянов"),
    Message(1,"Первое сообщение3faaaaaa", "11:36", "Артур", "Рахимзянов"),
    Message(1,"Первое сообщение4", "11:37", "Артур", "Рахимзянов"),
    Message(1,"Первое сообщение5dawffffffff", "11:38", "Артур", "Рахимзянов"),
    Message(1,"Первое сообщениеfAWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW", "11:39", "Артур", "Рахимзянов"),
)
