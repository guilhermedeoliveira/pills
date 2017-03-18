package com.josenaves.pills.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

import com.josenaves.pills.R;
import com.josenaves.pills.data.Injection;

import static com.google.common.base.Preconditions.checkNotNull;

public class PhraseActivity extends AppCompatActivity {

    private PhrasePresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // get the view
        PhraseView phraseView = new PhraseView(this);
        checkNotNull(phraseView, "phraseView not found");
        setContentView(phraseView);

        // Create the presenter
        presenter = new PhrasePresenter(
                Injection.providePhraseRepository(this),
                Injection.provideSessionRepository(this),
                Injection.provideTrackingRepository(),
                phraseView);
    }

    @Override protected void onResume() {
        super.onResume();
        presenter.start();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_share:
                presenter.getPhraseToShare();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
