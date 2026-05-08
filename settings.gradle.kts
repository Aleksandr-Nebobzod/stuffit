enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")

//println("SETTINGS EXECUTED")

rootProject.name = "stuff"

// ⬇️ ВАЖНО: include должен быть ДО или ВНЕ любых сложных конструкций
include(":shared")
include(":api-server")

pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
        maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
    }
}

dependencyResolutionManagement {
    repositories {
        google()
        mavenCentral()
        maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
    }
}