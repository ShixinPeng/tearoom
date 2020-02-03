package sort;

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

    }

    public static void main(String[] args) {
//        String a = "aF5cAzD1B";
        String a = "0123456789azAZ";
        char[] chars = a.toCharArray();
        String s = countingSortCharacter(a);
        System.out.println(s);

    }
}
