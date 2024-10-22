import java.util.Scanner;

// Node class for Binary Search Tree (BST)
class Node {
    int data;
    Node left, right;

    // Constructor
    public Node(int data) {
        this.data = data;
        this.left = this.right = null;
    }
}

// Binary Search Tree (BST) class
class BinarySearchTree {
    Node root;

    // Constructor
    public BinarySearchTree() {
        root = null;
    }

    // Insert a node into the BST
    public Node insert(Node root, int data) {
        if (root == null) {
            root = new Node(data);
            return root;
        }
        if (data < root.data) {
            root.left = insert(root.left, data);
        } else if (data > root.data) {
            root.right = insert(root.right, data);
        }
        return root;
    }

    // Inorder traversal of the tree
    public void inorder(Node root) {
        if (root != null) {
            inorder(root.left);
            System.out.print(root.data + " ");
            inorder(root.right);
        }
    }

    // Find the minimum node in the binary tree
    public Node findMin(Node root) {
        while (root.left != null) {
            root = root.left;
        }
        System.out.println("Minimum value: " + root.data);
        return root;
    }

    // Delete a node from the binary tree
    public Node delete(Node root, int key) {
        if (root == null) {
            return root;
        }

        // Traverse to find the node to be deleted
        if (key < root.data) {
            root.left = delete(root.left, key);
        } else if (key > root.data) {
            root.right = delete(root.right, key);
        } else {
            // Node to be deleted found

            // Case 1: Node has no children (leaf node)
            if (root.left == null && root.right == null) {
                return null;
            }

            // Case 2: Node has one child
            if (root.left == null) {
                return root.right;
            } else if (root.right == null) {
                return root.left;
            }

            // Case 3: Node has two children
            Node minNode = findMin(root.right);
            root.data = minNode.data;
            root.right = delete(root.right, minNode.data);
        }

        return root;
    }
}

// Main class to test the implementation with user input
public class Day6_Q1 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        BinarySearchTree bst = new BinarySearchTree();
        boolean exit = false;

        while (!exit) {
            System.out.println("\nMenu:");
            System.out.println("1. Insert a node");
            System.out.println("2. Delete a node");
            System.out.println("3. Display inorder traversal");
            System.out.println("4. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("Enter the value to insert: ");
                    int value = scanner.nextInt();
                    bst.root = bst.insert(bst.root, value);
                    System.out.println("Value inserted.");
                    break;

                case 2:
                    System.out.print("Enter the value to delete: ");
                    int key = scanner.nextInt();
                    bst.root = bst.delete(bst.root, key);
                    System.out.println("Value deleted.");
                    break;

                case 3:
                    System.out.println("Inorder traversal of the tree:");
                    bst.inorder(bst.root);
                    System.out.println();
                    break;

                case 4:
                    exit = true;
                    break;

                default:
                    System.out.println("Invalid option! Please choose again.");
            }
        }

        scanner.close();
    }
}
