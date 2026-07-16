package com.antigravity.steptracker.data.local.db.dao;

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
import com.antigravity.steptracker.data.local.db.entity.DailyStepsEntity;
import com.antigravity.steptracker.data.local.db.entity.HourlyStepsEntity;
import com.antigravity.steptracker.data.local.db.entity.SensorLogsEntity;
import com.antigravity.steptracker.data.local.db.entity.TrackingSessionEntity;
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
import javax.annotation.processing.Generated;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlinx.coroutines.flow.Flow;

@Generated("androidx.room.RoomProcessor")
@SuppressWarnings({"unchecked", "deprecation"})
public final class StepDao_Impl implements StepDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<DailyStepsEntity> __insertionAdapterOfDailyStepsEntity;

  private final EntityInsertionAdapter<HourlyStepsEntity> __insertionAdapterOfHourlyStepsEntity;

  private final EntityInsertionAdapter<SensorLogsEntity> __insertionAdapterOfSensorLogsEntity;

  private final EntityInsertionAdapter<TrackingSessionEntity> __insertionAdapterOfTrackingSessionEntity;

  private final EntityDeletionOrUpdateAdapter<DailyStepsEntity> __updateAdapterOfDailyStepsEntity;

  private final EntityDeletionOrUpdateAdapter<HourlyStepsEntity> __updateAdapterOfHourlyStepsEntity;

  public StepDao_Impl(@NonNull final RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfDailyStepsEntity = new EntityInsertionAdapter<DailyStepsEntity>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR REPLACE INTO `DailySteps` (`id`,`date`,`steps`,`distance`,`calories`,`walkingTime`,`goal`,`createdAt`) VALUES (nullif(?, 0),?,?,?,?,?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final DailyStepsEntity entity) {
        statement.bindLong(1, entity.getId());
        statement.bindLong(2, entity.getDate());
        statement.bindLong(3, entity.getSteps());
        statement.bindDouble(4, entity.getDistance());
        statement.bindDouble(5, entity.getCalories());
        statement.bindLong(6, entity.getWalkingTime());
        statement.bindLong(7, entity.getGoal());
        statement.bindLong(8, entity.getCreatedAt());
      }
    };
    this.__insertionAdapterOfHourlyStepsEntity = new EntityInsertionAdapter<HourlyStepsEntity>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR REPLACE INTO `HourlySteps` (`id`,`date`,`hour`,`steps`) VALUES (nullif(?, 0),?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final HourlyStepsEntity entity) {
        statement.bindLong(1, entity.getId());
        statement.bindLong(2, entity.getDate());
        statement.bindLong(3, entity.getHour());
        statement.bindLong(4, entity.getSteps());
      }
    };
    this.__insertionAdapterOfSensorLogsEntity = new EntityInsertionAdapter<SensorLogsEntity>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR ABORT INTO `SensorLogs` (`id`,`timestamp`,`sensorValue`,`bootId`) VALUES (nullif(?, 0),?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final SensorLogsEntity entity) {
        statement.bindLong(1, entity.getId());
        statement.bindLong(2, entity.getTimestamp());
        statement.bindDouble(3, entity.getSensorValue());
        statement.bindLong(4, entity.getBootId());
      }
    };
    this.__insertionAdapterOfTrackingSessionEntity = new EntityInsertionAdapter<TrackingSessionEntity>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR ABORT INTO `TrackingSession` (`id`,`startTime`,`endTime`,`steps`,`distance`,`calories`,`duration`) VALUES (nullif(?, 0),?,?,?,?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final TrackingSessionEntity entity) {
        statement.bindLong(1, entity.getId());
        statement.bindLong(2, entity.getStartTime());
        statement.bindLong(3, entity.getEndTime());
        statement.bindLong(4, entity.getSteps());
        statement.bindDouble(5, entity.getDistance());
        statement.bindDouble(6, entity.getCalories());
        statement.bindLong(7, entity.getDuration());
      }
    };
    this.__updateAdapterOfDailyStepsEntity = new EntityDeletionOrUpdateAdapter<DailyStepsEntity>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "UPDATE OR ABORT `DailySteps` SET `id` = ?,`date` = ?,`steps` = ?,`distance` = ?,`calories` = ?,`walkingTime` = ?,`goal` = ?,`createdAt` = ? WHERE `id` = ?";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final DailyStepsEntity entity) {
        statement.bindLong(1, entity.getId());
        statement.bindLong(2, entity.getDate());
        statement.bindLong(3, entity.getSteps());
        statement.bindDouble(4, entity.getDistance());
        statement.bindDouble(5, entity.getCalories());
        statement.bindLong(6, entity.getWalkingTime());
        statement.bindLong(7, entity.getGoal());
        statement.bindLong(8, entity.getCreatedAt());
        statement.bindLong(9, entity.getId());
      }
    };
    this.__updateAdapterOfHourlyStepsEntity = new EntityDeletionOrUpdateAdapter<HourlyStepsEntity>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "UPDATE OR ABORT `HourlySteps` SET `id` = ?,`date` = ?,`hour` = ?,`steps` = ? WHERE `id` = ?";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final HourlyStepsEntity entity) {
        statement.bindLong(1, entity.getId());
        statement.bindLong(2, entity.getDate());
        statement.bindLong(3, entity.getHour());
        statement.bindLong(4, entity.getSteps());
        statement.bindLong(5, entity.getId());
      }
    };
  }

  @Override
  public Object insertDailySteps(final DailyStepsEntity dailySteps,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __insertionAdapterOfDailyStepsEntity.insert(dailySteps);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object insertHourlySteps(final HourlyStepsEntity hourlySteps,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __insertionAdapterOfHourlyStepsEntity.insert(hourlySteps);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object insertSensorLog(final SensorLogsEntity log,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __insertionAdapterOfSensorLogsEntity.insert(log);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object insertTrackingSession(final TrackingSessionEntity session,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __insertionAdapterOfTrackingSessionEntity.insert(session);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object updateDailySteps(final DailyStepsEntity dailySteps,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __updateAdapterOfDailyStepsEntity.handle(dailySteps);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object updateHourlySteps(final HourlyStepsEntity hourlySteps,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __updateAdapterOfHourlyStepsEntity.handle(hourlySteps);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object getDailyStepsByDate(final long date,
      final Continuation<? super DailyStepsEntity> $completion) {
    final String _sql = "SELECT * FROM DailySteps WHERE date = ? LIMIT 1";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, date);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<DailyStepsEntity>() {
      @Override
      @Nullable
      public DailyStepsEntity call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfDate = CursorUtil.getColumnIndexOrThrow(_cursor, "date");
          final int _cursorIndexOfSteps = CursorUtil.getColumnIndexOrThrow(_cursor, "steps");
          final int _cursorIndexOfDistance = CursorUtil.getColumnIndexOrThrow(_cursor, "distance");
          final int _cursorIndexOfCalories = CursorUtil.getColumnIndexOrThrow(_cursor, "calories");
          final int _cursorIndexOfWalkingTime = CursorUtil.getColumnIndexOrThrow(_cursor, "walkingTime");
          final int _cursorIndexOfGoal = CursorUtil.getColumnIndexOrThrow(_cursor, "goal");
          final int _cursorIndexOfCreatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "createdAt");
          final DailyStepsEntity _result;
          if (_cursor.moveToFirst()) {
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final long _tmpDate;
            _tmpDate = _cursor.getLong(_cursorIndexOfDate);
            final int _tmpSteps;
            _tmpSteps = _cursor.getInt(_cursorIndexOfSteps);
            final float _tmpDistance;
            _tmpDistance = _cursor.getFloat(_cursorIndexOfDistance);
            final float _tmpCalories;
            _tmpCalories = _cursor.getFloat(_cursorIndexOfCalories);
            final long _tmpWalkingTime;
            _tmpWalkingTime = _cursor.getLong(_cursorIndexOfWalkingTime);
            final int _tmpGoal;
            _tmpGoal = _cursor.getInt(_cursorIndexOfGoal);
            final long _tmpCreatedAt;
            _tmpCreatedAt = _cursor.getLong(_cursorIndexOfCreatedAt);
            _result = new DailyStepsEntity(_tmpId,_tmpDate,_tmpSteps,_tmpDistance,_tmpCalories,_tmpWalkingTime,_tmpGoal,_tmpCreatedAt);
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
  public Flow<DailyStepsEntity> getDailyStepsByDateFlow(final long date) {
    final String _sql = "SELECT * FROM DailySteps WHERE date = ? LIMIT 1";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, date);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"DailySteps"}, new Callable<DailyStepsEntity>() {
      @Override
      @Nullable
      public DailyStepsEntity call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfDate = CursorUtil.getColumnIndexOrThrow(_cursor, "date");
          final int _cursorIndexOfSteps = CursorUtil.getColumnIndexOrThrow(_cursor, "steps");
          final int _cursorIndexOfDistance = CursorUtil.getColumnIndexOrThrow(_cursor, "distance");
          final int _cursorIndexOfCalories = CursorUtil.getColumnIndexOrThrow(_cursor, "calories");
          final int _cursorIndexOfWalkingTime = CursorUtil.getColumnIndexOrThrow(_cursor, "walkingTime");
          final int _cursorIndexOfGoal = CursorUtil.getColumnIndexOrThrow(_cursor, "goal");
          final int _cursorIndexOfCreatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "createdAt");
          final DailyStepsEntity _result;
          if (_cursor.moveToFirst()) {
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final long _tmpDate;
            _tmpDate = _cursor.getLong(_cursorIndexOfDate);
            final int _tmpSteps;
            _tmpSteps = _cursor.getInt(_cursorIndexOfSteps);
            final float _tmpDistance;
            _tmpDistance = _cursor.getFloat(_cursorIndexOfDistance);
            final float _tmpCalories;
            _tmpCalories = _cursor.getFloat(_cursorIndexOfCalories);
            final long _tmpWalkingTime;
            _tmpWalkingTime = _cursor.getLong(_cursorIndexOfWalkingTime);
            final int _tmpGoal;
            _tmpGoal = _cursor.getInt(_cursorIndexOfGoal);
            final long _tmpCreatedAt;
            _tmpCreatedAt = _cursor.getLong(_cursorIndexOfCreatedAt);
            _result = new DailyStepsEntity(_tmpId,_tmpDate,_tmpSteps,_tmpDistance,_tmpCalories,_tmpWalkingTime,_tmpGoal,_tmpCreatedAt);
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
  public Object getHourlySteps(final long date, final int hour,
      final Continuation<? super HourlyStepsEntity> $completion) {
    final String _sql = "SELECT * FROM HourlySteps WHERE date = ? AND hour = ? LIMIT 1";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 2);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, date);
    _argIndex = 2;
    _statement.bindLong(_argIndex, hour);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<HourlyStepsEntity>() {
      @Override
      @Nullable
      public HourlyStepsEntity call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfDate = CursorUtil.getColumnIndexOrThrow(_cursor, "date");
          final int _cursorIndexOfHour = CursorUtil.getColumnIndexOrThrow(_cursor, "hour");
          final int _cursorIndexOfSteps = CursorUtil.getColumnIndexOrThrow(_cursor, "steps");
          final HourlyStepsEntity _result;
          if (_cursor.moveToFirst()) {
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final long _tmpDate;
            _tmpDate = _cursor.getLong(_cursorIndexOfDate);
            final int _tmpHour;
            _tmpHour = _cursor.getInt(_cursorIndexOfHour);
            final int _tmpSteps;
            _tmpSteps = _cursor.getInt(_cursorIndexOfSteps);
            _result = new HourlyStepsEntity(_tmpId,_tmpDate,_tmpHour,_tmpSteps);
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
  public Flow<List<DailyStepsEntity>> getAllDailyStepsFlow() {
    final String _sql = "SELECT * FROM DailySteps ORDER BY date DESC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"DailySteps"}, new Callable<List<DailyStepsEntity>>() {
      @Override
      @NonNull
      public List<DailyStepsEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfDate = CursorUtil.getColumnIndexOrThrow(_cursor, "date");
          final int _cursorIndexOfSteps = CursorUtil.getColumnIndexOrThrow(_cursor, "steps");
          final int _cursorIndexOfDistance = CursorUtil.getColumnIndexOrThrow(_cursor, "distance");
          final int _cursorIndexOfCalories = CursorUtil.getColumnIndexOrThrow(_cursor, "calories");
          final int _cursorIndexOfWalkingTime = CursorUtil.getColumnIndexOrThrow(_cursor, "walkingTime");
          final int _cursorIndexOfGoal = CursorUtil.getColumnIndexOrThrow(_cursor, "goal");
          final int _cursorIndexOfCreatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "createdAt");
          final List<DailyStepsEntity> _result = new ArrayList<DailyStepsEntity>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final DailyStepsEntity _item;
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final long _tmpDate;
            _tmpDate = _cursor.getLong(_cursorIndexOfDate);
            final int _tmpSteps;
            _tmpSteps = _cursor.getInt(_cursorIndexOfSteps);
            final float _tmpDistance;
            _tmpDistance = _cursor.getFloat(_cursorIndexOfDistance);
            final float _tmpCalories;
            _tmpCalories = _cursor.getFloat(_cursorIndexOfCalories);
            final long _tmpWalkingTime;
            _tmpWalkingTime = _cursor.getLong(_cursorIndexOfWalkingTime);
            final int _tmpGoal;
            _tmpGoal = _cursor.getInt(_cursorIndexOfGoal);
            final long _tmpCreatedAt;
            _tmpCreatedAt = _cursor.getLong(_cursorIndexOfCreatedAt);
            _item = new DailyStepsEntity(_tmpId,_tmpDate,_tmpSteps,_tmpDistance,_tmpCalories,_tmpWalkingTime,_tmpGoal,_tmpCreatedAt);
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
