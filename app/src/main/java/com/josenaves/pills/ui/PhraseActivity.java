package com.josenaves.pills.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.josenaves.pills.data.Injection;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by guilhermeoliveira on 23/09/16.
 */

public class PhraseActivity extends AppCompatActivity {

    private PhrasePresenter presenter;
    private PhraseView phraseView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // get the view
        phraseView = new PhraseView(this);
        checkNotNull(phraseView, "phraseView not found");
        setContentView(phraseView);

        // Create the presenter
        presenter = new PhrasePresenter(
                Injection.providePhraseRepository(this),
                Injection.provideSessionRepository(this),
                phraseView);
    }

    @Override protected void onResume() {
        super.onResume();
        presenter.start();
    }
}
