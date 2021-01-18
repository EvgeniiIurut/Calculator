package com.IurutGUI.main;

import java.util.*;

public class Calculator {
    public String startCalc(String str) {
        Stack<Integer> numbers = new Stack<>(); // stack for accumulate all the numbers
        Stack<String> signs = new Stack<>(); // stack for accumulate all the signs
        int k_numbers = 0;
        int k_signs = 0;
        int k_open_parantnes = 0;
        int k_close_parantnes = 0;
        int k_num_temp = 0;
        StringBuilder temp_number = new StringBuilder();
        List<Character> numbersAll = Arrays.asList('+', '-', '*', '/');
        int agregate = 0;
        char[] chars = (str + " ").toCharArray(); // created array of chars from string
        for (int i = 0; i < chars.length; i++) {
            if (chars[i] == ' ' && signs.empty()) {
                return numbers.pop() + " " + k_numbers + " " + k_signs;
            } else if (chars[i] == ' ') {
                while (!signs.empty()) {
                    int variable1 = numbers.pop();
                    int variable2 = numbers.pop();
                    k_numbers -= 2;
                    switch (signs.peek()) {
                        case "*":
                            agregate = variable1 * variable2;
                            signs.pop();
                            k_signs--;
                            break;
                        case "/":
                            agregate = variable2 / variable1;
                            signs.pop();
                            k_signs--;
                            break;
                        case "+":
                            agregate = variable2 + variable1;
                            signs.pop();
                            k_signs--;
                            break;
                        case "-":
                            agregate = variable2 - variable1;
                            signs.pop();
                            k_signs--;
                            break;
                    }
                    numbers.push(agregate);
                }
                return numbers.pop() + "";
            } else if ((i + 1 < chars.length) && chars[i + 1] == '(') {
                k_signs++;
                signs.push(chars[i] + "");
            }
            /*----------------------------------------------------------------------*/
            else if (chars[i] <= '9' && chars[i] >= '0') {
                temp_number.append(chars[i]);
                if (((i + 1) < chars.length) && (chars[i + 1] < '0' || chars[i + 1] > '9')) {
                    numbers.push(Integer.parseInt(temp_number.toString()));
                    k_numbers++;
                    temp_number = new StringBuilder();
                }
            }
            /*-----------------------------------------------------------------------*/
            else if (numbersAll.contains(chars[i]) && (signs.empty() || signs.peek().equals("("))) {
                k_signs++;
                signs.push(chars[i] + "");
            } else if (chars[i] == '(') {
                k_open_parantnes++;
                k_signs++;
                signs.push(chars[i] + "");
            } else if (chars[i] == ')') {
                k_close_parantnes++;
                while (!signs.peek().equals("(")) {
                    int variable1 = numbers.pop();
                    int variable2 = numbers.pop();
                    k_numbers -= 2;
                    switch (signs.peek()) {
                        case "*":
                            agregate = variable1 * variable2;
                            signs.pop();
                            k_signs--;
                            break;
                        case "/":
                            agregate = variable2 / variable1;
                            signs.pop();
                            k_signs--;
                            break;
                        case "+":
                            agregate = variable2 + variable1;
                            signs.pop();
                            k_signs--;
                            break;
                        case "-":
                            agregate = variable2 - variable1;
                            signs.pop();
                            k_signs--;
                            break;
                    }
                    numbers.push(agregate);
                    k_numbers++;
                }
                signs.pop();
                k_signs--;
            } else if (chars[i] == '*' || chars[i] == '/') {
                if (signs.peek().equals("+") || signs.peek().equals("-")) {
                    numbers.push(Integer.parseInt(chars[i + 1] + ""));
                    int variable1 = numbers.pop();
                    int variable2 = numbers.pop();
                    k_numbers -= 2;
                    if (signs.peek().equals("*")) {
                        agregate = variable1 * variable2;
                        signs.pop();
                        k_signs--;
                    } else if (signs.peek().equals("/")) {
                        agregate = variable2 / variable1;
                        signs.pop();
                        k_signs--;
                    }
                    numbers.push(agregate);
                    k_numbers++;
                } else if (signs.peek().equals("*") || signs.peek().equals("/")) {
                    int variable1 = numbers.pop();
                    int variable2 = numbers.pop();
                    k_numbers -= 2;
                    if (signs.peek().equals("*")) {
                        agregate = variable1 * variable2;
                        signs.pop();
                        k_signs--;
                    } else if (signs.peek().equals("/")) {
                        agregate = variable2 / variable1;
                        signs.pop();
                        k_signs--;
                    }
                    numbers.push(agregate);
                    k_numbers++;
                }
                signs.push(chars[i] + "");
                k_signs++;
            } else if (chars[i] == '+' || chars[i] == '-') {
                while (!signs.empty()) {
                    if (signs.peek().equals("*") || signs.peek().equals("/")) {
                        int variable1 = numbers.pop();
                        int variable2 = numbers.pop();
                        k_numbers -= 2;
                        if (signs.peek().equals("*")) {
                            agregate = variable1 * variable2;
                            signs.pop();
                            k_signs--;
                        } else if (signs.peek().equals("/")) {
                            agregate = variable2 / variable1;
                            signs.pop();
                            k_signs--;
                        }
                        numbers.push(agregate);
                        k_numbers++;
                    } else {
                        int variable1 = numbers.pop();
                        int variable2 = numbers.pop();
                        k_numbers -= 2;
                        if (signs.peek().equals("+")) {
                            agregate = variable1 + variable2;
                            signs.pop();
                            k_signs--;
                        } else if (signs.peek().equals("-")) {
                            agregate = variable2 - variable1;
                            signs.pop();
                            k_signs--;
                        }
                        numbers.push(agregate);
                        k_numbers++;
                    }
                }
                signs.push(chars[i] + "");
                k_signs++;
            }
        }
        String temp = numbers.toString();
        String temp1 = signs.toString();
        return temp + temp1 + k_numbers + k_signs;
    }
}

