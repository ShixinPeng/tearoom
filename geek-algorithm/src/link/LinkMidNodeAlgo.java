package link;

/**
 *  求链表中间结点
 * @title
 * @author shixin peng
 * @date 2019-12-11 08:41
 * @param null
 * @return null
 * @throws
 */
public class LinkMidNodeAlgo {

    public static Node getMidNode(Node head){

        Node fast =head,slow = fast;
        while (null != fast.next && null != slow.next){
            fast = fast.next.next;
            slow = slow.next;
            if (null == fast){
                throw new RuntimeException("结点不为奇数");
            }

        }
        return slow;
    }

    public static void main(String[] args) {
        Node<Integer> s = new Node<>(2);
        s.add(4).add(6).add(8).add(10).add(12).add(20);
        Node midNode = getMidNode(s);
        System.out.println(midNode.data);

    }
}
