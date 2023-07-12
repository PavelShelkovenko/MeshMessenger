//
//  chatScreen.swift
//  iosApp
//
//  Created by macuser on 07.07.2023.
//  Copyright © 2023 orgName. All rights reserved.
//

import SwiftUI
import shared
import Foundation
import SDWebImage
import SDWebImageSwiftUI

struct ChatScreen: View {
    
    @ObservedObject var viewModel: IOSChatViewModel
    
    init() {
        self.viewModel = IOSChatViewModel()
    }
    
    var body: some View {
        
        @State var textOfMessage = viewModel.state.textOfMessage
        
        VStack {
            
            ChatNavBar()
            
            ScrollView {
                
                ForEach(messageList){ message in
                    if(message.idd == 1) {
                        
                        MyMessage(message: message)
                        
                    } else {
                        
                        OtherMessage(message: message)
                        
                    }
                }
                
                HStack { Spacer() }
                
            }
            .frame(maxWidth: .infinity, maxHeight: .infinity)
            .background(Color(BackgroundColor))
            .edgesIgnoringSafeArea(.all)
            
            
            UserInput( textOfMessage: Binding(
                get: { viewModel.state.textOfMessage },
                set: { value in
                    viewModel.onEvent(
                        event: ChatEvent.TextChanged(textChanged: value)
                    )
                })
            )
            
        }.onAppear {
            viewModel.startObserving()
        }.onDisappear {
            viewModel.dispose()
        }
    }
}

let messageList = [
    Message(idd: 2, text: "Это очень очень очень очень очень очень очень очень очень очень очень очень очень очень очень большое сообщение",time:  "11:37", authorName:  "Артур", authorSurname:  "Рахимзянов"),
    Message(idd: 1, text: "Привет", time: "11:38", authorName:  "Артур", authorSurname: "Рахимзянов"),
    
    Message(idd: 1, text: "Чем занимаешься ? Я пользуюсь приложением MeshApp !",time:  "11:34", authorName:  "Артур", authorSurname: "Рахимзянов"),
    Message(idd: 2, text: "Привет ", time: "11:34",  authorName: "Дима", authorSurname:"Смирнов"),
    Message(idd: 2,text: "Чем занимаешься ? Я пользуюсь приложением MeshApp !", time : "11:34", authorName:  "Дмитрий",authorSurname:  "Рахимзянов"),
    
    Message(idd: 1, text: "Привет вышел на улицу.", time: "11:39",  authorName: "Артур", authorSurname: "Рахимзянов"),
    Message(idd: 1,text: ".", time: "11:36", authorName:  "Артур", authorSurname: "Рахимзянов"),
    
    Message(idd: 1, text: "Это очень очень очень очень очень очень очень очень очень очень очень очень очень очень очень большое сообщение",time:  "11:37", authorName:  "Артур", authorSurname:  "Рахимзянов"),
    Message(idd: 1, text: "Привет", time: "11:38", authorName:  "Артур", authorSurname: "Рахимзянов"),
    
    Message(idd: 1, text: "Чем занимаешься ? Я пользуюсь приложением MeshApp !",time:  "11:34", authorName:  "Артур", authorSurname: "Рахимзянов"),
    
    Message(idd: 2, text: " Expressionless Face", time: "11:34",  authorName: "Кто-то",authorSurname:  "Рахимзянов"),
    Message(idd: 2, text: "Привет", time: "11:35", authorName: "Кто-то", authorSurname: "Рахимзянов"),
    Message(idd: 2, text: ".",time:  "11:36",  authorName: "Вова", authorSurname: "Рахимзянов"),
    
]

struct Message: Identifiable {
    let id = UUID()
    let idd : Int
    let text: String
    let time: String
    let authorName: String
    let authorSurname: String
}



struct chatListScreen: PreviewProvider {
    static var previews: some View {
        ChatScreen()
    }
}


