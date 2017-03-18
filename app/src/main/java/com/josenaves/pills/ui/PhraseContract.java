package com.josenaves.pills.ui;

import android.app.Activity;

import com.josenaves.pills.BasePresenter;
import com.josenaves.pills.BaseView;
import com.josenaves.pills.data.model.Phrase;

public interface PhraseContract {

    interface View extends BaseView<Presenter> {
        void setLoadingIndicator(boolean active);
        void showPhrase(Phrase phrase);
        boolean isActive();
    }

    interface Presenter extends BasePresenter {
        void loadPhrase();
        void sharePhrase(Activity activity); // TODO Coloquei a Activity como input
        void markAsFavorite();
    }
}

