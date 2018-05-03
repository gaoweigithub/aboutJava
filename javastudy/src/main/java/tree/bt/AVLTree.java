package tree.bt;

public class AVLTree extends BinaryTree {
    public void RRotate(Node t) {
        Node s;
        s = t.left;
        t.left = s.right;
        s.right = t;
        t = s;
    }

    public void LRotate(Node t) {
        Node s;
        s = t.right;
        t.right = s.left;
        s.left = t;
        t = s;
    }
}
