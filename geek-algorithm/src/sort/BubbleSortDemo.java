package sort;

import SelectionSort.SortTestHelper;

import java.util.Arrays;

/**
 * @author shixinpeng
 * @description 冒泡排序
 * @ClassName: BubbleSortDemo
 * @date 2020/1/11
 *
 */
public class BubbleSortDemo {

    static int dept = 0;
    public static void bubbleSort(int[] a,int n){
        boolean flag = true;
        for (int i = 0; i < n; i++) {
            // 每次只比较相邻的两个元素的关系，如果不满足关系，则交换位置

            if (!flag){
                //System.out.println("\n已经没有可交换元素\n");
                return;
            }
            dept++;
            // i理解为冒泡次数
            flag = false;
            for (int j = 0; j < n - i -1 ;++j){
                if (a[j] > a[j+1]){
                    int temp = a[j];
                    a[j] = a[j+1];
                    a[j+1] = temp;
                    flag = true;
                }
            }

        }
    }

    public static void main(String[] args) {
        int[] array = SortTestHelper.generateRandomArray(6, 1, 7);

        Arrays.stream(array).forEach(System.out::print);

        bubbleSort(array,6);
        System.out.println("");
        Arrays.stream(array).forEach(System.out::print);
        System.out.println("");
        System.out.println("dept:"+dept);
    }
}
