package pro.devapp.cast.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.mediarouter.media.MediaRouter
import pro.devapp.cast.R

@Composable
fun RoutersList(items: List<MediaRouter.RouteInfo>) {
    for (route in items) {
        val isConnected = route.connectionState == MediaRouter.RouteInfo.CONNECTION_STATE_CONNECTED
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Image(
                modifier = Modifier.width(32.dp),
                painter = painterResource(id = R.drawable.smart_tv),
                contentDescription = null
            )
            Text(
                text = route.name,
                style = MaterialTheme.typography.titleMedium,
                color = Color(0xFF574351)
            )
            if (isConnected) {
                Image(
                    modifier = Modifier.width(32.dp),
                    painter = painterResource(id = R.drawable.connected),
                    contentDescription = null
                )
            } else {
                Text(
                    text = stringResource(R.string.disconnected),
                    style = MaterialTheme.typography.bodySmall,
                    color = Color(0xFF777777)
                )
            }
        }
    }
}