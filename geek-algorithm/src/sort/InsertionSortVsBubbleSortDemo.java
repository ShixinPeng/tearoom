package sort;

import SelectionSort.SortTestHelper;

public class InsertionSortVsBubbleSortDemo {
    public static void main(String[] args) {

        // 测试冒泡排序和插入排序的性能比较
        int[][] ints = new int[20000][200];
        for (int i = 0; i <10000 ; i++) {
            int[] array = SortTestHelper.generateRandomArray(200, 0, 200);
            ints[i] = array;
        }
        // 插入预热
//        for (int i = 0; i < ints.length; i++) {
//            InsertionSortDemo.insertSort(ints[i],200);
//        }
        // 插入计算
        long t1 = System.currentTimeMillis();

        for (int i = 0; i < ints.length; i++) {
            InsertionSortDemo.insertSort(ints[i],200);
        }
        long t2 = System.currentTimeMillis();

        // 冒泡预热
//        for (int i = 0; i < ints.length; i++) {
//            BubbleSortDemo.bubbleSort(ints[i],200);
//        }
        // 冒泡计算
        long t3 = System.currentTimeMillis();
        for (int i = 0; i < ints.length; i++) {
            BubbleSortDemo.bubbleSort(ints[i],200);
        }
        long t4 = System.currentTimeMillis();

        System.out.printf("insertSort time:%d \n bubbleSort time:%d \n",t2-t1,t4-t3);

    }
}
