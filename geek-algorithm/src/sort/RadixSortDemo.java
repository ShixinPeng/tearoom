package sort;

import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * 基数排序
 * 使用基数排序对电话号码进行排序，时间复杂度要求O(n)
 */
public class RadixSortDemo {

    public static void radixSort(long[] data,int n){
        long max = data[0];
        for (int i = 0; i < data.length; i++) {
            if (data[i] > max){
                max = data[i];
            }

        }
        // 根据每一位进行排序
        for (long e = 1; max/e > 0; e *= 10) {
            System.out.println("e="+e);
            countingSortByExp(data,e);
        }
    }

    /**
     * 根据位数进行处理
     * @param data
     * @param e
     */
    public static void countingSortByExp(long[] data,long e){
        // 计算当前位上数值分布
        int[] c = new int[10];
        // 数值累加数组i
        for (int i = 0; i < data.length; i++) {
            int t = (int) (data[i]/e) % 10;
            c[t]++;
        }
        // 根据累加数据进行排序
        for (int i = 1; i < c.length; i++) {
            c[i] = c[i]+c[i-1];
        }
        // 排序后复制给原数组
        long[] r = new long[data.length];
        for (int i = data.length-1; i >= 0; i--) {
            int index = (int) (data[i]/e) % 10;
            int count = c[index];
            r[count-1] = data[i];
            c[index]--;
        }
        for (int i = 0; i < data.length; i++) {
            data[i] = r[i];
        }
    }

    public static void main(String[] args) {
        long[] a = new long[]{13782200888L,18682200888L,17621188512L,18647497021L};
        System.out.println(Arrays.stream(a).boxed().collect(Collectors.toList()));
       radixSort(a,4);
        System.out.println(Arrays.stream(a).boxed().collect(Collectors.toList()));
    }

    public static void radixSort2(int[] arr) {
        int max = arr[0];
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] > max) {
                max = arr[i];
            }
        }

        // 从个位开始，对数组arr按"指数"进行排序
        int exp = 1;
        for (; max / exp > 0; exp *= 10) {
            System.out.println("exp="+exp);
            System.out.println("max / exp="+(max / exp));
        }

        System.out.println("max / exp end="+(max / exp));
    }
}
