package com.example.footballmanager.data.remote.dto

import com.google.gson.annotations.SerializedName

// Response wrapper for https://www.thesportsdb.com/api/v1/json/3/search_all_teams.php?l=...
data class TeamsResponse(
    @SerializedName("teams") val teams: List<TeamDto>?
)

data class TeamDto(
    @SerializedName("idTeam") val idTeam: String?,
    @SerializedName("strTeam") val strTeam: String?,
    @SerializedName("strLeague") val strLeague: String?,
    @SerializedName("strTeamBadge") val strTeamBadge: String?,
    @SerializedName("intFormedYear") val intFormedYear: String?
)
