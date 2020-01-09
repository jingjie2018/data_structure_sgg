package com.iss.sort;

public class InsertSort {
    static void insertSort(int[] arr) {
        for (int i = 1; i < arr.length; i++) {
            int ti = i;
            int temp = arr[i];
            for (int j = 0; j <= i - 1; j++) {
                if (arr[i] < arr[j]) {
                    while (ti > j) {
                        arr[ti] = arr[--ti];
                    }
                    arr[j] = temp;
                    break;
                }
            }
        }
    }

    static void insertSort2(int[] arr) {
        int insertVal = 0;
        int insertIndex = 0;
        for (int i = 1; i < arr.length; i++) {
            insertVal = arr[i];
            insertIndex = i - 1;
            while (insertIndex >= 0 && insertVal < arr[insertIndex]) {
                arr[insertIndex + 1] = arr[insertIndex];
                insertIndex--;
            }
            arr[insertIndex + 1] = insertVal;
        }
    }
}
