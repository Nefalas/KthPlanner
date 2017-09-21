package nefalas.gui;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.text.DefaultCaret;
import java.awt.*;

class ConsolePanel extends JPanel {

    JTextArea console;

    ConsolePanel() {
        setLayout(new BorderLayout());
        setBackground(Color.BLACK);
        setBorder(new EmptyBorder(5, 5, 5, 5));
        createConsole();

        ModernScrollPane scrollPane = new ModernScrollPane(console, ModernScrollPane.SCROLLBAR_COLOR.LIGHT, 10);
        add(scrollPane, BorderLayout.CENTER);
    }

    private void createConsole() {
        console = new JTextArea();
        DefaultCaret caret = (DefaultCaret)console.getCaret();
        caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);
        console.setBackground(Color.BLACK);
        console.setForeground(Color.WHITE);
        console.setBorder(null);
        console.setFont(new Font("Lucida Sans", Font.PLAIN, 14));
    }

}
