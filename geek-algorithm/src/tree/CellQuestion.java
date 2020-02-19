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

        // 其实感觉可以理解为一个大的二叉树
        // 从第三层开始，删掉第三层的任意一个节点下的所有节点，
        // 都是第n小时的容器内细胞的个数，因为细胞的死亡也是一个2的n次方递增的
    }

}
