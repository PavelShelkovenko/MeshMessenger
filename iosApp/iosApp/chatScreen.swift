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
    

    @State var textOfMessage = ""
    
    var body: some View {
        
        VStack() {
            HStack() {
                WebImage(url: URL(string: "https://randompicturegenerator.com/img/people-generator/g167cc57243fe6f6c729e12865ad5dfa85070cba34876f0383cf9ed9dc743ebe29bb4b8f8cfdb8fd31780cf03757b94c0_640.jpg"))
                    .resizable()
                    .scaledToFill()
                    .frame(width: 24, height: 24)
                    .clipped()
                    .cornerRadius(24)
                
                Text("Имя Фамилия")
                    .padding(.horizontal, 25)
                    .padding(.vertical, 1)
                    .font(Font(OnestMedium))
            
            }
        }
        
        VStack {
            ScrollView {

                ForEach(messageList){ message in
                    if(message.idd == 1) {
                        HStack {

                            Spacer()
                            
                            VStack(alignment: .trailing) {
                                HStack( ) {
                                    Text(message.text)
                                        .foregroundColor(Color.black)
                                        .font(Font(OnestMedium))
                                    
                                
                                }.padding()
                                 .background(Color.white)
                                 .cornerRadius(8)
                                
                                Text(message.time)
                                    .foregroundColor(Color(.lightGray))
                                    .font(Font(OnestSmall))
                                
                            }
            

                            
                      
                        }.padding(.leading, 50)
                         .padding(.trailing)
                        
                    } else {
                        HStack(alignment: .bottom) {
                            
                            WebImage(url: URL(string: "https://randompicturegenerator.com/img/people-generator/g167cc57243fe6f6c729e12865ad5dfa85070cba34876f0383cf9ed9dc743ebe29bb4b8f8cfdb8fd31780cf03757b94c0_640.jpg"))
                                .resizable()
                                .scaledToFill()
                                .frame(width: 24, height: 24)
                                .clipped()
                                .cornerRadius(24)
                            
                            VStack(alignment: .leading) {
                                HStack() {
                                    VStack(alignment: .leading) {
                                        Text(message.authorName)
                                            .foregroundColor(Color(.lightGray))
                                            .font(Font(OnestSmall))
                                        
                                        Text(message.text)
                                            .foregroundColor(Color.black)
                                            .font(Font(OnestMedium))
                                    }
                                    
                                }.padding()
                                 .background(Color.white)
                                 .cornerRadius(8)
                                
                                Text(message.time)
                                    .foregroundColor(Color(.lightGray))
                                    .font(Font(OnestSmall))
                                
                            }
                            
                            Spacer()
                            
                        }.padding(.trailing, 50)
                         .padding(.leading)

                    }
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

}
  
struct chatListScreen: PreviewProvider {
    static var previews: some View {
        ChatScreen()
    }
}


