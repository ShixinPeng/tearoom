package sort;

import SelectionSort.SortTestHelper;

import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * @author shixinpeng
 * @description 计数排序
 * @ClassName: CountingSortDemo
 * @date 2020/2/2
 *
 */
public class CountingSortDemo {


    public static void countingSort(int[] data,int n){

        if (data.length<2){
            return;
        }
        // 计算数组范围
        int max = data[0];
        for (int i = 1; i < n; i++) {
            max = data[i]>max?data[i]:max;
        }
        System.out.println("max="+max);
        // 生成个数数组
        int[] c = new int[max+1];
        for (int i = 0; i < c.length; i++) {
            c[i] = 0;
        }
        // 个数统计
        for (int i = 0; i < n; i++) {
            c[data[i]]++;
        }
        System.out.print("c1=");
        System.out.println(Arrays.stream(c).boxed().collect(Collectors.toList()));
        // 个数累加
        for (int i = 1; i < c.length; i++) {
            c[i] = c[i-1]+c[i];
        }
        System.out.print("c2=");
        System.out.println(Arrays.stream(c).boxed().collect(Collectors.toList()));
        // 生成排序数组
        int[] r = new int[data.length];
        for (int i = data.length-1; i >=0 ; i--) {
            int temp = data[i];
            int count = c[temp];
            r[count-1] = temp;
            c[temp]--;
        }
        System.out.print("r=");
        System.out.println(Arrays.stream(r).boxed().collect(Collectors.toList()));
        // 复制
        for (int i = 0; i < data.length; i++) {
            data[i] = r[i];
        }
    }

    public static void main(String[] args) {
        int[] a = SortTestHelper.generateRandomArray(9, 1, 9);
        int[] a1 = new int[]{0,0,0,0,0,0,1,2,3};
        System.out.println(Arrays.stream(a1).boxed().collect(Collectors.toList()));
        countingSort(a1,9);
    }
}
