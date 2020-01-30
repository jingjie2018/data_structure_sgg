package com.iss.sparsearray;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.nio.channels.WritableByteChannel;
import java.util.Arrays;

public class SparseArray {
    public static void main(String[] args) {
        String path = toSparseArr();
        fromSparseArray(path);
    }

    public static void fromSparseArray(String path) {
        int[][] sparseArr = null;
        try (ReadableByteChannel readChannel = Channels.newChannel(new FileInputStream(path))) {
            ByteBuffer buffer = ByteBuffer.allocate(1024);
            StringBuilder sb = new StringBuilder();
            byte[] buf = new byte[1024];
            while (readChannel.read(buffer) != -1) {
                buffer.flip();
                buffer.get(buf, 0, buffer.limit());
                sb.append(new String(buf, 0, buffer.limit()));
                buffer.clear();
            }
            System.out.println(sb);
            String[] strArr = sb.toString().split("]\\[");
            String[] innerArr = strArr[0].split(",");
            sparseArr = new int[strArr.length][innerArr.length];
            for (int i = 0; i < strArr.length; i++) {
                String line = strArr[i].replace("[", "").replace("]", "");
                String[] ssArr = line.split(",");
                for (int j = 0; j < ssArr.length; j++) {
                    String ss = ssArr[j];
                    sparseArr[i][j] = Integer.parseInt(ss.trim());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        // 3.将稀疏数组恢复成原始的二维数组
        assert sparseArr != null;
        int rowLen = sparseArr[0][0];
        int colLen = sparseArr[0][1];
        int[][] chessArr2 = new int[rowLen][colLen];
        for (int i = 1; i < sparseArr.length; i++) {
            int[] rowArr = sparseArr[i];
            chessArr2[rowArr[0]][rowArr[1]] = rowArr[2];
        }
        System.out.println("从稀疏数组恢复的原始数组为：");
        for (int[] row : chessArr2) {
            for (int data : row) {
                System.out.printf("%d\t", data);
            }
            System.out.println();
        }
    }

    public static String toSparseArr() {
        // 创建一个原始二维数组  11*11
        // 0 表示没有棋子，1黑2蓝
        int[][] chessArr1 = new int[11][11];
        chessArr1[1][2] = 1;
        chessArr1[2][3] = 2;
        chessArr1[7][10] = 2;
        // 遍历原始二维数组
        System.out.println("原始二维数组：");
        for (int[] row : chessArr1) {
            for (int data : row) {
                System.out.printf("%d\t", data);
            }
            System.out.println();
        }
        // 将二维数组转稀疏数组
        // 1. 遍历二维数组，得到非0的数据的个数
        int sum = 0;
        for (int[] row : chessArr1) {
            for (int data : row) {
                if (data != 0) {
                    sum++;
                }
            }
        }
        System.out.printf("非0数据个数：%d", sum);
        // 2.创建对应的稀疏数组
        int[][] sparseArr = new int[sum + 1][3];
        sparseArr[0][0] = 11;
        sparseArr[0][1] = 11;
        sparseArr[0][2] = sum;
        // 遍历原始二维数组，将非0数据放入稀疏数组
        int count = 0;
        for (int i = 0; i < chessArr1.length; i++) {
            int[] row = chessArr1[i];
            for (int j = 0; j < row.length; j++) {
                int data = row[j];
                if (data != 0) {
                    count++;
                    sparseArr[count][0] = i;
                    sparseArr[count][1] = j;
                    sparseArr[count][2] = data;
                }
            }
        }
        System.out.println();
        System.out.println("得到稀疏数组为：");
        for (int[] row : sparseArr) {
            System.out.printf("%d\t%d\t%d\t\n",
                    row[0], row[1], row[2]);
        }
        String path = "map.data";
        write(path, sparseArr);
        return path;
    }

    private static void write(String path, int[][] array) {
        WritableByteChannel writeChannel = null;
        try {
            writeChannel = Channels.newChannel(new FileOutputStream(path));
            StringBuilder sb = new StringBuilder();
            for (int[] row : array) {
                sb.append(Arrays.toString(row));
            }
            byte[] bytes = sb.toString().getBytes();
            ByteBuffer buffer = ByteBuffer.wrap(bytes);
            writeChannel.write(buffer);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (writeChannel != null) {
                    writeChannel.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
