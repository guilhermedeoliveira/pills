package com.josenaves.pills.data.local;

import android.provider.BaseColumns;

/**
 * The contract used by database to save phrases in Sqlite
 */
public interface PhraseContract {
    interface PhraseEntry extends BaseColumns {
        String TABLE_NAME = "phrase";

        String COLUMN_NAME_PHRASE = "phrase";
        String COLUMN_NAME_AUTHOR = "author";
        String COLUMN_NAME_VIEWS = "views";
        String COLUMN_NAME_SHARED = "shared";
        String COLUMN_NAME_FAVORITE = "favorite";
    }
}
