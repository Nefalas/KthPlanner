package nefalas.kthplanner;

import nefalas.gui.ConsoleOutput;
import nefalas.gui.MainWindow;
import nefalas.webreader.sessionmanager.SessionManager;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.PrintStream;

@SpringBootApplication
public class KthPlannerApplication {

    private static final int SESSION_UPDATE_RATE = 2 * 60 * 1000;

    public static void main(String[] args) {
        MainWindow mainWindow = new MainWindow();
        mainWindow.updateSessionUiAtRate(1000);
        mainWindow.setProgressBarUpdateRate(SESSION_UPDATE_RATE);

        SessionManager.updateSessionsAtRate(SESSION_UPDATE_RATE, mainWindow);

        config(mainWindow);

        SpringApplication.run(KthPlannerApplication.class, args);
    }

    private static void config(MainWindow mainWindow) {
        PrintStream out = new PrintStream(new ConsoleOutput(mainWindow.getConsole()));
        System.setOut(out);
        System.setErr(out);
    }

}
