package sort;

import selectionSort.SortTestHelper;

import java.util.Arrays;
import java.util.Stack;

/**
 * @author shixinpeng
 * @description 使用栈模拟迭代进行快排
 * 思路，把每次迭代中需要排序的区间全部入栈，之后从末端开始进行依次处理
 * 思考？：迭代、迭代树和栈的关系
 * @ClassName: StackQuickSort
 * @date 2020/2/12
 *
 */
public class StackQuickSort {

    public static void quickSort(int[] a,int n){

        if (a.length != n || n<=1){
            return;
        }
        // 使用栈模拟快排中的递归调用
        Stack<Integer> stack = new Stack<>();
        int left = 0;
        int right = n-1;
        stack.push(left);
        stack.push(right);

        // 当栈中没有需要排序的区间时，完成排序
        while (!stack.isEmpty()){

            int j = stack.pop();
            int i = stack.pop();

            int mid = partition(a,i,j);

            // 本次计算区间至少有两个元素
            if (mid-1 > i){
                stack.push(i);
                stack.push(mid-1);
            }

            if (j > mid+1){
                stack.push(mid+ 1);
                stack.push(j);
            }
        }

    }

    /**
     * 区间排序
     * @param a
     * @param left
     * @param right
     */
    public static int partition(int[] a,int left,int right){
        int pivot = a[right];
        // i 区间分界 j 游标
        int i = left,j = left;
        for ( ; j < right ; j++){
            if (a[j]<pivot){
                int temp = a[i];
                a[i] = a[j];
                a[j] = temp;
                i++;
            }
        }
        // SWAP a[i] a[right]
        a[j] = a[i];
        a[i] = pivot;
        return i;
    }

    public static void main(String[] args) {
        int[] a = SortTestHelper.generateRandomArray(100, 0, 100);

        System.out.println(Arrays.toString(a));
        quickSort(a,100);

        System.out.println(Arrays.toString(a));
    }
}
