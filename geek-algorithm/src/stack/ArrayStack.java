package stack;


import java.util.Arrays;

/**
 * 使用数组实现顺序栈
 */
public class ArrayStack {

    /**
     * 存储数据数组
     */
    private String[] items;

    /**
     * 栈中元素数
     */
    private int count;
    /**
     * 栈的大小
     */
    private int n;

    public ArrayStack(int n){
        count = 0;
        this.n = n;
        items = new String[n];

    }

    /**
     * 出栈
     * @return
     */
    public String pop(){
        //出栈判栈空
        if (count < 1){
            return null;
        }
        String item = items[count-1];
        count--;
        return item;
    }

    /**
     * 入栈
     * @param data
     * @return
     */
    public boolean push(String data){

        // 入栈判栈满
        if (count>n-1){
            return false;
        }
        items[count]=data;
        count++;
        return true;
    }


    @Override
    public String toString() {
        return "ArrayStack{" +
                "items=" + Arrays.toString(items) +
                ", count=" + count +
                ", n=" + n +
                '}';
    }

    public static void main(String[] args) {
        ArrayStack arrayStack = new ArrayStack(3);

        arrayStack.push("a");
        arrayStack.push("b");
        arrayStack.push("c");
        arrayStack.push("d");
        System.out.println(arrayStack.toString());

        String data1 = arrayStack.pop();
        System.out.println(data1);
        String data2 = arrayStack.pop();
        System.out.println(data2);
        String data3 = arrayStack.pop();
        System.out.println(data3);
        String data4 = arrayStack.pop();
        System.out.println(data4);



    }
}

