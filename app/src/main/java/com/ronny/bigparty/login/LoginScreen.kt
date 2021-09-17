package com.ronny.bigparty.login

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ronny.bigparty.componets.CustomTextFieldPassword
import com.ronny.bigparty.componets.ElipseAnimate
import com.ronny.bigparty.core.exception.Failure
import com.ronny.bigparty.core.functional.LoginCallBack


@ExperimentalComposeUiApi
@Composable
fun LoginScreen(
    onLoginOk: (Boolean) -> Unit = {},
    onLogin: (String, String, LoginCallBack) -> Unit = { _, _, _ -> }
) {
    Scaffold {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            val (email, setTextUser) = remember { mutableStateOf("") }
            val (pass, setTexPass) = remember { mutableStateOf("") }
            val keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next)
            val focusRequester = remember { FocusRequester() }

            val submit = {
                onLogin(email, pass, object : LoginCallBack {
                    override fun onResponse(response: Boolean) {
                        onLoginOk(true)
                    }

                    override fun onFailure(failure: Failure) {

                    }

                })

            }

            ElipseAnimate()

            TextField(
                value = email,
                onValueChange = setTextUser,
                maxLines = 1,
                label = { Text("Email") },
                placeholder = { Text("Email") },
                modifier = Modifier
                    .padding(horizontal = 26.dp)
                    .padding(bottom = 5.dp)
                    .fillMaxWidth(),
                keyboardOptions = keyboardOptions,
                keyboardActions = KeyboardActions(
                    onNext = { focusRequester.requestFocus() }
                )
            )

            CustomTextFieldPassword(
                modifier = Modifier
                    .focusRequester(focusRequester = focusRequester)
                    .padding(horizontal = 26.dp)
                    .fillMaxWidth(),
                pass = pass,
                setTexPass = setTexPass
            )


            Button(
                onClick = submit,
                modifier = Modifier
                    .padding(vertical = 15.dp)
                    .padding(horizontal = 26.dp)
                    .fillMaxWidth()
            ) {
                Text("LOGIN")
            }


        }
    }

}


@ExperimentalComposeUiApi
@Preview
@Composable
fun PreviewLoginScreen() = LoginScreen()