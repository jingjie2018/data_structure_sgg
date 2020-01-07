package com.huawei.sort;

public class RadixSort {
    static void radixSort(int[] arr) {
        int[][] bucket = new int[10][arr.length];
        int[] bucketEleCounts = new int[10];

        int max = arr[0];
        for (int ele : arr) {
            if (max < ele) {
                max = ele;
            }
        }

        int maxLen = String.valueOf(max).length();

        for (int k = 0; k < maxLen; k++) {
            for (int ele : arr) {
                int digitOfEle = ele / (int) Math.pow(10, k) % 10;
                bucket[digitOfEle][bucketEleCounts[digitOfEle]] = ele;
                bucketEleCounts[digitOfEle] += 1;
            }

            int index = 0;
            for (int i = 0; i < bucket.length; i++) {
                if (bucketEleCounts[i] != 0) {
                    for (int j = 0; j < bucketEleCounts[i]; j++) {
                        arr[index++] = bucket[i][j];
                    }
                }
                bucketEleCounts[i] = 0;
            }
        }
    }
}
