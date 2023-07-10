package com.example.meshmessenger.android.screens.channels

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.meshmessenger.SharedRes
import com.example.meshmessenger.android.root.stringResource
import com.example.meshmessenger.android.theme.White

@Composable
fun BleUICard(navController: NavController) {
    Surface(elevation = 20.dp, shape = RoundedCornerShape(8.dp)) {
        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .padding(all = 20.dp)
                .fillMaxWidth(0.95F)
                .background(White)
                .clickable {
                    navController.navigate("ble") {
                        launchSingleTop = true
                    }
                }
        ) {
            Text(
                text = stringResource(id = SharedRes.strings.work_BLE)
            )
        }
    }
}