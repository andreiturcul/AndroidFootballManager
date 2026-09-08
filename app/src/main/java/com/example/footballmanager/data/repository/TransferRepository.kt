package com.example.footballmanager.data.repository

import com.example.footballmanager.data.local.dao.TransferDao
import com.example.footballmanager.data.local.dao.TransferVotingDao
import com.example.footballmanager.data.local.dao.UserTransferProposalDao
import com.example.footballmanager.data.local.dao.UserTransferProposalVotingDao
import com.example.footballmanager.data.local.entities.Player
import com.example.footballmanager.data.local.entities.PlayerTeam
import com.example.footballmanager.data.local.entities.Transfer
import com.example.footballmanager.data.local.entities.TransferVoting
import com.example.footballmanager.data.local.entities.UserTransferProposal
import com.example.footballmanager.data.local.entities.UserTransferProposalVoting
import kotlinx.coroutines.flow.Flow

class TransferRepository(
    private val transferDao: TransferDao,
    private val transferVotingDao: TransferVotingDao,
    private val proposalDao: UserTransferProposalDao,
    private val proposalVotingDao: UserTransferProposalVotingDao
) {
    fun observeTransfers(): Flow<List<Transfer>> = transferDao.observeAll()
    fun observeProposals(): Flow<List<UserTransferProposal>> = proposalDao.observeAll()

    suspend fun createTransfer(playerId: Long, fromTeamId: Long?, toTeamId: Long?, price: Double) {
        transferDao.insert(
            Transfer(playerId = playerId, fromTeamId = fromTeamId, toTeamId = toTeamId, price = price, date = System.currentTimeMillis())
        )
    }

    suspend fun voteTransfer(transferId: Long, userId: Long, upvote: Boolean) {
        val existing = transferVotingDao.findVote(transferId, userId)
        if (existing == null) {
            transferVotingDao.insert(
                TransferVoting(
                    transferId = transferId,
                    upVotes = if (upvote) 1 else 0,
                    downVotes = if (upvote) 0 else 1,
                    userId = userId,
                    userTeamId = null
                )
            )
            transferDao.addVotes(transferId, if (upvote) 1 else -1)
        }
    }

    suspend fun proposeTransfer(userId: Long, playerId: Long, fromTeamId: Long?, toTeamId: Long?) {
        proposalDao.insert(UserTransferProposal(userId = userId, playerId = playerId, fromTeamId = fromTeamId, toTeamId = toTeamId))
    }

    suspend fun voteProposal(proposalId: Long, userId: Long, upvote: Boolean) {
        val existing = proposalVotingDao.findVote(proposalId, userId)
        if (existing == null) {
            proposalVotingDao.insert(UserTransferProposalVoting(userId = userId, proposalId = proposalId))
            proposalDao.addVotes(proposalId, if (upvote) 1 else -1)
        }
    }

    suspend fun seedIfEmpty(players: List<Player>, teams: List<PlayerTeam>) {
        if (players.isEmpty() || teams.isEmpty()) return

        fun getPlayer(name: String) = players.find { it.name.equals(name, ignoreCase = true) }
        fun getTeam(name: String) = teams.find { it.name.equals(name, ignoreCase = true) }

        if (transferDao.count() == 0) {
            val mbappe = getPlayer("Kylian Mbappé")
            val psg = getTeam("PSG")
            val real = getTeam("Real Madrid")

            if (mbappe != null && real != null) {
                createTransfer(
                    playerId = mbappe.id,
                    fromTeamId = psg?.id,
                    toTeamId = real.id,
                    price = 180.0
                )
            }
        }

        if (proposalDao.count() == 0) {
            val haaland = getPlayer("Erling Haaland")
            val manCity = getTeam("Manchester City")
            val real = getTeam("Real Madrid")
            val barca = getTeam("Barcelona")
            val deBruyne = getPlayer("Kevin De Bruyne")

            if (haaland != null && real != null) {
                proposeTransfer(
                    userId = 1L,
                    playerId = haaland.id,
                    fromTeamId = manCity?.id,
                    toTeamId = real.id
                )
            }

            if (deBruyne != null && barca != null) {
                proposeTransfer(
                    userId = 1L,
                    playerId = deBruyne.id,
                    fromTeamId = manCity?.id,
                    toTeamId = barca.id
                )
            }
        }
    }
}
