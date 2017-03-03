package com.josenaves.pills.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.josenaves.pills.data.Injection;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by guilhermeoliveira on 23/09/16.
 */

public class PhraseActivity extends AppCompatActivity {

    private PhrasePresenter presenter;
    private PhraseView phraseView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // get the view
        phraseView = new PhraseView(this);
        checkNotNull(phraseView, "phraseView not found");
        setContentView(phraseView);

        // Create the presenter
        presenter = new PhrasePresenter(
                Injection.providePhraseRepository(getApplicationContext()),
                phraseView);
    }

    @Override protected void onResume() {
        super.onResume();
        presenter.start();
    }


//    /**
//     * Get a phrase in database
//     */
//    private void getPhraseInDatabase() {
//        SQLiteDatabase db = dbHelper.getReadableDatabase();
//
//        String[] projection = {
//                PhraseEntry.COLUMN_NAME_PHRASE,
//                PhraseEntry.COLUMN_NAME_AUTHOR
//        };
//        Cursor cursor = db.query(
//                PhraseEntry.TABLE_NAME,
//                projection,
//                null,
//                null,
//                null,
//                null,
//                null);
//
//        TextView phraseView = (TextView) findViewById(R.id.phrase_text_view);
//        TextView authorView = (TextView) findViewById(R.id.author_text_view);
//
//        boolean success = cursor.moveToFirst();
//        if (success) {
//            int phraseColumnIndex = cursor.getColumnIndex(PhraseEntry.COLUMN_NAME_PHRASE);
//            int authorColumnIndex = cursor.getColumnIndex(PhraseEntry.COLUMN_NAME_AUTHOR);
//            String phrase = cursor.getString(phraseColumnIndex);
//            String author = cursor.getString(authorColumnIndex);
//
//            phraseView.setText(phrase);
//            authorView.setText(author);
//        }
//
//
//        cursor.close();
//
//    }
}
