package com.josenaves.pills.data.remote;

import android.util.Log;

import com.crashlytics.android.answers.Answers;
import com.crashlytics.android.answers.ShareEvent;
import com.josenaves.pills.BuildConfig;
import com.josenaves.pills.data.TrackingDataSource;

public class TrackingDataSourceImpl implements TrackingDataSource {

    private static final String TAG = TrackingDataSourceImpl.class.getSimpleName();

    @Override
    public void trackShareEvent(String phrase) {
        if (BuildConfig.DEBUG) Log.d(TAG, "Tracking share event...");
        Answers.getInstance().logShare(new ShareEvent()
                .putContentName(phrase)
                .putContentType("text"));
    }
}
