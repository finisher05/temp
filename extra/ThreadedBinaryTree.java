class ThreadedBinaryTree {
    class Node {
        int data;
        Node left, right;
        boolean isLeftThreaded, isRightThreaded;

        Node(int data) {
            this.data = data;
            left = right = null;
            isLeftThreaded = isRightThreaded = false;
        }
    }

    private Node root;

    // Insert a node into the threaded binary tree
    public void insert(int data) {
        System.out.println("Inserting: " + data);
        Node newNode = new Node(data);

        if (root == null) {
            root = newNode;
            return;
        }

        Node parent = null, current = root;
        while (current != null) {
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
            newNode.right = parent;
            parent.isLeftThreaded = false;
            parent.left = newNode;
            newNode.isLeftThreaded = true;
            newNode.isRightThreaded = true;
        } else {
            newNode.right = parent.right;
            newNode.left = parent;
            parent.isRightThreaded = false;
            parent.right = newNode;
            newNode.isLeftThreaded = true;
            newNode.isRightThreaded = true;
        }
    }

    // Inorder traversal using threads
    public void inorderTraversal() {
        if (root == null) {
            return;
        }

        Node current = leftMost(root);
        while (current != null) {
            System.out.print(current.data + " ");

            if (current.isRightThreaded) {
                current = current.right;
            } else {
                current = leftMost(current.right);
            }
        }
    }

    // Utility function to find the leftmost node
    private Node leftMost(Node node) {
        if (node == null) {
            return null;
        }

        while (node.left != null && !node.isLeftThreaded) {
            node = node.left;
        }
        return node;
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

        System.out.println("Inorder Traversal:");
        tree.inorderTraversal();
    }
}
