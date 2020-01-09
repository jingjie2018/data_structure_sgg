package com.iss.stack;

public class ArrayStackDemo {
    public static void main(String[] args) {
        ArrayStack<Integer> stack = new ArrayStack<>(4);
        stack.push(1);
        stack.push(4);
        stack.push(2);
        stack.push(3);
        stack.push(3);
        stack.list();
        System.out.println(stack.pop());
        System.out.println(stack.pop());
        System.out.println(stack.pop());
        System.out.println(stack.pop());
        System.out.println(stack.pop());
    }
}

class ArrayStack<E> {
    private int maxSize;
    private Object[] stack;
    private int top = -1;

    public ArrayStack(int maxSize) {
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
}
