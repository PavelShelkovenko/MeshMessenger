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
    
    let PrimaryColor: UIColor = SharedRes.colors().PrimaryColor.getUIColor()
    
    let OnestLarge: UIFont = SharedRes.fontsOnest().onest.uiFont(withSize: 28.0)
    
    let OnestMedium: UIFont = SharedRes.fontsOnest().onest.uiFont(withSize: 18.0)
    
    var body: some View {
        VStack {
        
            Text(Strings().get(id: SharedRes.strings().welcome, args: []))
                .font(Font(OnestLarge))
                .padding()
                .multilineTextAlignment(.center)
                .foregroundColor(Color(PrimaryColor))
                
            Spacer().frame(height: 20)

            HStack {
                Image(systemName: "person.crop.circle.fill")
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
                Image(systemName: "lock.fill")
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
                    .background(Color(SharedRes.colors().PrimaryColor.getUIColor()))
                    .foregroundColor(.white)
                    .cornerRadius(40)
                    .multilineTextAlignment(.center)
            }
        }
        .padding(.horizontal, 16)
        .frame(maxWidth: .infinity, maxHeight: .infinity)
        .background(Color(SharedRes.colors().BackgroundColor.getUIColor()))
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
