package net.kaeff.zazu;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import org.joda.time.LocalDateTime;

import java.util.LinkedList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "zazu";
    public static final int DATABASE_VERSION = 2;
    private static final String ACTIVITY_LOG = "ACTIVITY_LOG";
    private static final String DAY = "DAY";
    private static final String TYPE = "TYPE";
    private static final String DICTIONARY_TABLE_CREATE =
            "CREATE TABLE " + ACTIVITY_LOG + " (" +
                    DAY + " DATE, " +
                    TYPE + " TEXT);";


    DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);

    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(DICTIONARY_TABLE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void insert(TimeLog timeLog) {
        ContentValues values = new ContentValues();
        values.put(DAY, timeLog.getDateTime().toString());
        values.put(TYPE, timeLog.getType().name());
        getWritableDatabase().insert(ACTIVITY_LOG, null, values);
    }

    public List<TimeLog> queryRecords() {
        List<TimeLog> result = new LinkedList<>();
        Cursor query = getReadableDatabase().query(ACTIVITY_LOG, new String[]{DAY, TYPE}, null, null, null, null, DAY);
        while (query.moveToNext()) {
            LocalDateTime dateTime = LocalDateTime.parse(query.getString(query.getColumnIndex(DAY)));
            TimeLog.Type type = TimeLog.Type.valueOf(query.getString(query.getColumnIndex(TYPE)));
            result.add(new TimeLog(dateTime, type));
        }
        return result;
    }
}
