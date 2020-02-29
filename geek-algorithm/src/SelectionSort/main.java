package selectionSort;

import java.util.Arrays;

public class main {

    public static void main(String[] args) {
        int count = 10;
        int rangeL = 0;
        int rangeR = 10;
        int[] arr = SortTestHelper.generateRandomArray(count,rangeL,rangeR);
        System.out.println(Arrays.toString(arr));
        selectionSort(arr,10);
        System.out.println(Arrays.toString(arr));
    }

    /**
     * @title selectionSort
     * @description TODO 选择排序
     * @version V1.0
     *
     * @param arr
     * @param n
     * @return void
     * @throws
     */
    public static void selectionSort(int[] arr, int n ){

        for (int i = 0; i < n; i++) {
            //寻找[i,n）区间里的最小值
            int minIndex = i;
            for (int j = i+1; j <n ; j++) {

                if (arr[j]<arr[minIndex]) {
                    minIndex = j;
                }
            }
            //把当前选择的位置i上的与获取到后面比i位置小的值交换
            int temp = arr[i];
            arr[i] = arr[minIndex];
            arr[minIndex] = temp;
        }


    }
}
