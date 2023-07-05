//
//  IOSRegistrationViewModel.swift
//  iosApp
//
//  Created by Pavel Shelkovenko on 04.07.2023.
//  Copyright © 2023 orgName. All rights reserved.
//

import Foundation
import shared

extension RegistrationScreen {
    @MainActor class IOSRegistrationViewModel: ObservableObject {
    
        private let viewModel: RegistrationViewModel

        @Published var state: RegistrationState = RegistrationState(
            email: "",
            emailError: "",
            password: "",
            passwordError: "",
            errorText: ""
        )
        
        private var handle: (any DisposableHandle_)?
        
        init() {
            self.viewModel = KotlinDependencies.shared.getRegistrationViewModel()
        }
        
        func onEvent(event: RegistrationEvent) {
            self.viewModel.onEvent(event: event)
        }
                
        func validatePassword() {
            self.viewModel.validatePassword()
        }
        
        func validateEmail() {
            self.viewModel.validateEmail()
        }
        
        func validateData() -> Bool {
            self.viewModel.validateData()
        }
        
        func startObserving() {
            handle = viewModel.state.subscribe(onCollect: { state in
                if let state = state {
                    self.state = state
                }
            })
        }
                	
        func dispose() {
            handle?.dispose()
        }
    }
}
