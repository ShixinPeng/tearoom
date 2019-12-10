package link;

/**
 * @author shixinpeng
 * @description 链表循环检测
 * @ClassName: LinkLoopCheckAlgo
 * @date 2019/12/10
 *
 */
public class LinkLoopCheckAlgo {

    public static Boolean checkLoop(Node head) {

        if (null == head) return false;

        Node fast = head, slow = head;

        while (null != slow.next && null != fast.next && null!= fast.next.next){
            slow = slow.next;
            fast = fast.next.next;
            System.out.println(String.format("fast:%s slow:%s",fast.data,slow.data));
            if (fast == slow){
                return true;
            }
        }

        return  false;
    }

    public static void main(String[] args) {
        Node head = new Node(0);

        Node node1 = new Node(1),node2 = new Node(2),node3 = new Node(3),node4 = new Node(4),node5 = new Node(5);

        head.next = node1;
        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node5;
        node5.next = node2;

        Boolean checkLoop = checkLoop(head);

        System.out.println("checkLoop:"+checkLoop);


    }
}
