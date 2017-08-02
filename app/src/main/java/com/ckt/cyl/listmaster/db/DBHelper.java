

package com.ckt.cyl.listmaster.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.ckt.cyl.listmaster.db.ListSchema.ListTable;

/**
 * Created by D22434 on 2017/8/1.
 */

public class DBHelper extends SQLiteOpenHelper {
    private static final int VERSION = 1;

    private static final String DATABASE_NAME = "listMaster.db";

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + ListTable.NAME + "(" +
                " _id integer primary key autoincrement, " +
                ListTable.Cols.UUID + ", " +
                ListTable.Cols.TITLE + ", " +
                ListTable.Cols.DATE + ", " +
                ListTable.Cols.TIME + ", " +
                ListTable.Cols.SUSPECT + ", " +
                ListTable.Cols.NUMBER +
                ")");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
