package com.example.settingview.ui.theme

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.settingview.R

//color

data class AppColorScheme(
    val background: Color,
    val onbackground: Color,
    val primary: Color,
    val onPrimary: Color,
    val secondary: Color,
    val onSecondary: Color
)

//typography
data class  AppTypography(
    val titleLarge: TextStyle,
    val titleNormal: TextStyle,
    val body: TextStyle,
    val labelLarge: TextStyle,
    val labelNormal: TextStyle,
    val labelSmall: TextStyle
)
//shape
data class AppShape(
    val container: Shape, val button: Shape
)

//size
data class AppSize(
    val large : Dp,
    val medium : Dp,
    val normal : Dp,
    val small : Dp
)



val LocalAppColorScheme = staticCompositionLocalOf {
    AppColorScheme(
        background = Color.Unspecified,
        onPrimary = Color(R.color.white),
        primary = Color.Unspecified,
        secondary = Color.Cyan,
        onbackground = Color.Unspecified,
        onSecondary = Color.Unspecified,
    )
}

val LocalAppShape = staticCompositionLocalOf {
    AppShape(
        container = RoundedCornerShape(16.dp),
        button = RectangleShape
    )
}

val locaAppSize = staticCompositionLocalOf {
    AppSize(
        large = 50.dp,
        medium = 50.dp,
        small = 25.dp,
        normal = 10.dp
    )
}

