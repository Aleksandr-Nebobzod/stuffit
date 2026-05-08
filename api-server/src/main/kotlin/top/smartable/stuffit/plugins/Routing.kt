package top.smartable.stuffit.plugins

import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import top.smartable.stuffit.model.*
import top.smartable.stuffit.repository.MockAuthRepository

fun Application.configureRouting() {
    val authRepo = MockAuthRepository()

//    routing {
//        post("/api/register") {
//            val req = call.receive<RegisterRequest>()
//            val result = authRepo.register(req.email, req.password)
//            call.respond(mapOf("success" to result, "token" to "mock-token-${req.email}"))
//        }
//
//        post("/api/login") {
//            val req = call.receive<LoginRequest>()
//            val result = authRepo.login(req.email, req.password)
//            call.respond(mapOf("success" to result, "token" to "mock-token-${req.email}"))
//        }
//
//        get("/api/profile") {
//            val token = call.request.headers["Authorization"]?.removePrefix("Bearer ")
//            call.respond(mapOf("id" to "123", "email" to "mock@example.com", "displayName" to null))
//        }
//    }
}