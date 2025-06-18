package br.com.oiti.oitisdk.demo.ui.theme

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext

private val DarkColorScheme = darkColorScheme(
    primary = OitiBlue,
    onPrimary = OitiWhite,
    secondary = OitiGreen,
    onSecondary = OitiDark,
    background = OitiDark,
    onBackground = OitiWhite,
    surface = Color(0xFF1C1C1E),
    onSurface = OitiWhite
)

private val LightColorScheme = lightColorScheme(
    primary = OitiBlue,
    onPrimary = OitiWhite,
    secondary = OitiGreen,
    onSecondary = OitiWhite,
    background = Color(0xFFF9F9F9),
    onBackground = Color(0xFF000000),
    surface = OitiWhite,
    onSurface = Color(0xFF000000)
)

@Composable
fun OitiSDKTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is available on Android 12+
    dynamicColor: Boolean = false,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }

        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}