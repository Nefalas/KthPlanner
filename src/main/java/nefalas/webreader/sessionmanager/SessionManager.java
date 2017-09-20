package nefalas.webreader.sessionmanager;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.WebClient;
import nefalas.webreader.WebReader;

import java.util.ArrayList;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class SessionManager {

    private static ArrayList<Session> sessions = new ArrayList<>();

    public static WebClient getWebClientByUsername(String username, String password) {
        Session session;
        if ((session = getSessionByUsername(username)) == null) {
            session = new Session(username, password);
            if (!session.login()) {
                return null;
            }
            sessions.add(session);
            System.out.println("No session for " + username + ", creating a new one");
        }
        return session.webClient;
    }

    private static Session getSessionByUsername(String username) {
        for (Session session : sessions) {
            if (session.username.equals(username)) {
                session.updateLastUse();
                System.out.println("Found session for " + username + ", updating last use");
                return session;
            }
        }
        return null;
    }

    public static void updateSessionsAtRate(int delay) {
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                System.out.println("\n-- Session update started --\n");
                ArrayList<Session> toRemove = new ArrayList<>();
                for (Session session : sessions) {
                    if (!session.keepAlive()) {
                        System.out.println("    * Destroying session for " + session.username + "\n");
                        toRemove.add(session);
                    }
                }
                sessions.removeAll(toRemove);
                System.out.println("-- Session update ended --\n");
            }
        }, delay, delay);

    }

    static class Session {

        String username;
        private String password;
        private Date lastUse;

        WebClient webClient;

        private final long LIMIT_ELAPSED = 300000; // 5 minutes
        private final long LIMIT_ALIVE = 600000; // 10 minutes


        Session(String username, String password) {
            this.username = username;
            this.password = password;
            this.lastUse = new Date();

            this.webClient = new WebClient(BrowserVersion.CHROME);
        }

        void updateLastUse() {
            lastUse = new Date();
        }

        boolean login() {
            return new WebReader(username, password, webClient).login();
        }

        boolean keepAlive() {
            if (shouldBeDestroyed()) {
                return false;
            }
            System.out.println("    * Refreshing session for " + username);
            return refreshSession();
        }

        private boolean refreshSession() {
            if (new WebReader(username, password, webClient).login()) {
                System.out.println("    * Session refreshed for " + username + "\n");
                return true;
            }
            System.out.println("    * Could not refresh session for " + username + "\n");
            return false;
        }

        private boolean shouldBeRefreshed() {
            long elapsed = (new Date().getTime()) - lastUse.getTime();
            return elapsed > LIMIT_ELAPSED;
        }

        private boolean shouldBeDestroyed() {
            long elapsed = (new Date().getTime()) - lastUse.getTime();
            return elapsed > LIMIT_ALIVE;
        }

    }
}
