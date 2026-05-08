package top.smartable.stuffit.model

import kotlinx.datetime.Clock
import kotlinx.datetime.Instant
import kotlinx.serialization.Serializable

@Serializable
data class User(
    val id: String,
    val email: String,
    val displayName: String? = null,
    val createdAt: Instant = Clock.System.now(),
    val lastLoginAt: Instant = Clock.System.now(),
    val settings: UserSettings = UserSettings(),
    val migrationFromSchulteId: String? = null
)

@Serializable
data class UserSettings(
    val defaultCurrency: Currency = Currency.RUB,
    val theme: Theme = Theme.AUTO,
    val listViewMode: ListMode = ListMode.GRID,
    val notificationsEnabled: Boolean = false,
    val language: String = "ru"
)

enum class Currency { RUB, USD, EUR }
enum class Theme { LIGHT, DARK, AUTO }
enum class ListMode { GRID, LIST }