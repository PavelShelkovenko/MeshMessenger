//
//  ChatNavBar.swift
//  iosApp
//
//  Created by macuser on 10.07.2023.
//  Copyright © 2023 orgName. All rights reserved.
//

import Foundation
import SwiftUI
import shared
import SDWebImage
import SDWebImageSwiftUI

struct ChatNavBar: View {
    var body: some View {
        HStack {
            WebImage(url: URL(string: "https://randompicturegenerator.com/img/people-generator/g167cc57243fe6f6c729e12865ad5dfa85070cba34876f0383cf9ed9dc743ebe29bb4b8f8cfdb8fd31780cf03757b94c0_640.jpg"))
                .resizable()
                .scaledToFill()
                .frame(width: 40, height: 40)
                .clipped()
                .cornerRadius(40)
            
            Text("Имя Фамилия")
                .padding(.horizontal, 25)
                .padding(.vertical, 1)
                .font(Font(OnestMedium))
        }
    }
}
