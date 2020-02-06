package sort;

import SelectionSort.SortTestHelper;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.stream.Collectors;

public class SortQuestion {
    /*
    * 对D，a，F，B，c，A，z这几个字符串进行排序
    * 要求将其中所有小写字母都排在大写字母前面，但是小写字母内部和大写字母内部不要求有序。
    * 比如经过排序后为a，c，z，D，F，B，A，这个如何实现呢？
    * 如果字符串中处理大小写，还有数字，将数字放在最前面，又该如何解决呢？
    *
    * 近似于荷兰国旗问题
    * */

    /**
     * 使用计数排序实现
     * 时间复杂度是O(n)
     * 但不是原地排序
     * @param s
     * @return
     */
    public static String countingSortCharacter(String s) {
        if (s.isEmpty()){
            return s;
        }
        char[] a = s.toCharArray();
        // 计数
        int[] c = new  int[3];
        for (int i = 0; i < a.length; i++) {
            char x = a[i];
            if ('0'<= a[i] && a[i]<='9'){
                System.out.println("数字"+x);
                c[0]++;
            }else if ('A'<= a[i] && a[i]<='Z'){
                System.out.println("大写字母"+x);
                c[2]++;
            }else if ('a'<= a[i] && a[i]<='z'){
                System.out.println("小写"+x);
                c[1]++;
            }
        }
        // 累加计数
        for (int i = 1; i < c.length; i++) {
            c[i] = c[i] + c[i-1];
        }
        // 排序
        char[] r = new char[s.length()];

        for (int i = r.length-1; i >= 0 ; i--) {
            char temp = a[i];
            int count = 0;
            if ('0'<= temp && temp<='9'){

                count=c[0];
                c[0]--;
            }else if ('A'<= temp && temp<='Z'){
                count=c[2];
                c[2]--;
            }else if ('a'<= temp && temp<='z'){
                count=c[1];
                c[1]--;

            }
            r[count-1] = temp;

        }

        return new String(r);

    }

    /**
     * 使用指针进行排序
     * 原地排序
     * @param s
     * @return
     */
    public static String sortCharacter(String s){
        // 三段范围为 数字： [48`57] 大写：[65~90] 小写：[97~122]
        // 两端逼近，交换位置
        if (s.isEmpty()){
            return s;
        }
        char[] a = s.toCharArray();
        int less = -1;
        int more = s.length();

        int L = 0;
        while (L<more){
            char x = a[L];
            if ('0'<= x && x<='9'){
                System.out.println("数字"+x);
                // 在最前 arr[++ less] 和 arr[L++] 的值
                swap(a,++less,L);
                L++;
            }else if ('A'<= x && x <='Z'){
                System.out.println("大写字母"+x);
                // 在最后 交换 交换 arr[--more] 和 arr[L] 的值
                swap(a,--more,L);
            }else if ('a'<= x && x <='z'){
                System.out.println("小写"+x);
                // 在中间 L++
                L++;
            }

            System.out.println(String.format("L = %d less = %d more = %d ",L,less,more));
        }
        return new String(a);

    }

    public static void swap(char[] a,int i,int j){
        char temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }

    public static void main(String[] args) {
        int length = 700;
        System.out.println(Integer.toBinaryString(length));
        int a=  length>>3;
        System.out.println(Integer.toBinaryString(a));
        System.out.println("a="+a);
        int b=  length>>6;
        System.out.println(Integer.toBinaryString(b));
        System.out.println("b="+b);
        int seventh = (length >> 3) + (length >> 6) + 1;

        System.out.println(seventh);
    }
    public static void main4(String[] args) {
        /**
         * 是否只有起始位置才检测重复元素个数?
         * 起始位相同元素大于33个则直接快排
         *
         */


        int[] a = new int[300];
        for (int i = 0; i < a.length; i++) {
            if (i<100){
                a[i] = i;
            }else if (i >= 100 && i<=200){
                a[i] = 10;
            }else {
                a[i] = i;
            }

        }

       // int[] a = SortTestHelper.generateRandomArray(300, 1, 300);

    /*
         Arrays.sort(a);
        int last = 0;
        int k =(last = 0) ;

        k++;
        System.out.println(k);
        System.out.println(last);

*/
        compareCalcSpeed();
    }

    public static void main3(String[] args) {
        int n  =1;
        byte odd = 0;
        int count = 1000;
        for (; (n <<= 1) < count; odd ^= 1){
            System.out.println(odd);
            System.out.println("n:"+n);
        }


    }

    public static void main1(String[] args) {
//        String a = "aF5cAzD1B";
        String a = "01D4a59a9Z";

        char[] chars = a.toCharArray();
        String s = sortCharacter(a);
        System.out.println(s);
        Arrays.sort(chars);
        Collections.sort(new ArrayList<String>());
    }

    static void compareCalcSpeed() {
        int count = 66;
        byte odd = 0;

        // Approach 1
        long start = System.nanoTime();
        for (int n = 1; (n <<= 1) < count; odd ^= 1) ;
        odd ^= 1;
        long period = System.nanoTime() - start;

        System.out.printf("loop time: %d ns\n", period);

        // Approach 2
        start = System.nanoTime();
        double logValue = Math.log(count) / Math.log(2);
        int mergeLoopTimes = (int) Math.ceil(logValue);
        period = System.nanoTime() - start;
        System.out.printf("calc time: %d ns\n", period);
    }

}
