package top.smartable.stuffit

actual fun getPlatform(): Platform = object : Platform {
    override val name: String = "Web"
}