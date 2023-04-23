package pro.devapp.cast.component

import android.content.Context
import android.util.Log
import androidx.mediarouter.media.MediaRouteSelector
import androidx.mediarouter.media.MediaRouter
import com.google.android.gms.cast.CastMediaControlIntent

class MediaRouterWatcher(
    context: Context,
    private val changeListener: OnDeviceChangeListener
) {

    private val mediaRouter = MediaRouter.getInstance(context)

    private val callback = object: MediaRouter.Callback() {
        override fun onRouteAdded(router: MediaRouter, route: MediaRouter.RouteInfo) {
            if (route.playbackType == MediaRouter.RouteInfo.PLAYBACK_TYPE_REMOTE){
                changeListener.onDeviceAdded(route)
                Log.d(
                    "MainActivity",
                    "onRouteAdded: ${route.name} ${route.isSelected} ${route.connectionState}"
                )
            }
        }

        override fun onRouteChanged(router: MediaRouter, route: MediaRouter.RouteInfo) {
            changeListener.onDeviceChanged(route)
            Log.d(
                "MainActivity",
                "onRouteChanged: ${route.name} ${route.isSelected}  ${route.connectionState}"
            )
        }

        override fun onRouteRemoved(router: MediaRouter, route: MediaRouter.RouteInfo) {
            changeListener.onDeviceRemoved(route)
            Log.d(
                "MainActivity",
                "onRouteRemoved: ${route.name} ${route.isSelected}  ${route.connectionState}"
            )
        }
    }

    fun startWatch() {
        val mMediaRouteSelector = MediaRouteSelector.Builder()
            .addControlCategory(CastMediaControlIntent.categoryForRemotePlayback())
            .build()
        mediaRouter
            .addCallback(mMediaRouteSelector, callback,
                MediaRouter.CALLBACK_FLAG_PERFORM_ACTIVE_SCAN
            )
    }

    fun stopWatch() {
        mediaRouter.removeCallback(callback)
    }

    interface OnDeviceChangeListener {
        fun onDeviceAdded(device: MediaRouter.RouteInfo)
        fun onDeviceChanged(device: MediaRouter.RouteInfo)
        fun onDeviceRemoved(device: MediaRouter.RouteInfo)
    }
}