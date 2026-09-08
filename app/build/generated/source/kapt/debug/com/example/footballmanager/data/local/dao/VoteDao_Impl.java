package com.example.footballmanager.data.local.dao;

import android.database.Cursor;
import android.os.CancellationSignal;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.room.CoroutinesRoom;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import com.example.footballmanager.data.local.entities.Vote;
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
import kotlin.coroutines.Continuation;

@SuppressWarnings({"unchecked", "deprecation"})
public final class VoteDao_Impl implements VoteDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<Vote> __insertionAdapterOfVote;

  public VoteDao_Impl(@NonNull final RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfVote = new EntityInsertionAdapter<Vote>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR REPLACE INTO `votes` (`id`,`eventId`,`eventTypeId`,`userId`,`isUpvote`) VALUES (nullif(?, 0),?,?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final Vote entity) {
        statement.bindLong(1, entity.getId());
        statement.bindLong(2, entity.getEventId());
        statement.bindLong(3, entity.getEventTypeId());
        statement.bindLong(4, entity.getUserId());
        final int _tmp = entity.isUpvote() ? 1 : 0;
        statement.bindLong(5, _tmp);
      }
    };
  }

  @Override
  public Object insert(final Vote vote, final Continuation<? super Long> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Long>() {
      @Override
      @NonNull
      public Long call() throws Exception {
        __db.beginTransaction();
        try {
          final Long _result = __insertionAdapterOfVote.insertAndReturnId(vote);
          __db.setTransactionSuccessful();
          return _result;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object findVote(final long eventTypeId, final long userId,
      final Continuation<? super Vote> $completion) {
    final String _sql = "SELECT * FROM votes WHERE eventTypeId = ? AND userId = ? LIMIT 1";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 2);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, eventTypeId);
    _argIndex = 2;
    _statement.bindLong(_argIndex, userId);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<Vote>() {
      @Override
      @Nullable
      public Vote call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfEventId = CursorUtil.getColumnIndexOrThrow(_cursor, "eventId");
          final int _cursorIndexOfEventTypeId = CursorUtil.getColumnIndexOrThrow(_cursor, "eventTypeId");
          final int _cursorIndexOfUserId = CursorUtil.getColumnIndexOrThrow(_cursor, "userId");
          final int _cursorIndexOfIsUpvote = CursorUtil.getColumnIndexOrThrow(_cursor, "isUpvote");
          final Vote _result;
          if (_cursor.moveToFirst()) {
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final long _tmpEventId;
            _tmpEventId = _cursor.getLong(_cursorIndexOfEventId);
            final long _tmpEventTypeId;
            _tmpEventTypeId = _cursor.getLong(_cursorIndexOfEventTypeId);
            final long _tmpUserId;
            _tmpUserId = _cursor.getLong(_cursorIndexOfUserId);
            final boolean _tmpIsUpvote;
            final int _tmp;
            _tmp = _cursor.getInt(_cursorIndexOfIsUpvote);
            _tmpIsUpvote = _tmp != 0;
            _result = new Vote(_tmpId,_tmpEventId,_tmpEventTypeId,_tmpUserId,_tmpIsUpvote);
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
