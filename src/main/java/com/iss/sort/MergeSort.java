package com.iss.sort;

public class MergeSort {

    static void mergeSort(int[] arr, int left, int right, int[] temp) {
        if (left < right) {
            int mid = (left + right) / 2;
            mergeSort(arr, left, mid, temp);
            mergeSort(arr, mid + 1, right, temp);
            merge(arr, left, mid, right, temp);
        }
    }

    private static void merge(int[] arr, int left, int mid, int right, int[] temp) {
        int i = left;
        int j = mid + 1;
        int t = 0;

        while (i <= mid && j <= right) {
            if (arr[i] < arr[j]) {
                temp[t] = arr[i++];
            } else {
                temp[t] = arr[j++];
            }
            t++;
        }

        while (i <= mid) {
            temp[t++] = arr[i++];
        }
        while (j <= right) {
            temp[t++] = arr[j++];
        }

        // 拷贝
        t = 0;
        int tempLeft = left;
        System.out.println("tempLeft=" + tempLeft + " right=" + right);
        while (tempLeft <= right) {
            arr[tempLeft++] = temp[t++];
        }
    }
}
