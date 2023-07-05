//
//  PasswordTextField.swift
//  iosApp
//
//  Created by Pavel Shelkovenko on 05.07.2023.
//  Copyright © 2023 orgName. All rights reserved.
//

import Foundation
import SwiftUI
import shared

struct PasswordTextField: View {
    
    @Binding var password: String

    var body: some View {
        HStack {
            Image(resource: \.ic_password_24)
                .foregroundColor(Color(PrimaryColor))
            
            TextField("Password", text: $password)
                .autocapitalization(.none)
                .disableAutocorrection(true)
                .font(Font(OnestMedium))
        }
        .padding()
        .background(RoundedRectangle(cornerRadius: 10).fill(Color.white))
        .shadow(radius: 5)
    }
    
    struct PasswordTextField_Previews: PreviewProvider {
        static var previews: some View {
            PasswordTextField(
                password: Binding(
                    get: { "test" },
                    set: { value in }
                )
            )
        }
    }
}
