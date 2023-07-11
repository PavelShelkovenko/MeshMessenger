//
//  Router.swift
//  iosApp
//
//  Created by Pavel Shelkovenko on 10.07.2023.
//  Copyright © 2023 orgName. All rights reserved.
//

import Foundation

final class Router: ObservableObject {
    static let router = Router()

    @Published var path = [Route]()
    
    func showRegistration() {
        path.append(.registration)
    }

    func showPin() {
        path.append(.pin)
    }
    
    func showChannelList() {
        path.append(.channelList)
    }
    
    func showChat() {
        path.append(.chat)
    }
    
    func backToRoot() {
        path.removeAll()
    }
    
    func back() {
        path.removeLast()
    }
    
    func popLast() {
        path.popLast()
    }
}
