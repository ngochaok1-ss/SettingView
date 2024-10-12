package com.example.settingview.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.graphics.Color

private val darkColorScheme = AppColorScheme(
    background = Color(0xFF0A0A0A),
    onbackground = Color(0xFF98F3F3),
    primary = Color.Black,
    onPrimary = Color.White,
    secondary = Color.Red,
    onSecondary = Pink80
)

private val lightColorScheme = AppColorScheme(
    background = Color(0x0F0A0A0A),
    onbackground = Color.White,
    primary = Color.Black,
    onPrimary = Color.Black,
    secondary = Color.Red,
    onSecondary = Pink80
)

@Composable
fun AppTheme(
    isDarkTheme : Boolean = isSystemInDarkTheme(),
    content : @Composable () -> Unit
){
    val colorScheme = if (isDarkTheme) darkColorScheme else lightColorScheme

    CompositionLocalProvider(
        LocalAppColorScheme provides colorScheme,
        content = content
    )
}
object AppTheme {
    val colorScheme : AppColorScheme
        @Composable get() = LocalAppColorScheme.current
}