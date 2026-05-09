package top.smartable.stuffit

import androidx.compose.runtime.*
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application

fun main() = application {

    val vm = remember { AppViewModel() }

    Window(
        onCloseRequest = ::exitApplication,
        title = "Stuffit"
    ) {
        App(vm)
    }
}