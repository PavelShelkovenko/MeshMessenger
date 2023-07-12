//
//  OtherMessage.swift
//  iosApp
//
//  Created by macuser on 12.07.2023.
//  Copyright © 2023 orgName. All rights reserved.
//

import Foundation
import SwiftUI
import shared
import SDWebImageSwiftUI

struct OtherMessage : View {
    
    var message: Message

    var body: some View {
        
        HStack(alignment: .bottom) {
            
            WebImage(url: URL(string: "https://randompicturegenerator.com/img/people-generator/g167cc57243fe6f6c729e12865ad5dfa85070cba34876f0383cf9ed9dc743ebe29bb4b8f8cfdb8fd31780cf03757b94c0_640.jpg"))
                .resizable()
                .scaledToFill()
                .frame(width: 32, height: 32)
                .clipped()
                .cornerRadius(32)
            
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
