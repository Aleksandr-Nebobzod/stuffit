package top.smartable.stuffit

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

data class ScreenState(
    val title: String = "Главная",
    val breadcrumbs: List<String> = listOf("Главная"),
    val menuOpened: Boolean = false,
    val content: String = "HOME"
)

class AppViewModel {

    private val _state = MutableStateFlow(ScreenState())
    val state: StateFlow<ScreenState> = _state

    fun toggleMenu() {
        _state.value = _state.value.copy(
            menuOpened = !_state.value.menuOpened
        )
    }

    fun navigate(to: String) {
        _state.value = ScreenState(
            title = to,
            breadcrumbs = listOf("Главная", to),
            content = to
        )
    }
}