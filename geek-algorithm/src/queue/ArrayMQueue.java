package queue;

import java.util.Arrays;

/**
 *  可以进行数据搬移的顺序队列
 * @author shixin peng
 * @date 2019-12-18 22:22
 */
public class ArrayMQueue {
    private String[] items;
    private int head;
    private int tail;
    private int n;

    public ArrayMQueue(int size) {
        n = size;
        this.items = new String[n];
        head = 0;
        tail = 0;
    }

    /**
     * 入队
     * @param item
     * @return
     */
    public Boolean enqueue(String item){
        if (tail==n){
            // 指向末尾
            if (head==0){
                // tail 指向尾 head指向头 则队满
                System.out.println("队满");
                return false;
            }else {
                // 搬移数据
//                int count = tail-head;
//                for (int i = 0; i < count; i++) {
//                    items[i] = items[head+i];
//                }
//                head = 0;
//                tail = count;
                // 搬移数据其他实现
                for (int i = head; i < tail; ++i) {
                    items[i-head] = items[i];
                }

                // 先复位队尾索引
                tail -=head;
                head = 0;
            }

        }
        items[tail] = item;
        ++tail;
        return true;
    }

    public String dequeue(){
        if (head==tail){
            return null;
        }
        String item = items[head];
        items[head]=null;
        ++head;
        return item;
    }

    @Override
    public String toString() {
        return "ArrayMQueue{" +
                "items=" + Arrays.toString(items) +
                ", head=" + head +
                ", tail=" + tail +
                ", n=" + n +
                '}';
    }

    public static void main(String[] args) {
        ArrayMQueue arrayQueue = new ArrayMQueue(5);

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

        arrayQueue.enqueue("g");
        arrayQueue.enqueue("h");
        arrayQueue.enqueue("k");
        System.out.println(arrayQueue.toString());
        arrayQueue.dequeue();
        arrayQueue.dequeue();
        arrayQueue.dequeue();
        arrayQueue.enqueue("l");
        arrayQueue.enqueue("m");
        arrayQueue.enqueue("k");
        System.out.println(arrayQueue.toString());


    }
}
