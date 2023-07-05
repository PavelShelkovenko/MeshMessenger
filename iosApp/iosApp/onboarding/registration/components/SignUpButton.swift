//
//  SignUpButton.swift
//  iosApp
//
//  Created by Pavel Shelkovenko on 05.07.2023.
//  Copyright © 2023 orgName. All rights reserved.
//

import Foundation
import SwiftUI
import shared

struct SignUpButton: View {
    var onClick: () -> Void
    
    var body: some View {
        Button(action: onClick) {
            Text(Strings().get(id: SharedRes.strings().sign_up, args: []))
                .padding()
                .font(Font(OnestMedium))
                .frame(minWidth: 150)
                .background(Color(PrimaryColor))
                .foregroundColor(.white)
                .cornerRadius(40)
                .multilineTextAlignment(.center)
        }
    }
}

struct SignUpButton_Previews: PreviewProvider {
    static var previews: some View {
        SignUpButton(onClick: {})
    }
}
