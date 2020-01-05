package com.huawei.recursion;

public class Queue8 {
    static int max = 8;
    static int[] arr = new int[max];

    static int count = 0;
    static int judgeCount = 0;

    public static void main(String[] args) {
        check(0);
        System.out.println(count + "种解法。");
        System.out.println(judgeCount);
    }

    static void check(int n) {
        // 全部放置完了
        if (n == max) {
            print();
            return;
        }
        // 依次放入皇后
        for (int i = 0; i < max; i++) {
            // 先把皇后依次放入所有格子
            arr[n] = i;
            // 判断冲突
            if (judge(n)) {
                // 不冲突
                check(n + 1);
            }
            // 冲突，进入本行的下一个位置
        }
    }

    // 查看当我们放置第n个皇后时，就去监测该皇后和前面已经摆放的皇后冲突
    static boolean judge(int n) {
        judgeCount++;
        for (int i = 0; i < n; i++) {
            if (arr[i] == arr[n]
                    || Math.abs(n - i) == Math.abs(arr[n] - arr[i])) {
                return false;
            }
        }
        return true;
    }

    // 将皇后摆放的位置打印出来
    static void print() {
        count++;
        for (int i : arr) {
            System.out.print(i + " ");
        }
        System.out.println();
    }
}
