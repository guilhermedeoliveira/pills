package com.josenaves.pills;

import android.app.Application;
import android.util.Log;

import com.crashlytics.android.Crashlytics;
import com.josenaves.pills.data.Injection;
import com.josenaves.pills.data.PhraseRepository;
import com.josenaves.pills.data.SessionRepository;
import com.josenaves.pills.data.local.PillsDbHelper;
import com.josenaves.pills.data.model.Session;

import io.fabric.sdk.android.Fabric;

/**
 * Created by josenaves on 9/17/16.
 */
public class PillsApplication extends Application {

    private static final String TAG = PillsApplication.class.getSimpleName();
    @Override
    public void onCreate() {
        super.onCreate();

        Fabric.with(this, new Crashlytics());
        PillsDbHelper.getInstance(this);

        SessionRepository sessionRepository = Injection.provideSessionRepository(this);
        Session session = sessionRepository.loadSession();
        if (!session.isImported()) {
            Log.d(TAG, "Importing phrases file...");
            PhraseRepository phraseRepository = Injection.providePhraseRepository(this);
            phraseRepository.importPhrases();

            session = new Session(session.getCurrentPhrase(), session.getAuthor(),
                    session.getDate(), true);
            sessionRepository.saveSession(session);
        } else {
            Log.d(TAG, "Phrases file was already imported :)");
        }


    }
}
