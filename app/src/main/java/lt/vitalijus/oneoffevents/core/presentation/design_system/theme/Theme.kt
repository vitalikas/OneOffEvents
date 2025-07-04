package lt.vitalijus.oneoffevents.core.presentation.design_system.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Shapes
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

val LightColorScheme = lightColorScheme(
    primary = Primary,
    surface = Surface,
    surfaceContainerLowest = SurfaceLowest,
    background = Background,
    onSurface = OnSurface,
    onSurfaceVariant = OnSurfaceVariant
)

val DarkColorScheme = darkColorScheme(
    primary = PrimaryDark,
    surface = SurfaceDark,
    surfaceContainerLowest = SurfaceLowestDark,
    background = BackgroundDark,
    onSurface = OnSurfaceDark,
    onSurfaceVariant = OnSurfaceVariantDark
)

val Shapes = Shapes(
    extraSmall = RoundedCornerShape(5.dp),
    medium = RoundedCornerShape(10.dp),
    large = RoundedCornerShape(15.dp)
)

@Composable
fun OneOffEventsTheme(
    content: @Composable () -> Unit
) {
    val colorScheme = if (isSystemInDarkTheme()) {
        DarkColorScheme
    } else {
        LightColorScheme
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}

@Composable
fun extendedColor(light: Color, dark: Color): Color {
    return if (isSystemInDarkTheme()) dark else light
}

val ColorScheme.extraColor: Color
    @Composable get() = extendedColor(
        light = Color(0xFF000000),
        dark = Color(0xFFFFFFFF)
    )
