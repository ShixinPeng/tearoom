package recursion;

import java.util.HashMap;

public class RecursionDemo {

    static int depth = 0;
    public static int f(int n){
       depth++;
        if ( n == 1) {
            return 1;
        }
        return f(n-1)+1;
    }

    private static HashMap<Integer,Integer> map = new HashMap();

    /**
     *  一次一步或者两步，求台阶的走法
     * @title f2
     * @author shixin peng
     * @date 2020-01-11 09:51
     * @param n
     * @return int
     * @throws
     */
    public static  int f2(int n){
        depth++;
        if (map.containsKey(n)){
            return map.get(n);
        }
        // 剩一阶台阶 一种走法
        if (n==1) return 1;
        // 剩两节台阶  两种走法
        if (n==2) return 2;
        int i = f2(n - 1) + f2(n - 2);
        map.put(n,i);

        return f2(n-1) + f2(n-2);
    }

    public static void main(String[] args) {
         // int f = f(10);
//        System.out.println("f:" + f);
//        System.out.println("depth:" + depth);


        int i = f2(30);

        System.out.println("f2:" + i);

        double pow = Math.pow(2, 20);

        System.out.println("f2 2的n次方:" + pow);
        System.out.println("f2 depth:" + depth);

    }
}
