package pro.devapp.cast

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.mediarouter.media.MediaRouter
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import pro.devapp.cast.model.ScreenState

class MainViewModel: ViewModel() {

    //MediaRouter.RouteInfo.CONNECTION_STATE_DISCONNECTED

    private val _uiState = MutableStateFlow(
        ScreenState(
        routes = emptyList(),
        time = System.currentTimeMillis()
    )
    )
    val uiState = _uiState.asStateFlow()

    fun addRouter(routeInfo: MediaRouter.RouteInfo) {
        viewModelScope.launch {
            val routers = _uiState.value.routes.toMutableList()
            routers.add(routeInfo)
            _uiState.emit(
                _uiState.value.copy(
                    routes = routers.distinctBy { it.id },
                    time = System.currentTimeMillis()
                )
            )
        }
    }

    fun removeRouter(routeInfo: MediaRouter.RouteInfo) {
        viewModelScope.launch {
            val routers = _uiState.value.routes.toMutableList()
            routers.removeIf { it.id == routeInfo.id }
            _uiState.emit(
                _uiState.value.copy(
                    routes = routers,
                    time = System.currentTimeMillis()
                )
            )
        }
    }

    fun changeRouter(routeInfo: MediaRouter.RouteInfo) {
        viewModelScope.launch {
            val routers = _uiState.value.routes.toMutableList()
            routers.removeIf { it.id == routeInfo.id }
            routers.add(routeInfo)
            _uiState.emit(
                _uiState.value.copy(
                    routes = routers,
                    time = System.currentTimeMillis()
                )
            )
        }
    }

    fun changeWifiConnection(hasConnection: Boolean) {
        viewModelScope.launch {
            _uiState.emit(
                _uiState.value.copy(
                    hasWifiConnection = hasConnection,
                    time = System.currentTimeMillis()
                )
            )
        }
    }

}