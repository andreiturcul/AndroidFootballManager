package com.example.footballmanager.data.repository

import com.example.footballmanager.data.local.dao.UserDao
import com.example.footballmanager.data.local.entities.User
import com.example.footballmanager.util.PasswordUtils

class AuthRepository(private val userDao: UserDao) {

    suspend fun register(name: String, email: String, password: String): Result<User> {
        if (name.isBlank() || email.isBlank() || password.length < 4) {
            return Result.failure(IllegalArgumentException("Please fill all fields (password min. 4 chars)."))
        }
        val existing = userDao.findByEmail(email.trim().lowercase())
        if (existing != null) {
            return Result.failure(IllegalStateException("An account with this email already exists."))
        }
        val user = User(
            name = name.trim(),
            email = email.trim().lowercase(),
            passwordHash = PasswordUtils.hash(password)
        )
        val id = userDao.insert(user)
        return Result.success(user.copy(id = id))
    }

    suspend fun login(email: String, password: String): Result<User> {
        val user = userDao.findByEmail(email.trim().lowercase())
            ?: return Result.failure(NoSuchElementException("No account found for this email."))
        return if (user.passwordHash == PasswordUtils.hash(password)) {
            Result.success(user)
        } else {
            Result.failure(SecurityException("Incorrect password."))
        }
    }

    suspend fun getUser(id: Long): User? = userDao.findById(id)
}
