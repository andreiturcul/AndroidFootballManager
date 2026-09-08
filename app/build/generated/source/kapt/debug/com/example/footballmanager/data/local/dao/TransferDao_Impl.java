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
import com.example.footballmanager.data.local.entities.Transfer;
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
public final class TransferDao_Impl implements TransferDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<Transfer> __insertionAdapterOfTransfer;

  private final SharedSQLiteStatement __preparedStmtOfAddVotes;

  public TransferDao_Impl(@NonNull final RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfTransfer = new EntityInsertionAdapter<Transfer>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR ABORT INTO `transfers` (`id`,`playerId`,`fromTeamId`,`toTeamId`,`price`,`date`,`votes`) VALUES (nullif(?, 0),?,?,?,?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final Transfer entity) {
        statement.bindLong(1, entity.getId());
        statement.bindLong(2, entity.getPlayerId());
        if (entity.getFromTeamId() == null) {
          statement.bindNull(3);
        } else {
          statement.bindLong(3, entity.getFromTeamId());
        }
        if (entity.getToTeamId() == null) {
          statement.bindNull(4);
        } else {
          statement.bindLong(4, entity.getToTeamId());
        }
        statement.bindDouble(5, entity.getPrice());
        statement.bindLong(6, entity.getDate());
        statement.bindLong(7, entity.getVotes());
      }
    };
    this.__preparedStmtOfAddVotes = new SharedSQLiteStatement(__db) {
      @Override
      @NonNull
      public String createQuery() {
        final String _query = "UPDATE transfers SET votes = votes + ? WHERE id = ?";
        return _query;
      }
    };
  }

  @Override
  public Object insert(final Transfer transfer, final Continuation<? super Long> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Long>() {
      @Override
      @NonNull
      public Long call() throws Exception {
        __db.beginTransaction();
        try {
          final Long _result = __insertionAdapterOfTransfer.insertAndReturnId(transfer);
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
  public Flow<List<Transfer>> observeAll() {
    final String _sql = "SELECT * FROM transfers ORDER BY date DESC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"transfers"}, new Callable<List<Transfer>>() {
      @Override
      @NonNull
      public List<Transfer> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfPlayerId = CursorUtil.getColumnIndexOrThrow(_cursor, "playerId");
          final int _cursorIndexOfFromTeamId = CursorUtil.getColumnIndexOrThrow(_cursor, "fromTeamId");
          final int _cursorIndexOfToTeamId = CursorUtil.getColumnIndexOrThrow(_cursor, "toTeamId");
          final int _cursorIndexOfPrice = CursorUtil.getColumnIndexOrThrow(_cursor, "price");
          final int _cursorIndexOfDate = CursorUtil.getColumnIndexOrThrow(_cursor, "date");
          final int _cursorIndexOfVotes = CursorUtil.getColumnIndexOrThrow(_cursor, "votes");
          final List<Transfer> _result = new ArrayList<Transfer>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final Transfer _item;
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
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
            final double _tmpPrice;
            _tmpPrice = _cursor.getDouble(_cursorIndexOfPrice);
            final long _tmpDate;
            _tmpDate = _cursor.getLong(_cursorIndexOfDate);
            final int _tmpVotes;
            _tmpVotes = _cursor.getInt(_cursorIndexOfVotes);
            _item = new Transfer(_tmpId,_tmpPlayerId,_tmpFromTeamId,_tmpToTeamId,_tmpPrice,_tmpDate,_tmpVotes);
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
