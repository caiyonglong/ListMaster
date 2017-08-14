

package com.ckt.cyl.listmaster.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.ckt.cyl.listmaster.db.RecordSchema.RecordTable;

/**
 * Created by D22434 on 2017/8/1.
 */

public class RecordBaseHelper extends SQLiteOpenHelper {

    /**
     * 数据库字段名
     * _id	记录ID
     * uuid	标志ID
     * title	标题
     * content	内容
     * time	创建时间
     * date	日期
     * status	状态（是否完成）
     * cycle	周期
     */

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
                RecordTable.Cols.DATE + ", " +
                RecordTable.Cols.MODE + ", " +
                RecordTable.Cols.STATUS + ", " +
                RecordTable.Cols.LEVEL +
                ")");
        db.execSQL("create table " + RecordTable.CONSUMPTION_NAME + "(" +
                " _id integer primary key autoincrement, " +
                RecordTable.Cols.UUID + ", " +
                RecordTable.Cols.TITLE + ", " +
                RecordTable.Cols.TIME + ", " +
                RecordTable.Cols.MONEY +
                ")");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
