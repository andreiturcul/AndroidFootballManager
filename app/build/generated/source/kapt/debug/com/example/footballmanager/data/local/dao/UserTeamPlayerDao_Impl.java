package com.example.footballmanager.data.local.dao;

import android.database.Cursor;
import androidx.annotation.NonNull;
import androidx.room.CoroutinesRoom;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.SharedSQLiteStatement;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import com.example.footballmanager.data.local.entities.UserTeamPlayer;
import java.lang.Class;
import java.lang.Exception;
import java.lang.Long;
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
public final class UserTeamPlayerDao_Impl implements UserTeamPlayerDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<UserTeamPlayer> __insertionAdapterOfUserTeamPlayer;

  private final SharedSQLiteStatement __preparedStmtOfClearSlot;

  private final SharedSQLiteStatement __preparedStmtOfClearTeam;

  public UserTeamPlayerDao_Impl(@NonNull final RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfUserTeamPlayer = new EntityInsertionAdapter<UserTeamPlayer>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR REPLACE INTO `user_team_players` (`id`,`userTeamId`,`playerId`,`slotNumber`) VALUES (nullif(?, 0),?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final UserTeamPlayer entity) {
        statement.bindLong(1, entity.getId());
        statement.bindLong(2, entity.getUserTeamId());
        statement.bindLong(3, entity.getPlayerId());
        statement.bindLong(4, entity.getSlotNumber());
      }
    };
    this.__preparedStmtOfClearSlot = new SharedSQLiteStatement(__db) {
      @Override
      @NonNull
      public String createQuery() {
        final String _query = "DELETE FROM user_team_players WHERE userTeamId = ? AND slotNumber = ?";
        return _query;
      }
    };
    this.__preparedStmtOfClearTeam = new SharedSQLiteStatement(__db) {
      @Override
      @NonNull
      public String createQuery() {
        final String _query = "DELETE FROM user_team_players WHERE userTeamId = ?";
        return _query;
      }
    };
  }

  @Override
  public Object insert(final UserTeamPlayer entry, final Continuation<? super Long> arg1) {
    return CoroutinesRoom.execute(__db, true, new Callable<Long>() {
      @Override
      @NonNull
      public Long call() throws Exception {
        __db.beginTransaction();
        try {
          final Long _result = __insertionAdapterOfUserTeamPlayer.insertAndReturnId(entry);
          __db.setTransactionSuccessful();
          return _result;
        } finally {
          __db.endTransaction();
        }
      }
    }, arg1);
  }

  @Override
  public Object clearSlot(final long userTeamId, final int slotNumber,
      final Continuation<? super Unit> arg2) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        final SupportSQLiteStatement _stmt = __preparedStmtOfClearSlot.acquire();
        int _argIndex = 1;
        _stmt.bindLong(_argIndex, userTeamId);
        _argIndex = 2;
        _stmt.bindLong(_argIndex, slotNumber);
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
          __preparedStmtOfClearSlot.release(_stmt);
        }
      }
    }, arg2);
  }

  @Override
  public Object clearTeam(final long userTeamId, final Continuation<? super Unit> arg1) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        final SupportSQLiteStatement _stmt = __preparedStmtOfClearTeam.acquire();
        int _argIndex = 1;
        _stmt.bindLong(_argIndex, userTeamId);
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
          __preparedStmtOfClearTeam.release(_stmt);
        }
      }
    }, arg1);
  }

  @Override
  public Flow<List<UserTeamPlayer>> observeForTeam(final long userTeamId) {
    final String _sql = "SELECT * FROM user_team_players WHERE userTeamId = ? ORDER BY slotNumber";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, userTeamId);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"user_team_players"}, new Callable<List<UserTeamPlayer>>() {
      @Override
      @NonNull
      public List<UserTeamPlayer> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfUserTeamId = CursorUtil.getColumnIndexOrThrow(_cursor, "userTeamId");
          final int _cursorIndexOfPlayerId = CursorUtil.getColumnIndexOrThrow(_cursor, "playerId");
          final int _cursorIndexOfSlotNumber = CursorUtil.getColumnIndexOrThrow(_cursor, "slotNumber");
          final List<UserTeamPlayer> _result = new ArrayList<UserTeamPlayer>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final UserTeamPlayer _item;
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final long _tmpUserTeamId;
            _tmpUserTeamId = _cursor.getLong(_cursorIndexOfUserTeamId);
            final long _tmpPlayerId;
            _tmpPlayerId = _cursor.getLong(_cursorIndexOfPlayerId);
            final int _tmpSlotNumber;
            _tmpSlotNumber = _cursor.getInt(_cursorIndexOfSlotNumber);
            _item = new UserTeamPlayer(_tmpId,_tmpUserTeamId,_tmpPlayerId,_tmpSlotNumber);
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

  @NonNull
  public static List<Class<?>> getRequiredConverters() {
    return Collections.emptyList();
  }
}
