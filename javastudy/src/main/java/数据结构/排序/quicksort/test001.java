package 数据结构.排序.quicksort;

import java.util.Arrays;

public class test001 {
    public static void main(String[] args) {
        int[] arrrr = {5, 3, 6, 7, 2, 345, 6, 78, 1, 0, 43};

        Arrays.stream(arrrr).forEach(t -> System.out.println(t));

        sort(arrrr, 0, arrrr.length - 1);

        System.out.println("sorted");
        Arrays.stream(arrrr).forEach(t -> System.out.println(t));
    }

    static void sort(int arr[], int left, int right) {
        if (left < right) {
            int mid = (left + right) / 2;
            int i = left;
            int j = right;
            int midvue = arr[mid];
            while (i < j) {
                while (arr[j] >= midvue && i < j) {
                    j--;
                }
                if (j > i) {
                    while (arr[i] <= midvue && i < j) {
                        i++;
                    }
                    if (i < j) {
                        int tmp = arr[i];
                        arr[i] = arr[j];
                        arr[j] = tmp;
                    }
                }
            }
            System.out.println(i + ":" + j);
            arr[i] = midvue;
            sort(arr, left, i - 1);
            sort(arr, i + 1, right);
        }
    }
}
