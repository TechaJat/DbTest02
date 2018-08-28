package com.example.dbtest02.models;

import android.provider.BaseColumns;

public class AnswerContract {

    private AnswerContract() {}

    public static class AnswerEntry implements BaseColumns {
        public static final String TABLE_NAME = "answers";
        public static final String _ID = "_id";
        public static final String COLUMN_NAME_DEBUG = "debug";
        public static final String COLUMN_NAME_Q1 = "q1";
        public static final String COLUMN_NAME_Q2 = "q2";
    }
}
