package com.example.footballmanager.data.remote.dto

import com.google.gson.annotations.SerializedName

// Response wrapper for https://www.thesportsdb.com/api/v1/json/3/lookup_all_players.php?id=...
data class PlayersResponse(
    @SerializedName("player") val player: List<PlayerDto>?
)

data class PlayerDto(
    @SerializedName("idPlayer") val idPlayer: String?,
    @SerializedName("strPlayer") val strPlayer: String?,
    @SerializedName("strPosition") val strPosition: String?,
    @SerializedName("idTeam") val idTeam: String?,
    @SerializedName("strNationality") val strNationality: String?
)
