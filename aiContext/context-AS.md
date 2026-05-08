# context-as.md v2.0
Расширяет глобальный CONTEXT.md для чата `1-as-questions`
Обновлён: 2026-05-02
Статус: рабочий (актуально)

---

## 🎯 Зона ответственности

- Настройка Android Studio для Kotlin Multiplatform
- Gradle конфигурация (KMP + Compose Web + Compose Desktop)
- Решение типичных ошибок сборки
- Запуск Web и Desktop приложений

---

## ✅ Текущее состояние проекта

### Платформы
| Платформа | Статус | Технологии |
|-----------|--------|-------------|
| **Desktop (JVM)** | ✅ Работает | Compose for Desktop, JDK 17 |
| **Web (JS)** | ✅ Работает | Compose for Web, Kotlin/JS |
| **Android** | 🟡 Заглушка | Android Library, minSdk 24 |
| **iOS** | ❌ Отключён | iOS таргеты закомментированы |

### Структура модулей

```
stuffit/ # корень проекта
├── shared/ # KMP общий код
│ ├── src/
│ │ ├── commonMain/kotlin/ # общая логика (AppViewModel, Platform expect)
│ │ ├── desktopMain/kotlin/ # Desktop entry point (App.kt, Main.kt)
│ │ ├── jsMain/kotlin/ # Web entry point (App.kt, Main.kt)
│ │ ├── jsMain/resources/ # index.html для Web
│ │ ├── androidMain/kotlin/ # Android заглушки
│ │ └── iosMain/kotlin/ # iOS заглушки (неактивны)
│ └── build.gradle.kts
├── stuffit/ # Android приложение (отдельный модуль)
├── gradle/
│ └── libs.versions.toml # version catalog
├── build.gradle.kts
├── settings.gradle.kts
└── gradle.properties
```


###Заметки для разработки

- Desktop сборка требует JDK 17
- Web сборка генерирует shared.js или main.js в зависимости от настроек
- Production Web лучше тестировать через Python сервер (не webpack-dev-server)
- Version catalog (libs.versions.toml) — все зависимости централизованы
- Compose plugin версии 1.6.10 работает с Kotlin 1.9.20