package com.josenaves.pills.ui;

import com.josenaves.pills.BasePresenter;
import com.josenaves.pills.BaseView;
import com.josenaves.pills.data.model.Phrase;

interface PhraseContract {

    interface View extends BaseView<Presenter> {
        void setLoadingIndicator(boolean active);
        void showPhrase(Phrase phrase);
        boolean isActive();
        void showShareChooser(String phrase);
    }

    interface Presenter extends BasePresenter {
        void loadPhrase();
        void getPhraseToShare();
        void markAsFavorite();
        void unmarkAsFavorite();
        void trackShareEvent(String phrase);
    }
}