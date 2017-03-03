package com.josenaves.pills.ui;

import android.support.annotation.NonNull;
import com.josenaves.pills.data.PhraseRepository;
import com.josenaves.pills.data.model.Phrase;

import static com.google.common.base.Preconditions.checkNotNull;

public class PhrasePresenter implements PhraseContract.Presenter {

    private final PhraseRepository phraseRepository;

    private final PhraseContract.View phraseView;

    public PhrasePresenter(@NonNull PhraseRepository phraseRepository,
                           @NonNull PhraseContract.View phraseView) {

        this.phraseRepository = checkNotNull(phraseRepository, "phraseRepository cannot be null!");
        this.phraseView = checkNotNull(phraseView, "phraseView cannot be null!");

        this.phraseView.setPresenter(this);
    }

    @Override
    public void start() {
        loadPhrase();
    }

    @Override
    public void loadPhrase() {
        // TODO move this to another thread
        Phrase phrase = phraseRepository.getRandomPhrase();
        if (phrase != null) phraseView.showPhrase(phrase);
    }

    @Override
    public void sharePhrase() {
        // TODO
    }

    @Override
    public void markAsFavorite() {
        // TODO
    }
}
