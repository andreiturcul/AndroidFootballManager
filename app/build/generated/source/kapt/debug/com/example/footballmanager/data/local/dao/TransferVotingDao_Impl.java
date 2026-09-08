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
import com.example.footballmanager.data.local.entities.TransferVoting;
import java.lang.Class;
import java.lang.Exception;
import java.lang.Long;
import java.lang.Object;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import kotlin.Unit;
import kotlin.coroutines.Continuation;

@SuppressWarnings({"unchecked", "deprecation"})
public final class TransferVotingDao_Impl implements TransferVotingDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<TransferVoting> __insertionAdapterOfTransferVoting;

  private final EntityDeletionOrUpdateAdapter<TransferVoting> __updateAdapterOfTransferVoting;

  public TransferVotingDao_Impl(@NonNull final RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfTransferVoting = new EntityInsertionAdapter<TransferVoting>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR REPLACE INTO `transfer_votings` (`id`,`transferId`,`upVotes`,`downVotes`,`userId`,`userTeamId`) VALUES (nullif(?, 0),?,?,?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final TransferVoting entity) {
        statement.bindLong(1, entity.getId());
        statement.bindLong(2, entity.getTransferId());
        statement.bindLong(3, entity.getUpVotes());
        statement.bindLong(4, entity.getDownVotes());
        statement.bindLong(5, entity.getUserId());
        if (entity.getUserTeamId() == null) {
          statement.bindNull(6);
        } else {
          statement.bindLong(6, entity.getUserTeamId());
        }
      }
    };
    this.__updateAdapterOfTransferVoting = new EntityDeletionOrUpdateAdapter<TransferVoting>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "UPDATE OR ABORT `transfer_votings` SET `id` = ?,`transferId` = ?,`upVotes` = ?,`downVotes` = ?,`userId` = ?,`userTeamId` = ? WHERE `id` = ?";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final TransferVoting entity) {
        statement.bindLong(1, entity.getId());
        statement.bindLong(2, entity.getTransferId());
        statement.bindLong(3, entity.getUpVotes());
        statement.bindLong(4, entity.getDownVotes());
        statement.bindLong(5, entity.getUserId());
        if (entity.getUserTeamId() == null) {
          statement.bindNull(6);
        } else {
          statement.bindLong(6, entity.getUserTeamId());
        }
        statement.bindLong(7, entity.getId());
      }
    };
  }

  @Override
  public Object insert(final TransferVoting vote, final Continuation<? super Long> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Long>() {
      @Override
      @NonNull
      public Long call() throws Exception {
        __db.beginTransaction();
        try {
          final Long _result = __insertionAdapterOfTransferVoting.insertAndReturnId(vote);
          __db.setTransactionSuccessful();
          return _result;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object update(final TransferVoting vote, final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __updateAdapterOfTransferVoting.handle(vote);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object findVote(final long transferId, final long userId,
      final Continuation<? super TransferVoting> $completion) {
    final String _sql = "SELECT * FROM transfer_votings WHERE transferId = ? AND userId = ? LIMIT 1";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 2);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, transferId);
    _argIndex = 2;
    _statement.bindLong(_argIndex, userId);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<TransferVoting>() {
      @Override
      @Nullable
      public TransferVoting call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfTransferId = CursorUtil.getColumnIndexOrThrow(_cursor, "transferId");
          final int _cursorIndexOfUpVotes = CursorUtil.getColumnIndexOrThrow(_cursor, "upVotes");
          final int _cursorIndexOfDownVotes = CursorUtil.getColumnIndexOrThrow(_cursor, "downVotes");
          final int _cursorIndexOfUserId = CursorUtil.getColumnIndexOrThrow(_cursor, "userId");
          final int _cursorIndexOfUserTeamId = CursorUtil.getColumnIndexOrThrow(_cursor, "userTeamId");
          final TransferVoting _result;
          if (_cursor.moveToFirst()) {
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final long _tmpTransferId;
            _tmpTransferId = _cursor.getLong(_cursorIndexOfTransferId);
            final int _tmpUpVotes;
            _tmpUpVotes = _cursor.getInt(_cursorIndexOfUpVotes);
            final int _tmpDownVotes;
            _tmpDownVotes = _cursor.getInt(_cursorIndexOfDownVotes);
            final long _tmpUserId;
            _tmpUserId = _cursor.getLong(_cursorIndexOfUserId);
            final Long _tmpUserTeamId;
            if (_cursor.isNull(_cursorIndexOfUserTeamId)) {
              _tmpUserTeamId = null;
            } else {
              _tmpUserTeamId = _cursor.getLong(_cursorIndexOfUserTeamId);
            }
            _result = new TransferVoting(_tmpId,_tmpTransferId,_tmpUpVotes,_tmpDownVotes,_tmpUserId,_tmpUserTeamId);
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
