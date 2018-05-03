package tree.bt;

public class Node {
    public int iData;
    public int bf;
    public Node(int iData, double dData) {
        this.iData = iData;
        this.dData = dData;
    }
    public Node() {
    }
    public double dData;
    public Node left;
    public Node right;

    @Override
    public String toString() {
        return String.format("{%s,%s}",iData,dData);
    }
}
