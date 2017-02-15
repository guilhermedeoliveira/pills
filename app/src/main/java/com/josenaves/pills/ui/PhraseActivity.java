package com.josenaves.pills.ui;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.josenaves.pills.R;
import com.josenaves.pills.data.local.PhraseContract.PhraseEntry;
import com.josenaves.pills.data.local.PillsDbHelper;

/**
 * Created by guilhermeoliveira on 23/09/16.
 */

public class PhraseActivity extends AppCompatActivity {

    PillsDbHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phrase);

        // Database helper that will provide us access to the database
        dbHelper = PillsDbHelper.getInstance(this);


        getPhraseInDatabase();
    }

    /**
     * Get a phrase in database
     */
    private void getPhraseInDatabase() {
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        String[] projection = {
                PhraseEntry.COLUMN_NAME_PHRASE,
                PhraseEntry.COLUMN_NAME_AUTHOR
        };
        Cursor cursor = db.query(
                PhraseEntry.TABLE_NAME,
                projection,
                null,
                null,
                null,
                null,
                null);

        TextView phraseView = (TextView) findViewById(R.id.phrase_text_view);
        TextView authorView = (TextView) findViewById(R.id.author_text_view);

        cursor.moveToFirst();

        int phraseColumnIndex = cursor.getColumnIndex(PhraseEntry.COLUMN_NAME_PHRASE);
        int authorColumnIndex = cursor.getColumnIndex(PhraseEntry.COLUMN_NAME_AUTHOR);
        String phrase = cursor.getString(phraseColumnIndex);
        String author = cursor.getString(authorColumnIndex);

        phraseView.setText(phrase);
        authorView.setText(author);

        cursor.close();

    }
}
