package com.huawei.sort;

import java.time.LocalDateTime;
import java.util.Arrays;

public class Test {

    public static void main(String[] args) {
        int length = 900;
        int[] arr = new int[length];
        for (int i = 0; i < length; i++) {
            arr[i] = (int) (Math.random() * 9999999);
        }
//        System.out.println(Arrays.toString(arr));

        LocalDateTime dateTime1 = LocalDateTime.now();
        System.out.println("排序前：" + dateTime1);


//        BubbleSort.bubbleSort(arr);
//        SelectSort.selectSort(arr);
        InsertSort.insertSort2 (arr);
        System.out.println(Arrays.toString(arr));

        LocalDateTime dateTime2 = LocalDateTime.now();
        System.out.println("排序后：" + dateTime2);
        System.out.printf("耗時：%ds", (dateTime2.getSecond() - dateTime1.getSecond()));
    }
}
