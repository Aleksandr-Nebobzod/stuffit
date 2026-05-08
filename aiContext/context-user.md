Инструкция для LLM:

- ознакомиться с содержимым файла
- продемонстрировать пользователю различия файла и текущих наработок
	- если только дополнения, то предложить пользователю состав дополнений (он сам обновит файл)
	- если удаления и изменения, перегенерировать весь файл, подняв версию файла
(при необходимости в Раздел ## Текущий статус, следует добавлять  раздел "## Информация для LLM в смежных контекстах проекта" с LLM-related data)

# context-user.md v1.1
Расширяет глобальный CONTEXT.md
Создан: 2026-04-28
Обновлён 2026-05-03
Статус: черновик (утверждается в чате 2-user-environment)

---

## 🎯 Зона ответственности этого контекста

- Модель пользователя (`User`, `UserSettings`)
- Аутентификация (регистрация, вход, выход, удаление аккаунта)
- Окружение (настройки, тема, валюта)
- Интеграция с Attention Schulte Plus (миграция учёток)

**Что НЕ входит:** вещи, категории, перемещения, аналитика.

---

## 👤 Модели данных (подробно)

### User
```kotlin
data class User(
    val id: String,                    // Firebase Auth uid
    val email: String,                 // обязательное поле, уникальное
    val displayName: String? = null,   // можно "Владелец" или имя из Schulte
    val createdAt: Timestamp,          // серверное время
    val lastLoginAt: Timestamp,        // обновляется при каждом входе
    val settings: UserSettings,
    val migrationFromSchulteId: String? = null  // id из старого проекта, если есть
)

---

## 🚧 Текущий статус реализации (2026-05-03)

### Web (JS)
- ✅ Компиляция и сборка работают (`jsBrowserDevelopmentWebpack`)
- ✅ Экран регистрации создан (`RegisterScreen.kt` в commonMain)
- ⚠️ Material3 требует `LocalFontFamilyResolver` для Web (решение найдено)
- ⚠️ CSP ошибки решены отключением meta-тега (production требует доработки)
- 🔄 В процессе: запуск UI в браузере

### Desktop (JVM)
- ✅ Сборка настроена (`desktopRunLocal`)
- 🔄 Ожидает запуска после фикса Web

### AuthRepository
- ✅ Реализована заглушка (без Firebase)
- 🔄 Реальная Firebase интеграция — следующий шаг

### Известные технические решения
- Для Web: `shared.js` подключается в `index.html` вручную
- Для Material3 на Web: нужен `CompositionLocalProvider` + `platformFontFamilyResolver()`
- Development сервер: Python `http.server` или `jsBrowserDevelopmentRun`