package nefalas.gui;


import javax.swing.*;
import java.awt.*;
import java.util.Timer;
import java.util.TimerTask;

public class MainWindow extends JFrame {

    private GridBagConstraints gbc = new GridBagConstraints();

    private ConsolePanel consolePanel;
    private SessionPanel sessionPanel;

    private static int progress = 0;
    private int rate;
    private java.util.Timer timer;
    private TimerTask timerTask;

    public MainWindow() {
        super("KTH Planner Backend");
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        Container container = getContentPane();
        container.setPreferredSize(new Dimension(1600, 900));

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new GridBagLayout());

        gbc.fill = GridBagConstraints.BOTH;

        gbc.weightx = 0.9;
        gbc.weighty = 0.6;
        gbc.gridx = 0;
        gbc.gridy = 0;
        mainPanel.add(new ControlPanel(), gbc);

        gbc.weightx = 0.1;
        gbc.weighty = 0.4;
        gbc.gridx = 0;
        gbc.gridy = 1;
        consolePanel = new ConsolePanel();
        mainPanel.add(consolePanel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.gridheight = 2;
        sessionPanel = new SessionPanel();
        mainPanel.add(sessionPanel, gbc);

        container.add(mainPanel, BorderLayout.CENTER);
        pack();
        setVisible(true);
    }

    public JTextArea getConsole() {
        return consolePanel.console;
    }

    public void updateSessionUiAtRate(int rate) {
        java.util.Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                sessionPanel.updateSessions();
            }
        }, rate, rate);
    }

    public void setProgressBarUpdateRate(int rate) {
        this.rate = rate/100;
        startProgressBar();
    }

    private void startProgressBar() {
        timer = new Timer();
        timerTask = new TimerTask() {
            @Override
            public void run() {
                if (progress < 100) {
                    progress++;
                    sessionPanel.setProgress(progress);
                }
            }
        };
        timer.scheduleAtFixedRate(timerTask, rate, rate);
    }

    public void resetProgressBar() {
        progress = 0;
        timerTask.cancel();
        timer.cancel();
        startProgressBar();
    }

}
