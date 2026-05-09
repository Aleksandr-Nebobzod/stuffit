### context-as.md v3.0
 [1, 2]

---
Расширяет глобальный CONTEXT.md для чата `1-as-questions`
Обновлён: 2026-05-09
Статус: **рефакторинг завершен**

---

## 🎯 Зона ответственности

- Настройка Android Studio для Kotlin Multiplatform
- Gradle конфигурация (KMP + Compose Web + Compose Desktop)
- Решение типичных ошибок сборки
- Запуск Web и Desktop приложений
- Настройка интеграции Firebase (GitLive SDK) и Ktor-сервера

---

## ✅ Текущее состояние проекта

### Платформы
| Платформа | Статус | Технологии |
|-----------|--------|-------------|
*   **Android / Desktop / iOS**: Общий UI и платформенный код для Android/iOS/Desktop в модуле `:composeApp`
*   **Web (JS)**: Отдельный UI и конфигурация для Kotlin/JS в модуле `:webApp`
*   **Logic**: Общие модели данных (`User.kt`) и интерфейсы репозиториев (`AuthRepository.kt`) строго в `:shared`
*   **Backend**: Firebase (основной) + локальный Ktor (мок-сервер в `:api-server`)
| **iOS** | ❌ Отключён | iOS таргеты закомментированы |

### Структура модулей

```
stuffit/ # корень проекта
.
├── composeApp/             # Общий UI и Android-код
├── shared/                # Бизнес-логика
├── iosApp/                # <--- Сюда переехали Swift-файлы и Xcode-проект
│   ├── stuffit.xcodeproj
│   └── stuffit/
│       ├── iOSApp.swift
│       └── Assets.xcassets
└── api-server/            # Ktor сервер
├── gradle/
│ └── libs.versions.toml # version catalog
├── build.gradle.kts
├── settings.gradle.kts
└── gradle.properties
```


### 🛠 Gradle & Сборка
*   **Централизация**: Репозитории объявлены только в `settings.gradle.kts` (`FAIL_ON_PROJECT_REPOS`).
*   **Удобство**: Включены `TYPESAFE_PROJECT_ACCESSORS` для обращения к проектам через `projects.name`.
*   **Версии**: Единое управление через `gradle/libs.versions.toml`
*   **Стек**: Kotlin 1.9.20, Compose 1.6.10, JDK 17

## ⚠️ Важные Заметки для разработки

- Desktop сборка требует JDK 17
- Web сборка генерирует shared.js или main.js в зависимости от настроек
- Production Web лучше тестировать через Python сервер (не webpack-dev-server)
- Version catalog (libs.versions.toml) — все зависимости централизованы
- Compose plugin версии 1.6.10 работает с Kotlin 1.9.20
###   заметки в 3
*   **Перенос кода**: При перемещении классов между модулями (например, в `:composeApp`) использовать **Refactor > Move**, чтобы AS обновила импорты.
*   **AndroidManifest**: Перенесен в `composeApp/src/androidMain/` [1].
*   **Firebase**: В `:shared` используются мультиплатформенные обертки (напр. GitLive SDK).
*   **Конфликты репозиториев**: Блоки `repositories {}` внутри `build.gradle.kts` модулей удалены.