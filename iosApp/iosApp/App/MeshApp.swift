//
//  MeshApp.swift
//  iosApp
//
//  Created by Pavel Shelkovenko on 10.07.2023.
//  Copyright © 2023 orgName. All rights reserved.
//

import Foundation

final class MeshApp {
    func build() -> RootScreen {
        let router = Router.router
        return RootScreen(router: router)
    }
}
