package heap;

import java.util.Arrays;

/**
 * @author shixinpeng
 * @description 堆
 * @ClassName: Heap
 * @date 2020/2/16
 *
 */
public class Heap {

    private int[] a;
    // 堆容量
    private int n;

    private int count;


    public Heap(int n) {
        this.n = n;
        // 实际存储从下标1开始，所以申请长度加1
        a = new int[n+1];
        count = 0;
    }

    /**
     *  插入元素
     * @title insert
     * @author shixin peng
     * @date 2020-02-16 16:39
     * @param data
     * @return Boolean
     * @throws
     */
    public Boolean insert(int data){
        if (count>=n){
            // 满了
            return false;
        }
        ++count;
        a[count] = data;

        int i = count;
        upHeapify(i);
        return true;
    }

    /**
     *  删除元素
     * @title delete
     * @author shixin peng
     * @date 2020-02-16 16:39
     * @param data
     * @return Boolean
     * @throws
     */
    public Boolean delete(int data){
        if (count==0){
            return false;
        }
        if (a[count] == data){
            a[count] = 0;
            count--;
            // 删除的为最后一个叶子节点
            return true;
        }
        int i = count;

        while ( i >0 && a[i] != data) {
            i--;
        }
        if (i==0){
            return false;
        }
        a[i] = a[count];

        count--;
        downHeapify(i);

        return false;
    }

    /**
     *  自下往上进行堆化
     * @title upHeapify
     * @author shixin peng
     * @date 2020-02-16 16:37
     * @param i
     * @return void
     * @throws
     */
    private void upHeapify(int i){
        while (i>>1 >0 &&a[i] > a[i>>1]){
            swap(i,i>>1);
            i=i>>1;
        }

    }

    /**
     *  向下堆化
     * @title downHeapify
     * @author shixin peng
     * @date 2020-02-16 16:59
     * @param i
     * @return void
     * @throws
     */
    private void downHeapify(int i){
        // 自上而下的堆化
        int t= 0;
        while (true){
            int maxP = i;
            if (i*2 <= n && a[i] < a[i*2]){
                maxP = i*2;
            }
            if (i*2+1 <=n && a[maxP] < a[2*i+1]){
                maxP = 2*i+1;
            }
            if (maxP == i) break;
            swap(i,maxP);
            i = maxP;
            t++;
        }
        System.out.println("堆化调整次数 count："+t);
    }


    /**
     *  堆排序
     * @title heapSort
     * @author shixin peng
     * @date 2020-02-19 20:22
     * @param a
     * @param n
     * @return void
     * @throws
     */
    public static void heapSort(int[] a,int n){
        // 建堆 大顶堆
        buildHeap(a,n);
        // 依次取出堆顶，并重新堆化
        int k = n;
        while (k>1){
            swap(a,1,k);
            k--;
            heapify(a,k,1);
        }
    }
    /**
     *  建堆
     * @title buildHeap
     * @author shixin peng
     * @date 2020-02-19 20:04
     * @param a
     * @param n
     * @return void
     * @throws
     */
    public static void buildHeap(int[] a,int n){
        for (int i = n/2; i > 1; --i) {
            heapify(a,n,i);
        }
    }

    public static void heapify(int[] a,int n,int i){

        while (true){
            // 关注点
            int maxP = i;

            // 左子节点
            if (i*2 <= n && a[i]<a[i*2]){
                maxP = i*2;
            }
            // 右子节点
            if (i*2+1 <= n && a[maxP] < a[i*2+1]){
                maxP = i*2+1;
            }
            if (maxP == i) break;
            Heap.swap(a,i,maxP);
            i = maxP;
        }
    }

    public static void swap(int[] a, int i,int j){
        int temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }


    public  void swap(int i,int j){
        int temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }

    public void printAll(){
        System.out.println(Arrays.toString(a));
    }

    public static void main(String[] args) {
        Heap heap = new Heap(10);
        for (int i = 10 ;i >= 1; i--) {
            heap.insert(i*2);
        }
//        heap.printAll();
//        heap.delete(18);
//        heap.printAll();
        heapSort(heap.a,10);
        System.out.println(Arrays.toString(heap.a));

    }
}
