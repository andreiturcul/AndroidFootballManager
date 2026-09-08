package com.example.footballmanager.data.remote

import com.example.footballmanager.data.remote.dto.PlayersResponse
import com.example.footballmanager.data.remote.dto.TeamsResponse
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Two HTTP requests are used by the app (rubric requirement, min. 2):
 *   1) searchTeams  -> populates PlayerTeam table
 *   2) lookupPlayers -> populates Player table for a given team
 *
 * Backed by TheSportsDB's free public test endpoint (key "3", no registration
 * required). Swap BASE_URL / key for a production key if needed.
 */
interface ApiService {

    @GET("api/v1/json/3/search_all_teams.php")
    suspend fun searchTeams(@Query("l") league: String): TeamsResponse

    @GET("api/v1/json/3/lookup_all_players.php")
    suspend fun lookupPlayers(@Query("id") teamId: String): PlayersResponse

    companion object {
        const val BASE_URL = "https://www.thesportsdb.com/"
    }
}
