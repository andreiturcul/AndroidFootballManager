package com.example.footballmanager.data.local;

import androidx.annotation.NonNull;
import androidx.room.DatabaseConfiguration;
import androidx.room.InvalidationTracker;
import androidx.room.RoomDatabase;
import androidx.room.RoomOpenHelper;
import androidx.room.migration.AutoMigrationSpec;
import androidx.room.migration.Migration;
import androidx.room.util.DBUtil;
import androidx.room.util.TableInfo;
import androidx.sqlite.db.SupportSQLiteDatabase;
import androidx.sqlite.db.SupportSQLiteOpenHelper;
import com.example.footballmanager.data.local.dao.EventDao;
import com.example.footballmanager.data.local.dao.EventDao_Impl;
import com.example.footballmanager.data.local.dao.FormationPositionDao;
import com.example.footballmanager.data.local.dao.FormationPositionDao_Impl;
import com.example.footballmanager.data.local.dao.PlayerDao;
import com.example.footballmanager.data.local.dao.PlayerDao_Impl;
import com.example.footballmanager.data.local.dao.PlayerTeamDao;
import com.example.footballmanager.data.local.dao.PlayerTeamDao_Impl;
import com.example.footballmanager.data.local.dao.TacticDao;
import com.example.footballmanager.data.local.dao.TacticDao_Impl;
import com.example.footballmanager.data.local.dao.TeamFormationDao;
import com.example.footballmanager.data.local.dao.TeamFormationDao_Impl;
import com.example.footballmanager.data.local.dao.TransferDao;
import com.example.footballmanager.data.local.dao.TransferDao_Impl;
import com.example.footballmanager.data.local.dao.TransferVotingDao;
import com.example.footballmanager.data.local.dao.TransferVotingDao_Impl;
import com.example.footballmanager.data.local.dao.UserDao;
import com.example.footballmanager.data.local.dao.UserDao_Impl;
import com.example.footballmanager.data.local.dao.UserTeamDao;
import com.example.footballmanager.data.local.dao.UserTeamDao_Impl;
import com.example.footballmanager.data.local.dao.UserTeamPlayerDao;
import com.example.footballmanager.data.local.dao.UserTeamPlayerDao_Impl;
import com.example.footballmanager.data.local.dao.UserTeamVotingDao;
import com.example.footballmanager.data.local.dao.UserTeamVotingDao_Impl;
import com.example.footballmanager.data.local.dao.UserTransferProposalDao;
import com.example.footballmanager.data.local.dao.UserTransferProposalDao_Impl;
import com.example.footballmanager.data.local.dao.UserTransferProposalVotingDao;
import com.example.footballmanager.data.local.dao.UserTransferProposalVotingDao_Impl;
import com.example.footballmanager.data.local.dao.VoteDao;
import com.example.footballmanager.data.local.dao.VoteDao_Impl;
import java.lang.Class;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

@SuppressWarnings({"unchecked", "deprecation"})
public final class AppDatabase_Impl extends AppDatabase {
  private volatile PlayerDao _playerDao;

  private volatile PlayerTeamDao _playerTeamDao;

  private volatile TransferDao _transferDao;

  private volatile TransferVotingDao _transferVotingDao;

  private volatile UserDao _userDao;

  private volatile UserTeamDao _userTeamDao;

  private volatile UserTeamPlayerDao _userTeamPlayerDao;

  private volatile UserTeamVotingDao _userTeamVotingDao;

  private volatile UserTransferProposalDao _userTransferProposalDao;

  private volatile UserTransferProposalVotingDao _userTransferProposalVotingDao;

  private volatile TeamFormationDao _teamFormationDao;

  private volatile FormationPositionDao _formationPositionDao;

  private volatile TacticDao _tacticDao;

  private volatile EventDao _eventDao;

  private volatile VoteDao _voteDao;

