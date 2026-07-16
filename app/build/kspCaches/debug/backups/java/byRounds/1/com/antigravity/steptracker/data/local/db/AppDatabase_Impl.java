package com.antigravity.steptracker.data.local.db;

import androidx.annotation.NonNull;
import androidx.room.DatabaseConfiguration;
import androidx.room.InvalidationTracker;
import androidx.room.RoomOpenHelper;
import androidx.room.RoomOpenHelper.Delegate;
import androidx.room.RoomOpenHelper.ValidationResult;
import androidx.room.migration.AutoMigrationSpec;
import androidx.room.migration.Migration;
import androidx.room.util.DBUtil;
import androidx.room.util.TableInfo;
import androidx.room.util.TableInfo.Column;
import androidx.room.util.TableInfo.ForeignKey;
import androidx.room.util.TableInfo.Index;
import androidx.sqlite.db.SupportSQLiteDatabase;
import androidx.sqlite.db.SupportSQLiteOpenHelper;
import androidx.sqlite.db.SupportSQLiteOpenHelper.Callback;
import androidx.sqlite.db.SupportSQLiteOpenHelper.Configuration;
import com.antigravity.steptracker.data.local.db.dao.StepDao;
import com.antigravity.steptracker.data.local.db.dao.StepDao_Impl;
import java.lang.Class;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

@SuppressWarnings({"unchecked", "deprecation"})
public final class AppDatabase_Impl extends AppDatabase {
  private volatile StepDao _stepDao;

