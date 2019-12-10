package test;

import link.SingleLinkedList;

public class SingleLinkedListTest {
    static SingleLinkedList<Integer> linkedList = new SingleLinkedList<>();

    static SingleLinkedList<Integer> emptyLinkedList = new SingleLinkedList<>();
    public static void main(String[] args) {

        linkedList.addNode(1);
        linkedList.addNode(2);
        linkedList.addNode(3);
        linkedList.print();
        linkedList.deleteNode(3);
        linkedList.print();
        linkedList.deleteNode(2);
        linkedList.print();
        linkedList.deleteNode(1);
        linkedList.print();

    }
}
