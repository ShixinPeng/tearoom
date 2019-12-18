package queue;

import java.util.Arrays;

/**
 * @author shixinpeng
 * @description 循环队列
 * @ClassName: CircularQueue
 * @date 2019/12/18
 *
 */
public class CircularQueue {
    private String[] items;
    private int head = 0;
    private int tail = 0;
    private int n;

    public CircularQueue(int size) {
        if (size<1) throw  new  RuntimeException("must size > 0");
        this.n = size;
        items = new String[size];
    }

    public Boolean enqueue(String item){
        // 队满（tail+1）%n = 0：圈中 队尾+1 在队首位置
        if ((tail + 1)%n ==head){
            // 队满
            System.out.println("队满");
            return false;
        }
        // 这种有上限
//        items[tail%n] = item;
//        tail ++;
        items[tail] = item;
        tail = (tail+1)%n;
        return true;
    }

    public String dequeue(){

//        String item = items[head%n];
//        head++;
         String item = items[head];
        items[head]=null;
        head = (head+1)%n;
        return item;
    }

    @Override
    public String toString() {
        return "CircularQueue{" +
                "items=" + Arrays.toString(items) +
                ", head=" + head +
                ", tail=" + tail +
                ", n=" + n +
                '}';
    }

    public static void main(String[] args) {
        CircularQueue circularQueue = new CircularQueue(5);
        circularQueue.enqueue("a");
        circularQueue.enqueue("b");
        circularQueue.enqueue("c");
        circularQueue.enqueue("d");
        circularQueue.enqueue("e");
        System.out.println(circularQueue);
        circularQueue.enqueue("f");
        System.out.println(circularQueue);
        circularQueue.dequeue();
        System.out.println(circularQueue);
        circularQueue.enqueue("g");
        System.out.println(circularQueue);
        circularQueue.dequeue();
        circularQueue.enqueue("h");
        System.out.println(circularQueue);
        circularQueue.enqueue("g");
    }
}
