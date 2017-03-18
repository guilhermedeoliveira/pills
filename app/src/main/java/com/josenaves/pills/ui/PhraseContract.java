package com.josenaves.pills.ui;

import com.josenaves.pills.BasePresenter;
import com.josenaves.pills.BaseView;
import com.josenaves.pills.data.model.Phrase;

public interface PhraseContract {

    interface View extends BaseView<Presenter> {
        void setLoadingIndicator(boolean active);
        void showPhrase(Phrase phrase);
        boolean isActive();
        void showShareChooser(String phrase); // TODO new method for the chooser
    }

    interface Presenter extends BasePresenter {
        void loadPhrase();
        void getPhraseToShare(); // TODO Modifiquei a assinatura
        void markAsFavorite();
    }
}