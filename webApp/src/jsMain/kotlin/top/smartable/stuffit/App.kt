package top.smartable.stuffit

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.*
import org.jetbrains.compose.web.dom.Text   // ← именно так
import org.jetbrains.compose.web.dom.Div
import org.jetbrains.compose.web.css.*
import top.smartable.stuffit.repository.AuthRepository
import top.smartable.stuffit.ui.auth.RegisterScreen

@Composable
fun App() {
    val authRepository = remember { AuthRepository() }
    var isRegistered by remember { mutableStateOf(false) }

    Div({
        style {
            width(100.percent)
            height(100.percent)
            fontFamily("system-ui, -apple-system, sans-serif")
        }
    }) {
        MaterialTheme {
            if (!isRegistered) {
                RegisterScreen(
                    authRepository = authRepository,
                    onRegisterSuccess = { user ->
                        println("Registered: ${user.email}")
                        isRegistered = true
                    }
                )
            } else {
                Text("Welcome! (Main screen in progress)")
            }
        }
    }
}

fun main() {
    org.jetbrains.compose.web.renderComposable(rootElementId = "root") {
        App()
    }
}