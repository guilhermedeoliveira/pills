package com.josenaves.pills.data.local;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.josenaves.pills.data.SessionDataSource;
import com.josenaves.pills.data.model.Session;

/**
 * Provides access to session data saved in shared properties.
 */
public class SessionDataSourceImpl implements SessionDataSource {

    private static final String TAG = SessionDataSourceImpl.class.getSimpleName();

    private Context context;

    public SessionDataSourceImpl(Context context) {
        this.context = context;
    }

    @Override
    public void saveSession(Session session) {
        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor edit = pref.edit();
        edit.putString(Session.PHRASE, session.getCurrentPhrase());
        edit.putString(Session.AUTHOR, session.getAuthor());
        edit.putString(Session.DATE, session.getDate());
        edit.commit();
    }

    @Override
    public Session loadSession() {
        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(context);
        String phrase = pref.getString(Session.PHRASE, "");
        String date = pref.getString(Session.DATE, "");
        String author = pref.getString(Session.AUTHOR, "");
        return new Session(phrase, author, date);
    }
}
