package nefalas.kthplanner;

import nefalas.webreader.sessionmanager.SessionManager;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class KthPlannerApplication {

    public static void main(String[] args) {
        SpringApplication.run(KthPlannerApplication.class, args);
        SessionManager.updateSessionsAtRate(2 * 60 * 1000);
    }

}
