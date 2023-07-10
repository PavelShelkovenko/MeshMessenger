//
//  IOSChatViewModel.swift
//  iosApp
//
//  Created by macuser on 10.07.2023.
//  Copyright © 2023 orgName. All rights reserved.
//

import Foundation
import shared

extension ChatScreen {
    @MainActor class IOSChatViewModel: ObservableObject {
    
        private let viewModel: ChatViewModel

        @Published var state: ChatState = ChatState(
            channelName: "",
            channelSurname: "",
            textOfMessage: "",
            messagesList: NSMutableArray()
        )
        
    
        
        private var handle: (any DisposableHandle_)?
        
        init() {
           self.viewModel = KotlinDependencies.shared.getChatViewModel()
        }
        
        func onEvent(event: ChatEvent) {
            self.viewModel.onEvent(event: event)
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

