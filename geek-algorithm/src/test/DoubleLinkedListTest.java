package test;

import link.DoubleLinkedList;

/**
 * 双向链表测试
 */
public class DoubleLinkedListTest {

    public static void main(String[] args) {
        DoubleLinkedList<Object> doubleLinkedList = new DoubleLinkedList<>();

        doubleLinkedList.addNode(1);
        doubleLinkedList.addNode(2);
        doubleLinkedList.addNode(3);
        doubleLinkedList.addNode(4);
        doubleLinkedList.addNode(5);

        doubleLinkedList.print();

        doubleLinkedList.deleteNode(2);

        doubleLinkedList.print();

    }
}
