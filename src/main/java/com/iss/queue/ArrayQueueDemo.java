package com.iss.queue;

import java.util.Scanner;

public class ArrayQueueDemo {
    public static void main(String[] args) {
        ArrayQueue arrayQueue = new ArrayQueue(5);
        System.out.println("a(添加数据)");
        System.out.println("g(取数据)");
        System.out.println("s(打印数据)");
        System.out.println("h(队列头)");
        System.out.println("r(队列尾)");
        System.out.println("e(退出)");
        Scanner scanner = new Scanner(System.in);
        char key;
        boolean loop = true;
        while (loop) {
            System.out.println("请输入操作：");
            key = scanner.next().charAt(0);
            try {
                switch (key) {
                    case 'a':
                        System.out.println("输入一个数：");
                        int input = scanner.nextInt();
                        arrayQueue.add(input);
                        break;
                    case 'g':
                        System.out.println(arrayQueue.get());
                        break;
                    case 's':
                        arrayQueue.showQueue();
                        break;
                    case 'h':
                        System.out.println(arrayQueue.head());
                        break;
                    case 'r':
                        System.out.println(arrayQueue.rear());
                        break;
                    case 'e':
                        scanner.close();
                        loop = false;
                        break;
                    default:
                        System.out.println("不支持该操作。");
                        break;
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        System.out.println("程序退出。");
    }
}

/**
 * 使用数组模拟队列
 */
class ArrayQueue {
    private int maxSize; // 容量
    private int front; // 队列头
    private int rear; // 队列尾
    private int[] arr; // 存放数据

    public ArrayQueue(int maxSize) {
        this.maxSize = maxSize;
        arr = new int[maxSize];
        front = -1; // 指向队列头的前一个位置
        rear = -1; // 指向队列尾当前位置
    }

    // 判断队列是否满
    public boolean isFull() {
        return rear == maxSize - 1;
    }

    // 判断队列是否为空
    public boolean isEmpty() {
        return rear == front;
    }

    // 添加
    public void add(int n) {
        if (isFull()) {
            throw new RuntimeException("队列满，不能添加数据。");
        }
        rear++;
        arr[this.rear] = n;
    }

    // 取数据
    public int get() {
        if (isEmpty()) {
            throw new RuntimeException("队列空，不能取数据。");
        }
        front++;
        return arr[this.front];
    }

    // 打印队列
    public void showQueue() {
        if (isEmpty()) {
            System.out.println("队列空，不能打印数据。");
            return;
        }
        for (int i = front + 1; i <= rear; i++) {
            int ele = arr[i];
            System.out.printf("%d\t", ele);
        }
        System.out.println();
    }

    // 头
    public int head() {
        if (isEmpty()) {
            throw new RuntimeException("队列空，不能取数据。");
        }
        return arr[this.front + 1];
    }

    // 尾
    public int rear() {
        if (isEmpty()) {
            throw new RuntimeException("队列空，不能取数据。");
        }
        return arr[this.rear];
    }
}