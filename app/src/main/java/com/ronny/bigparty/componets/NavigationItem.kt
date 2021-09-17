package com.ronny.bigparty.componets

import com.ronny.bigparty.R

sealed class NavigationItem(var route: String, var icon: Int, var title: String){
    object Home : NavigationItem("HomeScreen", R.drawable.ic_flashlight_off, "Home")
    object Music : NavigationItem("PartyScreen", R.drawable.ic_flashlight_off, "Party")
    object Movies : NavigationItem("UsersScreen", R.drawable.ic_flashlight_off, "Users")
    object Setings : NavigationItem("Setings", R.drawable.ic_flashlight_off, "Setings")

}
