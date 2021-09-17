package com.ronny.bigparty

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.ronny.bigparty.componets.BottomNavigationBar
import com.ronny.bigparty.home.HomeScreen
import com.ronny.bigparty.login.LoginScreen
import com.ronny.bigparty.login.LoginViewModel
import com.ronny.bigparty.party.PartyScreen
import com.ronny.bigparty.ui.theme.BigPartyTheme
import com.ronny.bigparty.users.UsersScreen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val loginViewModel by viewModels<LoginViewModel>()

    @ExperimentalAnimationApi
    @ExperimentalComposeUiApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BigPartyApp(loginViewModel)
        }
    }
}

@ExperimentalAnimationApi
@ExperimentalComposeUiApi
@Composable
fun BigPartyApp(loginViewModel: LoginViewModel) {
    BigPartyTheme {

        val navController = rememberNavController()
        val backstackEntry = navController.currentBackStackEntryAsState()

        val currentRoute = backstackEntry.value?.destination?.route

        Scaffold(
            bottomBar = {
                when (currentRoute) {
                    "LoginScreen" -> {
                    }
                    null -> {
                    }
                    else -> {
                        BottomNavigationBar(
                            navController = navController,
                            currentRoute = currentRoute
                        )
                    }
                }

            }
        ) { innerPAdding ->

            Box(modifier = Modifier.padding(innerPAdding)) {
                MainScreenNavigationConfigurations(navController, loginViewModel)
            }

        }

    }

}

@ExperimentalComposeUiApi
@Composable
private fun MainScreenNavigationConfigurations(
    navController: NavHostController,
    loginViewModel: LoginViewModel
) {
    NavHost(navController, startDestination = BigPartyScreen.LoginScreen.name) {
        composable(BigPartyScreen.LoginScreen.name) {
            LoginScreen(
                onLoginOk = { loginOk ->
                    if (loginOk) {
                        navController.navigate(BigPartyScreen.HomeScreen.name)
                    }
                },
                onLogin = loginViewModel::login
            )
        }
        composable(BigPartyScreen.HomeScreen.name) {
            HomeScreen()
        }
        composable(BigPartyScreen.PartyScreen.name) {
            PartyScreen()
        }
        composable(BigPartyScreen.UsersScreen.name) {
            UsersScreen()
        }
        composable(BigPartyScreen.Setings.name) {

        }
    }
}



