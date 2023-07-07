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

struct ChatScreen: View {
    

    @State var textOfMessage = ""
    
    var body: some View {
        
        VStack() {
            HStack() {
                Image( systemName: "person.circle.fill" )
                    .font(.system(size: 24))
                Text("Имя Фамилия")
                    .padding(.horizontal, 25)
                    .padding(.vertical, 1)
                    .font(Font(OnestMedium))
            
            }
        }
        
        VStack {
            ScrollView {

                ForEach(messageList){ message in
                    HStack {
                        Spacer()
                        HStack {
                            Text(message.text)
                                .foregroundColor(Color.black)
                                .font(Font(OnestMedium))
                        }.padding()
                            .background(Color.white)
                            .cornerRadius(8)
                    }.padding(.horizontal)
                }
                
                HStack { Spacer() }
            }
            .frame(maxWidth: .infinity, maxHeight: .infinity)
            .background(Color(BackgroundColor))
            .edgesIgnoringSafeArea(.all)
            
            HStack(spacing: 16 ) {
                
                Image(systemName: "face.smiling")
                    .font(.system(size: 24))
                    .foregroundColor(Color(.lightGray))

                
                TextField("Description", text: $textOfMessage)
                
                Spacer()
                Image(systemName: "paperclip")
                    .font(.system(size: 24))
                    .foregroundColor(Color(.lightGray))
                
                Image(systemName: "paperplane.fill")
                    .font(.system(size: 24))
                    .foregroundColor(Color(.lightGray))

                
            } .padding(.horizontal)
               .padding(.all, 2)
        }
        

        
    }
    
    struct navBar: View {
        var body : some View {
            
            VStack() {
                HStack() {
                    Image( systemName: "line.3.horizontal" )
                        .font(.system(size: 32))
                    Text("Mesh мессенджер")
                        .padding(.horizontal, 25)
                        .padding(.vertical, 5)
                        .font(Font(OnestLarge))
                    
                }
            }
            
        }
    }
    
    
    let messageList = [
        Message(idd: 2, text: "Это очень очень очень очень очень очень очень очень очень очень очень очень очень очень очень большое сообщение",time:  "11:37", authorName:  "Артур", authorSurname:  "Рахимзянов"),
        Message(idd: 1, text: "Привет", time: "11:38", authorName:  "Артур", authorSurname: "Рахимзянов"),

        Message(idd: 1, text: "Чем занимаешься ? Я пользуюсь приложением MeshApp !",time:  "11:34", authorName:  "Артур", authorSurname: "Рахимзянов"),
        Message(idd: 2, text: "Привет ", time: "11:34",  authorName: "Дмитрий", authorSurname:"Смирнов"),
        Message(idd: 2,text: "Чем занимаешься ? Я пользуюсь приложением MeshApp !", time : "11:34", authorName:  "Дмитрий",authorSurname:  "Рахимзянов"),

        Message(idd: 1, text: "Привет вышел на улицу.", time: "11:39",  authorName: "Артур", authorSurname: "Рахимзянов"),
        Message(idd: 1,text: ".", time: "11:36", authorName:  "Артур", authorSurname: "Рахимзянов"),

        Message(idd: 2, text: "Это очень очень очень очень очень очень очень очень очень очень очень очень очень очень очень большое сообщение",time:  "11:37", authorName:  "Артур", authorSurname:  "Рахимзянов"),
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

}
  
struct chatListScreen: PreviewProvider {
    static var previews: some View {
        ChatScreen()
    }
}


