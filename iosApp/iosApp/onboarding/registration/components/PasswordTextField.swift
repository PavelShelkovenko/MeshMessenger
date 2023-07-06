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
    @State private var showPassword = false
    
    var body: some View {
        HStack {
            Image(resource: \.ic_password_24)
                .foregroundColor(Color(PrimaryColor))
            
            if showPassword {
                TextField(password_string, text: $password)
                    .autocapitalization(.none)
                    .disableAutocorrection(true)
                    .font(Font(OnestMedium))
                
            } else {
                SecureField(password_string, text: $password)
                    .autocapitalization(.none)
                    .disableAutocorrection(true)
                    .font(Font(OnestMedium))
                }
    
            Spacer()
            Button(action: { showPassword.toggle() } ) {
            Image(systemName: showPassword ? "eye.fill" : "eye.slash.fill")
                    .foregroundColor(Color(PrimaryColor))
                    .padding(.trailing, 12)
            }
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
