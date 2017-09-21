package nefalas.gui;

import nefalas.webreader.sessionmanager.SessionManager.Session;
import nefalas.webreader.sessionmanager.SessionManager;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.Date;

class SessionPanel extends JPanel {

    private static final Color PANEL_BACKGROUND_COLOR = new Color(245, 245, 245);
    private static final Color HEADER_BACKGROUND_COLOR = new Color(94, 155, 141);
    private static final Color CARD_BACKGROUND_COLOR = new Color(193, 222, 215);

    private JPanel panel;
    private JProgressBar progressBar;

    SessionPanel() {
        setLayout(new BorderLayout());
        setBackground(PANEL_BACKGROUND_COLOR);

        panel = new JPanel();
        panel.setBackground(PANEL_BACKGROUND_COLOR);
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        add(new Header(), BorderLayout.PAGE_START);

        ModernScrollPane scrollPane = new ModernScrollPane(panel, ModernScrollPane.SCROLLBAR_COLOR.DARK, 18);
        add(scrollPane, BorderLayout.CENTER);
    }

    void updateSessions() {
        panel.removeAll();
        for (Session session : SessionManager.getSessions()) {
            String username = session.username;
            long lastUsed = (new Date().getTime()) - session.lastUse.getTime();
            panel.add(new SessionCard(username, lastUsed));
        }
        panel.revalidate();
        panel.repaint();
    }

    void setProgress(int progress) {
        progressBar.setValue(progress);
    }

    class Header extends JPanel {

        Header() {
            setLayout(new BorderLayout());
            setBackground(HEADER_BACKGROUND_COLOR);

            JLabel titleLabel = new JLabel("Sessions", SwingConstants.CENTER);
            titleLabel.setFont(new Font("Sans Serif", Font.PLAIN, 40));
            titleLabel.setForeground(Color.WHITE);
            titleLabel.setBorder(new EmptyBorder(15,0,25,0));
            add(titleLabel, BorderLayout.CENTER);

            progressBar = new JProgressBar(0, 100);
            progressBar.setValue(0);
            progressBar.setStringPainted(true);
            add(progressBar, BorderLayout.PAGE_END);
        }
    }

    class SessionCard extends JPanel {

        GridBagConstraints gbc = new GridBagConstraints();

        SessionCard(String username, long lastUsed) {
            setMaximumSize(new Dimension(2000, 100));
            setMinimumSize(new Dimension(0, 100));
            setLayout(new BorderLayout());

            setBorder(BorderFactory.createMatteBorder(10, 0, 0, 0, PANEL_BACKGROUND_COLOR));

            JPanel innerPanel = new JPanel();
            innerPanel.setLayout(new GridBagLayout());
            innerPanel.setBackground(CARD_BACKGROUND_COLOR);
            innerPanel.setBorder(new EmptyBorder(5,5,5,5));

            gbc.fill = GridBagConstraints.BOTH;

            gbc.weightx = 1.0;
            gbc.weighty = 1.0;

            gbc.gridx = 0;
            gbc.gridy = 0;
            Label usernameLabel = new Label(username);
            usernameLabel.setFont(new Font("Sans Serif", Font.BOLD, 16));
            innerPanel.add(usernameLabel, gbc);

            gbc.weighty = 0.7;

            gbc.gridy = 1;
            int secondsLU = (int) (lastUsed/1000)%60;
            int minutesLU = (int) (lastUsed/(1000*60))%60;
            String textLU = "Last used: " + ((minutesLU > 0)? minutesLU + " min " : "") + secondsLU + " sec ago";
            Label lastUsedLabel = new Label(textLU);
            innerPanel.add(lastUsedLabel, gbc);

            gbc.gridy = 2;
            int secondsEI = 60 - secondsLU;
            int minutesEI = 9 - minutesLU;
            String textEI = (minutesEI < 0)? "Expires at next refresh" : "Expires in: " + minutesEI + " min " + secondsEI;
            Label expiresInLabel = new Label(textEI);
            innerPanel.add(expiresInLabel, gbc);

            add(innerPanel, BorderLayout.CENTER);
        }

    }

}
