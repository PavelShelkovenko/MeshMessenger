//
//  IOSLoginViewModel.swift
//  iosApp
//
//  Created by macuser on 05.07.2023.
//  Copyright © 2023 orgName. All rights reserved.
//

import Foundation
import shared

extension LoginScreen {
    @MainActor class IOSLoginViewModel: ObservableObject {
    
        private let viewModel: LoginViewModel

        @Published var state: LoginState = LoginState(
            nextScreenNavigation: false,
            informText: "",
            remainingAttempts: 5,
            pinState: "",
            timer: 30,
            keyboardEnabled: true
        )
        
    
        
        private var handle: (any DisposableHandle_)?
        
        init() {
            self.viewModel = KotlinDependencies.shared.getLoginViewModel()
        }
        
        func onEvent(event: LoginEvent) {
            self.viewModel.onEvent(event: event)
        }
                
        func startObserving() {
            handle = viewModel.state.subscribe(onCollect: { state in
                if let state = state {
                    self.state = state
                }
            })
        }
        
        func getUserName() -> String {
            self.viewModel.getUserName()
        }
                    
        func dispose() {
            handle?.dispose()
        }
    }
}
