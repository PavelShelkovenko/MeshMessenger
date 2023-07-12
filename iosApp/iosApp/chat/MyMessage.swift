//
//  MyMessage.swift
//  iosApp
//
//  Created by macuser on 12.07.2023.
//  Copyright © 2023 orgName. All rights reserved.
//

import Foundation
import SwiftUI
import shared

struct MyMessage : View {
    
    var message: Message

    var body: some View {
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
    }
}
