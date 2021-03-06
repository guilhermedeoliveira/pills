package com.josenaves.pills.data.local;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.josenaves.pills.BuildConfig;
import com.josenaves.pills.R;
import com.josenaves.pills.data.PhraseDataSource;
import com.josenaves.pills.data.model.Phrase;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import static com.josenaves.pills.data.local.PillsDbHelper.TRUE;

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
                String citation = record[0];
                String author = record[1];
                final Phrase phrase = new Phrase(author, citation);

                //save on database
                savePhrase(phrase, new NewPhraseCallback() {
                    @Override
                    public void onPhraseSaved(Phrase phrase) {
                        if (BuildConfig.DEBUG) Log.d(TAG, "Phrase successfully imported.");
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
    public Phrase getRandomPhrase() {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Phrase phrase = null;
        String orderBy = "RANDOM()"; // Hardcoding avoid

        final Cursor c = db.query(
                PhraseContract.PhraseEntry.TABLE_NAME,
                getProjection(),
                null,
                null,
                null, null,
                orderBy);

        if (c != null && c.getCount() > 0) {
            if (c.moveToNext()) {
                phrase = createPhrase(c);
            }
        }

        if (c != null) {
            c.close();
        }

        return phrase;
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

    @Override
    public long countPhrases() {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor query = db.rawQuery("select count(*) from " + PhraseContract.PhraseEntry.TABLE_NAME, null);
        query.moveToFirst();
        long count= query.getInt(0);
        query.close();
        return count;
    }

    @Override
    public List<Phrase> getPhrasesByAuthor(String author) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        List<Phrase> phrases = new ArrayList<>();

        final String selection = PhraseContract.PhraseEntry.COLUMN_NAME_AUTHOR + " =?";
        final String[] selectionArgs = { author };

        final Cursor c = db.query(
                PhraseContract.PhraseEntry.TABLE_NAME,
                getProjection(),
                selection,
                selectionArgs,
                null, null, null);

        if (c != null && c.getCount() > 0) {
            while (c.moveToNext()) {
                phrases.add(createPhrase(c));
            }
        }

        if (c != null) {
            c.close();
        }

        return phrases;
    }

    @Override
    public void updateFavorite(int phraseId, boolean favorite) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String filter = String.format("%s=%d", PhraseContract.PhraseEntry._ID, phraseId);
        ContentValues values = new ContentValues();
        values.put(PhraseContract.PhraseEntry.COLUMN_NAME_FAVORITE, favorite);

        int rows = db.update(PhraseContract.PhraseEntry.TABLE_NAME, values, filter, null);
        if (BuildConfig.DEBUG) Log.d(TAG, String.format("Rows updated: %s", rows));
    }

    @Override
    public void incrementPhraseViews(Phrase phrase) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String filter = String.format("%s=%d", PhraseContract.PhraseEntry._ID, phrase.getId());
        ContentValues values = new ContentValues();
        values.put(PhraseContract.PhraseEntry.COLUMN_NAME_VIEWS, phrase.getViews() + 1);

        int rows = db.update(PhraseContract.PhraseEntry.TABLE_NAME, values, filter, null);
        if (BuildConfig.DEBUG) Log.d(TAG, String.format("Rows updated: %s", rows));
    }

    private Phrase createPhrase(final Cursor cursor) {
        int id = cursor.getInt(cursor.getColumnIndex(PhraseContract.PhraseEntry._ID));
        String phrase = cursor.getString(cursor.getColumnIndex(PhraseContract.PhraseEntry.COLUMN_NAME_PHRASE));
        String author = cursor.getString(cursor.getColumnIndex(PhraseContract.PhraseEntry.COLUMN_NAME_AUTHOR));
        long views = cursor.getLong(cursor.getColumnIndex(PhraseContract.PhraseEntry.COLUMN_NAME_VIEWS));
        long shared = cursor.getLong(cursor.getColumnIndex(PhraseContract.PhraseEntry.COLUMN_NAME_SHARED));
        boolean favorite = cursor.getLong(cursor.getColumnIndex(PhraseContract.PhraseEntry.COLUMN_NAME_FAVORITE)) == 1;

        return new Phrase(id, author, phrase, views, shared, favorite);
    }

    private String[] getProjection() {
        return new String[] {
                PhraseContract.PhraseEntry._ID,
                PhraseContract.PhraseEntry.COLUMN_NAME_PHRASE,
                PhraseContract.PhraseEntry.COLUMN_NAME_AUTHOR,
                PhraseContract.PhraseEntry.COLUMN_NAME_VIEWS,
                PhraseContract.PhraseEntry.COLUMN_NAME_SHARED,
                PhraseContract.PhraseEntry.COLUMN_NAME_FAVORITE
        };
    }
}
