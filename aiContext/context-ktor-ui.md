# context-ktor-ui.md v1.0
Расширяет глобальный CONTEXT.md v1.6
Создан: 2026-05-04 10:53 MSK
Статус: черновик (задача для чата `8-ktor-ui`)

---

## 🎯 Зона ответственности этого контекста

- Создание отдельного Ktor сервера (мок-бэкенд) для веб-дизайнера
- REST API эндпоинты: регистрация, вход, получение профиля
- Использование `AuthRepository`-заглушки (без Firebase на первом этапе)
- Сборка fat JAR для передачи дизайнеру
- Postman коллекция для тестирования

**Что НЕ входит:** 
- Реальный Firebase (будет позже в основном приложении)
- Бизнес-логика учёта вещей
- Compose UI

---

## 🏗 Архитектурное решение (утверждено глобально)

````
Дизайнер (HTML/JS) ←─ REST ─→ Ktor Server (JAR) ←─ вызывает ─→ AuthRepository (мок)
│
↓
Возвращает JSON
````

---


```

**Адреса:**
- Ktor сервер: `http://localhost:8080`
- HTML дизайнера: `http://localhost:5500` (Live Server)

---

## 📋 Задачи для реализации

### 1. Создать модуль `api-server`
- Расположение: `api-server/build.gradle.kts`
- Плагины: `kotlin("jvm")`, `application`, `id("com.github.johnrengelman.shadow")`
- Зависимости: Ktor сервер, kotlinx-serialization, `shared` модуль (для доступа к AuthRepository)

### 2. Реализовать Ktor Application
```kotlin
// api-server/src/main/kotlin/Application.kt
fun main() {
    embeddedServer(Netty, port = 8080) {
        module()
    }.start(wait = true)
}

fun Application.module() {
    install(ContentNegotiation) { json() }
    install(CORS)
    
    routing {
        post("/api/register") { /* вызывает authRepository.register */ }
        post("/api/login") { /* вызывает authRepository.login */ }
        get("/api/profile") { /* вызывает authRepository.currentUser */ }
    }
}
```

3. Подключить мок-реализацию AuthRepository

Создать MockAuthRepository (хранит пользователей в памяти)
Не требует Firebase, возвращает предсказуемые ответы

###4. Настроить сборку fat JAR

./gradlew :api-server:shadowJar
Результат: api-server/build/libs/api-server-all.jar

###5. Создать Postman коллекцию

Эндпоинты:

* POST http://localhost:8080/api/register body: {email, password}
* POST http://localhost:8080/api/login body: {email, password}
* GET http://localhost:8080/api/profile headers: Authorization: Bearer {token}

###6. Написать инструкцию для дизайнера

Как запустить JAR: java -jar api-server-all.jar
Как проверить работоспособность: перейти на http://localhost:8080/api/profile (должен вернуть 401)
Как подключиться из HTML: пример fetch запроса
