package com.example.footballmanager.data.repository

import com.example.footballmanager.data.local.dao.PlayerDao
import com.example.footballmanager.data.local.dao.PlayerTeamDao
import com.example.footballmanager.data.local.entities.Player
import com.example.footballmanager.data.local.entities.PlayerTeam
import com.example.footballmanager.data.remote.ApiService
import kotlinx.coroutines.flow.Flow

/**
 * Talks to the remote API (2 HTTP endpoints), deserializes the JSON and stores
 * the result into the local Room database, which the UI then observes.
 */
class FootballDataRepository(
    private val api: ApiService,
    private val playerTeamDao: PlayerTeamDao,
    private val playerDao: PlayerDao
) {
    fun observeTeams(): Flow<List<PlayerTeam>> = playerTeamDao.observeAll()
    fun observePlayers(): Flow<List<Player>> = playerDao.observeAll()
    fun observePlayersForTeam(teamId: Long): Flow<List<Player>> = playerDao.observeByTeam(teamId)

    private fun isNonPlayer(name: String, position: String?): Boolean {
        val text = "$name ${position.orEmpty()}".lowercase()
        val nonPlayerKeywords = listOf(
            "manager", "coach", "staff", "physio", "trainer", "scout",
            "analyst", "director", "doctor", "president", "assistant"
        )
        return nonPlayerKeywords.any { text.contains(it) }
    }

    private fun normalizePosition(rawPosition: String?): String {
        if (rawPosition.isNullOrBlank()) return "MID"
        val pos = rawPosition.lowercase()
        return when {
            pos.contains("goalkeeper") || pos.contains("keeper") || pos.contains("gk") -> "GK"
            pos.contains("defender") || pos.contains("back") || pos.contains("defence") || pos.contains("defense") || pos.contains("cb") || pos.contains("lb") || pos.contains("rb") -> "DEF"
            pos.contains("forward") || pos.contains("striker") || pos.contains("winger") || pos.contains("attack") || pos.contains("offence") || pos.contains("offense") || pos.contains("cf") || pos.contains("st") -> "FWD"
            pos.contains("midfield") || pos.contains("mid") || pos.contains("cm") || pos.contains("cam") || pos.contains("cdm") -> "MID"
            else -> "MID"
        }
    }

    suspend fun cleanupAndSeed() {
        playerTeamDao.deleteDuplicates()
        playerDao.deleteDuplicates()

        playerDao.deleteNonPlayers()

        playerDao.normalizeGk()
        playerDao.normalizeDef()
        playerDao.normalizeFwd()
        playerDao.normalizeMid()

        seedTopTeamsAndPlayers()
    }

    private suspend fun seedTopTeamsAndPlayers() {
        val existingTeams = playerTeamDao.getAllOnce()
        val defaultTeams = listOf(
            // Spanish La Liga
            PlayerTeam(name = "Real Madrid", league = "Spanish La Liga", ucl = true, cup = true, badgeUrl = "https://www.thesportsdb.com/images/media/team/badge/831a2v1678809072.png"),
            PlayerTeam(name = "Barcelona", league = "Spanish La Liga", ucl = true, cup = true, badgeUrl = "https://www.thesportsdb.com/images/media/team/badge/34p0e81678809095.png"),
            PlayerTeam(name = "Atletico Madrid", league = "Spanish La Liga", ucl = true, cup = true, badgeUrl = "https://www.thesportsdb.com/images/media/team/badge/1vptw11678809110.png"),
            PlayerTeam(name = "Sevilla", league = "Spanish La Liga", ucl = false, cup = true, badgeUrl = "https://www.thesportsdb.com/images/media/team/badge/1yptw11678809125.png"),
            PlayerTeam(name = "Real Sociedad", league = "Spanish La Liga", ucl = false, cup = true, badgeUrl = "https://www.thesportsdb.com/images/media/team/badge/2yptw11678809140.png"),

            // English Premier League
            PlayerTeam(name = "Manchester City", league = "English Premier League", ucl = true, cup = true, badgeUrl = "https://www.thesportsdb.com/images/media/team/badge/v2233q1678809180.png"),
            PlayerTeam(name = "Arsenal", league = "English Premier League", ucl = true, cup = true, badgeUrl = "https://www.thesportsdb.com/images/media/team/badge/uy234b1678809160.png"),
            PlayerTeam(name = "Liverpool", league = "English Premier League", ucl = true, cup = true, badgeUrl = "https://www.thesportsdb.com/images/media/team/badge/28i3a11678809170.png"),
            PlayerTeam(name = "Manchester United", league = "English Premier League", ucl = false, cup = true, badgeUrl = "https://www.thesportsdb.com/images/media/team/badge/xzppuq1678809190.png"),
            PlayerTeam(name = "Chelsea", league = "English Premier League", ucl = false, cup = true, badgeUrl = "https://www.thesportsdb.com/images/media/team/badge/21323i1678809150.png"),
            PlayerTeam(name = "Tottenham Hotspur", league = "English Premier League", ucl = false, cup = true, badgeUrl = "https://www.thesportsdb.com/images/media/team/badge/14298i1678809145.png"),
            PlayerTeam(name = "Aston Villa", league = "English Premier League", ucl = true, cup = true, badgeUrl = "https://www.thesportsdb.com/images/media/team/badge/45213i1678809135.png"),

            // German Bundesliga
            PlayerTeam(name = "Bayern Munich", league = "German Bundesliga", ucl = true, cup = true, badgeUrl = "https://www.thesportsdb.com/images/media/team/badge/19329i1678809200.png"),
            PlayerTeam(name = "Borussia Dortmund", league = "German Bundesliga", ucl = true, cup = true, badgeUrl = "https://www.thesportsdb.com/images/media/team/badge/18321i1678809210.png"),
            PlayerTeam(name = "Bayer Leverkusen", league = "German Bundesliga", ucl = true, cup = true, badgeUrl = "https://www.thesportsdb.com/images/media/team/badge/29381i1678809215.png"),

            // Italian Serie A
            PlayerTeam(name = "Inter Milan", league = "Italian Serie A", ucl = true, cup = true, badgeUrl = "https://www.thesportsdb.com/images/media/team/badge/47281i1678809240.png"),
            PlayerTeam(name = "AC Milan", league = "Italian Serie A", ucl = false, cup = true, badgeUrl = "https://www.thesportsdb.com/images/media/team/badge/38291i1678809250.png"),
            PlayerTeam(name = "Juventus", league = "Italian Serie A", ucl = true, cup = true, badgeUrl = "https://www.thesportsdb.com/images/media/team/badge/59281i1678809260.png"),
            PlayerTeam(name = "Napoli", league = "Italian Serie A", ucl = false, cup = true, badgeUrl = "https://www.thesportsdb.com/images/media/team/badge/69281i1678809270.png"),

            // French Ligue 1
            PlayerTeam(name = "PSG", league = "French Ligue 1", ucl = true, cup = true, badgeUrl = "https://www.thesportsdb.com/images/media/team/badge/27448i1678809220.png"),
            PlayerTeam(name = "Monaco", league = "French Ligue 1", ucl = true, cup = true, badgeUrl = "https://www.thesportsdb.com/images/media/team/badge/38491i1678809230.png")
        )

        for (team in defaultTeams) {
            if (existingTeams.none { it.name.equals(team.name, ignoreCase = true) }) {
                playerTeamDao.insert(team)
            }
        }

        val allTeams = playerTeamDao.getAllOnce()
        fun getTeamId(name: String): Long? = allTeams.find { it.name.equals(name, ignoreCase = true) }?.id

        val starPlayers = listOf(
            Player(name = "Erling Haaland", position = "FWD", price = 180.0, teamId = getTeamId("Manchester City")),
            Player(name = "Kylian Mbappé", position = "FWD", price = 180.0, teamId = getTeamId("Real Madrid")),
            Player(name = "Vinícius Júnior", position = "FWD", price = 170.0, teamId = getTeamId("Real Madrid")),
            Player(name = "Jude Bellingham", position = "MID", price = 180.0, teamId = getTeamId("Real Madrid")),
            Player(name = "Lamine Yamal", position = "FWD", price = 150.0, teamId = getTeamId("Barcelona")),
            Player(name = "Kevin De Bruyne", position = "MID", price = 100.0, teamId = getTeamId("Manchester City")),
            Player(name = "Mohamed Salah", position = "FWD", price = 90.0, teamId = getTeamId("Liverpool")),
            Player(name = "Harry Kane", position = "FWD", price = 110.0, teamId = getTeamId("Bayern Munich")),
            Player(name = "Rodri", position = "MID", price = 130.0, teamId = getTeamId("Manchester City")),
            Player(name = "Bukayo Saka", position = "FWD", price = 120.0, teamId = getTeamId("Arsenal")),
            Player(name = "Phil Foden", position = "MID", price = 110.0, teamId = getTeamId("Manchester City")),
            Player(name = "Pedri", position = "MID", price = 90.0, teamId = getTeamId("Barcelona")),
            Player(name = "Declan Rice", position = "MID", price = 100.0, teamId = getTeamId("Arsenal")),
            Player(name = "Lautaro Martínez", position = "FWD", price = 110.0, teamId = getTeamId("Inter Milan")),
            Player(name = "Virgil van Dijk", position = "DEF", price = 75.0, teamId = getTeamId("Liverpool")),
            Player(name = "Rúben Dias", position = "DEF", price = 80.0, teamId = getTeamId("Manchester City")),
            Player(name = "William Saliba", position = "DEF", price = 80.0, teamId = getTeamId("Arsenal")),
            Player(name = "Trent Alexander-Arnold", position = "DEF", price = 70.0, teamId = getTeamId("Liverpool")),
            Player(name = "Achraf Hakimi", position = "DEF", price = 65.0, teamId = getTeamId("PSG")),
            Player(name = "Federico Valverde", position = "MID", price = 120.0, teamId = getTeamId("Real Madrid")),
            Player(name = "Thibaut Courtois", position = "GK", price = 60.0, teamId = getTeamId("Real Madrid")),
            Player(name = "Alisson Becker", position = "GK", price = 55.0, teamId = getTeamId("Liverpool")),
            Player(name = "Gianluigi Donnarumma", position = "GK", price = 40.0, teamId = getTeamId("PSG")),
            Player(name = "Marc-André ter Stegen", position = "GK", price = 50.0, teamId = getTeamId("Barcelona")),
            Player(name = "Robert Lewandowski", position = "FWD", price = 40.0, teamId = getTeamId("Barcelona"))
        )

        val existingPlayers = playerDao.getAllOnce()
        val existingNames = existingPlayers.map { it.name.lowercase() }.toSet()

        val playersToInsert = starPlayers.filter { it.name.lowercase() !in existingNames }
        if (playersToInsert.isNotEmpty()) {
            playerDao.insertAll(playersToInsert)
        }
    }

    /** HTTP request #1: fetch all teams for a league and cache them in Room. */
    suspend fun refreshTeams(league: String): Result<Int> = runCatching {
        val cleanLeague = league.replace('_', ' ')
        val response = api.searchTeams(cleanLeague)
        val dtos = response.teams.orEmpty()
        val existingTeams = playerTeamDao.getAllOnce()
        val existingByRemoteId = existingTeams.mapNotNull { t -> t.remoteId?.let { it to t } }.toMap()
        val existingByName = existingTeams.associateBy { it.name.lowercase() }

        val entities = dtos.mapNotNull { dto ->
            val name = dto.strTeam ?: return@mapNotNull null
            val existing = dto.idTeam?.let { existingByRemoteId[it] } ?: existingByName[name.lowercase()]
            PlayerTeam(
                id = existing?.id ?: 0,
                name = name,
                league = dto.strLeague ?: cleanLeague,
                ucl = existing?.ucl ?: false,
                cup = existing?.cup ?: false,
                votes = existing?.votes ?: 0,
                badgeUrl = dto.strTeamBadge ?: existing?.badgeUrl,
                remoteId = dto.idTeam ?: existing?.remoteId
            )
        }
        if (entities.isNotEmpty()) playerTeamDao.insertAll(entities)
        entities.size
    }

    /** HTTP request #2: fetch the squad for a given remote team id and cache it in Room. */
    suspend fun refreshPlayers(remoteTeamId: String, localTeamId: Long): Result<Int> = runCatching {
        val response = api.lookupPlayers(remoteTeamId)
        val dtos = response.player.orEmpty()
        val existingPlayers = playerDao.getAllOnce()
        val existingByRemoteId = existingPlayers.mapNotNull { p -> p.remoteId?.let { it to p } }.toMap()
        val existingByNameAndTeam = existingPlayers.associateBy { "${it.name}_${it.teamId}" }

        val entities = dtos.mapNotNull { dto ->
            val name = dto.strPlayer ?: return@mapNotNull null
            if (isNonPlayer(name, dto.strPosition)) return@mapNotNull null

            val normalizedPos = normalizePosition(dto.strPosition)
            val key = "${name}_$localTeamId"
            val existing = dto.idPlayer?.let { existingByRemoteId[it] } ?: existingByNameAndTeam[key]
            Player(
                id = existing?.id ?: 0,
                name = name,
                nickname = existing?.nickname,
                position = normalizedPos,
                teamId = localTeamId,
                price = existing?.price ?: (20..120).random().toDouble(),
                remoteId = dto.idPlayer ?: existing?.remoteId
            )
        }
        if (entities.isNotEmpty()) playerDao.insertAll(entities)
        entities.size
    }

    suspend fun addManualPlayer(player: Player) = playerDao.insert(player)

    suspend fun getPlayer(id: Long): Player? = playerDao.getById(id)
    suspend fun getTeam(id: Long): PlayerTeam? = playerTeamDao.getById(id)
}
