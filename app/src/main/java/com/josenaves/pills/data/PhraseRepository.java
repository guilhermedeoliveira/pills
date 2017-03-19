package com.josenaves.pills.data;

import android.util.Log;

import com.josenaves.pills.data.model.Phrase;

import java.util.List;

public class PhraseRepository implements PhraseDataSource {

    private static final String TAG = PhraseRepository.class.getSimpleName();

    private final PhraseDataSource phraseDataSource;

    PhraseRepository(PhraseDataSource phraseDataSource) {
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

    @Override
    public long countPhrases() {
        return phraseDataSource.countPhrases();
    }

    @Override
    public List<Phrase> getPhrasesByAuthor(String author) {
        return phraseDataSource.getPhrasesByAuthor(author);
    }

    @Override
    public Phrase getRandomPhrase() {
        Log.d(TAG, "Getting random phrase...");
        return phraseDataSource.getRandomPhrase();
    }

    @Override
    public void updateFavorite(int phraseId, boolean favorite) {
        Log.d(TAG, String.format("Updating favorite flag (%s) for phraseId %d...", favorite, phraseId));
        phraseDataSource.updateFavorite(phraseId, favorite);
    }

    @Override
    public void incrementPhraseViews(Phrase phrase) {
        Log.d(TAG, String.format("Incrementing views for phrase %s...", phrase));
        phraseDataSource.incrementPhraseViews(phrase);
    }
}
