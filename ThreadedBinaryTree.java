//Idea of threaded binary trees is to make inorder traversal faster and do it without stack and without recursion.

class Node {
    int data;
    Node left, right;
    boolean isRightThreaded;
    boolean isLeftThreaded;

    public Node(int data) {
        this.data = data;
        this.left = null;
        this.right = null;
        this.isLeftThreaded = false;
        this.isRightThreaded = false;
    }
}

class ThreadedBinaryTree {
    private Node root;

    public ThreadedBinaryTree() {
        root = null;
    }

    public void insert(int data) {
        Node newNode = new Node(data);

        if (root == null) {
            root = newNode;
            return;
        }

        Node current = root;
        Node parent = null;

        while (current != null) //Helps to reach the place where the insertion will be done.
        {
            parent = current;

            if (data < current.data) {
                if (!current.isLeftThreaded) {
                    current = current.left;
                } else {
                    break;
                }
            } else {
                if (!current.isRightThreaded) {
                    current = current.right;
                } else {
                    break;
                }
            }
        }

        if (data < parent.data) {
            newNode.left = parent.left;
            newNode.isLeftThreaded = true;
            newNode.right = parent;
            newNode.isRightThreaded = true;
            parent.left = newNode;
            parent.isLeftThreaded = false;
        } else {
            if (parent.isRightThreaded) {
                newNode.right = parent.right;
                newNode.isRightThreaded = true;
            }
            newNode.left = parent;
            newNode.isLeftThreaded = true;
            parent.right = newNode;
            parent.isRightThreaded = false;
        }
    }

    public Node leftmost(Node node) {
        if (node == null) {
            return null;
        }
        while (node.left != null && !node.isLeftThreaded) {
            node = node.left;
        }
        return node;
    }

    public void inorder() {
        Node current = leftmost(root);

        while (current != null) {
            System.out.print(current.data + " ");

            if (current.isRightThreaded) {
                current = current.right;
            } else {
                current = leftmost(current.right);
            }
        }
        System.out.println();
    }

    public static void main(String[] args) {
        ThreadedBinaryTree tree = new ThreadedBinaryTree();
        tree.insert(20);
        tree.insert(10);
        tree.insert(30);
        tree.insert(5);
        tree.insert(15);
        tree.insert(25);
        tree.insert(35);

        System.out.println("Inorder Traversal of the constructed tree is:");
        tree.inorder();
    }
}
