package com.example.footballmanager.data.local.dao;

import android.database.Cursor;
import android.os.CancellationSignal;
import androidx.annotation.NonNull;
import androidx.room.CoroutinesRoom;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.SharedSQLiteStatement;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import com.example.footballmanager.data.local.entities.UserTransferProposal;
import java.lang.Class;
import java.lang.Exception;
import java.lang.Integer;
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
public final class UserTransferProposalDao_Impl implements UserTransferProposalDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<UserTransferProposal> __insertionAdapterOfUserTransferProposal;

  private final SharedSQLiteStatement __preparedStmtOfAddVotes;

  public UserTransferProposalDao_Impl(@NonNull final RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfUserTransferProposal = new EntityInsertionAdapter<UserTransferProposal>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR ABORT INTO `user_transfer_proposals` (`id`,`userId`,`playerId`,`fromTeamId`,`toTeamId`,`votes`) VALUES (nullif(?, 0),?,?,?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final UserTransferProposal entity) {
        statement.bindLong(1, entity.getId());
        statement.bindLong(2, entity.getUserId());
        statement.bindLong(3, entity.getPlayerId());
        if (entity.getFromTeamId() == null) {
          statement.bindNull(4);
        } else {
          statement.bindLong(4, entity.getFromTeamId());
        }
        if (entity.getToTeamId() == null) {
          statement.bindNull(5);
        } else {
          statement.bindLong(5, entity.getToTeamId());
        }
        statement.bindLong(6, entity.getVotes());
      }
    };
    this.__preparedStmtOfAddVotes = new SharedSQLiteStatement(__db) {
      @Override
      @NonNull
      public String createQuery() {
        final String _query = "UPDATE user_transfer_proposals SET votes = votes + ? WHERE id = ?";
        return _query;
      }
    };
  }

  @Override
  public Object insert(final UserTransferProposal proposal,
      final Continuation<? super Long> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Long>() {
      @Override
      @NonNull
      public Long call() throws Exception {
        __db.beginTransaction();
        try {
          final Long _result = __insertionAdapterOfUserTransferProposal.insertAndReturnId(proposal);
          __db.setTransactionSuccessful();
          return _result;
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
  public Flow<List<UserTransferProposal>> observeAll() {
    final String _sql = "SELECT * FROM user_transfer_proposals ORDER BY votes DESC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"user_transfer_proposals"}, new Callable<List<UserTransferProposal>>() {
      @Override
      @NonNull
      public List<UserTransferProposal> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfUserId = CursorUtil.getColumnIndexOrThrow(_cursor, "userId");
          final int _cursorIndexOfPlayerId = CursorUtil.getColumnIndexOrThrow(_cursor, "playerId");
          final int _cursorIndexOfFromTeamId = CursorUtil.getColumnIndexOrThrow(_cursor, "fromTeamId");
          final int _cursorIndexOfToTeamId = CursorUtil.getColumnIndexOrThrow(_cursor, "toTeamId");
          final int _cursorIndexOfVotes = CursorUtil.getColumnIndexOrThrow(_cursor, "votes");
          final List<UserTransferProposal> _result = new ArrayList<UserTransferProposal>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final UserTransferProposal _item;
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final long _tmpUserId;
            _tmpUserId = _cursor.getLong(_cursorIndexOfUserId);
            final long _tmpPlayerId;
            _tmpPlayerId = _cursor.getLong(_cursorIndexOfPlayerId);
            final Long _tmpFromTeamId;
            if (_cursor.isNull(_cursorIndexOfFromTeamId)) {
              _tmpFromTeamId = null;
            } else {
              _tmpFromTeamId = _cursor.getLong(_cursorIndexOfFromTeamId);
            }
            final Long _tmpToTeamId;
            if (_cursor.isNull(_cursorIndexOfToTeamId)) {
              _tmpToTeamId = null;
            } else {
              _tmpToTeamId = _cursor.getLong(_cursorIndexOfToTeamId);
            }
            final int _tmpVotes;
            _tmpVotes = _cursor.getInt(_cursorIndexOfVotes);
            _item = new UserTransferProposal(_tmpId,_tmpUserId,_tmpPlayerId,_tmpFromTeamId,_tmpToTeamId,_tmpVotes);
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
  public Object count(final Continuation<? super Integer> $completion) {
    final String _sql = "SELECT COUNT(*) FROM user_transfer_proposals";
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
