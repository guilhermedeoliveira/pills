package com.josenaves.pills.data;

import android.content.Context;
import android.support.annotation.NonNull;

import com.josenaves.pills.data.local.PhraseDataSourceImpl;
import com.josenaves.pills.data.local.SessionDataSourceImpl;
import com.josenaves.pills.data.remote.TrackingDataSourceImpl;

/**
 * Dependency injection without frameworks
 */
public class Injection {
    public static PhraseRepository providePhraseRepository(@NonNull Context context) {
        return new PhraseRepository(new PhraseDataSourceImpl(context));
    }

    public static SessionRepository provideSessionRepository(@NonNull Context context) {
        return new SessionRepository(new SessionDataSourceImpl(context));
    }

    public static TrackingRepository provideTrackingRepository() {
        return new TrackingRepository(new TrackingDataSourceImpl());
    }
}
