package com.josenaves.pills;

import android.app.Application;

import com.crashlytics.android.Crashlytics;
import com.josenaves.pills.data.Injection;
import com.josenaves.pills.data.PhraseRepository;
import com.josenaves.pills.data.local.PillsDbHelper;

import io.fabric.sdk.android.Fabric;

/**
 * Created by josenaves on 9/17/16.
 */
public class PillsApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        Fabric.with(this, new Crashlytics());
        PillsDbHelper.getInstance(this);

        PhraseRepository repo = Injection.providePhraseRepository(this);
        repo.importPhrases();
    }
}
