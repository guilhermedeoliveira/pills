package com.josenaves.pills.ui;

import android.content.Context;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.josenaves.pills.R;
import com.josenaves.pills.data.model.Phrase;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by josenaves on 3/3/17.
 */

public class PhraseView extends LinearLayout implements PhraseContract.View {

    private PhraseContract.Presenter presenter;

    private LinearLayout linearLayout;
    private TextView phraseTextView;
    private TextView authorTextView;

    private boolean active;

    public PhraseView(Context context) {
        super(context);
        init();
    }

    private void init() {
        inflate(getContext(), R.layout.activity_phrase, this);

        // set up main view
        linearLayout = (LinearLayout) findViewById(R.id.phrase_layout);
        phraseTextView = (TextView) findViewById(R.id.phrase_text_view);
        authorTextView = (TextView) findViewById(R.id.author_text_view);

        /*
         * TODO:
         * This is correct with the actual implementation because the view passed to the presenter
         * is already attached to an Activity
         */
        active = true;
    }

    @Override
    public void setPresenter(PhraseContract.Presenter presenter) {
        this.presenter = checkNotNull(presenter);
    }

    @Override
    public void setLoadingIndicator(boolean active) {

    }

    // TODO Modifiquei a assinatura do método
    @Override
    public void showPhrase(Phrase phrase) {
        phraseTextView.setText(phrase.getPhrase());
        authorTextView.setText(phrase.getAuthor());
    }

    @Override
    public boolean isActive() {
        return active;
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        active = true;
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        active = false;
    }
}
