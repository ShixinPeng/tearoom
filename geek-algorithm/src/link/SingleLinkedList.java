package link;

/**
 * 单链表
 * 单链表 节点有一个后驱指针，指向下一个节点，有data，用来存放数据
 */
public class SingleLinkedList<T> {

    /**
     * 头结点
     */
    public Node head;

    /**
     * 添加结点，在尾部追加结点
     * @param data
     */
    public void addNode(T data) {
        // 空链表情况
        Node newNode = new Node<T>(data);
        if (null == head){
         head = newNode;
         return;
        }
        // 不为空的链表追加
        Node lastNode = head;
        while (lastNode.next != null){
            lastNode = lastNode.next;
        }
        lastNode.next = newNode;
    }

    /**
     * 删除第几条数据
     * 如果是删除指定值的数据，应该是先执行遍历查询操作，找到值相等的节点，在进行删除操作
     *
     * @param index
     * @return
     */
    public Boolean deleteNode(int index){
        if (index<=0){
            throw new  RuntimeException("index<=0");
        }
        // 没有结点
        if (head==null){
            return false;
        }

        // 删除首节点
        if (index==1){
            head = head.next;
            return true;
        }

        // 删除除首节点之外的结点
        //指定要删除结点的前结点
        Node preNode = head;
        int i = 2;
        while (i<index){
            preNode = preNode.next;
            if (preNode!=null){
                i++;
            }else{
                System.out.println("index > linkedList size");
                return false;
            }
        }

        // 是否要删除的末尾结点
//        if (preNode.next.next==null){
//            // 要删除的结点next为空，为尾结点 则前结点next指向null即可
//            preNode.next = null;
//            return true;
//        }

        // 删除中间结点 前结点指向孙子结点
        preNode.next = preNode.next.next;
        return true;

    }

    /**
     * 节点
     */
    public class Node<T> {
        /**
         * 数据部分
         */
        T data;
        /**
         * 下一个节点
         * */
        Node next;

        public Node(T data){
            this.data = data;
        }

        @Override
        public String toString() {

            return "Node{" +
                    "data=" + data +
                    ", next=" + next +
                    '}';
        }
    }

    public void print(){

        if (head==null){
            System.out.println("empty!");
        }
        Node current = head;
        int i = 1;
        while (current != null){
            System.out.println(String.format("第%d位：data=%s",i,current.data.toString()) );
            current = current.next;
        }
        System.out.println();
    }
}
