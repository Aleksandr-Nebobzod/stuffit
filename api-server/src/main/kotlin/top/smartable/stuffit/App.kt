package top.smartable.stuffit


//import androidx.compose.ui.autofill.ContentType
import io.ktor.http.ContentType
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import java.util.UUID
import kotlinx.datetime.Clock
import io.ktor.server.plugins.contentnegotiation.ContentNegotiation
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import top.smartable.stuffit.model.User
import top.smartable.stuffit.model.UserSettings

val mockUser = User(
    id = "1",
    email = "user1@us.org",
    displayName = "User One",
    createdAt = Clock.System.now(),
    lastLoginAt = Clock.System.now(),
    settings = UserSettings(),
    migrationFromSchulteId = null
)


fun main() {
    val server = embeddedServer(Netty, port = 7777) {
        install(ContentNegotiation) {
            json()
        }

        routing {
            get("/") {
                println("GET / called")
                call.respondText("The server works / Сервер отвечает")
            }

            get("/api/v1/routes") {
                val routes = mapOf(
                    "register" to "POST /api/v1/auth/register {email, password, displayName?} -> AuthResponse",
                    "login" to "POST /api/v1/auth/login {email, password} -> AuthResponse",
                    "profile" to "GET /api/v1/user/profile (Header: Authorization: Bearer {token}) -> User"
                )
                try {
                    println("GET /api/v1/routes метод вызван")
                    call.respondText(routes.toString())
                    call.respond(routes)
                } catch (e: Exception) {
                    println("Ошибка: ${e.message}")
                    e.printStackTrace()
                }
            }

            get("/api/v1/user/mock") {
                println("GET /api/v1/user/mock called")
                call.respond(mockUser)
            }
        }
    }
    println("Сервер запущен по адресу http://localhost:7777")
    server.start(wait = true)
}