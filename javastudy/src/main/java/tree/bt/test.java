package tree.bt;

public class test {
    public static void main(String[] args) {
        BinaryTree tree = new BinaryTree();
        tree.Insert(3, 3.666);
        tree.Insert(1, 1.111);
        tree.Insert(2, 2.362);
        tree.Insert(4, 4.672);
        tree.Insert(5, 5.865);
        tree.Insert(6, 6.681);

        Node node = tree.Find(6);
        System.out.println(node);

        Node min = tree.FindMin();
        System.out.println("min:" + min);

        Node max = tree.FindMax();
        System.out.println("max:" + max);
    }
}
