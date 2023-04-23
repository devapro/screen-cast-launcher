package pro.devapp.cast.model

import androidx.mediarouter.media.MediaRouter

data class ScreenState(
    val routes: List<MediaRouter.RouteInfo> = emptyList(),
    val time: Long = 0,
    val hasWifiConnection: Boolean = false
)