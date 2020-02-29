package sort;

import selectionSort.SortTestHelper;

import java.util.Arrays;
import java.util.stream.Collectors;

public class QuickSort {

    public static void quickSort(int[] data,int n){

        quickSortSub(data,0,n-1);
    }

    public static void quickSortSub(int[] data,int p ,int r){

        if (p>=r){
            return ;
        }
        // 获取分区点pivot
        int q = partition(data,p,r);

        quickSortSub(data,p,q-1);
        quickSortSub(data,q+1,r);

    }

    /**
     * 原地分区函数
     * @param data
     * @param p
     * @param r
     * @return
     */
    private static int partition(int[] data, int p, int r) {

        // 找到pivot
        int pivot = data[r];
        // j 是游标，i是分界点
        int i = p, j = p;
        // 进行类似选择排序
        for (; j < r ; j++) {
            if (data[j] < pivot) {
                // swap 这个交换过程相当于在小于区间中插入一个数，采用交换的方式进行插入
                int temp = data[j];
                data[j] = data[i];
                data[i] = temp;
                i++;
            }
        }

        // swap i & r
        int temp2 = data[r];
        data[r] = data[i];
        data[i] = temp2;
        return i;

    }

    /**
     * 在O(n)时间复杂度内找到无序数组中第k个元素
     * @param data
     * @param n
     * @param k
     * @return
     */
    public static int quickSearchK(int[] data,int n,int k){

        int pivot = partition(data, 0, n - 1);

        while (pivot+1 != k){
            if (pivot+1 < k){
                pivot = partition(data,pivot+1,n-1);
            }

            if (pivot+1>k){
                pivot = partition(data,0,pivot-1);
            }
        }
        return data[pivot];
    }

    public static void main(String[] args) {
        int[] a = SortTestHelper.generateRandomArray(8, 0, 9);

        System.out.println(Arrays.stream(a).boxed().collect(Collectors.toList()));
        quickSort(a,8);

        System.out.println(Arrays.stream(a).boxed().collect(Collectors.toList()));

        int[] b = SortTestHelper.generateRandomArray(8, 0, 9);
        System.out.println(Arrays.stream(b).boxed().collect(Collectors.toList()));

        int searchK = quickSearchK(b, 8, 3);
        System.out.println(searchK);
    }
}
