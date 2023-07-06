import SwiftUI
import shared



struct ContentView: View {
	let greet = Greeting().greet()
    
    @State private var pin = ""

	var body: some View {
        VStack {
            Spacer()

            Text("Создайте  PIN")
                .font(Font(OnestLarge))
                .padding()
                .multilineTextAlignment(.center)
                .foregroundColor(Color(PrimaryColor)
                )
            
            HStack {
                ForEach(0..<4) { index in
                    if(index < pin.count){
                        Circle()
                             .foregroundColor(Color(PrimaryColor))
                             .frame(width: 20, height: 20)
                             .padding(10)

                    } else {
                        Circle()
                       .fill(Color(SharedRes.colors().BackgroundColor.getUIColor()))
                       .frame(width: 20, height:  20)
                       .overlay(Circle().stroke(Color(PrimaryColor), style: StrokeStyle(lineWidth: 2)))
                       .padding(10)
                    }

                }
            }
            Spacer()
            
            HStack {
                SingleKeyboardButton(text : "1", pin: $pin)

                SingleKeyboardButton(text : "2", pin: $pin)
                
                SingleKeyboardButton(text : "3", pin: $pin)

            }
            
            HStack {
                SingleKeyboardButton(text : "4", pin: $pin)

                SingleKeyboardButton(text : "5", pin: $pin)
                
                SingleKeyboardButton(text : "6", pin: $pin)

            }
            
            HStack {
                SingleKeyboardButton(text : "7", pin: $pin)

                SingleKeyboardButton(text : "8", pin: $pin)
                
                SingleKeyboardButton(text : "9", pin: $pin)

            }
            
            HStack {
                
                Button {
                    exit(0)
                } label: {
                    Text("Выйти")
                        .font(Font(OnestMedium))
                        .frame(width: 85, height: 85)
                        .multilineTextAlignment(.center)
                        .foregroundColor(Color(PrimaryColor)
                    )
                }

                SingleKeyboardButton(text : "0", pin: $pin)
                
                Button {
                    if(pin.count > 0){
                        pin.removeLast()
                    }
                } label: {
                    Image(systemName: "arrow.left")
                        .frame(width: 85, height: 85)
                        .foregroundColor(Color(PrimaryColor))
                }
            }
        }
        .padding()
        .frame(maxWidth: .infinity, maxHeight: .infinity)
        .background(Color(SharedRes.colors().BackgroundColor.getUIColor()))
        .edgesIgnoringSafeArea(.all)

	}
}

func SingleKeyboardButton(text: String,  pin: Binding<String>) -> some View {
 
        HStack {
            Button(action: {
                if(pin.wrappedValue.count < 4){
                    pin.wrappedValue.append(text)
                }
            
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
