package pro.devapp.cast.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import pro.devapp.cast.model.ScreenState

@Composable
fun Content(
    screenState: ScreenState,
    onOpenSettings: () -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = "Screen castings/mirroring",
            style = MaterialTheme.typography.headlineMedium,
            textAlign = TextAlign.Center,
            color = MaterialTheme.colorScheme.onPrimary
        )

        WifiState(isConnected = screenState.hasWifiConnection)

        RoutersList(items = screenState.routes)

        Text(text = "For start or stop screen casting/mirroring, open settings")
        OutlinedButton(onClick = {
            onOpenSettings()
        }) {
            Text(text = "Open cast settings")
        }
    }
}

@Preview
@Composable
private fun ContentPreview() {
    Content(
        screenState = ScreenState(),
        onOpenSettings = {}
    )
}