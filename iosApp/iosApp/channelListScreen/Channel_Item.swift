//
//  Channel_Item.swift
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

struct ChannelItem: View {
    var channel: Channel
    var body: some View {
        
        NavigationLink(destination:ChatScreen()) {
            
            HStack(spacing: 16)  {
                
                WebImage( url: URL(string: channel.imageURL))
                    .resizable()
                    .scaledToFill()
                    .frame(width: 48, height: 48)
                    .clipped()
                    .cornerRadius(48)
                
                VStack(alignment: .leading) {
                    Text(channel.name + " " + channel.surname)
                        .font(Font(OnestMedium))
                        .foregroundColor(Color.black)

                    
                    Text(channel.lastMessage)
                        .padding(.horizontal, 10)
                        .font(Font(OnestSmall))
                        .foregroundColor(Color(.lightGray))
                }
                
                Spacer()
                
                VStack {
                    Text(channel.time)
                        .font(Font(OnestSmall))
                        .foregroundColor(Color(.lightGray))
                
                }

            }.padding(.horizontal)
        }

        Divider()
    }
}




