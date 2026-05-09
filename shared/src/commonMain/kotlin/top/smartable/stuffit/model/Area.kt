package top.smartable.stuffit.model

import kotlinx.serialization.Serializable


@Serializable
data class Area(
    val id: String,
    val name: String,
    val displayName: String? = null
)
