package top.smartable.stuffit.repository

import top.smartable.stuffit.model.User
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

// Временная заглушка. Позже заменим на реальную Firebase
class AuthRepository {

    private val _authState = MutableStateFlow<AuthState>(AuthState.Unauthenticated)
    val authState: Flow<AuthState> = _authState.asStateFlow()

    val currentUser: Flow<User?> = MutableStateFlow(null)
    val isAuthenticated: Flow<Boolean> = MutableStateFlow(false)

    suspend fun register(email: String, password: String): Result<User> {
        // Заглушка: имитируем успешную регистрацию
        return if (email.isNotBlank() && password.length >= 6) {
            val fakeUser = User(
                id = "fake_id_123",
                email = email,
                displayName = null
            )
            _authState.value = AuthState.Authenticated(fakeUser)
            Result.success(fakeUser)
        } else {
            Result.failure(Exception("Invalid email or password"))
        }
    }

    suspend fun login(email: String, password: String): Result<User> {
        return if (email.isNotBlank() && password.isNotBlank()) {
            val fakeUser = User(
                id = "fake_id_123",
                email = email,
                displayName = null
            )
            _authState.value = AuthState.Authenticated(fakeUser)
            Result.success(fakeUser)
        } else {
            Result.failure(Exception("Invalid credentials"))
        }
    }

    suspend fun logout(): Result<Unit> {
        _authState.value = AuthState.Unauthenticated
        return Result.success(Unit)
    }

    suspend fun deleteAccount(): Result<Unit> {
        _authState.value = AuthState.Unauthenticated
        return Result.success(Unit)
    }

    suspend fun updateSettings(settings: top.smartable.stuffit.model.UserSettings): Result<Unit> {
        return Result.success(Unit)
    }

    suspend fun resetPassword(email: String): Result<Unit> {
        return Result.success(Unit)
    }
}

sealed class AuthState {
    data object Loading : AuthState()
    data object Unauthenticated : AuthState()
    data class Authenticated(val user: User) : AuthState()
}