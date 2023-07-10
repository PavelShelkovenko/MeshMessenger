//
//  channelNavBar.swift
//  iosApp
//
//  Created by macuser on 10.07.2023.
//  Copyright © 2023 orgName. All rights reserved.
//

import Foundation
import SwiftUI
import shared


struct NavUp: View {
    var body: some View {
        
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
