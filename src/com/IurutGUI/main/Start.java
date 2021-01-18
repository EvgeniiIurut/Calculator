package com.IurutGUI.main;

import javax.swing.*;

public class Start {
    private JFrame window;

    public Start(){
        window = new JFrame("Calculator");
        window.setSize(317,337); // set size of frame
        window.add(new Panel());
        window.setLocationRelativeTo(null); // set location of fame to center
        window.setResizable(false);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setVisible(true);
    }


    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Start();
            }
        });
    }
}
