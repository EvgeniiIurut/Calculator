package com.IurutGUI.main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Arrays;
import java.util.List;

public class Panel extends JPanel {

    private JButton numbers[] = new JButton[10];
    private Font font = new Font("SanSerif", Font.BOLD, 20);
    private JTextField output = new JTextField();
    private JButton backspace = new JButton("<");
    private JButton dot = new JButton(".");
    private JButton equ = new JButton("=");
    private JButton plus = new JButton("+");
    private JButton minus = new JButton("-");
    private JButton multi = new JButton("*");
    private JButton div = new JButton("/");
    private JButton parentheses1 = new JButton("(");
    private JButton parentheses2 = new JButton(")");
    private JButton del = new JButton("C");
    Calculator calculator = new Calculator();

    private List<Character> charsAll = Arrays.asList('+', '-', '*', '/','(',')','.');

    public Panel() {
        setLayout(null); // allow to set any location of buttons
        setFocusable(true);
        grabFocus();
        //---------------
        backspace.setBounds(250, 190, 50, 50);
        backspace.setFont(font);
        add(backspace);
        //---------------
        dot.setBounds(10, 250, 50, 50);
        dot.setFont(font);
        add(dot);
        //---------------
        equ.setBounds(130, 250, 50, 50);
        equ.setFont(font);
        add(equ);
        //---------------
        plus.setBounds(190, 70, 50, 50);
        plus.setFont(font);
        add(plus);
        //---------------
        minus.setBounds(190, 130, 50, 50);
        minus.setFont(font);
        add(minus);
        //---------------
        parentheses1.setBounds(250, 70, 50, 50);
        parentheses1.setFont(font);
        add(parentheses1);
        //---------------
        parentheses2.setBounds(250, 130, 50, 50);
        parentheses2.setFont(font);
        add(parentheses2);
        //---------------
        multi.setBounds(190, 190, 50, 50);
        multi.setFont(font);
        add(multi);
        //---------------
        div.setBounds(190, 250, 50, 50);
        div.setFont(font);
        add(div);
        //---------------
        del.setBounds(250, 250, 50, 50);
        del.setFont(font);
        add(del);
        //---------------
        numbers[0] = new JButton("0");
        numbers[0].setBounds(70,250, 50, 50);
        numbers[0].setFont(font);
        add(numbers[0]);

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                numbers[i * 3 + j + 1] = new JButton(i * 3 + j + 1 + "");
                numbers[i * 3 + j + 1].setBounds(j * (60) + 10, i * (60) + 70, 50, 50);
                numbers[i * 3 + j + 1].setFont(font);
                add(numbers[i * 3 + j + 1]);
            }
        }
        output.setBounds(10, 10, 290, 50);
        output.setEditable(false);
        output.setFont(font);
        add(output);

        ActionListener l = (ActionEvent e) -> {
            JButton b = (JButton) e.getSource();
            output.setText(output.getText() + b.getText());
        };
        for (JButton b : numbers) {
            b.addActionListener(l);
        }
        plus.addActionListener(l);
        minus.addActionListener(l);
        multi.addActionListener(l);
        div.addActionListener(l);
        parentheses1.addActionListener(l);
        parentheses2.addActionListener(l);
        dot.addActionListener(l);

        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                char symbol = e.getKeyChar();
                if (Character.isDigit(symbol) || (charsAll.contains(symbol))) {
                    output.setText(output.getText() + symbol);
                }
                else return;
            }
        });

        del.addActionListener (e -> {
            String temp = output.getText ();
            output.setText (null);
        });

        backspace.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String temp = output.getText();
                    output.setText(temp.substring(0, temp.length() - 1));
                }catch (Exception b) {
                    System.out.println("Значение отсутстует");
                }
            }
        });

        equ.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String temp = output.getText();
                    output.setText(calculator.startCalc(temp));
                }catch (Exception b) {
                    System.out.println("Значение отсутстует");
                    output.setText("Ошибка ввода выражения");
                }
            }
        });



    }
}
