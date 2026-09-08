package com.example.footballmanager.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.footballmanager.data.local.dao.*
import com.example.footballmanager.data.local.entities.*

@Database(
    entities = [
        Player::class,
        PlayerTeam::class,
        Transfer::class,
        TransferVoting::class,
        User::class,
        UserTeam::class,
        UserTeamPlayer::class,
        UserTeamVoting::class,
        UserTransferProposal::class,
        UserTransferProposalVoting::class,
        TeamFormation::class,
        FormationPosition::class,
        Tactic::class,
        Event::class,
        Vote::class
    ],
    version = 1,
    exportSchema = false
)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {

    abstract fun playerDao(): PlayerDao
    abstract fun playerTeamDao(): PlayerTeamDao
    abstract fun transferDao(): TransferDao
    abstract fun transferVotingDao(): TransferVotingDao
    abstract fun userDao(): UserDao
    abstract fun userTeamDao(): UserTeamDao
    abstract fun userTeamPlayerDao(): UserTeamPlayerDao
    abstract fun userTeamVotingDao(): UserTeamVotingDao
    abstract fun userTransferProposalDao(): UserTransferProposalDao
    abstract fun userTransferProposalVotingDao(): UserTransferProposalVotingDao
    abstract fun teamFormationDao(): TeamFormationDao
    abstract fun formationPositionDao(): FormationPositionDao
    abstract fun tacticDao(): TacticDao
    abstract fun eventDao(): EventDao
    abstract fun voteDao(): VoteDao

    companion object {
        @Volatile private var INSTANCE: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase =
            INSTANCE ?: synchronized(this) {
                INSTANCE ?: Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "football_manager.db"
                )
                    .fallbackToDestructiveMigration()
                    .build().also { INSTANCE = it }
            }
    }
}
