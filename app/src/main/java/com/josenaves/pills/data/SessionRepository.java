package com.josenaves.pills.data;

import com.josenaves.pills.data.model.Session;

public class SessionRepository implements SessionDataSource {

    private static final String TAG = SessionRepository.class.getSimpleName();

    private final SessionDataSource sessionDataSource;

    public SessionRepository(SessionDataSource sessionDataSource) {
        this.sessionDataSource = sessionDataSource;
    }

    @Override
    public void saveSession(Session session) {
        sessionDataSource.saveSession(session);
    }

    @Override
    public Session loadSession() {
        return sessionDataSource.loadSession();
    }
}
