//
//  UserInput.swift
//  iosApp
//
//  Created by macuser on 10.07.2023.
//  Copyright © 2023 orgName. All rights reserved.
//


import Foundation
import SwiftUI
import shared

struct UserInput: View {
    
    @Binding var textOfMessage: String
    
    var body: some View {
        
        HStack(spacing: 16 ) {
            
            Image(systemName: "face.smiling")
                .font(.system(size: 24))
                .foregroundColor(Color(.lightGray))


            TextField("Message", text: $textOfMessage)

            Spacer()
            Image(systemName: "paperclip")
                .font(.system(size: 24))
                .foregroundColor(Color(.lightGray))
                .onTapGesture {
                  
                }

            Image(systemName: "paperplane.fill")
                .font(.system(size: 24))
                .foregroundColor(Color(.lightGray))
            
        } .padding(.horizontal)
          .padding(.all, 3)
        
    }
}


