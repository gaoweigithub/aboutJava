package 数据结构.排序.归并;

import java.util.Arrays;
import java.util.stream.Stream;

public class test001 {
    public static void main(String[] args) {
        int [] arrrr  = {5,3,6,7,2,345,6,78,1,0,43};

        Arrays.stream(arrrr).forEach(t -> System.out.println(t));

        sort(arrrr);

        System.out.println("sorted:");
        Arrays.stream(arrrr).forEach(t -> System.out.println(t));

    }

    public static void sort(int[] arr) {
        int[] tmp = new int[arr.length];
        sort(arr, 0, arr.length - 1, tmp);
    }

    public static void sort(int[] arr, int left, int right, int[] tmp) {
        if (right > left) {
            int mid = (left + right) / 2;
            sort(arr, left, mid, tmp);
            sort(arr, mid + 1, right, tmp);
            merge(arr, left, mid, right, tmp);
        }
    }

    public static void merge(int[] arr, int left, int mid, int right, int[] tmp) {
        int i = left;
        int j = mid+1;
        int k = 0;
        while (i <= mid && j <= right) {
            if (arr[i] <= arr[j]) {
                tmp[k++] = arr[i++];
            } else {
                tmp[k++] = arr[j++];
            }
        }

        while (i <= mid) {
            tmp[k++] = arr[i++];
        }
        while (j <= right) {
            tmp[k++] = arr[j++];
        }
        k = 0;
        while (left <= right) {
            arr[left++] = tmp[k++];
        }
    }
}
