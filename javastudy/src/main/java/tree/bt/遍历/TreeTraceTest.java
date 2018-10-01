package tree.bt.遍历;

import org.apache.zookeeper.data.Stat;
import tree.bt.Node;

import java.util.Stack;

public class TreeTraceTest {
    public static void main(String[] args) {
        Node A = new Node("A");
        Node B = new Node("B");
        Node C = new Node("C");
        Node D = new Node("D");
        Node E = new Node("E");
        Node F = new Node("F");

        A.setLeft(B);
        B.setLeft(D);
        A.setRight(C);
        C.setLeft(E);
        C.setRight(F);

        Node root = A;

//        System.out.println("先序遍历");
//        printHeadTrace(root);
//        System.out.println("中序遍历");
//        printMiddleTrace(root);
        System.out.println("后序遍历");
        printLastTrace(root);
    }

    /**
     * 先序遍历
     *
     * @param node
     */
    static void printHeadTrace(Node node) {
        //1,递归的
//        if (node == null) {
//            return;
//        }
//        System.out.println(node.strData);
//        printHeadTrace(node.left);
//        printHeadTrace(node.right);

        //2,栈
        Stack<Node> stack = new Stack<>();
        Node tmp = node;
        while (tmp != null || stack.size() > 0) {
            while (tmp != null) {
                System.out.println(tmp.strData);
                stack.push(tmp);
                tmp = tmp.getLeft();
            }
            while (stack.size() > 0) {
                tmp = stack.pop();
                tmp = tmp.getRight();
            }
        }
    }

    /**
     * 中序遍历
     *
     * @param node
     */
    static void printMiddleTrace(Node node) {
//        if (node == null) return;
//        printMiddleTrace(node.left);
//        System.out.println(node.strData);
//        printMiddleTrace(node.right);

        //2.栈
        Stack<Node> stack = new Stack<>();
        Node tmp = node;
        while (tmp != null || stack.size() > 0) {
            while (tmp != null) {
                stack.push(tmp);
                tmp = tmp.getLeft();
            }
            while (stack.size() > 0) {
                tmp = stack.pop();
                System.out.println(tmp.strData);
                tmp = tmp.getRight();
            }
        }
    }

    /**
     * 后序遍历
     *
     * @param node
     */
    static void printLastTrace(Node node) {
//        if (node == null) return;
//        printLastTrace(node.left);
//        printLastTrace(node.right);
//        System.out.println(node.strData);
        //2.栈
        Stack<Node> stack = new Stack<>();
        Node cur;
        Node pre = null;
        stack.push(node);
        while (stack.size() > 0) {
            cur = stack.peek();
            if ((cur.getLeft() == null && cur.getRight() == null) || (pre != null && (pre == cur.getLeft() || pre == cur.getRight()))) {
                System.out.println(cur.strData);
                stack.pop();
                pre = cur;
            } else {
                if (cur.getRight() != null) {
                    stack.push(cur.getRight());
                }
                if (cur.getLeft() != null) {
                    stack.push(cur.getLeft());
                }
            }
        }
    }
}
