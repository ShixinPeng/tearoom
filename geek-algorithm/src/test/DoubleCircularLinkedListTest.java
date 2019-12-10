package test;

import link.DoubleCircularLinkedList;

/**
 * 双向循环链表测试
 */
public class DoubleCircularLinkedListTest {

    public static void main(String[] args) {

        DoubleCircularLinkedList dcll = new DoubleCircularLinkedList<>();

        dcll.addNode(1);
        dcll.addNode(2);
        dcll.addNode(3);
        dcll.addNode(4);
        dcll.addNode(5);

        dcll.print();

        System.out.println(" dcll.head.pre.data：" + dcll.head.pre.data);


        System.out.println(" dcll.head.next.pre.data：" + dcll.head.next.pre.data);

        dcll.deleteNode(2);
        dcll.print();


        dcll.deleteNode(1);
        System.out.println("=========");
        dcll.print();
        dcll.deleteNode(1);
        System.out.println("=========");
        dcll.print();
        dcll.deleteNode(1);
        System.out.println("=========");
        dcll.print();

    }
}
