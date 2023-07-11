import SwiftUI
import shared

@main
struct iOSApp: App {
    @UIApplicationDelegateAdaptor(AppDelegate.self) var appDelegate

    @ObservedObject var router = Router.router
    
    var body: some Scene {
        WindowGroup {
            NavigationStack(path: $router.path) {
                MeshApp().build()
                    .navigationDestination(for: Route.self) { route in
                    switch route {
                        case .registration:
                        RegistrationScreen()
                        case .pin:
                            LoginScreen()
                        case .channelList:
                            ChannelListScreen()
                        case .chat:
                            ChatScreen()
                    }
                }
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
