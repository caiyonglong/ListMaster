

package com.ckt.cyl.listmaster.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.ckt.cyl.listmaster.Record;
import com.ckt.cyl.listmaster.db.RecordSchema.RecordTable;
import com.ckt.cyl.listmaster.db.RecordSchema.RecordTable;

/**
 * Created by D22434 on 2017/8/1.
 */

public class RecordBaseHelper extends SQLiteOpenHelper {
    private static final int VERSION = 1;

    private static final String DATABASE_NAME = "listMaster.db";

    public RecordBaseHelper(Context context) {
        super(context, DATABASE_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + RecordTable.NAME + "(" +
                " _id integer primary key autoincrement, " +
                RecordTable.Cols.UUID + ", " +
                RecordTable.Cols.TITLE + ", " +
                RecordTable.Cols.CONTENT + ", " +
                RecordTable.Cols.TIME + ", " +
                RecordTable.Cols.REPEAT + ", " +
                RecordTable.Cols.REPEAT_TIMES + ", " +
                RecordTable.Cols.STATUS + ", " +
                RecordTable.Cols.SCHEDULE +
                ")");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
