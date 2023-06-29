import SwiftUI
import shared

struct ContentView: View {
	let greet = Greeting().greet()

	var body: some View {
        VStack {
            Text("Создайте  PIN")
                .font(Font(OnestLarge))
                .padding()
                .multilineTextAlignment(.center)
                .foregroundColor(Color(PrimaryColor)
                )
            
            Spacer().frame(height: 200)
            
            HStack {
                SingleKeyboardButton(text : "1")

                SingleKeyboardButton(text : "2")
                
                SingleKeyboardButton(text : "3")

            }
            
            HStack {
                SingleKeyboardButton(text : "4")

                SingleKeyboardButton(text : "5")
                
                SingleKeyboardButton(text : "6")

            }
            
            HStack {
                SingleKeyboardButton(text : "7")

                SingleKeyboardButton(text : "8")
                
                SingleKeyboardButton(text : "9")

            }
            
            HStack {
                Text("Выйти")
                    .font(Font(OnestMedium))
                    .frame(width: 85, height: 85)
                    .multilineTextAlignment(.center)
                    .foregroundColor(Color(PrimaryColor)
                )

                SingleKeyboardButton(text : "0")
                
                Image(systemName: "arrow.left")
                    .frame(width: 85, height: 85)
                    .foregroundColor(Color(PrimaryColor))
            }

        }
        .frame(maxWidth: .infinity, maxHeight: .infinity)
        .background(Color(SharedRes.colors().BackgroundColor.getUIColor()))
        .edgesIgnoringSafeArea(.all)

	}
}

func SingleKeyboardButton(text: String) -> some View {
        HStack {
            Button(action: {
                print("Hello button tapped!")
            }) {
                Text(text)
                    .fontWeight(.bold)
                    .font(.title)
                    .foregroundColor(Color(PrimaryColor))
                    .frame(width: 85, height: 85)
                    .overlay(Circle().stroke(Color(PrimaryColor), style: StrokeStyle(lineWidth: 2)))
            }
            
        }
    }

struct ContentView_Previews: PreviewProvider {
	static var previews: some View {
		ContentView()
	}
}
