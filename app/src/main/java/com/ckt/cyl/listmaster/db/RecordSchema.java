

package com.ckt.cyl.listmaster.db;

/**
 * Created by D22434 on 2017/7/24.
 */

public class RecordSchema {

    public static final class RecordTable {
        public static final String NAME = "record";
        public static final String CONSUMPTION_NAME = "consumption";

        public static final class Cols {

            public static final String UUID = "uuid";
            public static final String TITLE = "title";
            public static final String CONTENT = "content";
            public static final String TIME = "time";
            public static final String DATE = "date";
            public static final String MODE = "mode";
            public static final String STATUS = "status";
            public static final String LEVEL = "level";


            /**
             * 消费表
             */
            public static final String MONEY = "money";

        }


    }
}
