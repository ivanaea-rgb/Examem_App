package com.example.examem_app.ui.theme

import android.app.Activity
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

// Paleta de colores para Modo Claro (usando Material 3)
private val LightColorScheme = lightColorScheme(
    primary = AppBlue,
    secondary = Purple700,
    tertiary = Teal200,
    background = Color.White,
    surface = Color.White,
    onPrimary = Color.White,
    onSecondary = Color.Black,
    onBackground = Color.Black,
    onSurface = Color.Black,
    error = RedError // Color de error de la rúbrica
)

// Paleta de colores para Modo Oscuro
private val DarkColorScheme = darkColorScheme(
    primary = AppBlue,
    secondary = Purple700,
    tertiary = Teal200,
    error = RedError // Color de error de la rúbrica
)

@Composable
fun ExamemAppTheme( // Este es el nombre de tu tema
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colorScheme = if (darkTheme) {
        DarkColorScheme
    } else {
        LightColorScheme
    }

    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            window.statusBarColor = colorScheme.primary.toArgb()
            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = !darkTheme
        }
    }

    MaterialTheme(
        colorScheme = colorScheme, // "colorScheme" es para M3
        typography = Typography,   // Usa el Typography de Type.kt
        shapes = Shapes,           // Usa el Shapes de Shapes.kt
        content = content
    )
}