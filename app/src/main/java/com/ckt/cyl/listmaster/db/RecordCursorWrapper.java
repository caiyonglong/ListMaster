package com.ckt.cyl.listmaster.db;

import android.database.Cursor;
import android.database.CursorWrapper;

import com.ckt.cyl.listmaster.Record;
import com.ckt.cyl.listmaster.db.RecordSchema.RecordTable;

import java.util.Date;

/**
 * Created by D22434 on 2017/7/24.
 */

public class RecordCursorWrapper extends CursorWrapper {
    /**
     * Creates a cursor wrapper.
     *
     * @param cursor The underlying cursor to wrap.
     */
    public RecordCursorWrapper(Cursor cursor) {
        super(cursor);
    }

    public Record getRecord() {
        String uuid = getString(getColumnIndex(RecordTable.Cols.UUID));
        String title = getString(getColumnIndex(RecordTable.Cols.TITLE));
        String content = getString(getColumnIndex(RecordTable.Cols.CONTENT));
        long time = getLong(getColumnIndex(RecordTable.Cols.TIME));
        long date = getLong(getColumnIndex(RecordTable.Cols.DATE));
        String mode = getString(getColumnIndex(RecordTable.Cols.MODE));
        int level = getInt(getColumnIndex(RecordTable.Cols.LEVEL));
        boolean status = getInt(getColumnIndex(RecordTable.Cols.STATUS)) > 0;

        Record record = new Record();
        record.setId(uuid);
        record.setTitle(title);
        record.setContent(content);
        record.setTime(new Date(time));
        record.setDate(new Date(date));
        record.setMode(mode);
        record.setLevel(level);
        record.setStatus(status);
        return record;
    }
}
