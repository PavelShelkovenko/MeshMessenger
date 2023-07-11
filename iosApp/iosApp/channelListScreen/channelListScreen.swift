//
//  channelListScreen.swift
//  iosApp
//
//  Created by macuser on 07.07.2023.
//  Copyright © 2023 orgName. All rights reserved.
//

import SwiftUI
import shared
import SDWebImage
import SDWebImageSwiftUI

struct ChannelListScreen: View {

    var body: some View {
        NavigationView {
            ScrollView {
                
                ChannelNavBar()
                
                ForEach( channelList ) { channel in
                    
                    ChannelItem(channel: channel)
                    
                }
                
            }.navigationBarHidden(true)
            
        }
    }
}

struct channelListScreen: PreviewProvider {
    static var previews: some View {
        ChannelListScreen()
    }
}
    
    
let channelList = [
        Channel(idd: 21, imageURL: "https://randompicturegenerator.com/img/people-generator/g167cc57243fe6f6c729e12865ad5dfa85070cba34876f0383cf9ed9dc743ebe29bb4b8f8cfdb8fd31780cf03757b94c0_640.jpg",name: "Для приватных", surname: "чатов", lastMessage: "будем", time: "10:23"),
        Channel(idd: 3,imageURL:  "https://randompicturegenerator.com/img/people-generator/gc1d29ce8fc0d5331f847c263c6ce1b8239a30e684ad92740c4b6d3eb2794e50355dfcf7011495fd0c1ac16390a9a9ed6_640.jpg",name: "Для публичных", surname: "чатов", lastMessage: "скоро", time: "10:26"),
        Channel(idd: 4, imageURL: "https://images.unsplash.com/photo-1682530016961-19763e9599b9?crop=entropy&cs=tinysrgb&fit=max&fm=jpg&ixid=M3wxODY2Nzh8MHwxfHJhbmRvbXx8fHx8fHx8fDE2ODQ2NzI1MjF8&ixlib=rb-4.0.3&q=80&w=1080",name:  "Алексей", surname: "Алексеев", lastMessage: "привет",time:  "13:34"),
        Channel(idd: 544, imageURL: "https://plus.unsplash.com/premium_photo-1683135218463-12fd419b0b85?crop=entropy&cs=tinysrgb&fit=max&fm=jpg&ixid=M3wxODY2Nzh8MHwxfHJhbmRvbXx8fHx8fHx8fDE2ODQ2NzI1NDF8&ixlib=rb-4.0.3&q=80&w=1080", name: "Вова",surname:  "Ершов",lastMessage:  "в итоге ", time: "6:23"),
        Channel(idd: 0, imageURL: "https://plus.unsplash.com/premium_photo-1675754403388-7548ba37809e?crop=entropy&cs=tinysrgb&fit=max&fm=jpg&ixid=M3wxODY2Nzh8MHwxfHJhbmRvbXx8fHx8fHx8fDE2ODQ2NzI1NTd8&ixlib=rb-4.0.3&q=80&w=1080",name:  "Никита", surname: "Нейман", lastMessage: ".", time: "11:53"),
        Channel(idd: 7,imageURL: "https://images.unsplash.com/photo-1683220642973-a4d0ca134714?crop=entropy&cs=tinysrgb&fit=max&fm=jpg&ixid=M3wxODY2Nzh8MHwxfHJhbmRvbXx8fHx8fHx8fDE2ODQ2NzI1ODJ8&ixlib=rb-4.0.3&q=80&w=1080", name: "Паша",surname:  "Зыков",lastMessage:  "до встречи!", time: "18:33"),
        Channel(idd: 0, imageURL: "https://plus.unsplash.com/premium_photo-1681584472258-6ef06bfa771c?crop=entropy&cs=tinysrgb&fit=max&fm=jpg&ixid=M3wxODY2Nzh8MHwxfHJhbmRvbXx8fHx8fHx8fDE2ODQ2NzI1OTN8&ixlib=rb-4.0.3&q=80&w=1080",name: "Искандер", surname: "Сафиуллин", lastMessage: "хорошо", time: "19:15"),
        Channel(idd: 9, imageURL: "https://plus.unsplash.com/premium_photo-1666264200782-8cc1096bb417?crop=entropy&cs=tinysrgb&fit=max&fm=jpg&ixid=M3wxODY2Nzh8MHwxfHJhbmRvbXx8fHx8fHx8fDE2ODQ2NzI2MDh8&ixlib=rb-4.0.3&q=80&w=1080",name: "Александр", surname: "Архипов",lastMessage:  "ок", time: "22:21"),
        Channel(idd: 10, imageURL: "https://images.unsplash.com/photo-1682530016961-19763e9599b9?crop=entropy&cs=tinysrgb&fit=max&fm=jpg&ixid=M3wxODY2Nzh8MHwxfHJhbmRvbXx8fHx8fHx8fDE2ODQ2NzI1MjF8&ixlib=rb-4.0.3&q=80&w=1080", name: "Артур", surname: "Рахимзянов", lastMessage: "завтра в 12.30", time: "56:34"),
        Channel(idd: 8, imageURL: "https://plus.unsplash.com/premium_photo-1681584472258-6ef06bfa771c?crop=entropy&cs=tinysrgb&fit=max&fm=jpg&ixid=M3wxODY2Nzh8MHwxfHJhbmRvbXx8fHx8fHx8fDE2ODQ2NzI1OTN8&ixlib=rb-4.0.3&q=80&w=1080",name: "Ярослав", surname: "Николаев",lastMessage:  "ок", time: "19:15"),
        
        Channel(idd: 6, imageURL: "https://plus.unsplash.com/premium_photo-1675754403388-7548ba37809e?crop=entropy&cs=tinysrgb&fit=max&fm=jpg&ixid=M3wxODY2Nzh8MHwxfHJhbmRvbXx8fHx8fHx8fDE2ODQ2NzI1NTd8&ixlib=rb-4.0.3&q=80&w=1080",name:  "Никита", surname: "Нейман", lastMessage: ".", time: "11:53"),
        Channel(idd: 7,imageURL: "https://images.unsplash.com/photo-1683220642973-a4d0ca134714?crop=entropy&cs=tinysrgb&fit=max&fm=jpg&ixid=M3wxODY2Nzh8MHwxfHJhbmRvbXx8fHx8fHx8fDE2ODQ2NzI1ODJ8&ixlib=rb-4.0.3&q=80&w=1080", name: "Паша",surname:  "Зыков",lastMessage:  "до встречи!", time: "18:33"),
        Channel(idd: 8, imageURL: "https://plus.unsplash.com/premium_photo-1681584472258-6ef06bfa771c?crop=entropy&cs=tinysrgb&fit=max&fm=jpg&ixid=M3wxODY2Nzh8MHwxfHJhbmRvbXx8fHx8fHx8fDE2ODQ2NzI1OTN8&ixlib=rb-4.0.3&q=80&w=1080",name: "Искандер", surname: "Сафиуллин", lastMessage: "хорошо", time: "19:15"),
]
    
struct Channel: Identifiable {
    let id = UUID()
    let idd: Int
    let imageURL: String
    let name: String
    let surname: String
    let lastMessage: String
    let time: String
}

    
    

