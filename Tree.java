//Design a phone book application using suitable Trees data structure,enabling users to efficiently insert, search, update, and delete contacts.
//AVL tree is a Balanced Binary Tree.
//A tree is said to be balanced if height of the two subtrees of a node differs by at most one.

class Contact {
    String name;
    String number;

    public Contact(String name, String phonenumber) {
        this.name = name;
        this.number = phonenumber;
    }
}

class Node {
    Contact contact;
    Node left, right;
    int height;

    public Node(Contact contact) {
        this.contact = contact;
        this.left = null;
        this.right = null;
        this.height = 1;
    }
}

class Tree {
    private Node root;

    public Tree() {
        root = null;
    }

    public int height(Node node) {
        if (node == null)
            return 0;
        return node.height;
    }

    public int BalaFactor(Node node) {
        if (node == null)
            return 0;
        return height(node.left) - height(node.right);
    }

    Node RightRotate(Node x) {
        Node y = x.left;
        Node temp = y.right;

        y.right = x;
        x.left = temp;
        x.height = Math.max(height(x.left), height(x.right)) + 1;
        y.height = Math.max(height(y.left), height(y.right)) + 1;

        return y;
    }

    Node LeftRotate(Node x) {
        Node y = x.right;
        Node temp = y.left;

        y.left = x;
        x.right = temp;
        x.height = Math.max(height(x.left), height(x.right)) + 1;
        y.height = Math.max(height(y.left), height(y.right)) + 1;

        return y;
    }

    private Node insert(Node node, Contact contact) //Use of polymorphism.
    {
        if (node == null) {
            System.out.println(contact.name + " has been inserted.");
            return new Node(contact);
        }

        String nme = node.contact.name;
        String newnme = contact.name;

        if (newnme.compareTo(nme) < 0) {
            node.left = insert(node.left, contact); //use of recursion.
        } else if (newnme.compareTo(nme) > 0) {
            node.right = insert(node.right, contact);
        } else {
            System.out.println(newnme+" already exist.");
            return node; // Duplicate keys not allowed
        }

        node.height = Math.max(height(node.left), height(node.right)) + 1;
        int BalaFactor = BalaFactor(node);

        if (BalaFactor > 1 && newnme.compareTo(nme) < 0) {
            return RightRotate(node);
        }
        if (BalaFactor > 1 && newnme.compareTo(nme) > 0) {
            node.left = LeftRotate(node.left);
            return RightRotate(node);
        }

        if (BalaFactor < -1 && newnme.compareTo(nme) > 0) {
            return LeftRotate(node);
        }

        if (BalaFactor < -1 && newnme.compareTo(nme) < 0) {
            node.right = RightRotate(node.right);
            return LeftRotate(node);
        }

        return node;
    }

    void insert(Contact contact) {
        root = insert(root, contact);
    }

    Node search(Node node, String name) {
        if (node == null) {
            System.out.println(name + " not found in phonebook.");
            return node;
        }

        if (node.contact.name.equals(name)) {
            System.out.println("Phone number of " + name + " is " + node.contact.number);
            return node;
        }

        if (node.contact.name.compareTo(name) > 0) {
            return search(node.left, name);
        } else {
            return search(node.right, name);
        }
    }

    void update(String name, String phonenumber) {
        Node node = search(root, name);

        if (node != null) {
            node.contact.number = phonenumber;
            System.out.println("Phone number for " + node.contact.name + " has been updated to " + node.contact.number);
        }
    }

    Node miNode(Node node) {
        Node current = node;
        while (current.left != null) {
            current = current.left;
        }
        return current;
    }

    Node deleteNode(Node node, String name) {
        if (node == null) {
            System.out.println(name + " Not found in phonebook");
            return node;
        }

        String nme = node.contact.name;

        if (name.compareTo(nme) < 0) {
            node.left = deleteNode(node.left, name);
        } else if (name.compareTo(nme) > 0) {
            node.right = deleteNode(node.right, name);
        } else {
            if ((node.left == null) || node.right == null) {
                Node temp;
                if (node.left != null) {
                    temp = node.left;
                } else {
                    temp = node.right;
                }

                if (temp == null) {
                    temp = node;
                    node = null;
                } else {
                    node = temp;
                }
            } else {
                Node temp = miNode(node.right);
                node.contact = temp.contact;
                node.right = deleteNode(node.right, temp.contact.name);
            }
        }

        if (node == null)
            return node;

        node.height = Math.max(height(node.left), height(node.right)) + 1;
        int BalaFactor = BalaFactor(node);

        if (BalaFactor > 1 && BalaFactor(node.left) >= 0) {
            return RightRotate(node);
        }
        if (BalaFactor > 1 && BalaFactor(node.left) < 0) {
            node.left = LeftRotate(node.left);
            return RightRotate(node);
        }

        if (BalaFactor < -1 && BalaFactor(node.right) <= 0) {
            return LeftRotate(node);
        }

        if (BalaFactor < -1 && BalaFactor(node.right) > 0) {
            node.right = RightRotate(node.right);
            return LeftRotate(node);
        }

        return node;
    }

    void delete(String name) {
        root = deleteNode(root, name);
    }

    public static void main(String[] args) {
        // Create a tree instance
        Tree phoneBook = new Tree();

        // Inserting five contacts
        phoneBook.insert(new Contact("Alice", "12345"));
        phoneBook.insert(new Contact("Bob", "54321"));
        phoneBook.insert(new Contact("Charlie", "67890"));
        phoneBook.insert(new Contact("Bob", "4567"));
        phoneBook.insert(new Contact("David", "98765"));
        phoneBook.insert(new Contact("Eve", "11223"));

        // Search for a contact
        phoneBook.search(phoneBook.root, "Charlie");

        // Update a contact's phone number
        phoneBook.update("Eve", "11111");

        // Search again to verify the update
        phoneBook.search(phoneBook.root, "Eve");

        // Delete a contact
        phoneBook.delete("Bob");

        // Search to verify deletion
        phoneBook.search(phoneBook.root, "Bob");
    }
}
