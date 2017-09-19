package nefalas.webreader.sessionmanager;

import java.util.Date;

public class Session {

    private String username;
    private Date lastUse;

    private final long LIMIT = 900000; // 15 minutes

    public Session(String username) {
        this.username = username;
        this.lastUse = new Date();
    }

    public void keepAlive() {
        if ((new Date().getTime()) - lastUse.getTime() > LIMIT) {
            refreshSession();
        }
    }

    private void refreshSession() {

    }

}
