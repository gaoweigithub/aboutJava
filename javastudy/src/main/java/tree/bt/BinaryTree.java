package tree.bt;

public class BinaryTree {
    private Node root;
    public void Insert(int iData,double dData){
        Node newNode = new Node(iData,dData);
        if (root == null){
            root = newNode;
            return;
        }

        Node current = root;
        while (true){
            Node parent = current;
            if(newNode.iData > current.iData){
                current = current.right;
                if (current == null){
                    parent.right = newNode;
                    return;
                }
            }
            else{
                current = current.left;
                if (current == null){
                    parent.left = newNode;
                    return;
                }
            }

        }
    }
    public Node Find(int iData){
        Node current =root;
        if (current == null){
            return null;
        }

        while (current.iData != iData){
            if (iData > current.iData){
                current = current.right;
            }
            else{
                current = current.left;
            }
            if (current == null){
                return null;
            }
        }
        return current;
    }
    public Node FindMax(){
        Node current = root;

        if (current == null){
            return current;
        }

        Node max = null;
        Node min = null;
        while (current != null){
                max = current;
                current = current.right;

        }
        return max;
    }
    public Node FindMin(){
        Node current = root;

        if (current == null){
            return current;
        }

        Node min = null;
        while (current != null){
                min = current;
                current = current.left;
        }
        return min;
    }

}
