package com.example.meshmessenger.android.uicompose

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.sp
import com.linecorp.abc.sharedstorage.SharedStorage

@Composable
fun HelloWorld() {

    Column( verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),) {
        Column {
            Text(
                text = "hello ${SharedStorage.secureLoad("login", "Unknown user")}",
                fontSize = 15.sp,
                fontFamily = FontFamily.Monospace
            )
        }
    }
}