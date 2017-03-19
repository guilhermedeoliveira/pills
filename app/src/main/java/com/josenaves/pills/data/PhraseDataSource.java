package com.josenaves.pills.data;

import com.josenaves.pills.data.model.Phrase;

import java.util.List;

/**
 * Datasource to access phrase data on database and file
 */
public interface PhraseDataSource {

    String PHRASES_FILENAME = "phrases.txt";
    String SEPARATOR = "\\|";

    void importPhrases();

    interface NewPhraseCallback {
        void onPhraseSaved(Phrase phrase);
        void onError(String errorMessage);
    }

    void savePhrase(Phrase phrase, NewPhraseCallback callback);
    long countPhrases();

    Phrase getRandomPhrase();

    List<Phrase> getPhrasesByAuthor(String author);
    void updateFavorite(int phraseId, boolean favorite);

    void incrementPhraseViews(Phrase phrase);
}