  @Override
  @NonNull
  protected SupportSQLiteOpenHelper createOpenHelper(@NonNull final DatabaseConfiguration config) {
    final SupportSQLiteOpenHelper.Callback _openCallback = new RoomOpenHelper(config, new RoomOpenHelper.Delegate(1) {
      @Override
      public void createAllTables(@NonNull final SupportSQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS `players` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `name` TEXT NOT NULL, `nickname` TEXT, `position` TEXT NOT NULL, `teamId` INTEGER, `price` REAL NOT NULL, `remoteId` TEXT)");
        db.execSQL("CREATE TABLE IF NOT EXISTS `player_teams` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `name` TEXT NOT NULL, `ucl` INTEGER NOT NULL, `league` TEXT, `cup` INTEGER NOT NULL, `votes` INTEGER NOT NULL, `badgeUrl` TEXT, `remoteId` TEXT)");
        db.execSQL("CREATE TABLE IF NOT EXISTS `transfers` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `playerId` INTEGER NOT NULL, `fromTeamId` INTEGER, `toTeamId` INTEGER, `price` REAL NOT NULL, `date` INTEGER NOT NULL, `votes` INTEGER NOT NULL)");
        db.execSQL("CREATE TABLE IF NOT EXISTS `transfer_votings` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `transferId` INTEGER NOT NULL, `upVotes` INTEGER NOT NULL, `downVotes` INTEGER NOT NULL, `userId` INTEGER NOT NULL, `userTeamId` INTEGER)");
        db.execSQL("CREATE TABLE IF NOT EXISTS `users` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `name` TEXT NOT NULL, `email` TEXT NOT NULL, `passwordHash` TEXT NOT NULL, `userTeamId` INTEGER, `favouritePlayerTeamId` INTEGER)");
        db.execSQL("CREATE TABLE IF NOT EXISTS `user_teams` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `name` TEXT NOT NULL, `formationId` INTEGER, `tacticId` INTEGER, `submitted` INTEGER NOT NULL, `ownerUserId` INTEGER NOT NULL)");
        db.execSQL("CREATE TABLE IF NOT EXISTS `user_team_players` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `userTeamId` INTEGER NOT NULL, `playerId` INTEGER NOT NULL, `slotNumber` INTEGER NOT NULL)");
        db.execSQL("CREATE TABLE IF NOT EXISTS `user_team_votings` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `userId` INTEGER NOT NULL, `userTeamId` INTEGER NOT NULL)");
        db.execSQL("CREATE TABLE IF NOT EXISTS `user_transfer_proposals` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `userId` INTEGER NOT NULL, `playerId` INTEGER NOT NULL, `fromTeamId` INTEGER, `toTeamId` INTEGER, `votes` INTEGER NOT NULL)");
        db.execSQL("CREATE TABLE IF NOT EXISTS `user_transfer_proposal_votings` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `userId` INTEGER NOT NULL, `proposalId` INTEGER NOT NULL)");
        db.execSQL("CREATE TABLE IF NOT EXISTS `team_formations` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `name` TEXT NOT NULL, `description` TEXT NOT NULL)");
        db.execSQL("CREATE TABLE IF NOT EXISTS `formation_positions` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `formationId` INTEGER NOT NULL, `slotNumber` INTEGER NOT NULL, `position` TEXT NOT NULL)");
        db.execSQL("CREATE TABLE IF NOT EXISTS `tactics` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `details` TEXT NOT NULL, `style` TEXT NOT NULL)");
        db.execSQL("CREATE TABLE IF NOT EXISTS `events` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `name` TEXT NOT NULL)");
        db.execSQL("CREATE TABLE IF NOT EXISTS `votes` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `eventId` INTEGER NOT NULL, `eventTypeId` INTEGER NOT NULL, `userId` INTEGER NOT NULL, `isUpvote` INTEGER NOT NULL)");
        db.execSQL("CREATE TABLE IF NOT EXISTS room_master_table (id INTEGER PRIMARY KEY,identity_hash TEXT)");
        db.execSQL("INSERT OR REPLACE INTO room_master_table (id,identity_hash) VALUES(42, '49ea92e372fa2424aa3ecc4845214756')");
      }

      @Override
      public void dropAllTables(@NonNull final SupportSQLiteDatabase db) {
        db.execSQL("DROP TABLE IF EXISTS `players`");
        db.execSQL("DROP TABLE IF EXISTS `player_teams`");
        db.execSQL("DROP TABLE IF EXISTS `transfers`");
        db.execSQL("DROP TABLE IF EXISTS `transfer_votings`");
        db.execSQL("DROP TABLE IF EXISTS `users`");
        db.execSQL("DROP TABLE IF EXISTS `user_teams`");
        db.execSQL("DROP TABLE IF EXISTS `user_team_players`");
        db.execSQL("DROP TABLE IF EXISTS `user_team_votings`");
        db.execSQL("DROP TABLE IF EXISTS `user_transfer_proposals`");
        db.execSQL("DROP TABLE IF EXISTS `user_transfer_proposal_votings`");
        db.execSQL("DROP TABLE IF EXISTS `team_formations`");
        db.execSQL("DROP TABLE IF EXISTS `formation_positions`");
        db.execSQL("DROP TABLE IF EXISTS `tactics`");
        db.execSQL("DROP TABLE IF EXISTS `events`");
        db.execSQL("DROP TABLE IF EXISTS `votes`");
        final List<? extends RoomDatabase.Callback> _callbacks = mCallbacks;
        if (_callbacks != null) {
          for (RoomDatabase.Callback _callback : _callbacks) {
            _callback.onDestructiveMigration(db);
          }
        }
      }

      @Override
      public void onCreate(@NonNull final SupportSQLiteDatabase db) {
        final List<? extends RoomDatabase.Callback> _callbacks = mCallbacks;
        if (_callbacks != null) {
          for (RoomDatabase.Callback _callback : _callbacks) {
            _callback.onCreate(db);
          }
        }
      }

      @Override
      public void onOpen(@NonNull final SupportSQLiteDatabase db) {
        mDatabase = db;
        internalInitInvalidationTracker(db);
        final List<? extends RoomDatabase.Callback> _callbacks = mCallbacks;
        if (_callbacks != null) {
          for (RoomDatabase.Callback _callback : _callbacks) {
            _callback.onOpen(db);
          }
        }
      }

      @Override
      public void onPreMigrate(@NonNull final SupportSQLiteDatabase db) {
        DBUtil.dropFtsSyncTriggers(db);
      }

      @Override
      public void onPostMigrate(@NonNull final SupportSQLiteDatabase db) {
      }

      @Override
      @NonNull
      public RoomOpenHelper.ValidationResult onValidateSchema(
          @NonNull final SupportSQLiteDatabase db) {
        final HashMap<String, TableInfo.Column> _columnsPlayers = new HashMap<String, TableInfo.Column>(7);
        _columnsPlayers.put("id", new TableInfo.Column("id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsPlayers.put("name", new TableInfo.Column("name", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsPlayers.put("nickname", new TableInfo.Column("nickname", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsPlayers.put("position", new TableInfo.Column("position", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsPlayers.put("teamId", new TableInfo.Column("teamId", "INTEGER", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsPlayers.put("price", new TableInfo.Column("price", "REAL", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsPlayers.put("remoteId", new TableInfo.Column("remoteId", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysPlayers = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesPlayers = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoPlayers = new TableInfo("players", _columnsPlayers, _foreignKeysPlayers, _indicesPlayers);
        final TableInfo _existingPlayers = TableInfo.read(db, "players");
        if (!_infoPlayers.equals(_existingPlayers)) {
          return new RoomOpenHelper.ValidationResult(false, "players(com.example.footballmanager.data.local.entities.Player).\n"
                  + " Expected:\n" + _infoPlayers + "\n"
                  + " Found:\n" + _existingPlayers);
        }
        final HashMap<String, TableInfo.Column> _columnsPlayerTeams = new HashMap<String, TableInfo.Column>(8);
        _columnsPlayerTeams.put("id", new TableInfo.Column("id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsPlayerTeams.put("name", new TableInfo.Column("name", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsPlayerTeams.put("ucl", new TableInfo.Column("ucl", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsPlayerTeams.put("league", new TableInfo.Column("league", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsPlayerTeams.put("cup", new TableInfo.Column("cup", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsPlayerTeams.put("votes", new TableInfo.Column("votes", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsPlayerTeams.put("badgeUrl", new TableInfo.Column("badgeUrl", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsPlayerTeams.put("remoteId", new TableInfo.Column("remoteId", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysPlayerTeams = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesPlayerTeams = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoPlayerTeams = new TableInfo("player_teams", _columnsPlayerTeams, _foreignKeysPlayerTeams, _indicesPlayerTeams);
        final TableInfo _existingPlayerTeams = TableInfo.read(db, "player_teams");
        if (!_infoPlayerTeams.equals(_existingPlayerTeams)) {
          return new RoomOpenHelper.ValidationResult(false, "player_teams(com.example.footballmanager.data.local.entities.PlayerTeam).\n"
                  + " Expected:\n" + _infoPlayerTeams + "\n"
                  + " Found:\n" + _existingPlayerTeams);
        }
        final HashMap<String, TableInfo.Column> _columnsTransfers = new HashMap<String, TableInfo.Column>(7);
        _columnsTransfers.put("id", new TableInfo.Column("id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsTransfers.put("playerId", new TableInfo.Column("playerId", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsTransfers.put("fromTeamId", new TableInfo.Column("fromTeamId", "INTEGER", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsTransfers.put("toTeamId", new TableInfo.Column("toTeamId", "INTEGER", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsTransfers.put("price", new TableInfo.Column("price", "REAL", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsTransfers.put("date", new TableInfo.Column("date", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsTransfers.put("votes", new TableInfo.Column("votes", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysTransfers = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesTransfers = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoTransfers = new TableInfo("transfers", _columnsTransfers, _foreignKeysTransfers, _indicesTransfers);
        final TableInfo _existingTransfers = TableInfo.read(db, "transfers");
        if (!_infoTransfers.equals(_existingTransfers)) {
          return new RoomOpenHelper.ValidationResult(false, "transfers(com.example.footballmanager.data.local.entities.Transfer).\n"
                  + " Expected:\n" + _infoTransfers + "\n"
                  + " Found:\n" + _existingTransfers);
        }
        final HashMap<String, TableInfo.Column> _columnsTransferVotings = new HashMap<String, TableInfo.Column>(6);
        _columnsTransferVotings.put("id", new TableInfo.Column("id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsTransferVotings.put("transferId", new TableInfo.Column("transferId", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsTransferVotings.put("upVotes", new TableInfo.Column("upVotes", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsTransferVotings.put("downVotes", new TableInfo.Column("downVotes", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsTransferVotings.put("userId", new TableInfo.Column("userId", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsTransferVotings.put("userTeamId", new TableInfo.Column("userTeamId", "INTEGER", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysTransferVotings = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesTransferVotings = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoTransferVotings = new TableInfo("transfer_votings", _columnsTransferVotings, _foreignKeysTransferVotings, _indicesTransferVotings);
        final TableInfo _existingTransferVotings = TableInfo.read(db, "transfer_votings");
        if (!_infoTransferVotings.equals(_existingTransferVotings)) {
          return new RoomOpenHelper.ValidationResult(false, "transfer_votings(com.example.footballmanager.data.local.entities.TransferVoting).\n"
                  + " Expected:\n" + _infoTransferVotings + "\n"
                  + " Found:\n" + _existingTransferVotings);
        }
        final HashMap<String, TableInfo.Column> _columnsUsers = new HashMap<String, TableInfo.Column>(6);
        _columnsUsers.put("id", new TableInfo.Column("id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsUsers.put("name", new TableInfo.Column("name", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsUsers.put("email", new TableInfo.Column("email", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsUsers.put("passwordHash", new TableInfo.Column("passwordHash", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsUsers.put("userTeamId", new TableInfo.Column("userTeamId", "INTEGER", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsUsers.put("favouritePlayerTeamId", new TableInfo.Column("favouritePlayerTeamId", "INTEGER", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysUsers = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesUsers = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoUsers = new TableInfo("users", _columnsUsers, _foreignKeysUsers, _indicesUsers);
        final TableInfo _existingUsers = TableInfo.read(db, "users");
        if (!_infoUsers.equals(_existingUsers)) {
          return new RoomOpenHelper.ValidationResult(false, "users(com.example.footballmanager.data.local.entities.User).\n"
                  + " Expected:\n" + _infoUsers + "\n"
                  + " Found:\n" + _existingUsers);
        }
        final HashMap<String, TableInfo.Column> _columnsUserTeams = new HashMap<String, TableInfo.Column>(6);
        _columnsUserTeams.put("id", new TableInfo.Column("id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsUserTeams.put("name", new TableInfo.Column("name", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsUserTeams.put("formationId", new TableInfo.Column("formationId", "INTEGER", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsUserTeams.put("tacticId", new TableInfo.Column("tacticId", "INTEGER", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsUserTeams.put("submitted", new TableInfo.Column("submitted", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsUserTeams.put("ownerUserId", new TableInfo.Column("ownerUserId", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysUserTeams = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesUserTeams = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoUserTeams = new TableInfo("user_teams", _columnsUserTeams, _foreignKeysUserTeams, _indicesUserTeams);
        final TableInfo _existingUserTeams = TableInfo.read(db, "user_teams");
        if (!_infoUserTeams.equals(_existingUserTeams)) {
          return new RoomOpenHelper.ValidationResult(false, "user_teams(com.example.footballmanager.data.local.entities.UserTeam).\n"
                  + " Expected:\n" + _infoUserTeams + "\n"
                  + " Found:\n" + _existingUserTeams);
        }
        final HashMap<String, TableInfo.Column> _columnsUserTeamPlayers = new HashMap<String, TableInfo.Column>(4);
        _columnsUserTeamPlayers.put("id", new TableInfo.Column("id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsUserTeamPlayers.put("userTeamId", new TableInfo.Column("userTeamId", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsUserTeamPlayers.put("playerId", new TableInfo.Column("playerId", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsUserTeamPlayers.put("slotNumber", new TableInfo.Column("slotNumber", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysUserTeamPlayers = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesUserTeamPlayers = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoUserTeamPlayers = new TableInfo("user_team_players", _columnsUserTeamPlayers, _foreignKeysUserTeamPlayers, _indicesUserTeamPlayers);
        final TableInfo _existingUserTeamPlayers = TableInfo.read(db, "user_team_players");
        if (!_infoUserTeamPlayers.equals(_existingUserTeamPlayers)) {
          return new RoomOpenHelper.ValidationResult(false, "user_team_players(com.example.footballmanager.data.local.entities.UserTeamPlayer).\n"
                  + " Expected:\n" + _infoUserTeamPlayers + "\n"
                  + " Found:\n" + _existingUserTeamPlayers);
        }
        final HashMap<String, TableInfo.Column> _columnsUserTeamVotings = new HashMap<String, TableInfo.Column>(3);
        _columnsUserTeamVotings.put("id", new TableInfo.Column("id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsUserTeamVotings.put("userId", new TableInfo.Column("userId", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsUserTeamVotings.put("userTeamId", new TableInfo.Column("userTeamId", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysUserTeamVotings = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesUserTeamVotings = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoUserTeamVotings = new TableInfo("user_team_votings", _columnsUserTeamVotings, _foreignKeysUserTeamVotings, _indicesUserTeamVotings);
        final TableInfo _existingUserTeamVotings = TableInfo.read(db, "user_team_votings");
        if (!_infoUserTeamVotings.equals(_existingUserTeamVotings)) {
          return new RoomOpenHelper.ValidationResult(false, "user_team_votings(com.example.footballmanager.data.local.entities.UserTeamVoting).\n"
                  + " Expected:\n" + _infoUserTeamVotings + "\n"
                  + " Found:\n" + _existingUserTeamVotings);
        }
        final HashMap<String, TableInfo.Column> _columnsUserTransferProposals = new HashMap<String, TableInfo.Column>(6);
        _columnsUserTransferProposals.put("id", new TableInfo.Column("id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsUserTransferProposals.put("userId", new TableInfo.Column("userId", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsUserTransferProposals.put("playerId", new TableInfo.Column("playerId", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsUserTransferProposals.put("fromTeamId", new TableInfo.Column("fromTeamId", "INTEGER", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsUserTransferProposals.put("toTeamId", new TableInfo.Column("toTeamId", "INTEGER", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsUserTransferProposals.put("votes", new TableInfo.Column("votes", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysUserTransferProposals = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesUserTransferProposals = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoUserTransferProposals = new TableInfo("user_transfer_proposals", _columnsUserTransferProposals, _foreignKeysUserTransferProposals, _indicesUserTransferProposals);
        final TableInfo _existingUserTransferProposals = TableInfo.read(db, "user_transfer_proposals");
        if (!_infoUserTransferProposals.equals(_existingUserTransferProposals)) {
          return new RoomOpenHelper.ValidationResult(false, "user_transfer_proposals(com.example.footballmanager.data.local.entities.UserTransferProposal).\n"
                  + " Expected:\n" + _infoUserTransferProposals + "\n"
                  + " Found:\n" + _existingUserTransferProposals);
        }
        final HashMap<String, TableInfo.Column> _columnsUserTransferProposalVotings = new HashMap<String, TableInfo.Column>(3);
        _columnsUserTransferProposalVotings.put("id", new TableInfo.Column("id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsUserTransferProposalVotings.put("userId", new TableInfo.Column("userId", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsUserTransferProposalVotings.put("proposalId", new TableInfo.Column("proposalId", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysUserTransferProposalVotings = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesUserTransferProposalVotings = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoUserTransferProposalVotings = new TableInfo("user_transfer_proposal_votings", _columnsUserTransferProposalVotings, _foreignKeysUserTransferProposalVotings, _indicesUserTransferProposalVotings);
        final TableInfo _existingUserTransferProposalVotings = TableInfo.read(db, "user_transfer_proposal_votings");
        if (!_infoUserTransferProposalVotings.equals(_existingUserTransferProposalVotings)) {
          return new RoomOpenHelper.ValidationResult(false, "user_transfer_proposal_votings(com.example.footballmanager.data.local.entities.UserTransferProposalVoting).\n"
                  + " Expected:\n" + _infoUserTransferProposalVotings + "\n"
                  + " Found:\n" + _existingUserTransferProposalVotings);
        }
        final HashMap<String, TableInfo.Column> _columnsTeamFormations = new HashMap<String, TableInfo.Column>(3);
        _columnsTeamFormations.put("id", new TableInfo.Column("id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsTeamFormations.put("name", new TableInfo.Column("name", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsTeamFormations.put("description", new TableInfo.Column("description", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysTeamFormations = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesTeamFormations = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoTeamFormations = new TableInfo("team_formations", _columnsTeamFormations, _foreignKeysTeamFormations, _indicesTeamFormations);
        final TableInfo _existingTeamFormations = TableInfo.read(db, "team_formations");
        if (!_infoTeamFormations.equals(_existingTeamFormations)) {
          return new RoomOpenHelper.ValidationResult(false, "team_formations(com.example.footballmanager.data.local.entities.TeamFormation).\n"
                  + " Expected:\n" + _infoTeamFormations + "\n"
                  + " Found:\n" + _existingTeamFormations);
        }
        final HashMap<String, TableInfo.Column> _columnsFormationPositions = new HashMap<String, TableInfo.Column>(4);
        _columnsFormationPositions.put("id", new TableInfo.Column("id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsFormationPositions.put("formationId", new TableInfo.Column("formationId", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsFormationPositions.put("slotNumber", new TableInfo.Column("slotNumber", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsFormationPositions.put("position", new TableInfo.Column("position", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysFormationPositions = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesFormationPositions = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoFormationPositions = new TableInfo("formation_positions", _columnsFormationPositions, _foreignKeysFormationPositions, _indicesFormationPositions);
        final TableInfo _existingFormationPositions = TableInfo.read(db, "formation_positions");
        if (!_infoFormationPositions.equals(_existingFormationPositions)) {
          return new RoomOpenHelper.ValidationResult(false, "formation_positions(com.example.footballmanager.data.local.entities.FormationPosition).\n"
                  + " Expected:\n" + _infoFormationPositions + "\n"
                  + " Found:\n" + _existingFormationPositions);
        }
        final HashMap<String, TableInfo.Column> _columnsTactics = new HashMap<String, TableInfo.Column>(3);
        _columnsTactics.put("id", new TableInfo.Column("id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsTactics.put("details", new TableInfo.Column("details", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsTactics.put("style", new TableInfo.Column("style", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysTactics = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesTactics = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoTactics = new TableInfo("tactics", _columnsTactics, _foreignKeysTactics, _indicesTactics);
        final TableInfo _existingTactics = TableInfo.read(db, "tactics");
        if (!_infoTactics.equals(_existingTactics)) {
          return new RoomOpenHelper.ValidationResult(false, "tactics(com.example.footballmanager.data.local.entities.Tactic).\n"
                  + " Expected:\n" + _infoTactics + "\n"
                  + " Found:\n" + _existingTactics);
        }
        final HashMap<String, TableInfo.Column> _columnsEvents = new HashMap<String, TableInfo.Column>(2);
        _columnsEvents.put("id", new TableInfo.Column("id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsEvents.put("name", new TableInfo.Column("name", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysEvents = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesEvents = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoEvents = new TableInfo("events", _columnsEvents, _foreignKeysEvents, _indicesEvents);
        final TableInfo _existingEvents = TableInfo.read(db, "events");
        if (!_infoEvents.equals(_existingEvents)) {
          return new RoomOpenHelper.ValidationResult(false, "events(com.example.footballmanager.data.local.entities.Event).\n"
                  + " Expected:\n" + _infoEvents + "\n"
                  + " Found:\n" + _existingEvents);
        }
        final HashMap<String, TableInfo.Column> _columnsVotes = new HashMap<String, TableInfo.Column>(5);
        _columnsVotes.put("id", new TableInfo.Column("id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsVotes.put("eventId", new TableInfo.Column("eventId", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsVotes.put("eventTypeId", new TableInfo.Column("eventTypeId", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsVotes.put("userId", new TableInfo.Column("userId", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsVotes.put("isUpvote", new TableInfo.Column("isUpvote", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysVotes = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesVotes = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoVotes = new TableInfo("votes", _columnsVotes, _foreignKeysVotes, _indicesVotes);
        final TableInfo _existingVotes = TableInfo.read(db, "votes");
        if (!_infoVotes.equals(_existingVotes)) {
          return new RoomOpenHelper.ValidationResult(false, "votes(com.example.footballmanager.data.local.entities.Vote).\n"
                  + " Expected:\n" + _infoVotes + "\n"
                  + " Found:\n" + _existingVotes);
        }
        return new RoomOpenHelper.ValidationResult(true, null);
      }
    }, "49ea92e372fa2424aa3ecc4845214756", "9ce02e3edec3295d4ada112ad0ab942c");
    final SupportSQLiteOpenHelper.Configuration _sqliteConfig = SupportSQLiteOpenHelper.Configuration.builder(config.context).name(config.name).callback(_openCallback).build();
    final SupportSQLiteOpenHelper _helper = config.sqliteOpenHelperFactory.create(_sqliteConfig);
    return _helper;
  }

  @Override
  @NonNull
  protected InvalidationTracker createInvalidationTracker() {
    final HashMap<String, String> _shadowTablesMap = new HashMap<String, String>(0);
    final HashMap<String, Set<String>> _viewTables = new HashMap<String, Set<String>>(0);
    return new InvalidationTracker(this, _shadowTablesMap, _viewTables, "players","player_teams","transfers","transfer_votings","users","user_teams","user_team_players","user_team_votings","user_transfer_proposals","user_transfer_proposal_votings","team_formations","formation_positions","tactics","events","votes");
  }

  @Override
  public void clearAllTables() {
    super.assertNotMainThread();
    final SupportSQLiteDatabase _db = super.getOpenHelper().getWritableDatabase();
    try {
      super.beginTransaction();
      _db.execSQL("DELETE FROM `players`");
      _db.execSQL("DELETE FROM `player_teams`");
      _db.execSQL("DELETE FROM `transfers`");
      _db.execSQL("DELETE FROM `transfer_votings`");
      _db.execSQL("DELETE FROM `users`");
      _db.execSQL("DELETE FROM `user_teams`");
      _db.execSQL("DELETE FROM `user_team_players`");
      _db.execSQL("DELETE FROM `user_team_votings`");
      _db.execSQL("DELETE FROM `user_transfer_proposals`");
      _db.execSQL("DELETE FROM `user_transfer_proposal_votings`");
      _db.execSQL("DELETE FROM `team_formations`");
      _db.execSQL("DELETE FROM `formation_positions`");
      _db.execSQL("DELETE FROM `tactics`");
      _db.execSQL("DELETE FROM `events`");
      _db.execSQL("DELETE FROM `votes`");
      super.setTransactionSuccessful();
    } finally {
      super.endTransaction();
      _db.query("PRAGMA wal_checkpoint(FULL)").close();
      if (!_db.inTransaction()) {
        _db.execSQL("VACUUM");
      }
    }
  }

  @Override
  @NonNull
  protected Map<Class<?>, List<Class<?>>> getRequiredTypeConverters() {
    final HashMap<Class<?>, List<Class<?>>> _typeConvertersMap = new HashMap<Class<?>, List<Class<?>>>();
    _typeConvertersMap.put(PlayerDao.class, PlayerDao_Impl.getRequiredConverters());
    _typeConvertersMap.put(PlayerTeamDao.class, PlayerTeamDao_Impl.getRequiredConverters());
    _typeConvertersMap.put(TransferDao.class, TransferDao_Impl.getRequiredConverters());
    _typeConvertersMap.put(TransferVotingDao.class, TransferVotingDao_Impl.getRequiredConverters());
    _typeConvertersMap.put(UserDao.class, UserDao_Impl.getRequiredConverters());
    _typeConvertersMap.put(UserTeamDao.class, UserTeamDao_Impl.getRequiredConverters());
    _typeConvertersMap.put(UserTeamPlayerDao.class, UserTeamPlayerDao_Impl.getRequiredConverters());
    _typeConvertersMap.put(UserTeamVotingDao.class, UserTeamVotingDao_Impl.getRequiredConverters());
    _typeConvertersMap.put(UserTransferProposalDao.class, UserTransferProposalDao_Impl.getRequiredConverters());
    _typeConvertersMap.put(UserTransferProposalVotingDao.class, UserTransferProposalVotingDao_Impl.getRequiredConverters());
    _typeConvertersMap.put(TeamFormationDao.class, TeamFormationDao_Impl.getRequiredConverters());
    _typeConvertersMap.put(FormationPositionDao.class, FormationPositionDao_Impl.getRequiredConverters());
    _typeConvertersMap.put(TacticDao.class, TacticDao_Impl.getRequiredConverters());
    _typeConvertersMap.put(EventDao.class, EventDao_Impl.getRequiredConverters());
    _typeConvertersMap.put(VoteDao.class, VoteDao_Impl.getRequiredConverters());
    return _typeConvertersMap;
  }

  @Override
  @NonNull
  public Set<Class<? extends AutoMigrationSpec>> getRequiredAutoMigrationSpecs() {
    final HashSet<Class<? extends AutoMigrationSpec>> _autoMigrationSpecsSet = new HashSet<Class<? extends AutoMigrationSpec>>();
    return _autoMigrationSpecsSet;
  }

  @Override
  @NonNull
  public List<Migration> getAutoMigrations(
      @NonNull final Map<Class<? extends AutoMigrationSpec>, AutoMigrationSpec> autoMigrationSpecs) {
    final List<Migration> _autoMigrations = new ArrayList<Migration>();
    return _autoMigrations;
  }

  @Override
  public PlayerDao playerDao() {
    if (_playerDao != null) {
      return _playerDao;
    } else {
      synchronized(this) {
        if(_playerDao == null) {
          _playerDao = new PlayerDao_Impl(this);
        }
        return _playerDao;
      }
    }
  }

  @Override
  public PlayerTeamDao playerTeamDao() {
    if (_playerTeamDao != null) {
      return _playerTeamDao;
    } else {
      synchronized(this) {
        if(_playerTeamDao == null) {
          _playerTeamDao = new PlayerTeamDao_Impl(this);
        }
        return _playerTeamDao;
      }
    }
  }

  @Override
  public TransferDao transferDao() {
    if (_transferDao != null) {
      return _transferDao;
    } else {
      synchronized(this) {
        if(_transferDao == null) {
          _transferDao = new TransferDao_Impl(this);
        }
        return _transferDao;
      }
    }
  }

  @Override
  public TransferVotingDao transferVotingDao() {
    if (_transferVotingDao != null) {
      return _transferVotingDao;
    } else {
      synchronized(this) {
        if(_transferVotingDao == null) {
          _transferVotingDao = new TransferVotingDao_Impl(this);
        }
        return _transferVotingDao;
      }
    }
  }

  @Override
  public UserDao userDao() {
    if (_userDao != null) {
      return _userDao;
    } else {
      synchronized(this) {
        if(_userDao == null) {
          _userDao = new UserDao_Impl(this);
        }
        return _userDao;
      }
    }
  }

  @Override
  public UserTeamDao userTeamDao() {
    if (_userTeamDao != null) {
      return _userTeamDao;
    } else {
      synchronized(this) {
        if(_userTeamDao == null) {
          _userTeamDao = new UserTeamDao_Impl(this);
        }
        return _userTeamDao;
      }
    }
  }

  @Override
  public UserTeamPlayerDao userTeamPlayerDao() {
    if (_userTeamPlayerDao != null) {
      return _userTeamPlayerDao;
    } else {
      synchronized(this) {
        if(_userTeamPlayerDao == null) {
          _userTeamPlayerDao = new UserTeamPlayerDao_Impl(this);
        }
        return _userTeamPlayerDao;
      }
    }
  }

  @Override
  public UserTeamVotingDao userTeamVotingDao() {
    if (_userTeamVotingDao != null) {
      return _userTeamVotingDao;
    } else {
      synchronized(this) {
        if(_userTeamVotingDao == null) {
          _userTeamVotingDao = new UserTeamVotingDao_Impl(this);
        }
        return _userTeamVotingDao;
      }
    }
  }

  @Override
  public UserTransferProposalDao userTransferProposalDao() {
    if (_userTransferProposalDao != null) {
      return _userTransferProposalDao;
    } else {
      synchronized(this) {
        if(_userTransferProposalDao == null) {
          _userTransferProposalDao = new UserTransferProposalDao_Impl(this);
        }
        return _userTransferProposalDao;
      }
    }
  }

  @Override
  public UserTransferProposalVotingDao userTransferProposalVotingDao() {
    if (_userTransferProposalVotingDao != null) {
      return _userTransferProposalVotingDao;
    } else {
      synchronized(this) {
        if(_userTransferProposalVotingDao == null) {
          _userTransferProposalVotingDao = new UserTransferProposalVotingDao_Impl(this);
        }
        return _userTransferProposalVotingDao;
      }
    }
  }

  @Override
  public TeamFormationDao teamFormationDao() {
    if (_teamFormationDao != null) {
      return _teamFormationDao;
    } else {
      synchronized(this) {
        if(_teamFormationDao == null) {
          _teamFormationDao = new TeamFormationDao_Impl(this);
        }
        return _teamFormationDao;
      }
    }
  }

  @Override
  public FormationPositionDao formationPositionDao() {
    if (_formationPositionDao != null) {
      return _formationPositionDao;
    } else {
      synchronized(this) {
        if(_formationPositionDao == null) {
          _formationPositionDao = new FormationPositionDao_Impl(this);
        }
        return _formationPositionDao;
      }
    }
  }

  @Override
  public TacticDao tacticDao() {
    if (_tacticDao != null) {
      return _tacticDao;
    } else {
      synchronized(this) {
        if(_tacticDao == null) {
          _tacticDao = new TacticDao_Impl(this);
        }
        return _tacticDao;
      }
    }
  }

  @Override
  public EventDao eventDao() {
    if (_eventDao != null) {
      return _eventDao;
    } else {
      synchronized(this) {
        if(_eventDao == null) {
          _eventDao = new EventDao_Impl(this);
        }
        return _eventDao;
      }
    }
  }

  @Override
  public VoteDao voteDao() {
    if (_voteDao != null) {
      return _voteDao;
    } else {
      synchronized(this) {
        if(_voteDao == null) {
          _voteDao = new VoteDao_Impl(this);
        }
        return _voteDao;
      }
    }
  }
}
