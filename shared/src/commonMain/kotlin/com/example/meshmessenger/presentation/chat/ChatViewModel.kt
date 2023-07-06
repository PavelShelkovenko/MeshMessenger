package com.example.meshmessenger.presentation.chat

import com.example.meshmessenger.domain.database_repository.DatabaseRepository
import dev.icerock.moko.mvvm.viewmodel.ViewModel

class ChatViewModel(private val databaseRepository: DatabaseRepository): ViewModel() {

    val channelsListExample = arrayListOf(
        Channel(2, "https://randompicturegenerator.com/img/people-generator/g2dea49ea67b65abe151529e5a8d259864982e8e1513fd005659c38c641bb7a7b690da9617f7bdd37dcd6087299779b72_640.jpg","Для приватных", "чатов", ".", "10:23"),
        Channel(3, "https://randompicturegenerator.com/img/people-generator/g69b41a50682984f0e04b9720b4dc30d30e91cabaa59cdc4edf0dd41918f85cd798335f5b3d69811ad9b191d51839a79c_640.jpg","Для публичных", "чатов", "....", "10:26"),
        Channel(4,"https://images.unsplash.com/photo-1682530016961-19763e9599b9?crop=entropy&cs=tinysrgb&fit=max&fm=jpg&ixid=M3wxODY2Nzh8MHwxfHJhbmRvbXx8fHx8fHx8fDE2ODQ2NzI1MjF8&ixlib=rb-4.0.3&q=80&w=1080", "Алексей", "Алексеев", "йцукенг", "13:34"),
        Channel(5,"https://plus.unsplash.com/premium_photo-1683135218463-12fd419b0b85?crop=entropy&cs=tinysrgb&fit=max&fm=jpg&ixid=M3wxODY2Nzh8MHwxfHJhbmRvbXx8fHx8fHx8fDE2ODQ2NzI1NDF8&ixlib=rb-4.0.3&q=80&w=1080", "Вова", "Ершов", "чсмрпм итоь ", "6:23"),
        Channel(6,"https://plus.unsplash.com/premium_photo-1675754403388-7548ba37809e?crop=entropy&cs=tinysrgb&fit=max&fm=jpg&ixid=M3wxODY2Nzh8MHwxfHJhbmRvbXx8fHx8fHx8fDE2ODQ2NzI1NTd8&ixlib=rb-4.0.3&q=80&w=1080", "Никита", "Нейман", ".", "11:53"),
        Channel(7,"https://randompicturegenerator.com/img/people-generator/gfc47389984697c096f32cff6ef7f6a08ca7c646ac3d1766aeaa987c4404267bb8fb6877a32229a86121ff842e0fd7553_640.jpg", "Паша", "Зыков", ".", "18:33"),
        Channel(8, "https://plus.unsplash.com/premium_photo-1681584472258-6ef06bfa771c?crop=entropy&cs=tinysrgb&fit=max&fm=jpg&ixid=M3wxODY2Nzh8MHwxfHJhbmRvbXx8fHx8fHx8fDE2ODQ2NzI1OTN8&ixlib=rb-4.0.3&q=80&w=1080","Имя1", "Сафиуллин", ".", "19:15"),
        Channel(9, "https://plus.unsplash.com/premium_photo-1666264200782-8cc1096bb417?crop=entropy&cs=tinysrgb&fit=max&fm=jpg&ixid=M3wxODY2Nzh8MHwxfHJhbmRvbXx8fHx8fHx8fDE2ODQ2NzI2MDh8&ixlib=rb-4.0.3&q=80&w=1080","Имя2", "Архипов", ".", "22:21"),
        Channel(10, "https://images.unsplash.com/photo-1682530016961-19763e9599b9?crop=entropy&cs=tinysrgb&fit=max&fm=jpg&ixid=M3wxODY2Nzh8MHwxfHJhbmRvbXx8fHx8fHx8fDE2ODQ2NzI1MjF8&ixlib=rb-4.0.3&q=80&w=1080", "NAMe3", "Famofij", "ddddddddddd", "56:34"),Channel(2, "https://randompicturegenerator.com/img/people-generator/g2dea49ea67b65abe151529e5a8d259864982e8e1513fd005659c38c641bb7a7b690da9617f7bdd37dcd6087299779b72_640.jpg","Для приватных", "чатов", ".", "10:23"),
        Channel(3, "https://randompicturegenerator.com/img/people-generator/g69b41a50682984f0e04b9720b4dc30d30e91cabaa59cdc4edf0dd41918f85cd798335f5b3d69811ad9b191d51839a79c_640.jpg","Для публичных", "чатов", "....", "10:26"),
        Channel(4,"https://images.unsplash.com/photo-1682530016961-19763e9599b9?crop=entropy&cs=tinysrgb&fit=max&fm=jpg&ixid=M3wxODY2Nzh8MHwxfHJhbmRvbXx8fHx8fHx8fDE2ODQ2NzI1MjF8&ixlib=rb-4.0.3&q=80&w=1080", "Алексей", "Алексеев", "йцукенг", "13:34"),
        Channel(5,"https://plus.unsplash.com/premium_photo-1683135218463-12fd419b0b85?crop=entropy&cs=tinysrgb&fit=max&fm=jpg&ixid=M3wxODY2Nzh8MHwxfHJhbmRvbXx8fHx8fHx8fDE2ODQ2NzI1NDF8&ixlib=rb-4.0.3&q=80&w=1080", "Вова", "Ершов", "чсмрпм итоь ", "6:23"),
        Channel(6,"https://plus.unsplash.com/premium_photo-1675754403388-7548ba37809e?crop=entropy&cs=tinysrgb&fit=max&fm=jpg&ixid=M3wxODY2Nzh8MHwxfHJhbmRvbXx8fHx8fHx8fDE2ODQ2NzI1NTd8&ixlib=rb-4.0.3&q=80&w=1080", "Никита", "Нейман", ".", "11:53"),
        Channel(7,"https://randompicturegenerator.com/img/people-generator/gfc47389984697c096f32cff6ef7f6a08ca7c646ac3d1766aeaa987c4404267bb8fb6877a32229a86121ff842e0fd7553_640.jpg", "Паша", "Зыков", ".", "18:33"),
        Channel(8, "https://plus.unsplash.com/premium_photo-1681584472258-6ef06bfa771c?crop=entropy&cs=tinysrgb&fit=max&fm=jpg&ixid=M3wxODY2Nzh8MHwxfHJhbmRvbXx8fHx8fHx8fDE2ODQ2NzI1OTN8&ixlib=rb-4.0.3&q=80&w=1080","Имя1", "Сафиуллин", ".", "19:15"),
        Channel(9, "https://plus.unsplash.com/premium_photo-1666264200782-8cc1096bb417?crop=entropy&cs=tinysrgb&fit=max&fm=jpg&ixid=M3wxODY2Nzh8MHwxfHJhbmRvbXx8fHx8fHx8fDE2ODQ2NzI2MDh8&ixlib=rb-4.0.3&q=80&w=1080","Имя2", "Архипов", ".", "22:21"),
        Channel(10, "https://images.unsplash.com/photo-1682530016961-19763e9599b9?crop=entropy&cs=tinysrgb&fit=max&fm=jpg&ixid=M3wxODY2Nzh8MHwxfHJhbmRvbXx8fHx8fHx8fDE2ODQ2NzI1MjF8&ixlib=rb-4.0.3&q=80&w=1080", "NAMe3", "Famofij", "ddddddddddd", "56:34")
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