//
//  SingleKeyBoardButton.swift
//  iosApp
//
//  Created by macuser on 12.07.2023.
//  Copyright © 2023 orgName. All rights reserved.
//

import SwiftUI
import shared
import Foundation

struct SingleKeyboardButton : View {
    
    var text: String
    @State var pinState : String
    var onClick : (String) -> Void
    
    var body: some View {
        HStack {
            Button(action: {
                onClick(text)
            }) {
                Text(text)
                    .fontWeight(.bold)
                    .font(.title)
                    .foregroundColor(Color(PrimaryColor))
                    .frame(width: 85, height: 85)
                    .overlay(Circle().stroke(Color(PrimaryColor), style: StrokeStyle(lineWidth: 2)))
            }
        }
    }
}
