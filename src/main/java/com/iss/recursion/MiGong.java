package com.iss.recursion;

public class MiGong {
    public static void main(String[] args) {
        int[][] map = createMap();
        print(map);
        boolean isSuccess = setWay(map, 1, 1);
        System.out.println(isSuccess);
        print(map);
    }

    private static int[][] createMap() {
        int[][] map = new int[8][7];
        for (int i = 0; i < 7; i++) {
            map[0][i] = 1;
            map[7][i] = 1;
        }
        for (int i = 0; i < 8; i++) {
            map[i][0] = 1;
            map[i][6] = 1;
        }
        map[3][1] = 1;
        map[3][2] = 1;
        map[5][3] = 1;
        map[5][4] = 1;
        map[5][5] = 1;
        map[5][6] = 1;
        return map;
    }

    static void print(int[][] map) {
        System.out.println("迷宫地图：");
        for (int[] row : map) {
            for (int item : row) {
                System.out.print(item + " ");
            }
            System.out.println();
        }
    }

    static boolean setWay(int[][] map, int i, int j) {
        if (map[6][5] == 2) { // 通路已经找到ok
            return true;
        } else {
            if (map[i][j] == 0) { // 未走
                map[i][j] = 2; // 假设可以走通
                if (setWay(map, i + 1, j)) { // 向下
                    return true;
                } else if (setWay(map, i, j + 1)) { // 向右
                    return true;
                } else if (setWay(map, i - 1, j)) { // 向上
                    return true;
                } else if (setWay(map, i, j - 1)) { // 向左
                    return true;
                } else {
                    map[i][j] = 3;
                    return false;
                }
            } else {  // 1墙 2走过了 3不通
                return false;
            }
        }
    }
}
