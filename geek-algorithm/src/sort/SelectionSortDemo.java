package sort;

import selectionSort.SortTestHelper;

import java.util.Arrays;

/**
 * @author shixinpeng
 * @description 选择排序
 * @ClassName: SelectionSortDemo
 * @date 2020/1/11
 *
 */
public class SelectionSortDemo {

    /**
     *  选择排序，把数组分为已排序区间和未排序区间
     *  每次比较为从未排序区间中选择最小的元素放置到已排序区间末尾
     * @title selectionSort
     * @author shixin peng
     * @date 2020-01-11 17:53
     * @param a
     * @param n
     * @return void
     * @throws
     */
    public static void selectionSort(int[] a ,int n){

        for (int i = 0; i < n; i++) {
            // 这里的i也是已选择区和未选择的界值
            int value = a[i];
            int j = i+1;
            int minIndex = i;
            for (; j < n; j++) {
                if (a[j]<value){
                    value = a[j];
                    minIndex = j;
                }
            }
            int temp = a[minIndex];
            a[minIndex] = a[i];
            a[i] = temp;
        }
    }
    public static void main(String[] args) {
        int[] a = SortTestHelper.generateRandomArray(9, 1, 9);
        Arrays.stream(a).forEach(System.out::print);
        System.out.println("");
        selectionSort(a,9);
        Arrays.stream(a).forEach(System.out::print);
        System.out.println("");

    }
}
