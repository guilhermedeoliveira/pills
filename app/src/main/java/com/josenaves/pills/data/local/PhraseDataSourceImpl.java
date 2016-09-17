package com.josenaves.pills.data.local;

import android.content.Context;
import android.util.Log;

import com.josenaves.pills.data.PhraseDataSource;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Provides access to phrases.
 */
public class PhraseDataSourceImpl implements PhraseDataSource {

    private static final String TAG = PhraseDataSourceImpl.class.getSimpleName();

    private Context context;

    public PhraseDataSourceImpl(Context context) {
        this.context = context;
    }

    @Override
    public void importPhrases() {
        // read phases.txt file and save it on database
        try {
            InputStreamReader is = new InputStreamReader(context.getAssets().open(PHRASES_FILENAME));
            BufferedReader reader = new BufferedReader(is);
            String line;
            while ((line = reader.readLine()) != null) {
                // parse line
                String[] record = line.split(SEPARATOR);

                String phrase = record[0];
                String author = record[1];

                // TODO save on database

                is.close();
                reader.close();
            }
        } catch (IOException io) {
            Log.e(TAG, String.format("Error reading phrases file - %s", io.getMessage()));
        }


    }
}
