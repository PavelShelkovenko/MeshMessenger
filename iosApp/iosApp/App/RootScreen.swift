//
//  RootScreen.swift
//  iosApp
//
//  Created by Pavel Shelkovenko on 10.07.2023.
//  Copyright © 2023 orgName. All rights reserved.
//

import SwiftUI

struct RootScreen: View {
    
    private let router: Router
    
    init(router: Router) {
        self.router = router
        router.showRegistration()
    }
    
    var body: some View {
        Text("hello")
    }
}

struct RootScreen_Previews: PreviewProvider {
    static var previews: some View {
        MeshApp().build()
    }
}
