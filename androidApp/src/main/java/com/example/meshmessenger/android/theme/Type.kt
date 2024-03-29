package com.example.meshmessenger.android.theme

import androidx.compose.material.Typography
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.meshmessenger.SharedRes
import com.example.meshmessenger.android.R


// Set of Material typography styles to start with
val ReemKufi = FontFamily(Font(R.font.reemkufi))
val Poppins = FontFamily(Font(R.font.poppins))

// Shared Font
val Onest = FontFamily(Font((SharedRes.fonts.Onest).onest.fontResourceId))



val Typography = Typography(
    body1 = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp
    )
    /* Other default text styles to override
    button = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.W500,
        fontSize = 14.sp
    ),
    caption = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp
    )
    */
)