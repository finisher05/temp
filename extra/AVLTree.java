class AVLTree {
    class Node {
        String name;
        String phoneNumber;
        int height;
        Node left, right;

        Node(String name, String phoneNumber) {
            this.name = name;
            this.phoneNumber = phoneNumber;
            height = 1;
        }
    }

    private Node root;

    // Utility function to get the height of the tree
    int height(Node N) {
        if (N == null)
            return 0;
        return N.height;
    }

    // Utility function to right rotate subtree rooted with y
    Node rightRotate(Node y) {
        Node x = y.left;
        Node T2 = x.right;

        // Perform rotation
        x.right = y;
        y.left = T2;

        // Update heights
        y.height = Math.max(height(y.left), height(y.right)) + 1;
        x.height = Math.max(height(x.left), height(x.right)) + 1;

        // Return new root
        return x;
    }

    // Utility function to left rotate subtree rooted with x
    Node leftRotate(Node x) {
        Node y = x.right;
        Node T2 = y.left;

        // Perform rotation
        y.left = x;
        x.right = T2;

        // Update heights
        x.height = Math.max(height(x.left), height(x.right)) + 1;
        y.height = Math.max(height(y.left), height(y.right)) + 1;

        // Return new root
        return y;
    }

    // Get balance factor of node N
    int getBalance(Node N) {
        if (N == null)
            return 0;
        return height(N.left) - height(N.right);
    }

    // Insert a contact and print the order of insertion
    Node insert(Node node, String name, String phoneNumber) {
        if (node == null) {
            System.out.println("Inserting contact: " + name + " with phone number: " + phoneNumber);
            return new Node(name, phoneNumber);
        }

        if (phoneNumber.compareTo(node.phoneNumber) < 0)
            node.left = insert(node.left, name, phoneNumber);
        else if (phoneNumber.compareTo(node.phoneNumber) > 0)
            node.right = insert(node.right, name, phoneNumber);
        else // Duplicate phone numbers are not allowed
            return node;

        // Update height of this ancestor node
        node.height = 1 + Math.max(height(node.left), height(node.right));

        // Get the balance factor of this ancestor node to check whether
        // this node became unbalanced
        int balance = getBalance(node);

        // If this node becomes unbalanced, then there are 4 cases

        // Left Left Case
        if (balance > 1 && phoneNumber.compareTo(node.left.phoneNumber) < 0)
            return rightRotate(node);

        // Right Right Case
        if (balance < -1 && phoneNumber.compareTo(node.right.phoneNumber) > 0)
            return leftRotate(node);

        // Left Right Case
        if (balance > 1 && phoneNumber.compareTo(node.left.phoneNumber) > 0) {
            node.left = leftRotate(node.left);
            return rightRotate(node);
        }

        // Right Left Case
        if (balance < -1 && phoneNumber.compareTo(node.right.phoneNumber) < 0) {
            node.right = rightRotate(node.right);
            return leftRotate(node);
        }

        // Return the (unchanged) node pointer
        return node;
    }

    // A utility function to print preorder traversal of the tree.
    // The function also prints height of every node
    void preOrder(Node node) {
        if (node != null) {
            System.out.print(node.phoneNumber + " ");
            preOrder(node.left);
            preOrder(node.right);
        }
    }

    // Function to search for a contact
    Node search(Node root, String phoneNumber) {
        if (root == null || root.phoneNumber.equals(phoneNumber))
            return root;

        if (root.phoneNumber.compareTo(phoneNumber) > 0)
            return search(root.left, phoneNumber);

        return search(root.right, phoneNumber);
    }

    // Function to update a contact
    void update(Node root, String phoneNumber, String newName) {
        Node node = search(root, phoneNumber);
        if (node != null) {
            node.name = newName;
            System.out.println("Contact updated.");
        } else {
            System.out.println("Contact not found.");
        }
    }

    // Function to delete a node
    Node deleteNode(Node root, String phoneNumber) {
        // STEP 1: PERFORM STANDARD BST DELETE
        if (root == null)
            return root;

        // If the phoneNumber to be deleted is smaller than the root's phoneNumber,
        // then it lies in left subtree
        if (phoneNumber.compareTo(root.phoneNumber) < 0)
            root.left = deleteNode(root.left, phoneNumber);

        // If the phoneNumber to be deleted is greater than the root's phoneNumber,
        // then it lies in right subtree
        else if (phoneNumber.compareTo(root.phoneNumber) > 0)
            root.right = deleteNode(root.right, phoneNumber);

        // if phoneNumber is same as root's phoneNumber, then This is the node
        // to be deleted
        else {
            // node with only one child or no child
            if ((root.left == null) || (root.right == null)) {
                Node temp = null;
                if (temp == root.left)
                    temp = root.right;
                else
                    temp = root.left;

                // No child case
                if (temp == null) {
                    temp = root;
                    root = null;
                } else   // One child case
                    root = temp; // Copy the contents of the non-empty child
            } else {
                // node with two children: Get the inorder successor (smallest
                // in the right subtree)
                Node temp = minValueNode(root.right);

                // Copy the inorder successor's data to this node
                root.phoneNumber = temp.phoneNumber;

                // Delete the inorder successor
                root.right = deleteNode(root.right, temp.phoneNumber);
            }
        }

        // If the tree had only one node then return
        if (root == null)
            return root;

        // STEP 2: UPDATE HEIGHT OF THE CURRENT NODE
        root.height = Math.max(height(root.left), height(root.right)) + 1;

        // STEP 3: GET THE BALANCE FACTOR OF THIS NODE (to check whether
        // this node became unbalanced)
        int balance = getBalance(root);

        // If this node becomes unbalanced, then there are 4 cases

        // Left Left Case
        if (balance > 1 && getBalance(root.left) >= 0)
            return rightRotate(root);

        // Left Right Case
        if (balance > 1 && getBalance(root.left) < 0) {
            root.left = leftRotate(root.left);
            return rightRotate(root);
        }

        // Right Right Case
        if (balance < -1 && getBalance(root.right) <= 0)
            return leftRotate(root);

        // Right Left Case
        if (balance < -1 && getBalance(root.right) > 0) {
            root.right = rightRotate(root.right);
            return leftRotate(root);
        }

        return root;
    }

    Node minValueNode(Node node) {
        Node current = node;

        /* loop down to find the leftmost leaf */
        while (current.left != null)
            current = current.left;

        return current;
    }

    public static void main(String[] args) {
        AVLTree tree = new AVLTree();

        tree.root = tree.insert(tree.root, "Alice", "5551234");
        tree.root = tree.insert(tree.root, "Bob", "5552345");
        tree.root = tree.insert(tree.root, "Charlie", "5553456");
        tree.root = tree.insert(tree.root, "David", "5554567");
        tree.root = tree.insert(tree.root, "Eve", "5555678");

        System.out.println("Preorder traversal of the AVL tree is:");
        tree.preOrder(tree.root);

        // Search for a contact
        Node result = tree.search(tree.root, "5553456");
        if (result != null) {
            System.out.println("\nContact found: " + result.name + " with phone number: " + result.phoneNumber);
        } else {
            System.out.println("\nContact not found.");
        }

        // Update a contact
        tree.update(tree.root, "5553456", "Charlie Updated");

        // Delete a contact
        tree.root = tree.deleteNode(tree.root, "5554567");

        System.out.println("Preorder traversal after deletions:");
        tree.preOrder(tree.root);
    }
}
