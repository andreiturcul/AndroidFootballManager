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
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import com.example.footballmanager.data.local.entities.UserTeam;
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
public final class UserTeamDao_Impl implements UserTeamDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<UserTeam> __insertionAdapterOfUserTeam;

  private final EntityDeletionOrUpdateAdapter<UserTeam> __updateAdapterOfUserTeam;

  public UserTeamDao_Impl(@NonNull final RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfUserTeam = new EntityInsertionAdapter<UserTeam>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR REPLACE INTO `user_teams` (`id`,`name`,`formationId`,`tacticId`,`submitted`,`ownerUserId`) VALUES (nullif(?, 0),?,?,?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final UserTeam entity) {
        statement.bindLong(1, entity.getId());
        if (entity.getName() == null) {
          statement.bindNull(2);
        } else {
          statement.bindString(2, entity.getName());
        }
        if (entity.getFormationId() == null) {
          statement.bindNull(3);
        } else {
          statement.bindLong(3, entity.getFormationId());
        }
        if (entity.getTacticId() == null) {
          statement.bindNull(4);
        } else {
          statement.bindLong(4, entity.getTacticId());
        }
        final int _tmp = entity.getSubmitted() ? 1 : 0;
        statement.bindLong(5, _tmp);
        statement.bindLong(6, entity.getOwnerUserId());
      }
    };
    this.__updateAdapterOfUserTeam = new EntityDeletionOrUpdateAdapter<UserTeam>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "UPDATE OR ABORT `user_teams` SET `id` = ?,`name` = ?,`formationId` = ?,`tacticId` = ?,`submitted` = ?,`ownerUserId` = ? WHERE `id` = ?";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final UserTeam entity) {
        statement.bindLong(1, entity.getId());
        if (entity.getName() == null) {
          statement.bindNull(2);
        } else {
          statement.bindString(2, entity.getName());
        }
        if (entity.getFormationId() == null) {
          statement.bindNull(3);
        } else {
          statement.bindLong(3, entity.getFormationId());
        }
        if (entity.getTacticId() == null) {
          statement.bindNull(4);
        } else {
          statement.bindLong(4, entity.getTacticId());
        }
        final int _tmp = entity.getSubmitted() ? 1 : 0;
        statement.bindLong(5, _tmp);
        statement.bindLong(6, entity.getOwnerUserId());
        statement.bindLong(7, entity.getId());
      }
    };
  }

  @Override
  public Object insert(final UserTeam team, final Continuation<? super Long> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Long>() {
      @Override
      @NonNull
      public Long call() throws Exception {
        __db.beginTransaction();
        try {
          final Long _result = __insertionAdapterOfUserTeam.insertAndReturnId(team);
          __db.setTransactionSuccessful();
          return _result;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object update(final UserTeam team, final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __updateAdapterOfUserTeam.handle(team);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object findByOwner(final long userId, final Continuation<? super UserTeam> $completion) {
    final String _sql = "SELECT * FROM user_teams WHERE ownerUserId = ? LIMIT 1";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, userId);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<UserTeam>() {
      @Override
      @Nullable
      public UserTeam call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfName = CursorUtil.getColumnIndexOrThrow(_cursor, "name");
          final int _cursorIndexOfFormationId = CursorUtil.getColumnIndexOrThrow(_cursor, "formationId");
          final int _cursorIndexOfTacticId = CursorUtil.getColumnIndexOrThrow(_cursor, "tacticId");
          final int _cursorIndexOfSubmitted = CursorUtil.getColumnIndexOrThrow(_cursor, "submitted");
          final int _cursorIndexOfOwnerUserId = CursorUtil.getColumnIndexOrThrow(_cursor, "ownerUserId");
          final UserTeam _result;
          if (_cursor.moveToFirst()) {
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final String _tmpName;
            if (_cursor.isNull(_cursorIndexOfName)) {
              _tmpName = null;
            } else {
              _tmpName = _cursor.getString(_cursorIndexOfName);
            }
            final Long _tmpFormationId;
            if (_cursor.isNull(_cursorIndexOfFormationId)) {
              _tmpFormationId = null;
            } else {
              _tmpFormationId = _cursor.getLong(_cursorIndexOfFormationId);
            }
            final Long _tmpTacticId;
            if (_cursor.isNull(_cursorIndexOfTacticId)) {
              _tmpTacticId = null;
            } else {
              _tmpTacticId = _cursor.getLong(_cursorIndexOfTacticId);
            }
            final boolean _tmpSubmitted;
            final int _tmp;
            _tmp = _cursor.getInt(_cursorIndexOfSubmitted);
            _tmpSubmitted = _tmp != 0;
            final long _tmpOwnerUserId;
            _tmpOwnerUserId = _cursor.getLong(_cursorIndexOfOwnerUserId);
            _result = new UserTeam(_tmpId,_tmpName,_tmpFormationId,_tmpTacticId,_tmpSubmitted,_tmpOwnerUserId);
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
  public Flow<UserTeam> observeByOwner(final long userId) {
    final String _sql = "SELECT * FROM user_teams WHERE ownerUserId = ? LIMIT 1";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, userId);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"user_teams"}, new Callable<UserTeam>() {
      @Override
      @Nullable
      public UserTeam call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfName = CursorUtil.getColumnIndexOrThrow(_cursor, "name");
          final int _cursorIndexOfFormationId = CursorUtil.getColumnIndexOrThrow(_cursor, "formationId");
          final int _cursorIndexOfTacticId = CursorUtil.getColumnIndexOrThrow(_cursor, "tacticId");
          final int _cursorIndexOfSubmitted = CursorUtil.getColumnIndexOrThrow(_cursor, "submitted");
          final int _cursorIndexOfOwnerUserId = CursorUtil.getColumnIndexOrThrow(_cursor, "ownerUserId");
          final UserTeam _result;
          if (_cursor.moveToFirst()) {
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final String _tmpName;
            if (_cursor.isNull(_cursorIndexOfName)) {
              _tmpName = null;
            } else {
              _tmpName = _cursor.getString(_cursorIndexOfName);
            }
            final Long _tmpFormationId;
            if (_cursor.isNull(_cursorIndexOfFormationId)) {
              _tmpFormationId = null;
            } else {
              _tmpFormationId = _cursor.getLong(_cursorIndexOfFormationId);
            }
            final Long _tmpTacticId;
            if (_cursor.isNull(_cursorIndexOfTacticId)) {
              _tmpTacticId = null;
            } else {
              _tmpTacticId = _cursor.getLong(_cursorIndexOfTacticId);
            }
            final boolean _tmpSubmitted;
            final int _tmp;
            _tmp = _cursor.getInt(_cursorIndexOfSubmitted);
            _tmpSubmitted = _tmp != 0;
            final long _tmpOwnerUserId;
            _tmpOwnerUserId = _cursor.getLong(_cursorIndexOfOwnerUserId);
            _result = new UserTeam(_tmpId,_tmpName,_tmpFormationId,_tmpTacticId,_tmpSubmitted,_tmpOwnerUserId);
          } else {
            _result = null;
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
  public Flow<List<UserTeam>> observeSubmitted() {
    final String _sql = "SELECT * FROM user_teams WHERE submitted = 1";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"user_teams"}, new Callable<List<UserTeam>>() {
      @Override
      @NonNull
      public List<UserTeam> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfName = CursorUtil.getColumnIndexOrThrow(_cursor, "name");
          final int _cursorIndexOfFormationId = CursorUtil.getColumnIndexOrThrow(_cursor, "formationId");
          final int _cursorIndexOfTacticId = CursorUtil.getColumnIndexOrThrow(_cursor, "tacticId");
          final int _cursorIndexOfSubmitted = CursorUtil.getColumnIndexOrThrow(_cursor, "submitted");
          final int _cursorIndexOfOwnerUserId = CursorUtil.getColumnIndexOrThrow(_cursor, "ownerUserId");
          final List<UserTeam> _result = new ArrayList<UserTeam>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final UserTeam _item;
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final String _tmpName;
            if (_cursor.isNull(_cursorIndexOfName)) {
              _tmpName = null;
            } else {
              _tmpName = _cursor.getString(_cursorIndexOfName);
            }
            final Long _tmpFormationId;
            if (_cursor.isNull(_cursorIndexOfFormationId)) {
              _tmpFormationId = null;
            } else {
              _tmpFormationId = _cursor.getLong(_cursorIndexOfFormationId);
            }
            final Long _tmpTacticId;
            if (_cursor.isNull(_cursorIndexOfTacticId)) {
              _tmpTacticId = null;
            } else {
              _tmpTacticId = _cursor.getLong(_cursorIndexOfTacticId);
            }
            final boolean _tmpSubmitted;
            final int _tmp;
            _tmp = _cursor.getInt(_cursorIndexOfSubmitted);
            _tmpSubmitted = _tmp != 0;
            final long _tmpOwnerUserId;
            _tmpOwnerUserId = _cursor.getLong(_cursorIndexOfOwnerUserId);
            _item = new UserTeam(_tmpId,_tmpName,_tmpFormationId,_tmpTacticId,_tmpSubmitted,_tmpOwnerUserId);
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
