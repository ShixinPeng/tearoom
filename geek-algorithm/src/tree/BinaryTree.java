package tree;

import sun.misc.Queue;

import javax.xml.crypto.Data;
import java.util.PrimitiveIterator;

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
       node.left.setLeft(7);

        /**
         *
         *          1
         *        3  6
         *      7
         *
         */

        prePrint(node);
        System.out.println("===========");
        inPrint(node);
        System.out.println("===========");
        postPrint(node);
        System.out.println("===========");
        try {
            System.out.println("=====level======");
            levelPrint(node);
        }catch (Exception e){
            e.printStackTrace();
        }


    }



}
