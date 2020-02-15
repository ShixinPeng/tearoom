package tree;

import com.sun.org.apache.regexp.internal.RE;

/**
 * @author shixinpeng
 * @description 细胞问题思考题
 * 一个细胞，一个小时分裂一次，可以存活三个小时，n小时，容器内有多少细胞
 * @ClassName: CellQuestion
 * @date 2020/2/15
 *
 */
public class CellQuestion {



    public static int cellDouble(int n){

        if (n<0){
            return 0;
        }
       if (n==0){
           return 1;
       }

        return cellDouble(n-1)*2 - cellDouble(n-3);

    }

    public static void main(String[] args) {
        int i = cellDouble(3);
        System.out.println(i);


    }

}
