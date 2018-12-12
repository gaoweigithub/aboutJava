package 数据结构.排序.quicksort;

import java.util.Arrays;

public class test002 {
    public static void main(String[] args) {
        int[] arrrr = {5, 3, 6, 7, 2, 345, 6, 78, 1, 0, 43};

        Arrays.stream(arrrr).forEach(t -> System.out.println(t));

        sort(arrrr, 0, arrrr.length - 1);

        System.out.println("sorted");
        Arrays.stream(arrrr).forEach(t -> System.out.println(t));
    }

    public static int partition(int[] array, int lo, int hi) {
        //固定的切分方式
        int key = array[lo];
        while (lo < hi) {
            while (array[hi] >= key && hi > lo) {//从后半部分向前扫描
                hi--;
            }
            array[lo] = array[hi];
            while (array[lo] <= key && hi > lo) {
                lo++;
            }
            array[hi] = array[lo];
        }
        array[hi] = key;
        return hi;
    }

    public static void sort(int[] array, int lo, int hi) {
        if (lo >= hi) {
            return;
        }
        int index = partition(array, lo, hi);
        sort(array, lo, index - 1);
        sort(array, index + 1, hi);
    }
}
