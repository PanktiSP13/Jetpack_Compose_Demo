package com.pankti.jetpackcomposedemo.democompose.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import com.pankti.democompose.ui.theme.Purple200
import com.pankti.democompose.ui.theme.Purple500
import com.pankti.democompose.ui.theme.Purple700
import com.pankti.democompose.ui.theme.Shapes
import com.pankti.democompose.ui.theme.Teal200
import com.pankti.democompose.ui.theme.Typography

private val DarkColorPalette = darkColors(primary = Purple200, primaryVariant = Purple700, secondary = Teal200)

private val LightColorPalette = lightColors(primary = Purple500, primaryVariant = Purple700, secondary = Teal200)

@Composable
fun DemoComposeTheme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable () -> Unit) {
    val colors = if (darkTheme) {
        DarkColorPalette
    } else {
        LightColorPalette
    }

    MaterialTheme(colors = colors, typography = Typography, shapes = Shapes, content = content)
}