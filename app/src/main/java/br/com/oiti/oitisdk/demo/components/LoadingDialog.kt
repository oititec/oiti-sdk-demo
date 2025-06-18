package br.com.oiti.oitisdk.demo.components

import android.widget.Toast
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import br.com.oiti.oitisdk.demo.ui.theme.OitiGreen
import kotlinx.coroutines.delay

@Composable
fun LoadingDialog(
    isLoading: Boolean,
    timeoutMs: Long = 15_000L,
    onTimeout: (() -> Unit)? = null,
    background: Int? = null,
    circularProgressIndicatorColor: Int? = null,
    loadingScaleSize: Int? = null,
    loadingProgressIndicatorWidth: Int? = null,
    content: @Composable () -> Unit
) {
    val context = LocalContext.current
    val interceptBackPressed = remember { mutableStateOf(false) }

    BackHandler(enabled = isLoading && interceptBackPressed.value) {}

    LaunchedEffect(isLoading) {
        if (isLoading) {
            delay(timeoutMs)
            Toast.makeText(
                context,
                "O carregamento expirou, verifique sua conexão.",
                Toast.LENGTH_LONG
            ).show()
            onTimeout?.invoke()
        }
    }

    Box(modifier = Modifier.fillMaxSize()) {
        content()
        if (isLoading) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        background?.let { Color(it) }
                            ?: Color.Black.copy(alpha = 0.5f)
                    )
                    .pointerInput(Unit) {},
                contentAlignment = Alignment.Center
            ) {
                interceptBackPressed.value = true
                CircularProgressIndicator(
                    modifier = Modifier.size((loadingScaleSize ?: 50).dp),
                    strokeWidth = (loadingProgressIndicatorWidth ?: 5).dp,
                    color = Color(circularProgressIndicatorColor ?: OitiGreen.toArgb())
                )
            }
        } else {
            interceptBackPressed.value = false
        }
    }
}
