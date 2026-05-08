package top.smartable.stuffit


import io.ktor.server.engine.*
import io.ktor.server.netty.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun main() {
    val server = embeddedServer(Netty, port = 7777) {
        routing {
            get("/") {
                println("GET / called")
                call.respondText("The server works / Сервер отвечает")
            }
            post("/api/register") {
                val body = call.receiveText()
                println("POST /api/register body: $body")
                call.respondText("Registered")
            }
        }
    }
    println("Сервер запущен по адресу http://localhost:7777")
    server.start(wait = true)
}