package pro.devapp.cast.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.ColorMatrix
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import pro.devapp.cast.R

@Composable
fun ConnectionState(wifiConnected: Boolean, devicesAvailable: Boolean) {
    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            Column(
                modifier = Modifier
                    .padding(end = 16.dp)
                    .weight(1f),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                if (wifiConnected) {
                    Image(
                        modifier = Modifier.width(90.dp),
                        painter = painterResource(id = R.drawable.wifi_connected),
                        contentDescription = null
                    )
                    Spacer(modifier = Modifier.padding(2.dp))
                    Text(
                        text = stringResource(R.string.wifi_connected),
                        style = MaterialTheme.typography.bodyMedium,
                        color = Color(0xFF574351)
                    )
                } else {
                    Image(
                        modifier = Modifier.width(90.dp),
                        painter = painterResource(id = R.drawable.wifi_connected),
                        contentDescription = null,
                        colorFilter = ColorFilter.colorMatrix(ColorMatrix().apply {
                            setToSaturation(
                                0f
                            )
                        })
                    )
                    Spacer(modifier = Modifier.padding(2.dp))
                    Text(
                        text = stringResource(R.string.wifi_disconnected),
                        style = MaterialTheme.typography.bodyMedium,
                        color = Color(0xFF574351)
                    )
                }
            }

            Column(
                modifier = Modifier
                    .padding(end = 16.dp)
                    .weight(1f),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                if (devicesAvailable) {
                    Image(
                        modifier = Modifier.width(90.dp),
                        painter = painterResource(id = R.drawable.devices_connected),
                        contentDescription = null
                    )
                    Spacer(modifier = Modifier.padding(2.dp))
                    Text(
                        text = "Devices available",
                        style = MaterialTheme.typography.bodyMedium,
                        color = Color(0xFF574351)
                    )
                } else {
                    Image(
                        modifier = Modifier.width(90.dp),
                        painter = painterResource(id = R.drawable.devices_connected),
                        contentDescription = null,
                        colorFilter = ColorFilter.colorMatrix(ColorMatrix().apply {
                            setToSaturation(
                                0f
                            )
                        })
                    )
                    Spacer(modifier = Modifier.padding(2.dp))
                    Text(
                        text = stringResource(R.string.disconnected),
                        style = MaterialTheme.typography.bodyMedium,
                        color = Color(0xFF574351)
                    )
                }
            }
        }
        if (wifiConnected.not() || devicesAvailable.not()) {
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = stringResource(R.string.your_device_and_tv_must_be_connected_to_the_same_wi_fi_network),
                style = MaterialTheme.typography.bodyMedium,
                textAlign = TextAlign.Center,
                color = Color(0xFF574351)
            )
        }
    }
}