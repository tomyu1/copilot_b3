package com.example.copilot;

public class TestCopilot {

  //冒泡排序
    public static void bubbleSort(int[] arr) {
        int len = arr.length;
        for (int i = 0; i < len - 1; i++) {
        for (int j = 0; j < len - 1 - i; j++) {
            if (arr[j] > arr[j + 1]) { // 相邻元素两两对比
            int temp = arr[j + 1]; // 元素交换
            arr[j + 1] = arr[j];
            arr[j] = temp;
            }
        }
        }
    }


}
