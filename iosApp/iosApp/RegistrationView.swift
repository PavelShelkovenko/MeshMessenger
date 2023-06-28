//
//  RegistrationScreen.swift
//  iosApp
//
//  Created by Pavel Shelkovenko on 17.06.2023.
//  Copyright © 2023 orgName. All rights reserved.
//

import SwiftUI
import shared
import Foundation

struct RegistrationView: View {
    @State private var email = ""
    @State private var password = ""
    @State private var confirmPassword = ""

    var body: some View {
        VStack {
        
            Text(Strings().get(id: SharedRes.strings().welcome, args: []))
                .font(.largeTitle)
                .padding()
                .multilineTextAlignment(.center)
            
            Spacer().frame(height: 20)

            HStack {
                Image(systemName: "person.crop.circle.fill")
                    .foregroundColor(Color.gray)
                    
                TextField("Email", text: $email)
                    .keyboardType(.emailAddress)
                    .autocapitalization(.none)
                    .disableAutocorrection(true)
            }
            .padding()
            .background(RoundedRectangle(cornerRadius: 10).fill(Color.white))
            .shadow(radius: 5)
            
            Spacer().frame(height: 20)

            HStack {
                Image(systemName: "lock.fill")
                    .foregroundColor(Color.gray)
                    

                SecureField("Password", text: $password)
                    .autocapitalization(.none)
                    .disableAutocorrection(true)
            }
            .padding()
            .background(RoundedRectangle(cornerRadius: 10).fill(Color.white))
            .shadow(radius: 5)

            Spacer().frame(height: 20)
            
            Button(action: registerUser) {
                Text(Strings().get(id: SharedRes.strings().sign_up, args: []))
                    .padding()
                    .frame(minWidth: 150)
                    .background(Color.blue)
                    .foregroundColor(.white)
                    .cornerRadius(10)
                    .multilineTextAlignment(.center)
            }
        }
        .padding(.horizontal, 16)
    }

    func registerUser() {
        // perform user registration logic here
    }
}

struct RegistrationView_Previews: PreviewProvider {
    static var previews: some View {
        RegistrationView()
    }
}
