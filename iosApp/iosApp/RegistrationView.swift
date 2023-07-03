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
                .font(Font(OnestLarge))
                .padding()
                .multilineTextAlignment(.center)
                .foregroundColor(Color(PrimaryColor))
                
            Spacer().frame(height: 20)

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
            
            Spacer().frame(height: 20)

            HStack {
                Image(resource: \.ic_password_24)
                    .foregroundColor(Color(PrimaryColor))
                    

                SecureField("Password", text: $password)
                    .autocapitalization(.none)
                    .disableAutocorrection(true)
                    .font(Font(OnestMedium))
            }
            .padding()
            .background(RoundedRectangle(cornerRadius: 10).fill(Color.white))
            .shadow(radius: 5)

            Spacer().frame(height: 20)
            
            Button(action: registerUser) {
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
        .padding(.horizontal, 16)
        .frame(maxWidth: .infinity, maxHeight: .infinity)
        .background(Color(BackgroundColor))
        .edgesIgnoringSafeArea(.all)
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
