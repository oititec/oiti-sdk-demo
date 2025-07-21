package br.com.oiti.oitisdk.demo.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import br.com.oiti.oitisdk.demo.model.Features
import br.com.oiti.oitisdk.demo.ui.theme.OitiBlue
import br.com.oiti.oitisdk.demo.ui.theme.OitiWhite

@Composable
fun ProdutoToggleButtons(
    features: List<Features>,
    selected: Features,
    onSelect: (Features) -> Unit
) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        modifier = Modifier.fillMaxWidth()
    ) {
        features.forEach { features ->
            val isSel = features == selected
            OutlinedButton(
                onClick = { onSelect(features) },
                modifier = Modifier
                    .weight(1f),
                shape = RoundedCornerShape(6.dp),
                border = BorderStroke(
                    1.dp,
                    if (isSel) Color.Transparent else MaterialTheme.colorScheme.onSurfaceVariant
                ),
                colors = ButtonDefaults.outlinedButtonColors(
                    containerColor = if (isSel) OitiBlue else MaterialTheme.colorScheme.surfaceVariant,
                    contentColor = if (isSel) OitiWhite else MaterialTheme.colorScheme.onSurfaceVariant
                ),
                contentPadding = PaddingValues(horizontal = 8.dp, vertical = 0.dp)
            ) {
                Text(
                    text = features.name,
                    style = MaterialTheme.typography.labelLarge
                )
            }
        }
    }
}
