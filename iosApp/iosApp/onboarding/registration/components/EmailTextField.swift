//
//  RegistrationTextField.swift
//  iosApp
//
//  Created by Pavel Shelkovenko on 05.07.2023.
//  Copyright © 2023 orgName. All rights reserved.
//

import Foundation
import SwiftUI
import shared


struct EmailTextField: View {
    
    @Binding var email: String
    
    var body: some View {

        HStack {
            Image(resource: \.ic_person_24)
                .foregroundColor(Color(PrimaryColor))
                        
            TextField("Email", text: $email)
                    .keyboardType(.emailAddress)
                    .autocapitalization(.none)
                    .disableAutocorrection(true)
                    .font(Font(OnestMedium))
        }
        .padding()
        .background(RoundedRectangle(cornerRadius: 10).fill(Color.white))
        .shadow(radius: 5)
    }
}

struct RegistrationTextField_Previews: PreviewProvider {
    static var previews: some View {
        EmailTextField(
            email: Binding(
                get: { "test" },
                set: { value in }
            )
        )
    }
}
