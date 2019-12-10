package test;

import link.LinkListAlgo;
import link.Node;
import link.SingleLinkedList;

public class LinklistAlgoTest {
    public static void main(String[] args) {
        SingleLinkedList<Object> linkedList = new SingleLinkedList<>();
        linkedList.addNode(1);
        linkedList.addNode(2);
        linkedList.addNode(3);
        linkedList.addNode(4);
        linkedList.addNode(5);

//        SingleLinkedList.Node reverse = LinkListAlgo.reverse3(linkedList.head);

        Node head = new Node<>(1).add(2).add(3).add(4).add(5);

        Node node = LinkListAlgo.reverse5(head);


        System.out.println(node.toString());
    }
}
