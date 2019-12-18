package stack;

import link.Node;

import java.util.LinkedList;

/**
 *  使用链表实现栈
 * @title
 * @author shixin peng
 * @date 2019-12-12 08:40
 * @param
 * @return null
 * @throws
 */
public class LinkedStack {

    Node<String> top;

    public LinkedStack() {

    }

    /**
     * 出栈
     * @return
     */
    public String pop(){

        if (top == null){
            return null;
        }
        Node item = top;
        top = top.next;
        return (String) item.data;
    }

    /**
     * 入栈
     * @return
     */
    public boolean push(String data){
        if (null == data){
            return false;
        }
        Node<String> item = new Node<>(data);
        if (top == null){
            top = item;
        }else {
            item.next = top;
            top = item;
        }
        return true;
    }

    public void clear(){
        top = null;
    }
    public static void main(String[] args) {
        LinkedStack linkedStack = new LinkedStack();

        linkedStack.push("a");
        linkedStack.push("b");
        linkedStack.push("c");
        linkedStack.push("d");
        System.out.println(linkedStack.toString());

        String data1 = linkedStack.pop();
        System.out.println(data1);
        String data2 = linkedStack.pop();
        System.out.println(data2);
        String data3 = linkedStack.pop();
        System.out.println(data3);
        String data4 = linkedStack.pop();
        System.out.println(data4);
        String data5 = linkedStack.pop();
        System.out.println(data5);
    }
}
