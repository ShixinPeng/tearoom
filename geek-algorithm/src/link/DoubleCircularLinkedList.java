package link;

/**
 * 双向循环链表
 * 区别与双向链表，头结点的前置指针指向尾结点，尾结点的后置结点指向头结点
 * 双向循环链表为空时：
 * @param <T>
 */
public class DoubleCircularLinkedList<T> {

    public DNode head;

    /**
     * 添加结点，在末尾添加数据，新结点pre指向前置结点，前置结点的后置指向新结点
     * @param data
     */
    public void addNode(T data){
        DNode<T> newNode = new DNode<>(data);

        // 如果只是第一个节点，则前置节点和后置节点都指向首节点
        if (head == null){
            head = newNode;
            head.pre = head;
            head.next = head;
            return;
        }

        DNode lastNode = head;
        // （不为空的双向循环链表中）判断不是尾结点方法是next不指向首节点
        while (lastNode.next!=head){
            lastNode = lastNode.next;
        }
        // 先追加到最后 保证首尾循环
        lastNode.next = head.pre = newNode;
        newNode.pre  = lastNode;
        newNode.next = head;

    }

    /**
     * 删除指定顺序的结点
     * @param index
     * @return
     */
    public boolean deleteNode(int index){
        // 空链
        if (head == null){
            return false;
        }
        // 只有一个节点 且删除的第一个
        if (index == 1 && head.next == head){
            head = null;
            return true;
        }

        // 如果顺序大于总的链表数
        DNode lastDNode = head;
        int i = 1;
        while (lastDNode.next != head){
            lastDNode = lastDNode.next;
            i++;
        }

        if (index>i){
            throw  new  RuntimeException("index out of the linkedList count");
        }

        DNode deleteNode = head;
        int j = 1;
        while (j<index){
            deleteNode = deleteNode.next;
            j++;
        }
        // 这里要注意是要修改的前置结点的next指向，和后置结点的pre指向。不是修改前置结点和后置结点
        deleteNode.next.pre = deleteNode.pre;
        deleteNode.pre.next = deleteNode.next;
        return  true;
    }

    public void print(){
        DNode current = head;

        while (current.next!=head){
            System.out.println(current.data);
            current = current.next;
        }
        System.out.println(current.data);
        System.out.println();
    }

    /**
     * 双向链表的指针
     * @param <T>
     */
    public class DNode<T> {
        public T data;
        public DNode pre;
        public DNode next;

        public DNode(T data){
            this.data = data;
        }
    }
}
