package com.josenaves.pills.data.local;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.josenaves.pills.R;
import com.josenaves.pills.data.PhraseDataSource;
import com.josenaves.pills.data.model.Phrase;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Provides access to phrases.
 */
public class PhraseDataSourceImpl implements PhraseDataSource {

    private static final String TAG = PhraseDataSourceImpl.class.getSimpleName();

    private Context context;

    private PillsDbHelper dbHelper;

    public PhraseDataSourceImpl(Context context) {
        this.context = context;
        this.dbHelper = PillsDbHelper.getInstance(context);
    }

    @Override
    public void importPhrases() {
        // read phases.txt file and save it on database
        try {
            InputStreamReader is = new InputStreamReader(context.getResources().openRawResource(R.raw.phrases));
            BufferedReader reader = new BufferedReader(is);
            String line;
            while ((line = reader.readLine()) != null) {
                // parse line
                String[] record = line.split(SEPARATOR);
                final Phrase phrase = new Phrase(record[1], record[0], 0, 0);

                //save on database
                savePhrase(phrase, new NewPhraseCallback() {
                    @Override
                    public void onPhraseSaved(Phrase phrase) {
                        Log.d(TAG, "Phrase successfully imported.");
                    }

                    @Override
                    public void onError(String errorMessage) {
                        Log.w(TAG, String.format("Error importing phrase - %s - %s", phrase.toString(), errorMessage));
                    }
                });
            }

            is.close();
            reader.close();

        } catch (IOException io) {
            Log.e(TAG, String.format("Error reading phrases file - %s", io.getMessage()));
        }
    }

    @Override
    public void savePhrase(Phrase phrase, NewPhraseCallback callback) {
        try {
            SQLiteDatabase db = dbHelper.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put(PhraseContract.PhraseEntry.COLUMN_NAME_AUTHOR, phrase.getAuthor());
            values.put(PhraseContract.PhraseEntry.COLUMN_NAME_PHRASE, phrase.getPhrase());
            values.put(PhraseContract.PhraseEntry.COLUMN_NAME_SHARED, phrase.getShared());
            values.put(PhraseContract.PhraseEntry.COLUMN_NAME_VIEWS, phrase.getViews());
            db.insert(PhraseContract.PhraseEntry.TABLE_NAME, null, values);
            callback.onPhraseSaved(phrase);
        } catch (Throwable t) {
            callback.onError(t.getMessage());
        }
    }

}
