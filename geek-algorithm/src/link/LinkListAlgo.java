package link;

/**
 * 单链表翻转
 */
public class LinkListAlgo {


    /**
     * 单链表反转，依次转向
     * @param head
     */
    public static SingleLinkedList.Node reverse(SingleLinkedList.Node head) {

        if (null == head || head.next == null) {
            return head;
        }
        // 新的头结点
        SingleLinkedList.Node newHead = null;
        // 末尾节点
        SingleLinkedList.Node lastNode = head;
        SingleLinkedList.Node currentNode = head;
        // 依次取链中数据
        while (currentNode.next!=null){
            if (null != newHead){
                lastNode = newHead;
            }

            // 记录即将成为头结点的结点
            newHead = head.next;
            // 先拉住要动节点的后置节点，防止断链
            currentNode.next = newHead.next;

            // 新的头结点登基
            newHead.next = lastNode;


        }
        return newHead;
    }

    public static SingleLinkedList.Node reverse2(SingleLinkedList.Node head) {

        if (null == head || head.next == null) {
            return head;
        }
        // 新的头结点
        SingleLinkedList.Node newHead = null;
        // 末尾节点
        SingleLinkedList.Node lastNode = head;
        // 依次取链中数据
        while (head.next!=null){

            // 记录即将成为头结点的结点
            newHead = head.next;
            // 先拉住要动节点的后置节点，防止断链
            head.next = newHead.next;

            // 新的头结点登基
            newHead.next = lastNode;

            lastNode = newHead;
        }
        return newHead;
    }


    public static SingleLinkedList.Node reverse3(SingleLinkedList.Node head) {

        if (null == head || head.next == null) {
            return head;
        }
        // 新的头结点
        SingleLinkedList.Node newHead = null;

        // 依次取链中数据
        while (head.next!=null){

            // 记录即将成为头结点的结点
            newHead = head.next;
            // 先拉住要动节点的后置节点，防止断链
            head.next = newHead.next;

            // 新的头结点登基
            newHead.next = head;

            head = newHead;
        }
        return newHead;
    }

    public static Node reverse5(Node head) {
        Node curr = head, pre = null;
        while (curr != null) {
            // 老二准备
            Node next = curr.next;
            // 老老大就位
            curr.next = pre;
            // 新老大上位
            pre = curr;
            // 老二上位
            curr = next;
        }
        return pre;
    }
}
