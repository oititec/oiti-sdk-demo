package br.com.oiti.oitisdk.demo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.com.oiti.manager.exports.LivenessResult
import br.com.oiti.oitisdk.demo.components.LoadingDialog
import br.com.oiti.oitisdk.demo.components.ProdutoToggleButtons
import br.com.oiti.oitisdk.demo.extensions.format
import br.com.oiti.oitisdk.demo.model.Features
import br.com.oiti.oitisdk.demo.ui.theme.CertifaceSDKTheme

class LivenessActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            CertifaceSDKTheme {
                Scaffold { inner ->
                    var isLoading by remember { mutableStateOf(false) }
                    LoadingDialog(
                        isLoading = isLoading,
                        onTimeout = { isLoading = false }
                    ) {
                        LivenessScreen(modifier = Modifier.padding(inner)) { appKey, selectedFeature, onSuccess, onError, isCustomEnabled ->
                            isLoading = true
                            LivenessExecutor(appKey, selectedFeature).executeLiveness(
                                context = this@LivenessActivity,
                                execOnSuccess = { result ->
                                    isLoading = false
                                    onSuccess(result)
                                },
                                execOnError = { throwable ->
                                    isLoading = false
                                    onError(throwable?.errorMessage ?: "erro desconhecido")
                                },
                                isCustomEnabled = isCustomEnabled
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun LivenessScreen(
    modifier: Modifier = Modifier,
    onStartClick: (
        appKey: String,
        feature: Features,
        onSuccess: (LivenessResult?) -> Unit,
        onError: (String) -> Unit,
        isCustomEnabled: Boolean
    ) -> Unit
) {
    var appKey by remember { mutableStateOf("") }
    val resultados = remember { mutableStateListOf<String>() }
    val features = Features.entries
    var selectedFeature by remember { mutableStateOf(features.first()) }

    Column(
        modifier = modifier
            .fillMaxSize()
            .imePadding()
            .padding(16.dp)
    ) {
        Card(
            modifier = Modifier.fillMaxWidth(),
            elevation = CardDefaults.cardElevation(4.dp),
            shape = RoundedCornerShape(8.dp)
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text("Certiface SDK", fontSize = 20.sp)
                Spacer(Modifier.height(8.dp))
                OutlinedTextField(
                    value = appKey,
                    onValueChange = { appKey = it },
                    label = { Text("AppKey") },
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(Modifier.height(12.dp))

                ProdutoToggleButtons(
                    features = features,
                    selected = selectedFeature,
                    onSelect = { selectedFeature = it }
                )

                Spacer(Modifier.height(12.dp))
                Button(
                    onClick = {
                        resultados.clear()
                        onStartClick(
                            appKey,
                            selectedFeature,
                            { msg -> resultados += "OK: ${msg?.format()}" },
                            { err -> resultados += "ERRO: $err" },
                            false
                        )
                    },
                    enabled = appKey.isNotBlank(),
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(6.dp)
                ) {
                    Text("Iniciar Default")
                }
                Spacer(Modifier.height(12.dp))
                Button(
                    onClick = {
                        resultados.clear()
                        onStartClick(
                            appKey,
                            selectedFeature,
                            { msg -> resultados += "OK: ${msg?.format()}" },
                            { err -> resultados += "ERRO: $err" },
                            true
                        )
                    },
                    enabled = appKey.isNotBlank(),
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(6.dp)
                ) {
                    Text("Iniciar Custom")
                }
            }
        }

        Spacer(Modifier.height(16.dp))

        Card(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
            elevation = CardDefaults.cardElevation(2.dp),
            shape = RoundedCornerShape(8.dp)
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text("Resultados", fontSize = 18.sp)
                Spacer(Modifier.height(4.dp))
                HorizontalDivider()
                Spacer(Modifier.height(8.dp))

                if (resultados.isEmpty()) {
                    Text(
                        "Sem resultados ainda",
                        modifier = Modifier.fillMaxSize(),
                        textAlign = TextAlign.Center
                    )
                } else {
                    LazyColumn {
                        items(resultados) { linha ->
                            Text(
                                text = linha.substringAfter(": ").trim(),
                                modifier = Modifier.padding(vertical = 4.dp)
                            )
                        }
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@PreviewLightDark
@Composable
fun LivenessScreenPreview() {
    CertifaceSDKTheme {
        LivenessScreen(onStartClick = { _, _, _, _, _ -> })
    }
}