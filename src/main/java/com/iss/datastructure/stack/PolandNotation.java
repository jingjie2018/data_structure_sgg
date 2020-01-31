package com.iss.datastructure.stack;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class PolandNotation {
    public static void main(String[] args) {
        String expression = "1 ";
        List<String> middleExpressionList = toMiddleExpressionList(expression);
        System.out.println(middleExpressionList);
        List<String> suffixExpressionList = toSuffixExpressionList(middleExpressionList);
        System.out.println(suffixExpressionList);
        int result = calculate(suffixExpressionList);
        System.out.printf("result=%d", result);
    }

    static List<String> toMiddleExpressionList(String expression) {
        List<String> result = new ArrayList<>();
        int i = 0;
        String str = "";
        char c;
        do {
            c = expression.charAt(i);
            if (c == ' ') {
                i++;
                continue;
            }
            if (c < 48 || c > 57) {
                result.add(String.valueOf(c));
                i++;
            } else {
                while (i < expression.length()
                        && (c = expression.charAt(i)) >= 48
                        && (c = expression.charAt(i)) <= 57) {
                    str += c - 48;
                    i++;
                }
                result.add(str);
                str = "";
            }
        } while (i < expression.length());
        return result;
    }

    static List<String> toSuffixExpressionList(List<String> middleExpressionList) {
        List<String> result = new ArrayList<>(); // s2栈无弹栈操作直接加入返回的List
        Stack<String> s1 = new Stack<>(); // 符号栈
        for (String item : middleExpressionList) {
            // 如果是一个数，加入result
            if (item.matches("\\d+")) {
                result.add(item);
            } else if ("(".equals(item)) {
                s1.push(item);
            } else if (")".equals(item)) {
                while (!"(".equals(s1.peek())) {
                    result.add(s1.pop());
                }
                s1.pop(); // 消除)
            } else {
                // 符号栈顶符号优先级大于当前的符号优先级，取出放入result
                while (s1.size() != 0
                        && Operation.getValue(s1.peek()) >= Operation.getValue(item)) {
                    result.add(s1.pop());
                }
                // 把item压入s1
                s1.push(item);
            }
        }
        // 将s1取出放入result
        while (s1.size() != 0) {
            result.add(s1.pop());
        }
        return result;
    }

    static int calculate(List<String> suffixExpressionList) {
        Stack<String> stack = new Stack<>();
        for (String item : suffixExpressionList) {
            if (item.matches("\\d+")) {
                stack.push(item);
            } else {
                int num1 = Integer.parseInt(stack.pop());
                int num2 = Integer.parseInt(stack.pop());
                int result;
                switch (item) {
                    case "+":
                        result = num2 + num1;
                        break;
                    case "-":
                        result = num2 - num1;
                        break;
                    case "*":
                        result = num2 * num1;
                        break;
                    case "/":
                        result = num2 / num1;
                        break;
                    default:
                        throw new UnsupportedOperationException();
                }
                stack.push(String.valueOf(result));
            }
        }
        return Integer.parseInt(stack.pop());
    }
}

class Operation {
    private static int ADD = 1;
    private static int SUB = 1;
    private static int MUL = 2;
    private static int DIV = 2;

    public static int getValue(String operation) {
        switch (operation) {
            case "+":
                return ADD;
            case "-":
                return SUB;
            case "*":
                return MUL;
            case "/":
                return DIV;
            default:
                System.out.println("unsupported operation.");
                return 0;
        }
    }
}
