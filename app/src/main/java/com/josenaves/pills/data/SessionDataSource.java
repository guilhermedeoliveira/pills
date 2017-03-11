package com.josenaves.pills.data;

import com.josenaves.pills.data.model.Session;

/**
 * Data source to keep session data persisted using shared properties.
 */
public interface SessionDataSource {
    void saveSession(Session session);
    Session loadSession();
}
