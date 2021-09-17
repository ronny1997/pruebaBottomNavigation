package com.ronny.bigparty.componets

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.ConstraintSet
import  com.ronny.bigparty.R

@ExperimentalComposeUiApi
@Composable
fun CustomTextFieldPassword(
    modifier: Modifier = Modifier,
    pass: String,
    setTexPass: (String) -> Unit = {}
) {
    var passwordVisibility by remember { mutableStateOf(false) }


    ConstraintLayout {
        val (canvas, textField) = createRefs()
        Canvas(
            modifier = modifier.size(40.dp).constrainAs(canvas) {
                top.linkTo(textField.top)
                bottom.linkTo(textField.bottom)
                start.linkTo(textField.start,margin = 26.dp)
                end.linkTo(textField.end,margin = 56.dp)
            }
        ) {
            val mWidth = size.width
            val mHeight = size.height

            val trianglePath = Path().apply {
                moveTo(0f, 0f)
                lineTo(mWidth*0.8f, 0f)
                lineTo(mWidth, mHeight/2)
                lineTo(mWidth*0.8f, mHeight)
                lineTo(0f, mHeight)
            }
            if(passwordVisibility){
            drawPath(
                brush = Brush.horizontalGradient(
                    colors = listOf(
                        Color.Black.copy(alpha = 0.0f),
                        Color.White.copy(alpha = 0.09f)
                    )),
                path = trianglePath
            )
            }




        }
        TextField(
            value = pass,
            onValueChange = setTexPass,
            maxLines = 1,
            label = { Text("Password") },
            placeholder = { Text("Password") },
            modifier = modifier.constrainAs(textField){
                top.linkTo(parent.top)
                bottom.linkTo(parent.bottom)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            },
            visualTransformation = if (passwordVisibility) VisualTransformation.None else PasswordVisualTransformation(),
            trailingIcon = {
                val iconPass = if (passwordVisibility)
                    Icon(painter = painterResource(id = R.drawable.ic_flashlight_off), "")
                else Icon(painter = painterResource(id = R.drawable.ic_flashlight_on), "")
                IconButton(onClick = {
                    passwordVisibility = !passwordVisibility
                }) {
                    iconPass
                }
            },
            keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Done)
        )

    }

}

private fun decoupledConstraints(): ConstraintSet {
    return ConstraintSet {
        val button = createRefFor("button")

        constrain(button) {
            top.linkTo(parent.top)
            bottom.linkTo(parent.bottom)
            start.linkTo(parent.start)
            end.linkTo(parent.end)
        }
    }
}

@ExperimentalComposeUiApi
@Preview
@Composable
fun PreviewLoginScreen() = CustomTextFieldPassword(Modifier.fillMaxWidth(), "")