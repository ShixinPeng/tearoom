package link;

import jdk.nashorn.internal.ir.IfNode;

public class Node<T> {
    /**
     * 数据部分
     */
    T data;
    /**
     * 下一个节点
     * */
    public Node next;

    public Node(T data){
        this.data = data;
    }

    public Node add(T data){
       Node newNode  = this;
      while (newNode.next != null) {
          newNode = newNode.next;
      }
      newNode.next = new Node(data);
      return this;
    }

    @Override
    public String toString() {

        String next = null != this.next ? this.next.toString():"{}";
        return "Node{" +
                "data=" + data +
                ", next=" + next +
                '}';
    }
}
