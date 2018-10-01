package tree.bt;

public class Node {
    public int iData;
    public double dData;
    public String strData;

    public Node(int iData) {
        this.iData = iData;
    }
    public Node(int iData,double dData) {
        this.iData = iData;
        this.dData = dData;
    }

    public Node(double dData) {
        this.dData = dData;
    }

    public Node(String sData) {
        this.strData = sData;
    }

    public Node() {
    }

    public Node getLeft() {
        return left;
    }

    public void setLeft(Node left) {
        this.left = left;
    }

    public Node getRight() {
        return right;
    }

    public void setRight(Node right) {
        this.right = right;
    }

    public Node left;
    public Node right;

    @Override
    public String toString() {
        return String.format("{%s,%s}", iData, dData);
    }
}
