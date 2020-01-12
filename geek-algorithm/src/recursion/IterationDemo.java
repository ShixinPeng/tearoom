package recursion;

public class IterationDemo {
    public static int f(int n){
        int ret = 1;
        for (int i = 2;i<=n;i++){
            ret = ret +1;
        }
        return ret;
    }

    public static int f2(int n){
        if (n ==1) return 1;
        if (n ==2) return 2;

        int ret = 0;
        int pre = 2;
        int prepre =1;
        for (int i = 3; i <= n; i++) {
            // 走到当前台阶的走法 = 一步到当前台阶的走法量 + 使用两步走到当前台阶的走法量
            ret = pre + prepre;

            // 记录一步到达该台阶的和
            prepre = pre;
            // 记录两步走到该台阶的和
            pre = ret;
        }
        return ret;
    }

    public static void main(String[] args) {
//        int f = f(10);
//
//        System.out.println("f:"+f);

        int i = f2(30);

        System.out.println("f2:"+i);


    }
}
