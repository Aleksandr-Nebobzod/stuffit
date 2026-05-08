package top.smartable.stuffit

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform