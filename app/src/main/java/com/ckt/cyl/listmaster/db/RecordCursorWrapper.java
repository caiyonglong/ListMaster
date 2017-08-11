package com.ckt.cyl.listmaster.db;

import android.database.Cursor;
import android.database.CursorWrapper;

import com.ckt.cyl.listmaster.Record;
import com.ckt.cyl.listmaster.db.RecordSchema.RecordTable;

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

        Record record = new Record();
        record.setId(uuid);
        record.setTitle(title);
        return record;
    }
}
