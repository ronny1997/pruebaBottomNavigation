package com.ronny.bigparty.componets

import androidx.compose.animation.*
import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.lerp
import androidx.compose.ui.layout.*
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Constraints
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.ronny.bigparty.R
import kotlin.math.max
import kotlin.math.roundToInt

@ExperimentalAnimationApi
@ExperimentalComposeUiApi
@Composable
fun RowScope.CustomNavigationItem(
    selected: Boolean,
    onClick: () -> Unit,
    icon: @Composable () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    label: @Composable (() -> Unit)? = null,
    alwaysShowLabel: Boolean = true,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    selectedContentColor: Color = LocalContentColor.current,
    unselectedContentColor: Color = selectedContentColor.copy(alpha = ContentAlpha.medium)
) {
    val styledLabel: @Composable (() -> Unit)? = label?.let {
        @Composable {
            val style = MaterialTheme.typography.caption.copy(textAlign = TextAlign.Center)
            ProvideTextStyle(style, content = label)
        }
    }

    val ripple = rememberRipple(bounded = false, color = selectedContentColor)

    Box(
        modifier
            .selectable(
                selected = selected,
                onClick = onClick,
                enabled = enabled,
                role = Role.Tab,
                interactionSource = interactionSource,
                indication = ripple
            )
            .weight(1f),
        contentAlignment = Alignment.Center
    ) {
        CustoomBottomNavigationTransition(
            selectedContentColor,
            unselectedContentColor,
            selected
        ) {

            CustomBottomNavigationItemBaselineLayout(icon, label!!, selected)

        }
    }
}


@Composable
private fun CustoomBottomNavigationTransition(
    activeColor: Color,
    inactiveColor: Color,
    selected: Boolean,
    content: @Composable (animationProgress: Float) -> Unit
) {
    val animationProgress by animateFloatAsState(
        targetValue = if (selected) 1f else 0f,
        animationSpec = tween()
    )

    val color = lerp(inactiveColor, activeColor, animationProgress)

    CompositionLocalProvider(
        LocalContentColor provides color.copy(alpha = 1f),
        LocalContentAlpha provides color.alpha,
    ) {
        content(animationProgress)
    }
}

@ExperimentalAnimationApi
@Composable
private fun CustomBottomNavigationItemBaselineLayout(
    icon: @Composable () -> Unit,
    label: @Composable (() -> Unit),
    selected: Boolean
) {

    Box(
        modifier = Modifier
            .clip(shape = RoundedCornerShape(10.dp))
            .padding(10.dp)
            .fillMaxWidth()

    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier
                .clip(shape = RoundedCornerShape(10.dp))
                .background(
                    if (selected) {
                        MaterialTheme.colors.primarySurface
                    } else {
                        Color.Transparent
                    }
                )
                .align(Alignment.Center),

            ) {
            icon()
            AnimatedVisibility(
                selected,
                enter = expandHorizontally(
                    expandFrom = Alignment.Start,
                    initialWidth = { 20 },
                    animationSpec = tween(durationMillis = 900)
                ),
                exit = shrinkHorizontally(
                    targetWidth = { fullWidth -> fullWidth / 2 },
                    animationSpec = tween(durationMillis = 300)
                )
            ) {
                label()
            }
        }


    }

}

@ExperimentalAnimationApi
@Preview
@Composable
fun PreviewCustomBottomNavigationItemBaselineLayout() = CustomBottomNavigationItemBaselineLayout({
    Icon(
        painterResource(id = R.drawable.ic_flashlight_off),
        contentDescription = "item.title"
    )
}, {
    Text(text = "PRUEBA")
}, false)