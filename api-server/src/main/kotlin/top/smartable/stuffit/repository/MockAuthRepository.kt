package top.smartable.stuffit.repository

class MockAuthRepository {
    private val users = mutableMapOf<String, String>() // email -> password

    fun register(email: String, password: String): Boolean {
        return if (users.containsKey(email)) false
        else {
            users[email] = password
            true
        }
    }

    fun login(email: String, password: String): Boolean {
        return users[email] == password
    }
}