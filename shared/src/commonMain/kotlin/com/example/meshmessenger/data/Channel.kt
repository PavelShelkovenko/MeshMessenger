package com.example.meshmessenger.data

data class Channel(
     val id: Int,
     val imageURL: String,
     val name: String,
     val surname: String,
     val lastMessage: String,
     val time: String
    )

val channelsListExample = arrayListOf(
    Channel(1, "https://randart.ru/art/JD99/wallpapers" , "Артур", "Рахимзянов", ".", "10:23"), //для примера пока так потом data классы нужно поменять и оставить ток 2 поля
    Channel(2, "https://randart.ru/art/JD99/wallpapers","Артур2", "Рахимзянов2", ".", "10:23"),
    Channel(3, "https://randart.ru/art/JD99/wallpapers","Дмитрий", "Кургаев", "....", "10:26"),
    Channel(4,"https://images.unsplash.com/photo-1682530016961-19763e9599b9?crop=entropy&cs=tinysrgb&fit=max&fm=jpg&ixid=M3wxODY2Nzh8MHwxfHJhbmRvbXx8fHx8fHx8fDE2ODQ2NzI1MjF8&ixlib=rb-4.0.3&q=80&w=1080", "Алексей", "Алексеев", "йцукенг", "13:34"),
    Channel(5,"https://plus.unsplash.com/premium_photo-1683135218463-12fd419b0b85?crop=entropy&cs=tinysrgb&fit=max&fm=jpg&ixid=M3wxODY2Nzh8MHwxfHJhbmRvbXx8fHx8fHx8fDE2ODQ2NzI1NDF8&ixlib=rb-4.0.3&q=80&w=1080", "Вова", "Ершов", "чсмрпм итоь ", "6:23"),
    Channel(6,"https://plus.unsplash.com/premium_photo-1675754403388-7548ba37809e?crop=entropy&cs=tinysrgb&fit=max&fm=jpg&ixid=M3wxODY2Nzh8MHwxfHJhbmRvbXx8fHx8fHx8fDE2ODQ2NzI1NTd8&ixlib=rb-4.0.3&q=80&w=1080", "Никита", "Нейман", ".", "11:53"),
    Channel(7,"https://images.unsplash.com/photo-1683220642973-a4d0ca134714?crop=entropy&cs=tinysrgb&fit=max&fm=jpg&ixid=M3wxODY2Nzh8MHwxfHJhbmRvbXx8fHx8fHx8fDE2ODQ2NzI1ODJ8&ixlib=rb-4.0.3&q=80&w=1080", "Паша", "Зыков", ".", "18:33"),
    Channel(8, "https://plus.unsplash.com/premium_photo-1681584472258-6ef06bfa771c?crop=entropy&cs=tinysrgb&fit=max&fm=jpg&ixid=M3wxODY2Nzh8MHwxfHJhbmRvbXx8fHx8fHx8fDE2ODQ2NzI1OTN8&ixlib=rb-4.0.3&q=80&w=1080","Имя1", "Сафиуллин", ".", "19:15"),
    Channel(9, "https://plus.unsplash.com/premium_photo-1666264200782-8cc1096bb417?crop=entropy&cs=tinysrgb&fit=max&fm=jpg&ixid=M3wxODY2Nzh8MHwxfHJhbmRvbXx8fHx8fHx8fDE2ODQ2NzI2MDh8&ixlib=rb-4.0.3&q=80&w=1080","Имя2", "Архипов", ".", "22:21"),
    Channel(10, "https://images.unsplash.com/photo-1682530016961-19763e9599b9?crop=entropy&cs=tinysrgb&fit=max&fm=jpg&ixid=M3wxODY2Nzh8MHwxfHJhbmRvbXx8fHx8fHx8fDE2ODQ2NzI1MjF8&ixlib=rb-4.0.3&q=80&w=1080", "NAMe3", "Famofij", "ddddddddddd", "56:34")
    )
