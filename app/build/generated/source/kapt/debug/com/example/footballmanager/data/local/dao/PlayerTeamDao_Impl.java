package com.example.footballmanager.data.local.dao;

import android.database.Cursor;
import android.os.CancellationSignal;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.room.CoroutinesRoom;
import androidx.room.EntityDeletionOrUpdateAdapter;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.SharedSQLiteStatement;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import com.example.footballmanager.data.local.entities.PlayerTeam;
import java.lang.Class;
import java.lang.Exception;
import java.lang.Integer;
import java.lang.Object;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlinx.coroutines.flow.Flow;

@SuppressWarnings({"unchecked", "deprecation"})
public final class PlayerTeamDao_Impl implements PlayerTeamDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<PlayerTeam> __insertionAdapterOfPlayerTeam;

  private final EntityDeletionOrUpdateAdapter<PlayerTeam> __updateAdapterOfPlayerTeam;

  private final SharedSQLiteStatement __preparedStmtOfAddVotes;

  public PlayerTeamDao_Impl(@NonNull final RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfPlayerTeam = new EntityInsertionAdapter<PlayerTeam>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR REPLACE INTO `player_teams` (`id`,`name`,`ucl`,`league`,`cup`,`votes`,`badgeUrl`,`remoteId`) VALUES (nullif(?, 0),?,?,?,?,?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final PlayerTeam entity) {
        statement.bindLong(1, entity.getId());
        if (entity.getName() == null) {
          statement.bindNull(2);
        } else {
          statement.bindString(2, entity.getName());
        }
        final int _tmp = entity.getUcl() ? 1 : 0;
        statement.bindLong(3, _tmp);
        if (entity.getLeague() == null) {
          statement.bindNull(4);
        } else {
          statement.bindString(4, entity.getLeague());
        }
        final int _tmp_1 = entity.getCup() ? 1 : 0;
        statement.bindLong(5, _tmp_1);
        statement.bindLong(6, entity.getVotes());
        if (entity.getBadgeUrl() == null) {
          statement.bindNull(7);
        } else {
          statement.bindString(7, entity.getBadgeUrl());
        }
        if (entity.getRemoteId() == null) {
          statement.bindNull(8);
        } else {
          statement.bindString(8, entity.getRemoteId());
        }
      }
    };
    this.__updateAdapterOfPlayerTeam = new EntityDeletionOrUpdateAdapter<PlayerTeam>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "UPDATE OR ABORT `player_teams` SET `id` = ?,`name` = ?,`ucl` = ?,`league` = ?,`cup` = ?,`votes` = ?,`badgeUrl` = ?,`remoteId` = ? WHERE `id` = ?";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final PlayerTeam entity) {
        statement.bindLong(1, entity.getId());
        if (entity.getName() == null) {
          statement.bindNull(2);
        } else {
          statement.bindString(2, entity.getName());
        }
        final int _tmp = entity.getUcl() ? 1 : 0;
        statement.bindLong(3, _tmp);
        if (entity.getLeague() == null) {
          statement.bindNull(4);
        } else {
          statement.bindString(4, entity.getLeague());
        }
        final int _tmp_1 = entity.getCup() ? 1 : 0;
        statement.bindLong(5, _tmp_1);
        statement.bindLong(6, entity.getVotes());
        if (entity.getBadgeUrl() == null) {
          statement.bindNull(7);
        } else {
          statement.bindString(7, entity.getBadgeUrl());
        }
        if (entity.getRemoteId() == null) {
          statement.bindNull(8);
        } else {
          statement.bindString(8, entity.getRemoteId());
        }
        statement.bindLong(9, entity.getId());
      }
    };
    this.__preparedStmtOfAddVotes = new SharedSQLiteStatement(__db) {
      @Override
      @NonNull
      public String createQuery() {
        final String _query = "UPDATE player_teams SET votes = votes + ? WHERE id = ?";
        return _query;
      }
    };
  }

  @Override
  public Object insertAll(final List<PlayerTeam> teams,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __insertionAdapterOfPlayerTeam.insert(teams);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object update(final PlayerTeam team, final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __updateAdapterOfPlayerTeam.handle(team);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object addVotes(final long id, final int delta,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        final SupportSQLiteStatement _stmt = __preparedStmtOfAddVotes.acquire();
        int _argIndex = 1;
        _stmt.bindLong(_argIndex, delta);
        _argIndex = 2;
        _stmt.bindLong(_argIndex, id);
        try {
          __db.beginTransaction();
          try {
            _stmt.executeUpdateDelete();
            __db.setTransactionSuccessful();
            return Unit.INSTANCE;
          } finally {
            __db.endTransaction();
          }
        } finally {
          __preparedStmtOfAddVotes.release(_stmt);
        }
      }
    }, $completion);
  }

  @Override
  public Flow<List<PlayerTeam>> observeAll() {
    final String _sql = "SELECT * FROM player_teams ORDER BY name ASC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"player_teams"}, new Callable<List<PlayerTeam>>() {
      @Override
      @NonNull
      public List<PlayerTeam> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfName = CursorUtil.getColumnIndexOrThrow(_cursor, "name");
          final int _cursorIndexOfUcl = CursorUtil.getColumnIndexOrThrow(_cursor, "ucl");
          final int _cursorIndexOfLeague = CursorUtil.getColumnIndexOrThrow(_cursor, "league");
          final int _cursorIndexOfCup = CursorUtil.getColumnIndexOrThrow(_cursor, "cup");
          final int _cursorIndexOfVotes = CursorUtil.getColumnIndexOrThrow(_cursor, "votes");
          final int _cursorIndexOfBadgeUrl = CursorUtil.getColumnIndexOrThrow(_cursor, "badgeUrl");
          final int _cursorIndexOfRemoteId = CursorUtil.getColumnIndexOrThrow(_cursor, "remoteId");
          final List<PlayerTeam> _result = new ArrayList<PlayerTeam>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final PlayerTeam _item;
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final String _tmpName;
            if (_cursor.isNull(_cursorIndexOfName)) {
              _tmpName = null;
            } else {
              _tmpName = _cursor.getString(_cursorIndexOfName);
            }
            final boolean _tmpUcl;
            final int _tmp;
            _tmp = _cursor.getInt(_cursorIndexOfUcl);
            _tmpUcl = _tmp != 0;
            final String _tmpLeague;
            if (_cursor.isNull(_cursorIndexOfLeague)) {
              _tmpLeague = null;
            } else {
              _tmpLeague = _cursor.getString(_cursorIndexOfLeague);
            }
            final boolean _tmpCup;
            final int _tmp_1;
            _tmp_1 = _cursor.getInt(_cursorIndexOfCup);
            _tmpCup = _tmp_1 != 0;
            final int _tmpVotes;
            _tmpVotes = _cursor.getInt(_cursorIndexOfVotes);
            final String _tmpBadgeUrl;
            if (_cursor.isNull(_cursorIndexOfBadgeUrl)) {
              _tmpBadgeUrl = null;
            } else {
              _tmpBadgeUrl = _cursor.getString(_cursorIndexOfBadgeUrl);
            }
            final String _tmpRemoteId;
            if (_cursor.isNull(_cursorIndexOfRemoteId)) {
              _tmpRemoteId = null;
            } else {
              _tmpRemoteId = _cursor.getString(_cursorIndexOfRemoteId);
            }
            _item = new PlayerTeam(_tmpId,_tmpName,_tmpUcl,_tmpLeague,_tmpCup,_tmpVotes,_tmpBadgeUrl,_tmpRemoteId);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }

  @Override
  public Object getById(final long id, final Continuation<? super PlayerTeam> $completion) {
    final String _sql = "SELECT * FROM player_teams WHERE id = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, id);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<PlayerTeam>() {
      @Override
      @Nullable
      public PlayerTeam call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfName = CursorUtil.getColumnIndexOrThrow(_cursor, "name");
          final int _cursorIndexOfUcl = CursorUtil.getColumnIndexOrThrow(_cursor, "ucl");
          final int _cursorIndexOfLeague = CursorUtil.getColumnIndexOrThrow(_cursor, "league");
          final int _cursorIndexOfCup = CursorUtil.getColumnIndexOrThrow(_cursor, "cup");
          final int _cursorIndexOfVotes = CursorUtil.getColumnIndexOrThrow(_cursor, "votes");
          final int _cursorIndexOfBadgeUrl = CursorUtil.getColumnIndexOrThrow(_cursor, "badgeUrl");
          final int _cursorIndexOfRemoteId = CursorUtil.getColumnIndexOrThrow(_cursor, "remoteId");
          final PlayerTeam _result;
          if (_cursor.moveToFirst()) {
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final String _tmpName;
            if (_cursor.isNull(_cursorIndexOfName)) {
              _tmpName = null;
            } else {
              _tmpName = _cursor.getString(_cursorIndexOfName);
            }
            final boolean _tmpUcl;
            final int _tmp;
            _tmp = _cursor.getInt(_cursorIndexOfUcl);
            _tmpUcl = _tmp != 0;
            final String _tmpLeague;
            if (_cursor.isNull(_cursorIndexOfLeague)) {
              _tmpLeague = null;
            } else {
              _tmpLeague = _cursor.getString(_cursorIndexOfLeague);
            }
            final boolean _tmpCup;
            final int _tmp_1;
            _tmp_1 = _cursor.getInt(_cursorIndexOfCup);
            _tmpCup = _tmp_1 != 0;
            final int _tmpVotes;
            _tmpVotes = _cursor.getInt(_cursorIndexOfVotes);
            final String _tmpBadgeUrl;
            if (_cursor.isNull(_cursorIndexOfBadgeUrl)) {
              _tmpBadgeUrl = null;
            } else {
              _tmpBadgeUrl = _cursor.getString(_cursorIndexOfBadgeUrl);
            }
            final String _tmpRemoteId;
            if (_cursor.isNull(_cursorIndexOfRemoteId)) {
              _tmpRemoteId = null;
            } else {
              _tmpRemoteId = _cursor.getString(_cursorIndexOfRemoteId);
            }
            _result = new PlayerTeam(_tmpId,_tmpName,_tmpUcl,_tmpLeague,_tmpCup,_tmpVotes,_tmpBadgeUrl,_tmpRemoteId);
          } else {
            _result = null;
          }
          return _result;
        } finally {
          _cursor.close();
          _statement.release();
        }
      }
    }, $completion);
  }

  @Override
  public Object count(final Continuation<? super Integer> $completion) {
    final String _sql = "SELECT COUNT(*) FROM player_teams";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<Integer>() {
      @Override
      @NonNull
      public Integer call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final Integer _result;
          if (_cursor.moveToFirst()) {
            final Integer _tmp;
            if (_cursor.isNull(0)) {
              _tmp = null;
            } else {
              _tmp = _cursor.getInt(0);
            }
            _result = _tmp;
          } else {
            _result = null;
          }
          return _result;
        } finally {
          _cursor.close();
          _statement.release();
        }
      }
    }, $completion);
  }

  @NonNull
  public static List<Class<?>> getRequiredConverters() {
    return Collections.emptyList();
  }
}
