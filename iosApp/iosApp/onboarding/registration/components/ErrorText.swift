//
//  ErrorText.swift
//  iosApp
//
//  Created by Pavel Shelkovenko on 05.07.2023.
//  Copyright © 2023 orgName. All rights reserved.
//

import Foundation
import shared
import SwiftUI

struct ErrorText: View {
    
    @Binding var error: String
    
    var body: some View {
        Text(error)
            .font(Font(OnestMedium))
            .multilineTextAlignment(.center)
            .foregroundColor(Color(PrimaryColor))
            .frame(maxWidth: 300, maxHeight: 80)
            .lineLimit(nil)
            .background(Color(BackgroundColor))
    }
}

struct ErrorText_Previews: PreviewProvider {
    static var previews: some View {
        ErrorText(
            error: Binding(
            get: { "error" },
            set: { value in }
        ))
    }
}

