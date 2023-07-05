//
//  Koin.swift
//  iosApp
//
//  Created by Pavel Shelkovenko on 03.07.2023.
//  Copyright © 2023 orgName. All rights reserved.
//

import Foundation
import shared

func startKoin() {

    let iosAppInfo = IosAppInfo()
    let doOnStartup = { NSLog("Hello from iOS/Swift!") }

    let koinApplication = KoinIOSKt.doInitKoinIos(
        appInfo: iosAppInfo,
        doOnStartup: doOnStartup
    )
    _koin = koinApplication.koin
}

private var _koin: Koin_coreKoin?
var koin: Koin_coreKoin {
    return _koin!
}

class IosAppInfo: AppInfo {
    let appId: String = Bundle.main.bundleIdentifier!
}
