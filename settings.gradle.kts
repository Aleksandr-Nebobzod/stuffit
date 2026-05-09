rootProject.name = "stuff"

// Подключаем все модули согласно структуре v3
include(":shared")      // Общая бизнес-логика и модели данных [2]
include(":composeApp")   // Общий UI для Android, iOS, Desktop [3]
include(":webApp")       // Отдельный UI для Web (Kotlin/JS) [2]
include(":api-server")   // Ktor мок-сервер [3]

pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
        // Репозиторий для Compose Multiplatform [1]
        maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
    }
}

dependencyResolutionManagement {
    // Режим FAIL_ON_PROJECT_REPOS гарантирует, что все репозитории объявлены здесь
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
        // Репозиторий для библиотек Compose Multiplatform [1]
        maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
    }
}