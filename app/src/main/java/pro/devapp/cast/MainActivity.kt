package pro.devapp.cast

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.mediarouter.media.MediaRouter
import pro.devapp.cast.component.MediaRouterWatcher
import pro.devapp.cast.component.NetworkWatcher
import pro.devapp.cast.ui.components.Content
import pro.devapp.cast.ui.theme.TVCastTheme


class MainActivity : ComponentActivity() {

    private val viewModel: MainViewModel by viewModels()

    private val networkWatcher: NetworkWatcher by lazy {
        NetworkWatcher(this) {
            viewModel.changeWifiConnection(it)
        }
    }

    private val mediaRouterWatcher: MediaRouterWatcher by lazy {
        MediaRouterWatcher(this, object : MediaRouterWatcher.OnDeviceChangeListener {
            override fun onDeviceAdded(device: MediaRouter.RouteInfo) {
                viewModel.addRouter(device)
            }

            override fun onDeviceChanged(device: MediaRouter.RouteInfo) {
                viewModel.changeRouter(device)
            }

            override fun onDeviceRemoved(device: MediaRouter.RouteInfo) {
                viewModel.removeRouter(device)
            }
        })
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TVCastTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = Color.Transparent
                ) {
                    val screenState by viewModel.uiState.collectAsState()
                    Content(
                        screenState = screenState,
                        onOpenSettings = {
                            startActivity(Intent("android.settings.CAST_SETTINGS"))
                        }
                    )
                }
            }
        }
    }

    override fun onResume() {
        super.onResume()
        mediaRouterWatcher.startWatch()
        networkWatcher.startListen()
    }

    override fun onPause() {
        super.onPause()
        mediaRouterWatcher.stopWatch()
        networkWatcher.stopListen()
    }
}