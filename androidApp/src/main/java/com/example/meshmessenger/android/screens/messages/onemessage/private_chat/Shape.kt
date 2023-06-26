package com.example.meshmessenger.android.screens.messages.onemessage.private_chat

import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.LayoutDirection

class ShapesOfPrivateTextMessages(
    private val cornerRadius: Float,
    private val isLastMessage: Boolean,
    private val isMyMessage: Boolean
) : Shape {

    override fun createOutline(
        size: Size,
        layoutDirection: LayoutDirection,
        density: Density
    ) =

        if (isMyMessage && isLastMessage) {
            Outline.Generic(path = drawMyLastMessage(size = size, cornerRadius = cornerRadius))
        } else {
             Outline.Generic(path = drawMyNotLastMessage(size = size, cornerRadius = cornerRadius))
        }
    }


fun drawMyNotLastMessage(size: Size, cornerRadius: Float): Path {
    return Path().apply {
        reset()

        arcTo(
            rect = Rect(
                left = -cornerRadius,
                top =  -cornerRadius,
                right = cornerRadius, //основной вырез
                bottom = size.height
            ),
            startAngleDegrees = 90.0f,
            sweepAngleDegrees = -90.0f,
            forceMoveTo = false
        )

        lineTo(x = cornerRadius, y = 0f)

        arcTo(
            rect = Rect(
                left = cornerRadius,
                top =  0f,
                right = 2*cornerRadius, //левый верх
                bottom = cornerRadius
            ),
            startAngleDegrees = 180.0f,
            sweepAngleDegrees =  90.0f,
            forceMoveTo = false
        )

        lineTo(x = size.width, y = 0f)

        arcTo(
            rect = Rect(
                left = size.width - cornerRadius,
                top =  0f,
                right = size.width, //правый верх
                bottom = cornerRadius
            ),
            startAngleDegrees = 270.0f,
            sweepAngleDegrees =  90.0f,
            forceMoveTo = false
        )

        lineTo(x = size.width, y = size.height)

        arcTo(
            rect = Rect(
                left = size.width - cornerRadius,
                top =  size.height - cornerRadius,
                right = size.width,
                bottom = size.height  //правый низ
            ),
            startAngleDegrees = 0.0f,
            sweepAngleDegrees = 90.0f,
            forceMoveTo = false
        )

        lineTo(x = 0f, y = size.height)

        close()
    }
}

fun drawMyLastMessage(size: Size, cornerRadius: Float): Path {
    return Path().apply {
        reset()

        arcTo(
            rect = Rect(
                left = size.width - 2 * cornerRadius,
                top = 0f,
                right = size.width - cornerRadius,  //правый верх
                bottom = cornerRadius
            ),
            startAngleDegrees = 270f,
            sweepAngleDegrees = 90.0f,
            forceMoveTo = false
        )

        arcTo(
            rect = Rect(
                left = size.width - cornerRadius,
                top = -cornerRadius,
                right = size.width + cornerRadius, //правый низ
                bottom = size.height
            ),
            startAngleDegrees = 180.0f,
            sweepAngleDegrees = -90.0f,
            forceMoveTo = false
        )

        lineTo(x = size.width, y = size.height)
        arcTo(
            rect = Rect(
                left = 0f,
                top = size.height - cornerRadius,
                right = cornerRadius,
                bottom = size.height,   //левый низ
            ),
            startAngleDegrees = 90f,
            sweepAngleDegrees = 90f,
            forceMoveTo = false
        )

        lineTo(x = 0f, y = size.height)

        arcTo(
            rect = Rect(
                left = 0f,
                top = 0f,
                right = cornerRadius,   //левый верх
                bottom = cornerRadius,
            ),
            startAngleDegrees = 180f,
            sweepAngleDegrees = 90f,
            forceMoveTo = false
        )

        lineTo(x = 0f, y = 0f)
        close()
    }
}
