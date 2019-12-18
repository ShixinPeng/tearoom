package stack;


import java.util.Arrays;

/**
 * @author shixinpeng
 * @description 支持动态扩容数组的数组栈
 * @ClassName: DyArrayStack
 * @date 2019/12/12
 *
 */
public class DyArrayStack {

    String[] items;
    /**
     * 默认容量
     */
    int size = 32;
    /**
     * 扩容系数
     */
    int rate = 2;
    /**
     * 当前栈中元素个数
     */
    int count = 0;

    public DyArrayStack(int size) {
        if (size < 1){
            throw new  RuntimeException("size must > 0");
        }
        this.size = size;
        items = new String[size];
    }

    public DyArrayStack() {
        items = new String[size];
    }

    public String pop(){
        count--;
        String item = items[count];
        items[count] = null;
        return item;
    }

    public boolean push(String data){
        if (null == data){
            return false;
        }
        if (count == size){
            size = rate * size;
            String[] zoom = new String[size];
            for (int i = 0; i < count; i++) {
                zoom[i] = items[i];
            }
            items = zoom;
        }

        items[count] = data;
        count++;

        return true;
    }

    @Override
    public String toString() {
        return "DyArrayStack{" +
                "items=" + Arrays.toString(items) +
                ", size=" + size +
                '}';
    }


    public static void main(String[] args) {
        DyArrayStack arrayStack = new DyArrayStack(3);

        arrayStack.push("a");
        arrayStack.push("b");
        arrayStack.push("c");
        arrayStack.push("d");
        arrayStack.push("e");
        arrayStack.push("f");
        arrayStack.push("g");
        System.out.println(arrayStack.toString());

        String data1 = arrayStack.pop();
        System.out.println(data1);
        String data2 = arrayStack.pop();
        System.out.println(data2);
        String data3 = arrayStack.pop();
        System.out.println(data3);
        String data4 = arrayStack.pop();
        System.out.println(data4);
        System.out.println(arrayStack.toString());


    }
}
