package com.ronny.bigparty.componets

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.rotate
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.graphics.toColorInt
import com.ronny.bigparty.ui.theme.Blue1000
import com.ronny.bigparty.ui.theme.Red1000

@Composable
fun ElipseAnimate() {
    var animationPlayed by remember { mutableStateOf(false) }
    val rotate = 180f
    val rotateAnimation = animateFloatAsState(
        targetValue = if (animationPlayed) 0f else rotate,
        animationSpec = tween(
            durationMillis = 10000,
            delayMillis = 1000
        )
    )

    val animColor by animateColorAsState(
        targetValue = if (animationPlayed) Red1000 else Blue1000,
        animationSpec = tween(
            durationMillis = 10000,
            delayMillis = 1000
        )
    )

    val animateColorMas = animateFloatAsState(
        targetValue = if (animationPlayed) 127f else 0f,
        animationSpec = tween(
            durationMillis = 10000,
            delayMillis = 1000
        )
    )

    val rgbCircle = animateFloatAsState(
        targetValue = if (animationPlayed) 12f else 0f,
        animationSpec = tween(
            durationMillis = 10000,
            delayMillis = 1000
        )
    )

    val elipse = animateFloatAsState(
        targetValue = if (animationPlayed) 12.5f else 0f,
        animationSpec = tween(
            durationMillis = 10000,
            delayMillis = 1000
        )
    )

    LaunchedEffect(key1 = true) {
        animationPlayed = true
    }

    Canvas(
        modifier = Modifier
            .padding(15.dp)
            .size(150.dp)
    ) {

        drawCircle(
            color = Color(colorPersonal(animateColorMas, rgbCircle.value.toInt())),
            radius = size.minDimension / 14
        )


        rotate(degrees = rotateAnimation.value) {
            val canvasWidth = size.width
            val canvasHeight = size.height

            //top
            drawCircle(
                color = animColor,
                center = Offset(x = canvasWidth / 2, y = (canvasHeight / 2) - elipse.value),
                radius = size.minDimension / 14
            )
            //right

            drawCircle(
                color = animColor,
                center = Offset(x = (canvasWidth / 2) + elipse.value * 5, y = canvasHeight / 2),
                radius = size.minDimension / 14
            )
            //botton

            drawCircle(
                color = animColor,
                center = Offset(x = canvasWidth / 2, y = (canvasHeight / 2) + elipse.value * 10),
                radius = size.minDimension / 14
            )
            //left


        }
        rotate(degrees = 22.5f + rotateAnimation.value) {
            val canvasWidth = size.width
            val canvasHeight = size.height

            //top
            drawCircle(
                color = animColor,
                center = Offset(x = canvasWidth / 2, y = (canvasHeight / 2) - (elipse.value) * 2),
                radius = size.minDimension / 14
            )
            //right

            drawCircle(
                color = animColor,
                center = Offset(x = (canvasWidth / 2) + elipse.value * 7, y = canvasHeight / 2),
                radius = size.minDimension / 14
            )
            //botton

            drawCircle(
                color = animColor,
                center = Offset(x = canvasWidth / 2, y = (canvasHeight / 2) + elipse.value * 11),
                radius = size.minDimension / 14
            )
            //left


        }

        rotate(degrees = (22.5f) * 2 + rotateAnimation.value) {
            val canvasWidth = size.width
            val canvasHeight = size.height

            //top
            drawCircle(
                color = animColor,
                center = Offset(x = canvasWidth / 2, y = (canvasHeight / 2) - (elipse.value) * 3),
                radius = size.minDimension / 14
            )
            //right

            drawCircle(
                color = animColor,
                center = Offset(x = (canvasWidth / 2) + elipse.value * 8, y = canvasHeight / 2),
                radius = size.minDimension / 14
            )
            //botton

            drawCircle(
                color = animColor,
                center = Offset(x = canvasWidth / 2, y = (canvasHeight / 2) + elipse.value * 12),
                radius = size.minDimension / 14
            )
            //left


        }
        rotate(degrees = (22.5f) * 3 + rotateAnimation.value) {
            val canvasWidth = size.width
            val canvasHeight = size.height

            //top
            drawCircle(
                color = animColor,
                center = Offset(x = canvasWidth / 2, y = (canvasHeight / 2) - (elipse.value) * 4),
                radius = size.minDimension / 14
            )
            //right

            drawCircle(
                color = animColor,
                center = Offset(x = (canvasWidth / 2) + elipse.value * 9, y = canvasHeight / 2),
                radius = size.minDimension / 14
            )
            //botton

            drawCircle(
                color = animColor,
                center = Offset(x = canvasWidth / 2, y = (canvasHeight / 2) + elipse.value * 13),
                radius = size.minDimension / 14
            )
            //left


        }
    }
}

fun colorPersonal(animatecolor: State<Float>, colorNumber: Int): Int {


    var r = 0f
    var g = 0f
    var b = 0f

    when (colorNumber) {
        1 -> {
            r = animatecolor.value * 2
            g = 0f
            b = 0f
        }
        //tono1
        2 -> {
            r = animatecolor.value * 2
            g = animatecolor.value
            b = 0f
        }
        3 -> {
            r = animatecolor.value * 2
            g = animatecolor.value * 2
            b = 0f
        }
        //tono2
        4 -> {
            r = animatecolor.value
            g = animatecolor.value * 2
            b = 0f
        }
        5 -> {
            r = 0f
            g = animatecolor.value * 2
            b = 0f
        }
        //tono3
        6 -> {
            r = 0f
            g = animatecolor.value * 2
            b = animatecolor.value
        }
        7 -> {
            r = 0f
            g = animatecolor.value * 2
            b = animatecolor.value * 2
        }
        //tono4
        8 -> {
            r = 0f
            g = animatecolor.value
            b = animatecolor.value * 2
        }
        9 -> {
            r = 0f
            g = 0f
            b = animatecolor.value * 2
        }
        //tono5
        10 -> {
            r = animatecolor.value
            g = 0f
            b = animatecolor.value * 2
        }

        11 -> {
            r = animatecolor.value * 2
            g = 0f
            b = animatecolor.value * 2
        }
        //tono6
        12 -> {
            r = animatecolor.value * 2
            g = 0f
            b = animatecolor.value
        }
        else -> {
            r = animatecolor.value * 2
            g = animatecolor.value * 2
            b = animatecolor.value * 2
        }

    }
    val colorHex = String.format(
        "#%02x%02x%02x",
        Integer.parseInt(r.toInt().toString()),
        Integer.parseInt(g.toInt().toString()),
        Integer.parseInt(b.toInt().toString())
    )

    return colorHex.toColorInt()
}


@Preview
@Composable
fun ElipsePreview() = ElipseAnimate()