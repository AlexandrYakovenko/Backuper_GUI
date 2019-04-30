package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;

public class GUI extends JFrame {

    JButton buttonFrom = new JButton("Select the directory from which...");
    JButton buttonTo = new JButton("Select the directory to which...");
    JButton buttonCopy = new JButton("Press to Copy");

    JTextField inputFrom = new JTextField("", 2);
    JTextField inputTo = new JTextField("", 2);

    public GUI() {
        super("Backuper");
        Rectangle rec = new Rectangle(500, 200);
        this.setBounds(rec);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Container container = getContentPane();
        container.setLayout(new GridLayout(3, 2, 5, 2));
        buttonFrom.setSize(100, 100);
        buttonTo.setSize(100, 100);

        buttonFrom.setLayout(new BorderLayout());
        buttonTo.setLayout(new BorderLayout());

        buttonFrom.addActionListener(new ButtonEventListener());
        buttonTo.addActionListener(new ButtonEventListener());

        container.add(buttonFrom, BorderLayout.LINE_START);
        container.add(buttonTo, BorderLayout.LINE_END);

        buttonFrom.setVisible(true);
        buttonTo.setVisible(true);

        container.add(inputFrom);
        container.add(inputTo);

        container.add(buttonCopy);

        buttonCopy.addActionListener(new ButtonEventListener() {
            public void actionPerformed(ActionEvent e) {

                String message = "Files were copied successfully!" + "\n";
                message += "From: " + inputFrom.getText() + "\n";
                message += "To: " + inputTo.getText() + "\n";
                JOptionPane.showMessageDialog(null, message, "Result", JOptionPane.PLAIN_MESSAGE);

                new Backuper(inputFrom.getText(),inputTo.getText(), 10000).run();
            }
        });
    }


    class ButtonEventListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            File file = new File("C:\\");

            Desktop desktop = Desktop.getDesktop();
            try {
                desktop.open(file);
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
    }
}





