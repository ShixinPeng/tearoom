package sort;

import SelectionSort.SortTestHelper;

import java.util.Arrays;
import java.util.stream.Collectors;


/**
 * @author shixinpeng
 * @description 插入排序
 * @ClassName: InsertionSortDemo
 * @date 2020/1/11
 *
 */
public class InsertionSortDemo {

    static int move = 0;

    /**
     * Traditional (without sentinel) insertion sort
     * @param a
     * @param n
     */
    public static void insertSort(int[] a, int n){
        // 插入排序，把数组分为已排序区和未排序区，依次把未排序中的元素插入的已排序中

        for (int i = 1; i < n ; ++i){
            // 遍历已排序区，找到已排序中符合要求的位置
            // value 待插入的数据
            int value = a[i];
            // j = 已排序的区间索引前一位
            int j = i-1;
            for (;j>=0;--j){

               // System.out.printf("\n[%s : %s]", Arrays.stream(a).boxed().collect(Collectors.toList()).subList(0,i).toString(),Arrays.stream(a).boxed().collect(Collectors.toList()).subList(i,n).toString());
                if (a[j] > value){
                    // 大于待插入的数据的都自觉往后移动一位
                    a[j+1] = a[j];
                    move++;
                }else {
                    // 中止 当前j为目标位置
                    break;
                }
            }
            a[j+1] = value;
        }
    }


    public static void insertionWithSentrySort(int[] a,int n) {
        int i,j;
        for(i=1+1;i<a.length;i++){//a[0]作为哨兵，a[1]作为初始有序,i=1+1是为了后面希尔排序好理解
            if(a[i]<a[i-1]){ // 1
                a[0]=a[i];//设置哨兵
                for(j=i-1; a[j]>a[0];j--){
                    a[j+1]=a[j];//记录后移
                }
                a[j+1]=a[0];//插入
            }
        }

    }

    public static void main(String[] args) {
        int[] a = new int[]{0,5,5,5,1,2,1,0};
        // 此时我们传入的SqList参数的值为length=6,r[6]={0,5,3,4,6,2}，其中r[0]=0将用于后面起到哨兵的作用
        insertionWithSentrySort(a,7);
        System.out.println(Arrays.toString(a));
    }


    public static void main1(String[] args) {
        int[] a = new  int[]{0,5,5,5,1};
        insertSort(a,5);
        System.out.println(Arrays.toString(a));
    }

    public static void main2(String[] args) {
        int[] a = SortTestHelper.generateRandomArray(6, 1, 7);


        Arrays.stream(a).forEach(System.out::print);
        insertSort(a,6);
        System.out.println("");
        Arrays.stream(a).forEach(System.out::print);
        System.out.println("");
        System.out.println("move:"+move);
    }
}
