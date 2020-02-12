package skipList;

import link.Node;

/**
 * @author shixinpeng
 * @description 跳表
 * @ClassName: SkipList
 * @date 2020/2/12
 *
 */
public class SkipList {
    public final static int MAX_LEVEL = 16;
    public final static float SKIPLIST_P = 0.5f;

    private int levelCount = 1;

    private SkipListNode head = new SkipListNode();

    public SkipListNode find(int value) {
        SkipListNode p = head;
        for (int i = levelCount - 1;i>=0;--i){
            while (p.forwards[i] != null && p.forwards[i].data < value){
                p = p.forwards[i];
            }
        }
        if (p.forwards[0] != null && p.forwards[0].data == value){
            return p.forwards[0];
        }else {
            return null;
        }
    }
    public void  insert(int value){
        int level = randomLevel();
        SkipListNode newNode = new SkipListNode();
        newNode.data = value;
        newNode.maxLevel = level;
        SkipListNode[] update = new SkipListNode[level];
        for (int i = 0; i < level; i++) {
            update[i] = head;
        }

        SkipListNode p = head;
        // 记录每个级别中小于插入值的最大值，并保存到update[]中
        for (int i = level - 1; i >=0 ; i--) {
            while (p.forwards[i] != null && p.forwards[i].data < value){
                p = p.forwards[i];
            }
            // update[] 中保存查找的路径
            update[i] = p;
        }
        // 在查找路径的下一个结点成为新的结点
        for (int i = 0; i < level; i++) {
            newNode.forwards[i] = update[i].forwards[i];
            update[i].forwards[i] = newNode;
        }

        if (levelCount < level) levelCount = level;

    }

    private int randomLevel() {
        int level = 1;

        while (Math.random() < SKIPLIST_P && level < MAX_LEVEL)
            level += 1;
        return level;
    }
    public void printAll() {
        SkipListNode p = head;
        while (p.forwards[0] != null) {
            System.out.print(p.forwards[0] + " ");
            p = p.forwards[0];
        }
        System.out.println();
    }

    public class SkipListNode {
        private int data = -1;
        private SkipListNode forwards[] = new SkipListNode[MAX_LEVEL];
        private int maxLevel = 0;

        @Override
        public String toString() {
            StringBuilder builder = new StringBuilder();
            builder.append("{ data: ");
            builder.append(data);
            builder.append("; levels: ");
            builder.append(maxLevel);
            builder.append(" }");

            return builder.toString();
        }
    }

    public static void main(String[] args) {
        SkipList skipList = new SkipList();
//        skipList.printAll();
//        skipList.insert(1);
//        skipList.printAll();
//        skipList.insert(2);
//        skipList.insert(3);
//        skipList.printAll();
        for (int i = 5; i < 1000; i++) {
            skipList.insert(i);
        }
        skipList.printAll();
        skipList.find(500);

    }

}
