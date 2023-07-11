//
//  LoginView.swift
//  iosApp
//
//  Created by macuser on 05.07.2023.
//  Copyright © 2023 orgName. All rights reserved.
//

import SwiftUI
import shared

struct LoginScreen: View {
    
    @ObservedObject var viewModel: IOSLoginViewModel
    
    
    init() {
        self.viewModel = IOSLoginViewModel()
    }


    
    var body: some View {
        
        @State  var pin = viewModel.state.pinState
 
        VStack {
            Spacer()

            Text("Создайте  PIN")
                .font(Font(OnestLarge))
                .padding()
                .multilineTextAlignment(.center)
                .foregroundColor(Color(PrimaryColor)
                )
            
            Text(viewModel.getUserName())
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
                            
                            SingleKeyboardButton(
                                text : "1",
                                pinState: viewModel.state.pinState,
                                onClick: { text in
                                    viewModel.onEvent(event: LoginEvent.PinOneElementAdd(value: text ) )
                                })
                            
                            
                                            
                            SingleKeyboardButton(
                                text : "2",
                                pinState: viewModel.state.pinState,
                                onClick: { text in
                                    viewModel.onEvent(event: LoginEvent.PinOneElementAdd(value: text ) )
                                })
                            
                            SingleKeyboardButton(
                                text : "3",
                                pinState: viewModel.state.pinState,
                                onClick: { text in
                                    viewModel.onEvent(event: LoginEvent.PinOneElementAdd(value: text ) )
                                })
                        }
                        
                        HStack {
                            SingleKeyboardButton(
                                text : "4",
                                pinState: viewModel.state.pinState,
                                onClick: { text in
                                    viewModel.onEvent(event: LoginEvent.PinOneElementAdd(value: text ) )
                                })
                            SingleKeyboardButton(
                                text : "5",
                                pinState: viewModel.state.pinState,
                                onClick: { text in
                                    viewModel.onEvent(event: LoginEvent.PinOneElementAdd(value: text ) )
                                })
                            SingleKeyboardButton(
                                text : "6",
                                pinState: viewModel.state.pinState,
                                onClick: { text in
                                    viewModel.onEvent(event: LoginEvent.PinOneElementAdd(value: text ) )
                                })
                        }
                        
                        HStack {
                            SingleKeyboardButton(
                                text : "7",
                                pinState: viewModel.state.pinState,
                                onClick: { text in
                                    viewModel.onEvent(event: LoginEvent.PinOneElementAdd(value: text ) )
                                })
                            SingleKeyboardButton(
                                text : "8",
                                pinState: viewModel.state.pinState,
                                onClick: { text in
                                    viewModel.onEvent(event: LoginEvent.PinOneElementAdd(value: text ) )
                                })
                            SingleKeyboardButton(
                                text : "9",
                                pinState: viewModel.state.pinState,
                                onClick: { text in
                                    viewModel.onEvent(event: LoginEvent.PinOneElementAdd(value: text ) )
                                })
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
                            
                            SingleKeyboardButton(
                                text : "0",
                                pinState: viewModel.state.pinState,
                                onClick: { text in
                                    viewModel.onEvent(event: LoginEvent.PinOneElementAdd(value: text ) )
                                })
                            Button {
                                viewModel.onEvent(event: LoginEvent.PinDropLast() )
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
        .navigationBarBackButtonHidden(true)
        .onAppear {
            viewModel.startObserving()
        }.onDisappear {
            viewModel.dispose()
        }
    }
}

struct SingleKeyboardButton : View {
    
    var text: String
    @State var pinState : String
    var onClick : (String) -> Void
    
    var body: some View {
        HStack {
            Button(action: {
                onClick(text)
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
}

struct LoginScreen_Previews: PreviewProvider {
    static var previews: some View {
        LoginScreen()
    }
}
