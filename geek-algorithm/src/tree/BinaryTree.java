package tree;

import sun.misc.Queue;

import javax.xml.crypto.Data;
import java.util.PrimitiveIterator;
import java.util.Stack;

/**
 * @author shixinpeng
 * @description 二叉树
 * @ClassName: BinaryTree
 * @date 2020/2/13
 *
 */
public class BinaryTree {
    
    /**
     * @author shixinpeng 
     * @description 根节点前置打印
     * @ClassName: BinaryTree
     * @date 2020/2/13
     * 
     */
    public static void prePrint(Node root){
        if (root == null) return;
        System.out.println(root.data);
        prePrint(root.left);
        prePrint(root.right);
    }

    /**
     *  使用模拟栈来进行前序遍历
     * @title preStackPrint
     * @author shixin peng
     * @date 2020-02-13 16:26
     * @param root
     * @return void
     * @throws
     */
    public static void preStackPrint(Node root){
        Stack<Node> stack = new Stack<>();
        stack.push(root);
        // 前序遍历，先打印自身，在打印左结点，在打印右结点
        while (!stack.isEmpty()){
            Node node = stack.pop();
            System.out.println(node.data);
            if (node.right !=null){
                stack.push(node.right);
            }
            if (node.left !=null){
                stack.push(node.left);
            }

        }

    }
    /**
     *  递归中序遍历
     * @title inPrint
     * @author shixin peng
     * @date 2020-02-13 16:10
     * @param root
     * @return void
     * @throws
     */
    public static void inPrint(Node root){
        if (root==null) return;
        inPrint(root.left);
        System.out.println(root.data);
        inPrint(root.right);
    }

    /**
     * 后置打印
     * @param root
     */
    public static void postPrint(Node root){
        if (root==null) return;
        postPrint(root.left);
        postPrint(root.right);
        System.out.println(root.data);
    }

    /**
     *  层遍历
     * @title levelPrint
     * @author shixin peng
     * @date 2020-02-13 16:17
     * @param root
     * @return void
     * @throws
     */
    public static void levelPrint(Node root) throws InterruptedException {
        // 使用队列
        Queue<Node> queue = new Queue<>();

        // 依次把出队的左右子节点入队
        queue.enqueue(root);
        while (!queue.isEmpty()){
            Node node = queue.dequeue();
            System.out.println(node.data);
            if (node.left !=null){
                queue.enqueue(node.left);
            }

            if (node.right != null){
                queue.enqueue(node.right);
            }

        }
    }

    public static void main(String[] args) {
        // 构建一个二叉树
       Node node = new Node(1);
       node.setLeft(3);
       node.setRight(6);
       node.left.setLeft(5);
        node.left.setRight(8);
        node.right.setLeft(11);
        node.right.setRight(12);

        /**
         *
         *            1
         *        3     6
         *      5  8  11 12
         *
         */

        prePrint(node);
        System.out.println("===========");
        preStackPrint(node);
        System.out.println("===========");
        inPrint(node);
        System.out.println("===========");
        postPrint(node);
        System.out.println("=====level======");
        try {

            levelPrint(node);
        }catch (Exception e){
            e.printStackTrace();
        }



    }



}
