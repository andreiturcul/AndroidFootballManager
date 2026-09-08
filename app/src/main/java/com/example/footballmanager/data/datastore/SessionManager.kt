package com.example.footballmanager.data.datastore

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.longPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

private val Context.dataStore by preferencesDataStore(name = "session_prefs")

/**
 * Satisfies the "data stored in SharedPreferences/DataStore" requirement.
 * Persists the logged-in user's id/name across app restarts.
 */
class SessionManager(private val context: Context) {

    companion object {
        private val KEY_USER_ID = longPreferencesKey("logged_in_user_id")
        private val KEY_USER_NAME = stringPreferencesKey("logged_in_user_name")
        private val KEY_LAST_LEAGUE = stringPreferencesKey("last_browsed_league")
    }

    val userIdFlow: Flow<Long?> = context.dataStore.data.map { it[KEY_USER_ID] }
    val userNameFlow: Flow<String?> = context.dataStore.data.map { it[KEY_USER_NAME] }
    val lastLeagueFlow: Flow<String?> = context.dataStore.data.map { it[KEY_LAST_LEAGUE] }

    suspend fun saveSession(userId: Long, name: String) {
        context.dataStore.edit { prefs ->
            prefs[KEY_USER_ID] = userId
            prefs[KEY_USER_NAME] = name
        }
    }

    suspend fun saveLastLeague(league: String) {
        context.dataStore.edit { prefs -> prefs[KEY_LAST_LEAGUE] = league }
    }

    suspend fun clearSession() {
        context.dataStore.edit { it.clear() }
    }
}
