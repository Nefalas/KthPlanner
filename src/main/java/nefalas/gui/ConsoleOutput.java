package nefalas.gui;

import javax.swing.*;
import java.io.IOException;
import java.io.OutputStream;

public class ConsoleOutput extends OutputStream {

    private JTextArea console;

    public ConsoleOutput(JTextArea textArea) {
        console = textArea;
    }

    public void write(int b) throws IOException {
        console.append(String.valueOf((char)b));
    }

}
