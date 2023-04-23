package pro.devapp.cast.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import pro.devapp.cast.R
import pro.devapp.cast.model.ScreenState

@Composable
fun Content(
    screenState: ScreenState,
    onOpenSettings: () -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(brush = Brush.verticalGradient(
                colors = listOf(
                    Color(0xFFF64C72), Color(0xFFFEBA40)
                )
            ))
            .padding(16.dp)
    ) {
        RoutersList(items = screenState.routes)

        Spacer(modifier = Modifier.padding(32.dp))

        ConnectionState(
            wifiConnected = screenState.hasWifiConnection,
            devicesAvailable = screenState.routes.isEmpty().not()
        )

        Spacer(modifier = Modifier.padding(8.dp))

        Spacer(modifier = Modifier.padding(8.dp))

        Button(
            modifier = Modifier.fillMaxWidth(),
            onClick = {
                onOpenSettings()
            },
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFFF64C72)
            )
        ) {
            Text(
                text = stringResource(R.string.open_cast_settings),
                style = MaterialTheme.typography.bodyLarge,
                color = Color.White
            )
        }

        Spacer(modifier = Modifier.padding(2.dp))

        Text(
            modifier = Modifier.fillMaxWidth(),
            text = stringResource(R.string.for_start_or_stop_screen_casting_mirroring_open_settings),
            style = MaterialTheme.typography.bodyMedium,
            textAlign = TextAlign.Center,
            color = Color(0xFF574351)
        )
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