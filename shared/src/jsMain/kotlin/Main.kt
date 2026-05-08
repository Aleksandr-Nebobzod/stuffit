import org.jetbrains.compose.web.dom.*
import org.jetbrains.compose.web.renderComposable
import androidx.compose.runtime.*
import top.smartable.stuffit.AppViewModel

import top.smartable.stuffit.App
import org.jetbrains.compose.web.renderComposable

fun main() {
    renderComposable(rootElementId = "root") {
        App()
    }
}