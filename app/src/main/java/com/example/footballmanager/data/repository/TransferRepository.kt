package com.example.footballmanager.data.repository

import com.example.footballmanager.data.local.dao.TransferDao
import com.example.footballmanager.data.local.dao.TransferVotingDao
import com.example.footballmanager.data.local.dao.UserTransferProposalDao
import com.example.footballmanager.data.local.dao.UserTransferProposalVotingDao
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
}
