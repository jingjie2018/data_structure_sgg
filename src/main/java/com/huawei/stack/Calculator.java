package com.huawei.stack;

public class Calculator {
    public static void main(String[] args) {
        ArrayStack2<Integer> numStack = new ArrayStack2<>(10);
        ArrayStack2<Character> operStack = new ArrayStack2<>(10);
        String expression = "3+2*6-3/3+2";

        int index = expression.length() - 1;
        int num1 = 0;
        int num2 = 0;
        int oper = 0;
        int res = 0;
        char ch = ' ';
        String keepNum = "";
        while (true) {
            if (index < 0) {
                break;
            }
            ch = expression.substring(index, index + 1).charAt(0);
            index--;
            if (operStack.isOper(ch)) {
                if (operStack.isEmpty()) {
                    operStack.push(ch);
                } else {
                    // 如果操作符栈不为空
                    if (operStack.priority(ch) <= operStack.priority(operStack.peek())) {
                        num1 = numStack.pop();
                        num2 = numStack.pop();
                        oper = operStack.pop();
                        res = numStack.cal(num1, num2, oper);
                        numStack.push(res);
                        operStack.push(ch);
                    } else {
                        operStack.push(ch);
                    }
                }
            } else {
                keepNum += ch;
                numStack.push(ch - 48);
            }
        }

        while (true) {
            if (operStack.isEmpty()) {
                break;
            }
            num1 = numStack.pop();
            num2 = numStack.pop();
            oper = operStack.pop();
            res = numStack.cal(num1, num2, oper);
            numStack.push(res);
        }
        System.out.println(numStack.peek());
    }
}

class ArrayStack2<E> {
    private int maxSize;
    private Object[] stack;
    private int top = -1;

    public ArrayStack2(int maxSize) {
        this.maxSize = maxSize;
        this.stack = new Object[maxSize];
    }

    public boolean isFull() {
        return top == maxSize - 1;
    }

    public boolean isEmpty() {
        return top == -1;
    }

    public void push(E ele) {
        if (isFull()) {
            throw new RuntimeException("栈满。");
        }
        stack[++top] = ele;
    }

    public E pop() {
        if (isEmpty()) {
            throw new RuntimeException("栈空。");
        }
        E val = (E) stack[top];
        top--;
        return val;
    }

    public void list() {
        if (isEmpty()) {
            throw new RuntimeException("栈空");
        }
        for (int i = top; i >= 0; i--) {
            System.out.println(stack[i]);
        }
    }

    public int priority(int oper) {
        if (oper == '*' || oper == '/') {
            return 1;
        }
        if (oper == '+' || oper == '-') {
            return 0;
        }
        return -1;
    }

    public boolean isOper(char val) {
        return val == '+' || val == '-' || val == '*' || val == '/';
    }

    public int cal(int num1, int num2, int oper) {
        int res = 0;
        switch (oper) {
            case '+':
                res = num1 + num2;
                break;
            case '-':
                res = num1 - num2;
                break;
            case '*':
                res = num1 * num2;
                break;
            case '/':
                res = num1 / num2;
                break;
            default:
                break;
        }
        return res;
    }

    public E peek() {
        return (E) stack[top];
    }
}
