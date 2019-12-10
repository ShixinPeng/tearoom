package link;

/**
 *  两个有序链表的合并
 * @title
 * @author shixin peng
 * @date 2019-12-10 20:33
 * @param
 * @return null
 * @throws
 */
public class LinkMergeAlgo {
    public static Node linkMerge(Node f, Node s){

        if (null == f && null == s) {
            return null;
        }

        if (null == f){
            return s;
        }

        if (null == s){
            return f;
        }
        // 标记新的头结点 temp标记尾结点
        Node n = new Node(0) , temp = n;

        while (null != f && null != s) {
            if ((int)f.data < (int)s.data){
                // 尾结点新加结点；因为使用带头结点（哨兵），所以新增结点的操作保持一致
                temp.next = f;
                // temp 指向新的尾结点
                temp = f;
                // 去除使用过的头结点
                f = f.next;
            }else {
                // 同上处理
                temp.next = s;
                temp = s;
                s = s.next;
            }
        }
        // 补充剩余结点
        if (null == f) temp.next = s;
        if (null == s) temp.next = f;

        // 使用了带头结点，返回时把带头结点去除
        n = n.next;

        return n;
    }

    public static void main(String[] args) {

        Node<Integer> f = new Node<>(1);
        f.add(3).add(5).add(7).add(9);

        Node<Integer> s = new Node<>(2);
        s.add(4).add(6).add(8).add(10).add(12).add(20).add(23);


        Node merge = linkMerge(f, s);

        System.out.println(merge);
    }
}
