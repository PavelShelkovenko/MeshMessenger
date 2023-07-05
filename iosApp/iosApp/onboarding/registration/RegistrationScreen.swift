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

struct RegistrationScreen: View {
    
    @ObservedObject var viewModel: IOSRegistrationViewModel
    
    init() {
        self.viewModel = IOSRegistrationViewModel()
        viewModel.validateData()
    }
    
    var body: some View {
        VStack {
            
            Text(Strings().get(id: SharedRes.strings().welcome, args: []))
                .font(Font(OnestLarge))
                .padding()
                .multilineTextAlignment(.center)
                .foregroundColor(Color(PrimaryColor))
                
            Spacer().frame(height: 5)

            ErrorText(error: Binding(
                    get: { viewModel.state.errorText ?? "" },
                    set: { value in }
                )
            )
                        
            EmailTextField(
                email: Binding(
                    get: { viewModel.state.email },
                    set: { value in
                    viewModel.onEvent(
                        event: RegistrationEvent.EmailChanged(email: value)
                    )
                    viewModel.validateEmail()
                    viewModel.validateData()
                })
            )
            
            Spacer().frame(height: 20)
            
            PasswordTextField(
                password: Binding(
                    get: { viewModel.state.password },
                    set: { value in
                    viewModel.onEvent(
                        event: RegistrationEvent.PasswordChanged(password: value)
                    )
                    viewModel.validatePassword()
                    viewModel.validateData()
                })
            )

            Spacer().frame(height: 20)
            
            SignUpButton(onClick: {
                viewModel.onEvent(event: RegistrationEvent.SignUp())
            })
            .disabled(!viewModel.validateData())
        }
        .padding(.horizontal, 16)
        .frame(maxWidth: .infinity, maxHeight: .infinity)
        .background(Color(BackgroundColor))
        .edgesIgnoringSafeArea(.all)
        .onAppear {
            viewModel.startObserving()
        }.onDisappear {
            viewModel.dispose()
        }
    }
    
    func registerUser() {
        viewModel.onEvent(event: RegistrationEvent.SignUp())
    }
}

struct RegistrationScreen_Previews: PreviewProvider {
    static var previews: some View {
        RegistrationScreen()
    }
}
