package com.ckt.cyl.listmaster.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.ckt.cyl.listmaster.Record;
import com.ckt.cyl.listmaster.db.RecordSchema.RecordTable;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * Created by D22434 on 2017/7/21.
 */

public class RecordLab {
    private static RecordLab sRecordLab;

    private Context mContext;
    private SQLiteDatabase mDataBase;

    public static RecordLab get(Context context) {
        if (sRecordLab == null) {
            sRecordLab = new RecordLab(context);
        }
        return sRecordLab;
    }

    private RecordLab(Context context) {
        mContext = context.getApplicationContext();
        mDataBase = new RecordBaseHelper(mContext).getWritableDatabase();
    }

    /**
     * 插入记录
     *
     * @param Record
     */
    public void addRecord(Record Record) {
        ContentValues values = getContentValues(Record);
        mDataBase.insert(RecordTable.NAME, null, values);
    }

    /**
     * 返回Record列表
     */
    public List<Record> getmRecords() {
        List<Record> Records = new ArrayList<>();
        RecordCursorWrapper cursor = queryRecords(null, null, "time desc");
        try {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                Records.add(cursor.getRecord());
                cursor.moveToNext();
            }
        } finally {
            cursor.close();
        }
        return Records;
    }

    /**
     * 根据UUID 查找record
     *
     * @param id
     * @return
     */
    public Record getRecord(UUID id) {

        RecordCursorWrapper cursor = queryRecords(
                RecordTable.Cols.UUID + " = ?",
                new String[]{id.toString()}, "time desc");
        try {
            if (cursor.getCount() == 0) {
                return null;
            }
            cursor.moveToFirst();
            return cursor.getRecord();
        } finally {
            cursor.close();
        }
    }

    /**
     * 删除记录
     *
     * @param Record
     */
    public void deleteRecord(Record Record) {
        String uuidString = Record.getId().toString();
        mDataBase.delete(RecordTable.NAME,
                RecordTable.Cols.UUID + " = ?", new String[]{uuidString});
    }


    /**
     * 更新记录
     *
     * @param Record
     */
    public void updateRecord(Record Record) {
        String uuidString = Record.getId().toString();
        ContentValues values = getContentValues(Record);
        mDataBase.update(RecordTable.NAME, values,
                RecordTable.Cols.UUID + " = ?", new String[]{uuidString});
    }

    /**
     * 使用cursor封装方法
     *
     * @param whereClause
     * @param whereArgs
     * @return
     */
    private RecordCursorWrapper queryRecords(String whereClause, String[] whereArgs, String orderBy) {
        Cursor cursor = mDataBase.query(
                RecordTable.NAME,
                null,
                whereClause,
                whereArgs,
                null,
                null,
                orderBy
        );
        return new RecordCursorWrapper(cursor);
    }

    /**
     * 封装contentValues
     *
     * @param record
     * @return
     */
    private static ContentValues getContentValues(Record record) {
        ContentValues values = new ContentValues();
        values.put(RecordTable.Cols.UUID, record.getId());
        values.put(RecordTable.Cols.TITLE, record.getTitle());
        values.put(RecordTable.Cols.CONTENT, record.getContent());
        values.put(RecordTable.Cols.TIME, new Date().getTime());
        values.put(RecordTable.Cols.DATE, new Date().getTime());
        values.put(RecordTable.Cols.MODE, record.getMode());
        values.put(RecordTable.Cols.STATUS, record.isStatus() ? 1 : 0);
        values.put(RecordTable.Cols.LEVEL, record.getLevel());
        return values;
    }

}
