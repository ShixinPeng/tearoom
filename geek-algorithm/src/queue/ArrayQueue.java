package queue;

import java.util.Arrays;

/**
 *  数组实现队列  顺序队列
 * @author shixin peng
 * @date 2019-12-18 22:08
 */
public class ArrayQueue {
    private String[] items;
    /**
     * 队首下标
     */
    private int head;
    /**
     * 队尾下标
     */
    private int tail;
    /**
     * 队列容量
     */
    int n;

    public ArrayQueue(int size) {
        if (size<1){
            throw  new RuntimeException("must size > 0");
        }
        n = size;
        items = new String[size];
        head = 0;
        tail = 0;
    }

    /**
     * 入队
     * @return
     */
    public Boolean enqueue(String item){
        // 如果队尾指向最大下标，则数据无法入队
        if (n == tail){
            System.out.println("队满；无法插入");
            return false;
        }

        items[tail] = item;
        tail++;
        return true;
    }

    /**
     * 出队
     * @return
     */
    public String dequeue(){
        if (head == tail){
            // 首尾在同一下标，队列中没有数据
            return null;
        }

        String item = items[head];
        // items[head] 可清空可不清空
        items[head] = null;
        head++;
        return item;
    }

    @Override
    public String toString() {
        return "ArrayQueue{" +
                "items=" + Arrays.toString(items) +
                ", head=" + head +
                ", tail=" + tail +
                ", n=" + n +
                '}';
    }

    public static void main(String[] args) {
        ArrayQueue arrayQueue = new ArrayQueue(5);

        arrayQueue.enqueue("a");
        arrayQueue.enqueue("b");
        arrayQueue.enqueue("c");
        arrayQueue.enqueue("d");
        arrayQueue.enqueue("e");
        arrayQueue.enqueue("f");

        String dequeue = arrayQueue.dequeue();
        System.out.println("dequeue:"+dequeue);
        System.out.println(arrayQueue.toString());
        String dequeue1 = arrayQueue.dequeue();
        String dequeue2 = arrayQueue.dequeue();
        String dequeue3 = arrayQueue.dequeue();
        String dequeue4 = arrayQueue.dequeue();
        System.out.println("dequeue4:"+dequeue4);
        String dequeue5 = arrayQueue.dequeue();

        String dequeue6 = arrayQueue.dequeue();
        System.out.println("dequeue6:"+dequeue6);
        System.out.println(arrayQueue.toString());


    }
}
