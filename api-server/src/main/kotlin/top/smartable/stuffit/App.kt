package top.smartable.stuffit

import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import io.ktor.server.plugins.contentnegotiation.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import kotlinx.datetime.Clock
import top.smartable.stuffit.model.Area
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
    // Инициализируем хранилища
    val userStorage = mutableListOf(mockUser)
    val areaStorage = mutableListOf<Area>()

    // Создаем сервер (этого блока не хватало в источнике)
    val server = embeddedServer(Netty, port = 7777) {
        // Обязательно устанавливаем поддержку JSON
        install(ContentNegotiation) {
            json(kotlinx.serialization.json.Json {
                prettyPrint = true
                isLenient = true
                ignoreUnknownKeys = true // Игнорировать поля, которых нет в классе
                encodeDefaults = true    // Включать дефолтные значения в ответ
            })
        }

        routing {
            get("/") {
                call.respondText("Сервер работает 4")
            }

            // Регистрируем Generic-маршруты
            autoCrud("/api/v1/users", userStorage) { it.id }
            autoCrud("/api/v1/areas", areaStorage) { it.id }

//            get("/") {
//                println("GET / called")
//                call.respondText("The server works / Сервер отвечает 3")
//            }

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

    println("Сервер запущен на http://localhost:7777")
    server.start(wait = true)
}

// Исправленная функция autoCrud с правильными обертками DSL
inline fun <reified T : Any> Route.autoCrud(
    path: String,
    storage: MutableList<T>,
    crossinline getId: (T) -> String?
) {
    route(path) {
        // Получить все объекты
        get {
            println("get_all $path")
            call.respond(storage)
        }

        // Добавить объект (POST) — добавлена обертка post { }
        post {
            println("POST $path: получен запрос")
            try {
                val item = call.receive<T>()
                println("  получен объект: $item")
                storage.add(item)
                call.respond(HttpStatusCode.Created, item)
            } catch (e: Exception) {
                println("Ошибка при обработке POST: ${e.message}")
                // клиент получит 400 (Bad Request) вместо 500
                call.respond(HttpStatusCode.BadRequest, "Ошибка в данных: ${e.message}")
            }
        }

        // Получить по ID — добавлена обертка get("{id}") { }
        get("{id}") {
            println("get_by_id $path")
            val id = call.parameters["id"]
            val item = storage.find { getId(it) == id }
            if (item != null) call.respond(item) else call.respond(HttpStatusCode.NotFound)
        }
    }
}
