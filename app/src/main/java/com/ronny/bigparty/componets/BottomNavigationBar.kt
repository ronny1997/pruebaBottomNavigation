package com.ronny.bigparty.componets

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.ronny.bigparty.R


@ExperimentalAnimationApi
@ExperimentalComposeUiApi
@Composable
fun BottomNavigationBar(
    navController: NavHostController,
    currentRoute: String?
) {
    val items = listOf(
        NavigationItem.Home,
        NavigationItem.Music,
        NavigationItem.Movies,
        NavigationItem.Setings
    )
    var animationPlayed by remember { mutableStateOf(false) }
    ConstraintLayout() {
        val (bottomNavigation, bottonLike, bottonApoyo, bottonCustom) = createRefs()
        BottomNavigation(
            modifier = Modifier.constrainAs(bottomNavigation) {
                top.linkTo(parent.top)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
                bottom.linkTo(bottonLike.top)
            },
            backgroundColor = colorResource(id = R.color.design_default_color_primary),
            contentColor = Color.White
        ) {
            val backstackEntry by navController.currentBackStackEntryAsState()
            val currentDestination = backstackEntry?.destination
            items.forEach { item ->

               CustomNavigationItem(
                    icon = {
                        Icon(
                            painterResource(id = item.icon),
                            contentDescription = item.title
                        )
                    },
                    label = { Text(text = item.title, modifier = Modifier.padding(end = 5.dp)) },
                    selectedContentColor = Color.White,
                    unselectedContentColor = Color.White.copy(0.4f),
                    alwaysShowLabel = true,
                    selected = currentDestination?.hierarchy?.any { item.route == currentRoute } == true,
                    onClick = {
                        when (item.route == "Setings") {
                            false -> {
                                if (currentRoute != item.route && currentRoute != null) {
                                    navController.navigate(item.route)
                                }
                            }
                            true -> {
                                animationPlayed = !animationPlayed
                            }

                        }

                    }
                )

            }
        }
        val showCircle = animateDpAsState(
            targetValue = if (animationPlayed) 40.dp else 0.dp,
            animationSpec = tween(
                durationMillis = 1000
            )
        )
        Button(
            onClick = { },
            modifier = Modifier
                .height(showCircle.value)
                .constrainAs(bottonLike) {
                    top.linkTo(bottomNavigation.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(bottonApoyo.start)
                    bottom.linkTo(parent.bottom)
                }
        ) {
            Text("LIKE")
        }
        Button(
            onClick = { },
            modifier = Modifier
                .height(showCircle.value)
                .constrainAs(bottonApoyo) {
                    top.linkTo(bottomNavigation.bottom)
                    start.linkTo(bottonLike.end)
                    end.linkTo(bottonCustom.start)
                    bottom.linkTo(parent.bottom)
                }
        ) {
            Text("APOYO")
        }
        Button(
            onClick = { },
            modifier = Modifier
                .height(showCircle.value)
                .constrainAs(bottonCustom) {
                    top.linkTo(bottomNavigation.bottom)
                    start.linkTo(bottonApoyo.end)
                    end.linkTo(parent.end)
                    bottom.linkTo(parent.bottom)
                }
        ) {
            Text("CUSTOM")
        }


    }

}

@ExperimentalAnimationApi
@ExperimentalComposeUiApi
@Preview
@Composable
fun BottomNavigationBarPreview() {
    BottomNavigationBar(rememberNavController(), "")
}