import SwiftUI
import shared

@main
struct iOSApp: App {
    @UIApplicationDelegateAdaptor(AppDelegate.self) var appDelegate
    
    var body: some Scene {
        
        let securedStore = KotlinDependencies().getKVaultInstance()
        
        WindowGroup {
            if (securedStore.string(forKey: "login") == nil) {
                RegistrationScreen()
            } else {
                LoginScreen()
            }
        }
    }
}


class AppDelegate: UIResponder, UIApplicationDelegate {
    func application(_ application: UIApplication, didFinishLaunchingWithOptions
        launchOptions: [UIApplication.LaunchOptionsKey: Any]?) -> Bool {
        startKoin()
        return true
    }
}
