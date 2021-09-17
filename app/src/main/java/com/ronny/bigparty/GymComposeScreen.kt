package com.ronny.bigparty

enum class BigPartyScreen {

    LoginScreen(),
    HomeScreen(),
    PartyScreen(),
    UsersScreen(),
    Setings();


    companion object {
        fun fromRoute(route: String?): BigPartyScreen =
            when (route?.substringBefore("/")) {
                LoginScreen.name -> LoginScreen
                HomeScreen.name -> HomeScreen
                PartyScreen.name -> PartyScreen
                UsersScreen.name -> UsersScreen
                Setings.name->Setings
                null -> LoginScreen
                else -> throw IllegalArgumentException("Route $route is not recognized.")
            }
    }

}