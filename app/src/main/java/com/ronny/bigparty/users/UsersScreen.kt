package com.ronny.bigparty.users

import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun UsersScreen() {
    Scaffold{
        Column(
            verticalArrangement = Arrangement.Bottom
        ) {
            Button(
                onClick = {},
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth()
                    .fillMaxHeight()
            ) {
                Text("Users!!")
            }
        }
    }


}

@Preview
@Composable
fun PreviewUsersScreen() = UsersScreen()