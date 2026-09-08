package com.example.footballmanager.util

import java.security.MessageDigest

object PasswordUtils {
    /** Simple SHA-256 hashing so plaintext passwords are never stored in Room. */
    fun hash(password: String): String {
        val bytes = MessageDigest.getInstance("SHA-256").digest(password.toByteArray())
        return bytes.joinToString("") { "%02x".format(it) }
    }
}
