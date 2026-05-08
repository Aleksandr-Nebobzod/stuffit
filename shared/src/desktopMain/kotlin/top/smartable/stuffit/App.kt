package top.smartable.stuffit

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.material3.*
import androidx.compose.foundation.layout.*

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import top.smartable.stuffit.repository.AuthRepository
import top.smartable.stuffit.ui.auth.RegisterScreen

@Composable
fun App() {
    val authRepository = remember { AuthRepository() }
    var isRegistered by remember { mutableStateOf(false) }

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
            // TODO: Main screen after registration
            Text("Welcome! (Main screen in progress)")
        }
    }
}

fun main() = application {
    Window(onCloseRequest = ::exitApplication) {
        App()
    }
}