  @Override
  protected SupportSQLiteOpenHelper createOpenHelper(DatabaseConfiguration configuration) {
    final SupportSQLiteOpenHelper.Callback _openCallback = new RoomOpenHelper(configuration, new RoomOpenHelper.Delegate(1) {
      @Override
      public void createAllTables(SupportSQLiteDatabase _db) {
        _db.execSQL("CREATE TABLE IF NOT EXISTS `DailySteps` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `date` INTEGER NOT NULL, `steps` INTEGER NOT NULL, `distance` REAL NOT NULL, `calories` REAL NOT NULL, `walkingTime` INTEGER NOT NULL, `goal` INTEGER NOT NULL, `createdAt` INTEGER NOT NULL)");
        _db.execSQL("CREATE TABLE IF NOT EXISTS `HourlySteps` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `date` INTEGER NOT NULL, `hour` INTEGER NOT NULL, `steps` INTEGER NOT NULL)");
        _db.execSQL("CREATE TABLE IF NOT EXISTS `SensorLogs` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `timestamp` INTEGER NOT NULL, `sensorValue` REAL NOT NULL, `bootId` INTEGER NOT NULL)");
        _db.execSQL("CREATE TABLE IF NOT EXISTS `TrackingSession` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `startTime` INTEGER NOT NULL, `endTime` INTEGER NOT NULL, `steps` INTEGER NOT NULL, `distance` REAL NOT NULL, `calories` REAL NOT NULL, `duration` INTEGER NOT NULL)");
        _db.execSQL("CREATE TABLE IF NOT EXISTS room_master_table (id INTEGER PRIMARY KEY,identity_hash TEXT)");
        _db.execSQL("INSERT OR REPLACE INTO room_master_table (id,identity_hash) VALUES(42, 'bf744ca624271b6531edb68560700d92')");
      }

      @Override
      public void dropAllTables(SupportSQLiteDatabase _db) {
        _db.execSQL("DROP TABLE IF EXISTS `DailySteps`");
        _db.execSQL("DROP TABLE IF EXISTS `HourlySteps`");
        _db.execSQL("DROP TABLE IF EXISTS `SensorLogs`");
        _db.execSQL("DROP TABLE IF EXISTS `TrackingSession`");
        if (mCallbacks != null) {
          for (int _i = 0, _size = mCallbacks.size(); _i < _size; _i++) {
            mCallbacks.get(_i).onDestructiveMigration(_db);
          }
        }
      }

      @Override
      public void onCreate(SupportSQLiteDatabase _db) {
        if (mCallbacks != null) {
          for (int _i = 0, _size = mCallbacks.size(); _i < _size; _i++) {
            mCallbacks.get(_i).onCreate(_db);
          }
        }
      }

      @Override
      public void onOpen(SupportSQLiteDatabase _db) {
        mDatabase = _db;
        internalInitInvalidationTracker(_db);
        if (mCallbacks != null) {
          for (int _i = 0, _size = mCallbacks.size(); _i < _size; _i++) {
            mCallbacks.get(_i).onOpen(_db);
          }
        }
      }

      @Override
      public void onPreMigrate(SupportSQLiteDatabase _db) {
        DBUtil.dropFtsSyncTriggers(_db);
      }

      @Override
      public void onPostMigrate(SupportSQLiteDatabase _db) {
      }

      @Override
      public RoomOpenHelper.ValidationResult onValidateSchema(SupportSQLiteDatabase _db) {
        final HashMap<String, TableInfo.Column> _columnsDailySteps = new HashMap<String, TableInfo.Column>(8);
        _columnsDailySteps.put("id", new TableInfo.Column("id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsDailySteps.put("date", new TableInfo.Column("date", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsDailySteps.put("steps", new TableInfo.Column("steps", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsDailySteps.put("distance", new TableInfo.Column("distance", "REAL", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsDailySteps.put("calories", new TableInfo.Column("calories", "REAL", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsDailySteps.put("walkingTime", new TableInfo.Column("walkingTime", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsDailySteps.put("goal", new TableInfo.Column("goal", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsDailySteps.put("createdAt", new TableInfo.Column("createdAt", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysDailySteps = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesDailySteps = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoDailySteps = new TableInfo("DailySteps", _columnsDailySteps, _foreignKeysDailySteps, _indicesDailySteps);
        final TableInfo _existingDailySteps = TableInfo.read(_db, "DailySteps");
        if (! _infoDailySteps.equals(_existingDailySteps)) {
          return new RoomOpenHelper.ValidationResult(false, "DailySteps(com.antigravity.steptracker.data.local.db.entity.DailyStepsEntity).\n"
                  + " Expected:\n" + _infoDailySteps + "\n"
                  + " Found:\n" + _existingDailySteps);
        }
        final HashMap<String, TableInfo.Column> _columnsHourlySteps = new HashMap<String, TableInfo.Column>(4);
        _columnsHourlySteps.put("id", new TableInfo.Column("id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsHourlySteps.put("date", new TableInfo.Column("date", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsHourlySteps.put("hour", new TableInfo.Column("hour", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsHourlySteps.put("steps", new TableInfo.Column("steps", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysHourlySteps = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesHourlySteps = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoHourlySteps = new TableInfo("HourlySteps", _columnsHourlySteps, _foreignKeysHourlySteps, _indicesHourlySteps);
        final TableInfo _existingHourlySteps = TableInfo.read(_db, "HourlySteps");
        if (! _infoHourlySteps.equals(_existingHourlySteps)) {
          return new RoomOpenHelper.ValidationResult(false, "HourlySteps(com.antigravity.steptracker.data.local.db.entity.HourlyStepsEntity).\n"
                  + " Expected:\n" + _infoHourlySteps + "\n"
                  + " Found:\n" + _existingHourlySteps);
        }
        final HashMap<String, TableInfo.Column> _columnsSensorLogs = new HashMap<String, TableInfo.Column>(4);
        _columnsSensorLogs.put("id", new TableInfo.Column("id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsSensorLogs.put("timestamp", new TableInfo.Column("timestamp", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsSensorLogs.put("sensorValue", new TableInfo.Column("sensorValue", "REAL", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsSensorLogs.put("bootId", new TableInfo.Column("bootId", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysSensorLogs = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesSensorLogs = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoSensorLogs = new TableInfo("SensorLogs", _columnsSensorLogs, _foreignKeysSensorLogs, _indicesSensorLogs);
        final TableInfo _existingSensorLogs = TableInfo.read(_db, "SensorLogs");
        if (! _infoSensorLogs.equals(_existingSensorLogs)) {
          return new RoomOpenHelper.ValidationResult(false, "SensorLogs(com.antigravity.steptracker.data.local.db.entity.SensorLogsEntity).\n"
                  + " Expected:\n" + _infoSensorLogs + "\n"
                  + " Found:\n" + _existingSensorLogs);
        }
        final HashMap<String, TableInfo.Column> _columnsTrackingSession = new HashMap<String, TableInfo.Column>(7);
        _columnsTrackingSession.put("id", new TableInfo.Column("id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsTrackingSession.put("startTime", new TableInfo.Column("startTime", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsTrackingSession.put("endTime", new TableInfo.Column("endTime", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsTrackingSession.put("steps", new TableInfo.Column("steps", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsTrackingSession.put("distance", new TableInfo.Column("distance", "REAL", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsTrackingSession.put("calories", new TableInfo.Column("calories", "REAL", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsTrackingSession.put("duration", new TableInfo.Column("duration", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysTrackingSession = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesTrackingSession = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoTrackingSession = new TableInfo("TrackingSession", _columnsTrackingSession, _foreignKeysTrackingSession, _indicesTrackingSession);
        final TableInfo _existingTrackingSession = TableInfo.read(_db, "TrackingSession");
        if (! _infoTrackingSession.equals(_existingTrackingSession)) {
          return new RoomOpenHelper.ValidationResult(false, "TrackingSession(com.antigravity.steptracker.data.local.db.entity.TrackingSessionEntity).\n"
                  + " Expected:\n" + _infoTrackingSession + "\n"
                  + " Found:\n" + _existingTrackingSession);
        }
        return new RoomOpenHelper.ValidationResult(true, null);
      }
    }, "bf744ca624271b6531edb68560700d92", "408983dc6f91aa7179a8ec8f7bc73b04");
    final SupportSQLiteOpenHelper.Configuration _sqliteConfig = SupportSQLiteOpenHelper.Configuration.builder(configuration.context)
        .name(configuration.name)
        .callback(_openCallback)
        .build();
    final SupportSQLiteOpenHelper _helper = configuration.sqliteOpenHelperFactory.create(_sqliteConfig);
    return _helper;
  }

  @Override
  protected InvalidationTracker createInvalidationTracker() {
    final HashMap<String, String> _shadowTablesMap = new HashMap<String, String>(0);
    HashMap<String, Set<String>> _viewTables = new HashMap<String, Set<String>>(0);
    return new InvalidationTracker(this, _shadowTablesMap, _viewTables, "DailySteps","HourlySteps","SensorLogs","TrackingSession");
  }

  @Override
  public void clearAllTables() {
    super.assertNotMainThread();
    final SupportSQLiteDatabase _db = super.getOpenHelper().getWritableDatabase();
    try {
      super.beginTransaction();
      _db.execSQL("DELETE FROM `DailySteps`");
      _db.execSQL("DELETE FROM `HourlySteps`");
      _db.execSQL("DELETE FROM `SensorLogs`");
      _db.execSQL("DELETE FROM `TrackingSession`");
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
  protected Map<Class<?>, List<Class<?>>> getRequiredTypeConverters() {
    final HashMap<Class<?>, List<Class<?>>> _typeConvertersMap = new HashMap<Class<?>, List<Class<?>>>();
    _typeConvertersMap.put(StepDao.class, StepDao_Impl.getRequiredConverters());
    return _typeConvertersMap;
  }

  @Override
  public Set<Class<? extends AutoMigrationSpec>> getRequiredAutoMigrationSpecs() {
    final HashSet<Class<? extends AutoMigrationSpec>> _autoMigrationSpecsSet = new HashSet<Class<? extends AutoMigrationSpec>>();
    return _autoMigrationSpecsSet;
  }

  @Override
  public List<Migration> getAutoMigrations(
      @NonNull Map<Class<? extends AutoMigrationSpec>, AutoMigrationSpec> autoMigrationSpecsMap) {
    return Arrays.asList();
  }

  @Override
  public StepDao stepDao() {
    if (_stepDao != null) {
      return _stepDao;
    } else {
      synchronized(this) {
        if(_stepDao == null) {
          _stepDao = new StepDao_Impl(this);
        }
        return _stepDao;
      }
    }
  }
}
