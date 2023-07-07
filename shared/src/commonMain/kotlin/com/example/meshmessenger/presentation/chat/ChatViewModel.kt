package com.example.meshmessenger.presentation.chat

import com.example.meshmessenger.domain.database_repository.DatabaseRepository
import dev.icerock.moko.mvvm.viewmodel.ViewModel

class ChatViewModel(private val databaseRepository: DatabaseRepository): ViewModel() {

    val channelsListExample = arrayListOf(
        Channel(2, "https://randompicturegenerator.com/img/people-generator/g167cc57243fe6f6c729e12865ad5dfa85070cba34876f0383cf9ed9dc743ebe29bb4b8f8cfdb8fd31780cf03757b94c0_640.jpg","Для приватных", "чатов", "будем", "10:23"),
        Channel(3, "https://randompicturegenerator.com/img/people-generator/gc1d29ce8fc0d5331f847c263c6ce1b8239a30e684ad92740c4b6d3eb2794e50355dfcf7011495fd0c1ac16390a9a9ed6_640.jpg","Для публичных", "чатов", "скоро", "10:26"),
        Channel(4,"https://images.unsplash.com/photo-1682530016961-19763e9599b9?crop=entropy&cs=tinysrgb&fit=max&fm=jpg&ixid=M3wxODY2Nzh8MHwxfHJhbmRvbXx8fHx8fHx8fDE2ODQ2NzI1MjF8&ixlib=rb-4.0.3&q=80&w=1080", "Алексей", "Алексеев", "привет", "13:34"),
        Channel(5,"https://plus.unsplash.com/premium_photo-1683135218463-12fd419b0b85?crop=entropy&cs=tinysrgb&fit=max&fm=jpg&ixid=M3wxODY2Nzh8MHwxfHJhbmRvbXx8fHx8fHx8fDE2ODQ2NzI1NDF8&ixlib=rb-4.0.3&q=80&w=1080", "Вова", "Ершов", "в итоге ", "6:23"),
        Channel(6,"https://plus.unsplash.com/premium_photo-1675754403388-7548ba37809e?crop=entropy&cs=tinysrgb&fit=max&fm=jpg&ixid=M3wxODY2Nzh8MHwxfHJhbmRvbXx8fHx8fHx8fDE2ODQ2NzI1NTd8&ixlib=rb-4.0.3&q=80&w=1080", "Никита", "Нейман", ".", "11:53"),
        Channel(7,"https://images.unsplash.com/photo-1683220642973-a4d0ca134714?crop=entropy&cs=tinysrgb&fit=max&fm=jpg&ixid=M3wxODY2Nzh8MHwxfHJhbmRvbXx8fHx8fHx8fDE2ODQ2NzI1ODJ8&ixlib=rb-4.0.3&q=80&w=1080", "Паша", "Зыков", "до встречи!", "18:33"),
        Channel(8, "https://plus.unsplash.com/premium_photo-1681584472258-6ef06bfa771c?crop=entropy&cs=tinysrgb&fit=max&fm=jpg&ixid=M3wxODY2Nzh8MHwxfHJhbmRvbXx8fHx8fHx8fDE2ODQ2NzI1OTN8&ixlib=rb-4.0.3&q=80&w=1080","Искандер", "Сафиуллин", "хорошо", "19:15"),
        Channel(9, "https://plus.unsplash.com/premium_photo-1666264200782-8cc1096bb417?crop=entropy&cs=tinysrgb&fit=max&fm=jpg&ixid=M3wxODY2Nzh8MHwxfHJhbmRvbXx8fHx8fHx8fDE2ODQ2NzI2MDh8&ixlib=rb-4.0.3&q=80&w=1080","Александр", "Архипов", "ок", "22:21"),
        Channel(10, "https://images.unsplash.com/photo-1682530016961-19763e9599b9?crop=entropy&cs=tinysrgb&fit=max&fm=jpg&ixid=M3wxODY2Nzh8MHwxfHJhbmRvbXx8fHx8fHx8fDE2ODQ2NzI1MjF8&ixlib=rb-4.0.3&q=80&w=1080", "Артур", "Рахимзянов", "завтра в 12.30", "56:34"),
        Channel(8, "https://plus.unsplash.com/premium_photo-1681584472258-6ef06bfa771c?crop=entropy&cs=tinysrgb&fit=max&fm=jpg&ixid=M3wxODY2Nzh8MHwxfHJhbmRvbXx8fHx8fHx8fDE2ODQ2NzI1OTN8&ixlib=rb-4.0.3&q=80&w=1080","Ярослав", "Николаев", "ок", "19:15"),
    )
}

data class Channel(
    val id: Int,
    val imageURL: String,
    val name: String,
    val surname: String,
    val lastMessage: String,
    val time: String
)