package com.example.footballmanager.data.local.dao;

import android.database.Cursor;
import androidx.annotation.NonNull;
import androidx.room.CoroutinesRoom;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import com.example.footballmanager.data.local.entities.FormationPosition;
import java.lang.Class;
import java.lang.Exception;
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
public final class FormationPositionDao_Impl implements FormationPositionDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<FormationPosition> __insertionAdapterOfFormationPosition;

  public FormationPositionDao_Impl(@NonNull final RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfFormationPosition = new EntityInsertionAdapter<FormationPosition>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR IGNORE INTO `formation_positions` (`id`,`formationId`,`slotNumber`,`position`) VALUES (nullif(?, 0),?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final FormationPosition entity) {
        statement.bindLong(1, entity.getId());
        statement.bindLong(2, entity.getFormationId());
        statement.bindLong(3, entity.getSlotNumber());
        if (entity.getPosition() == null) {
          statement.bindNull(4);
        } else {
          statement.bindString(4, entity.getPosition());
        }
      }
    };
  }

  @Override
  public Object insertAll(final List<FormationPosition> positions,
      final Continuation<? super Unit> arg1) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __insertionAdapterOfFormationPosition.insert(positions);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, arg1);
  }

  @Override
  public Flow<List<FormationPosition>> observeForFormation(final long formationId) {
    final String _sql = "SELECT * FROM formation_positions WHERE formationId = ? ORDER BY slotNumber";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, formationId);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"formation_positions"}, new Callable<List<FormationPosition>>() {
      @Override
      @NonNull
      public List<FormationPosition> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfFormationId = CursorUtil.getColumnIndexOrThrow(_cursor, "formationId");
          final int _cursorIndexOfSlotNumber = CursorUtil.getColumnIndexOrThrow(_cursor, "slotNumber");
          final int _cursorIndexOfPosition = CursorUtil.getColumnIndexOrThrow(_cursor, "position");
          final List<FormationPosition> _result = new ArrayList<FormationPosition>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final FormationPosition _item;
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final long _tmpFormationId;
            _tmpFormationId = _cursor.getLong(_cursorIndexOfFormationId);
            final int _tmpSlotNumber;
            _tmpSlotNumber = _cursor.getInt(_cursorIndexOfSlotNumber);
            final String _tmpPosition;
            if (_cursor.isNull(_cursorIndexOfPosition)) {
              _tmpPosition = null;
            } else {
              _tmpPosition = _cursor.getString(_cursorIndexOfPosition);
            }
            _item = new FormationPosition(_tmpId,_tmpFormationId,_tmpSlotNumber,_tmpPosition);
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
