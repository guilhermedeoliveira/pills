package com.josenaves.pills.data;

import android.util.Log;

import com.josenaves.pills.data.model.Phrase;

public class PhraseRepository implements PhraseDataSource {

    private static final String TAG = PhraseRepository.class.getSimpleName();

    private final PhraseDataSource phraseDataSource;

    public PhraseRepository(PhraseDataSource phraseDataSource) {
        this.phraseDataSource = phraseDataSource;
    }

    @Override
    public void importPhrases() {
        phraseDataSource.importPhrases();
    }

    @Override
    public void savePhrase(Phrase phrase, final NewPhraseCallback callback) {
        phraseDataSource.savePhrase(phrase, new NewPhraseCallback() {
            @Override
            public void onPhraseSaved(Phrase phrase) {
                callback.onPhraseSaved(phrase);
            }

            @Override
            public void onError(String errorMessage) {
                Log.w(TAG, "No phrases imported");
                callback.onError(errorMessage);
            }
        });
    }
}
