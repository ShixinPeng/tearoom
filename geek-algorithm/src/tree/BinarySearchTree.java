package tree;

import org.omg.CORBA.NO_IMPLEMENT;

import java.util.logging.Level;

/**
 * @author shixinpeng
 * @description 二分查找法
 *
 * 二叉查找树 增、删、查
 * 二叉查找树支持重复数据
 * 二叉查找树求树高
 * @ClassName: BinarySearchTree
 * @date 2020/2/13
 *
 */
public class BinarySearchTree {
    private static Node tree;

    public static void insert(int data) {
        if (tree == null){
            tree = new Node(data);
            return;
        }

        // 迭代插入
        Node p = tree;
        while (p != null){
            if (data > p.data){
                // 属于右子节点
                if (p.right == null){
                    p.right = new Node(data);
                    return;
                }
                p = p.right;
            }else if (data == p.data){
                // 如果值相等，则一直找到不相同的值的节点，最后一个相同值节点B指向该节点A，该节点A的right为最后一个相同节点的right节点
              Node temp = new Node(data);
              while (p.right != null && p.right.data == data){
                  p = p.right;
              }
              // 链的插入
              temp.right = p.right;
              p.right = temp;
              return;

            }else {
                // 属于左结点
                if (p.left == null){
                    p.left = new Node(data);
                    return;
                }
                p = p.left;
            }
        }

    }

    public static Node find(int data){
        if (tree==null){
            return null;
        }
        Node p = tree;

        while (p != null){
            if (data<p.data){
                p = p.left;
            }else if (data>p.data){
                p = p.right;
            }else {
                return p;
            }
        }

        return null;

    }

    /**
     *  删除一个节点
     *  只有左节点或者右节点
     *  删除点有左右节点
     * @title delete
     * @author shixin peng
     * @date 2020-02-13 21:20
     * @param data
     * @return void
     * @throws
     */
    public static Boolean  delete(int data){
        if (tree == null){
            return false;
        }
        Node p = tree;
        Node pp = null;

        while (p!=null && p.data != data){
            pp = p;
            if (data>p.data){
                p = p.right;
            }else {
                p = p.left;
            }
        }
        // 没有要删除的数据
        if (p==null) return false;

        // 有左右子节点
        if (p.left !=null && p.right != null){
            // 查找右子树中的最小节点
            Node minP = p.right;
            // 最小节点的父节点
            Node minPP = p;
            while (minP.left!=null){
                minPP = p;
                minP = minP.left;
            }
            // 替换minP的数据
            p.data = minP.data;
            // 删除最小p 此时要删除的p只有为叶子节点和只有右子节点两种情况
            p = minP;
            pp = minPP;
        }
        // 删除叶子节点情况 || 单节点
        // p的子节点
        Node child = null;
        if (p.left !=null){
            child  = p.left;
        }else if (p.right !=null){
            child = p.right;
        }

        if (pp == null) tree = child;

        if (pp.left == p){
            pp.left = child;
        }else if (pp.right == p){
            pp.right = child;
        }
        return true;
    }

    public static void main(String[] args) {
        insert(10);
        insert(5);
        insert(15);
        insert(3);
        insert(7);
        insert(11);
        insert(12);
        insert(18);
        insert(12);
        insert(12);
        insert(11);

        try {
            BinaryTree.levelPrint(tree);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        delete(11);
        delete(11);
        delete(10);
        System.out.println("---------");
        try {
        BinaryTree.levelPrint(tree);
        } catch (InterruptedException e) {
        e.printStackTrace();
        }

    }

}
