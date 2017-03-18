package com.josenaves.pills.ui;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.util.Log;

import android.support.v4.app.ShareCompat;
import android.text.TextUtils;
import android.util.Log;

import com.josenaves.pills.PillsApplication;
import com.josenaves.pills.BuildConfig;
import com.josenaves.pills.data.PhraseRepository;
import com.josenaves.pills.data.SessionRepository;
import com.josenaves.pills.data.model.Phrase;
import com.josenaves.pills.data.model.Session;
import com.josenaves.pills.util.DateUtils;

import static com.google.common.base.Preconditions.checkNotNull;

public class PhrasePresenter implements PhraseContract.Presenter {

    public static final String TAG = PhrasePresenter.class.getSimpleName();

    private final PhraseRepository phraseRepository;
    private final SessionRepository sessionRepository;

    private final PhraseContract.View phraseView;

    public PhrasePresenter(@NonNull PhraseRepository phraseRepository,
                           @NonNull SessionRepository sessionRepository,
                           @NonNull PhraseContract.View phraseView) {

        this.phraseRepository = checkNotNull(phraseRepository, "phraseRepository cannot be null!");
        this.sessionRepository = checkNotNull(sessionRepository, "sessionRepository cannot be null!");
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

        // check if there is a saved phrase
        Session session = sessionRepository.loadSession();
        if (TextUtils.isEmpty(session.getCurrentPhrase())) {
            if (BuildConfig.DEBUG) Log.d(TAG, "There's no saved phrase yet... getting the first one !");
            getAndShowNewPhrase();
        } else {
            // check if saved date is today
            if (!DateUtils.getCurrentDate().equals(session.getDate())) {
                if (BuildConfig.DEBUG) Log.d(TAG, "This is another day... go get new phrase!");
                if (BuildConfig.DEBUG) Log.d(TAG, String.format("Current date: %s - session date: %s",
                        DateUtils.getCurrentDate(), session.getDate()));
                getAndShowNewPhrase();
            } else {
                if (BuildConfig.DEBUG) Log.d(TAG, "No need to get another phrase for today...");
                Phrase phrase = new Phrase(session.getAuthor(), session.getCurrentPhrase());
                phraseView.showPhrase(phrase);
            }
        }
    }

    private void getAndShowNewPhrase() {
        // make it happen
        Phrase phrase = phraseRepository.getRandomPhrase();
        if (phrase != null) phraseView.showPhrase(phrase);

        if (BuildConfig.DEBUG) Log.d(TAG, phrase.toString());

        // save session on database
        sessionRepository
                .saveSession(new Session(phrase.getPhrase(), phrase.getAuthor(),
                        DateUtils.getCurrentDate(), true));

        // increment the number of views
        phraseRepository.incrementPhraseViews(phrase);
    }

    @Override
    public void sharePhrase(Activity activity) {
        // TODO
        String mimeType = "text/plain";
        String shareablePhrase = sessionRepository.loadSession()
                .getShareablePhrase();

        ShareCompat.IntentBuilder
                .from(activity)
                .setType(mimeType)
                .setText(shareablePhrase)
                .startChooser();
    }

    @Override
    public void markAsFavorite() {
        // TODO
    }
}
