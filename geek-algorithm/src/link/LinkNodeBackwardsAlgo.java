package link;

/**
 *  链表中倒数结点
 * @title
 * @author shixin peng
 * @date 2019-12-10 21:01
 * @param null
 * @return null
 * @throws
 */
public class LinkNodeBackwardsAlgo {
    public static Node getBackwardNode(Node head, int count){
        // 异常值处理
        if (count<=0) return  null;
        // 指针间距
        int distance = 0;
        // 前行指针，后行指针
        Node fast = head, slow = head;
        // 判断先行指针没有走到尾部
        while (null != fast){
            // 先行指针前进
            fast = fast.next;
            // 当后行指针于先行指针的间隔大于等于倒数数时，后行指针同步前进
            if (distance>=count){
                slow = slow.next;
            }
            distance++;
        }
        // 当遍历结束时，间隔数依旧小于待查倒数数时，标识倒数数值超限
        if (distance < count ){
            throw new IndexOutOfBoundsException();
        }
        return slow;
    }


    public static void main(String[] args) {
        Node<Integer> s = new Node<>(2);
        s.add(4).add(6).add(8).add(10).add(12).add(20).add(23);

        Node backwardNode = getBackwardNode(s, 9);

        System.out.println(backwardNode.data);

    }

}
