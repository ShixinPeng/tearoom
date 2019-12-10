package link;

/**
 * 双向链表
 * 结点上除了数据还是前置结点的指向pre，还有后置结点的指向next
 *
 * [pre|【data】|next]
 *
 * 关于单链表和双链表中的插入和删除，一般都标识为单链表的删除和插入时间复杂度是O(n),双链表的时间复杂度为O(1)。因为单链表无法找到直接找到前置结点，修改其next指针。需要重新遍历，找到前置结点。
 * 但其实单链表也可以实现O(1)的删除操作，可以把删除结点的后置结点的data和next添加到删除结点，让后删除原待删除结点的后置结点即可。（相当于把后面的结点往前提了一次，覆盖了要删除的结点）
 *
 * 对于一些书上计算插入和删除链表结点给的插入和删除操作是针对直接操作给定的结点来的。实际中插入操作和删除操作看到的与书本理论上有所偏差，一般情况不会直接给你一个链表中的一个结点。
 */
public class DoubleLinkedList<T> {

    DNode head;

    /**
     * 添加结点，在末尾添加数据，新结点pre指向前置结点，前置结点的后置指向新结点
     * @param data
     */
    public void addNode(T data){
        DNode<T> newNode = new DNode<>(data);

        if (head == null){
            head = newNode;
            return;
        }

        DNode lastNode = head;
        while (lastNode.next!=null){
            lastNode = lastNode.next;
        }
        lastNode.next = newNode;
        newNode.pre = lastNode;
    }

    /**
     * 删除指定顺序的结点
     * @param index
     * @return
     */
    public boolean deleteNode(int index){
        if (head == null){
            return false;
        }

        if (index == 1){
            head = head.next;
            // 首节点pre保持指向null
            head.pre = null;
            return true;
        }

        DNode deleteNode = head;
        int i = 1;
        while (i<index){
            deleteNode = deleteNode.next;
            if (deleteNode!=null){
                i++;
            }else {
                return false;
            }

        }
        // 这里要注意是要修改的前置结点的next指向，和后置结点的pre指向。不是修改前置结点和后置结点
        deleteNode.next.pre = deleteNode.pre;
        deleteNode.pre.next = deleteNode.next;
        return  true;
    }

    public void print(){
        DNode current = head;

        while (current!=null){
            System.out.println(current.data);
            current = current.next;
        }
        System.out.println();
    }

    /**
     * 双向链表的指针
     * @param <T>
     */
    public class DNode<T> {
        T data;
        DNode pre;
        DNode next;

        public DNode(T data){
            this.data = data;
        }
    }
}
