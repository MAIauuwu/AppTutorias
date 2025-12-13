package com.example.apptutorias.ui.theme

import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val PastelColorScheme = lightColorScheme(
    primary = PrimaryPastel,
    secondary = SecondaryPastel,
    tertiary = TertiaryPastel,

    background = BackgroundPastel,
    surface = SurfacePastel,

    onPrimary = Color.White,
    onSecondary = Color.White,
    onBackground = TextDark,
    onSurface = TextDark
)

@Composable
fun AppTutoriasTheme(
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colorScheme = PastelColorScheme,
        typography = Typography,
        content = content
    )
}
