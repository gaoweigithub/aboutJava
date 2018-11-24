package 数据结构.链表;

public class Node {
    private Node pre;
    private Node next;
    private String value;

    public Node(String value) {
        this.value = value;
    }

    public Node() {
    }

    public Node getPre() {
        return pre;
    }

    public void setPre(Node pre) {
        this.pre = pre;
    }

    public Node getNext() {
        return next;
    }

    public void setNext(Node next) {
        this.next = next;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
