package nefalas.webreader.sessionmanager;

import java.util.ArrayList;

public class SessionManager {

    private static ArrayList<Session> sessions = new ArrayList<>();

    public SessionManager() {}

    public void addSession(Session session) {
        sessions.add(session);
    }

    public void killSession(Session session) {
        sessions.remove(session);
    }

    public static ArrayList<Session> getSessions() {
        return sessions;
    }

    public void update() {
        for (Session session : sessions) {
            session.keepAlive();
        }
    }
}
