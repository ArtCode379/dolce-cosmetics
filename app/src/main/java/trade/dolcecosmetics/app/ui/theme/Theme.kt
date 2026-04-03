package trade.dolcecosmetics.app.ui.theme

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Shapes
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp

private val DolceColorScheme = lightColorScheme(
    primary = DolcePrimary,
    onPrimary = DolceOnPrimary,
    primaryContainer = DolceDark,
    onPrimaryContainer = DolceOnPrimary,
    secondary = DolceAccent,
    onSecondary = DolceOnPrimary,
    secondaryContainer = DolceAccentLight,
    onSecondaryContainer = DolcePrimary,
    tertiary = DolceAccent,
    onTertiary = DolceOnPrimary,
    background = DolceBackground,
    onBackground = DolceTextPrimary,
    surface = DolceSurface,
    onSurface = DolceOnSurface,
    surfaceVariant = DolceBackground,
    onSurfaceVariant = DolceTextSecondary,
    outline = DolceBorder,
    outlineVariant = DolceBorder,
)

private val DolceShapes = Shapes(
    extraSmall = RoundedCornerShape(4.dp),
    small = RoundedCornerShape(4.dp),
    medium = RoundedCornerShape(4.dp),
    large = RoundedCornerShape(4.dp),
    extraLarge = RoundedCornerShape(4.dp),
)

@Composable
fun ProductAppLCDSMTheme(
    content: @Composable () -> Unit,
) {
    MaterialTheme(
        colorScheme = DolceColorScheme,
        typography = Typography,
        shapes = DolceShapes,
        content = content,
    )
}
