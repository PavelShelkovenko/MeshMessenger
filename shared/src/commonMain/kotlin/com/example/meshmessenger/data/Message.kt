package com.example.meshmessenger.data

data class Message(
    val id: Int,
    val text: String,
    val time: String,
    val authorName: String,
    val authorSurname: String,
) //добавить статус

val messagesListExample = mutableListOf(
    Message(2,"Привет", "11:34", "Дмитрий", "Смирнов"),
    Message(2,"Чем занимаешься ?", "11:34", "Дмитрий", "Рахимзянов"),

    Message(1,"Привет вышел на улицу подышать воздухом", "11:39", "Артур", "Рахимзянов"),
    Message(1,".", "11:36", "Артур", "Рахимзянов"),

    Message(1,"1Первое сообщение3faaaaaa", "11:36", "Артур", "Рахимзянов"),
    Message(1,"1Первое сообщение4", "11:37", "Артур", "Рахимзянов"),
    Message(1,"1Первое сообщение5dawffffffff", "11:38", "Артур", "Рахимзянов"),

    Message(2,"2Первое сообщениеfAWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA", "11:39", "Артур", "Рахимзянов"),
    Message(1,"1Первое dfkjbhvhgvsafkjbasljhablha", "11:34", "Артур", "Рахимзянов"),

    Message(2,"2Первое сообщение", "11:34", "Кто-то", "Рахимзянов"),
    Message(2,"2Первое сообщение2", "11:35","Кто-то", "Рахимзянов"),
    Message(2,".", "11:36", "Вова", "Рахимзянов"),

    Message(1,"1asdfrtyuioplkjhgfdxzxcvbnmmnbvcxzxcvbn123", "11:34", "Артур", "Рахимзянов"),
    Message(1,"1Первое сообщенalkjrsgnkjlbglkbwgelbwlegb lkdn ие", "11:34", "Артур", "Рахимзянов"),
    Message(1,"1Первое hvgvgvskebflbklFBLWhbglBсообщение", "11:34", "Артур", "Рахимзянов"),

    Message(2,"2Первое сообщение3faaaaaa", "11:36", "Кто-то", "Рахимзянов"),
    Message(2,"2Первое сообщение4", "11:37", "Кто-то", "Рахимзянов"),
    Message(2,"2Первое сообщение5dawffffffff", "11:38", "Кто-то", "Рахимзянов"),
    Message(2,"2asdfrtyuioplkjhgfdxzxcvbnmmnbvcxzxcvbn123", "11:34", "Кто-то", "Рахимзянов"),
    Message(1,"1Первое сообщение2", "11:35","Артур", "Рахимзянов"),
    Message(2,"2Первое dfkjbhvhgvsafkjbasljhablha", "11:34", "Дима", "Рахимзянов"),
    Message(2,"2Первое сообщенalkjrsgnkjlbglkbwgelbwlegb lkdn ие", "11:34", "Кто-то", "Рахимзянов"),
    Message(2,"2Первое сообщениеfAWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA", "11:39", "Кто-то", "Рахимзянов"),
)
