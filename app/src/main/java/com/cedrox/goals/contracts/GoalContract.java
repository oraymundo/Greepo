package com.cedrox.goals.contracts;

import android.provider.BaseColumns;

/**
 * Created by edelarosaraymun on 1/10/16.
 */
public final class GoalContract {

    public GoalContract() {}

    public static abstract class GoalEntry implements BaseColumns {
        public static final String TABLE_NAME = "goal";
        public static final String COLUMN_NAME_ENTRY_ID = "goalid";
        public static final String COLUMN_NAME_DESCRIPTION = "description";
        public static final String COLUMN_NAME_STATUS = "status";
        public static final String COLUMN_NAME_CREATIONDATE = "creationdate";
        public static final String COLUMN_NAME_EXPECTEDDATE = "expecteddate";
        public static final String COLUMN_NAME_FINALDATE = "finaldate";
    }

}
