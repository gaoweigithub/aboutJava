package 数据结构.链表;

/**
 * 单链表反转
 * <p>
 * 链表中环的检测
 * <p>
 * 两个有序的链表合并
 * <p>
 * 删除链表倒数第 n 个结点
 * <p>
 * 求链表的中间结点
 */
public class study001 {
    public static void main(String[] args) {
        test001();
    }

    private static void test001() {
        Node head = new Node();
        Node node0 = new Node("0");
        Node node1 = new Node("1");
        Node node2 = new Node("2");
        Node node3 = new Node("3");
        Node node4 = new Node("4");
        Node node5 = new Node("5");

        head = node0;
        node0.setNext(node1);
        node1.setNext(node2);
        node2.setNext(node3);
        node3.setNext(node4);
        node4.setNext(node5);

        printLink(head);

        System.out.println("reverse");

        Node pre = head;
        Node sentinel = pre.getNext();
        pre.setNext(null);
        Node nxt;
        while (sentinel != null) {
            nxt = sentinel.getNext();
            sentinel.setNext(pre);
            pre = sentinel;
            head = pre;
            sentinel = nxt;
        }
        printLink(head);

    }

    private static void printLink(Node node) {
        Node sentinel = node;
        while (sentinel != null) {
            System.out.println(sentinel.getValue());
            sentinel = sentinel.getNext();
        }
    }
}
