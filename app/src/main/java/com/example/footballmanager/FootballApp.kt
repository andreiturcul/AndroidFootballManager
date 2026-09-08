package com.example.footballmanager

import android.app.Application
import com.example.footballmanager.data.local.AppDatabase
import com.example.footballmanager.data.datastore.SessionManager
import com.example.footballmanager.data.remote.RetrofitInstance
import com.example.footballmanager.data.repository.AuthRepository
import com.example.footballmanager.data.repository.FootballDataRepository
import com.example.footballmanager.data.repository.TransferRepository
import com.example.footballmanager.data.repository.UserTeamRepository

/**
 * Simple, dependency-free service locator. Keeps the sample easy to read
 * without pulling in Hilt/Koin, while still giving every screen a single
 * shared instance of the database, session manager and repositories.
 */
class FootballApp : Application() {

    val database: AppDatabase by lazy { AppDatabase.getInstance(this) }
    val sessionManager: SessionManager by lazy { SessionManager(this) }

    val authRepository: AuthRepository by lazy { AuthRepository(database.userDao()) }

    val footballDataRepository: FootballDataRepository by lazy {
        FootballDataRepository(RetrofitInstance.api, database.playerTeamDao(), database.playerDao())
    }

    val transferRepository: TransferRepository by lazy {
        TransferRepository(
            database.transferDao(),
            database.transferVotingDao(),
            database.userTransferProposalDao(),
            database.userTransferProposalVotingDao()
        )
    }

    val userTeamRepository: UserTeamRepository by lazy {
        UserTeamRepository(
            database.userTeamDao(),
            database.userTeamPlayerDao(),
            database.teamFormationDao(),
            database.formationPositionDao(),
            database.tacticDao(),
            database.userTeamVotingDao()
        )
    }

    override fun onCreate() {
        super.onCreate()
        INSTANCE = this
    }

    companion object {
        lateinit var INSTANCE: FootballApp
            private set
    }
}
