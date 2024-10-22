
// import java.util.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

// Define the Node class
class Node {
    int data;
    Node left, right;

    // Constructor for the Node class
    public Node(int data) {
        this.data = data;
        left = right = null; // Set left and right children to null
    }
}

// Main class for binary tree operations
public class Day5_Q1 {
    Node root;

    // Function to insert a new node in the binary tree
    public void insert(int data) {
        Node newNode = new Node(data);

        // If the tree is empty, set the new node as root
        if (root == null) {
            root = newNode;
            return;
        }

        // Use a queue to perform level-order traversal
        Queue<Node> queue = new LinkedList<>();
        queue.add(root);

        while (!queue.isEmpty()) {
            Node current = queue.poll();

            // Check if the left child is null
            if (current.left == null) {
                current.left = newNode; // Insert new node here
                return;
            } else {
                queue.add(current.left); // Add left child to queue
            }

            // Check if the right child is null
            if (current.right == null) {
                current.right = newNode; // Insert new node here
                return;
            } else {
                queue.add(current.right); // Add right child to queue
            }
        }
    }

    // Insert node into the binary tree
    public Node insert(Node root, int data) {
        if (root == null) {
            root = new Node(data); // Create a new node if root is null
            return root;
        }

        if (data < root.data) {
            root.left = insert(root.left, data); // Insert in left subtree
        } else if (data > root.data) {
            root.right = insert(root.right, data); // Insert in right subtree
        }

        return root;
    }

    // Inorder Traversal (Left, Root, Right)
    public void inOrder(Node root) {
        if (root != null) {
            inOrder(root.left);
            System.out.print(root.data + " ");
            inOrder(root.right);
        }
    }

    // Preorder Traversal (Root, Left, Right)
    public void preOrder(Node root) {
        if (root != null) {
            System.out.print(root.data + " ");
            preOrder(root.left);
            preOrder(root.right);
        }
    }

    // Postorder Traversal (Left, Right, Root)
    public void postOrder(Node root) {
        if (root != null) {
            postOrder(root.left);
            postOrder(root.right);
            System.out.print(root.data + " ");
        }
    }

    // Search a node in the binary tree
    public boolean search(Node root, int key) {
        if (root == null) {
            return false;
        }

        if (root.data == key) {
            return true;
        }

        if (key < root.data) {
            return search(root.left, key);
        } else {
            return search(root.right, key);
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

    // Calculate the height of the binary tree
    public int height(Node root) {
        if (root == null) {
            return 0;
        }

        int leftHeight = height(root.left);
        int rightHeight = height(root.right);

        int treeHeight = Math.max(leftHeight, rightHeight) + 1;
        System.out.println("Height of the tree: " + treeHeight);
        return treeHeight;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Day5_Q1 tree = new Day5_Q1();
        int choice, value;

        do {
            System.out.println("\nBinary Tree Operations");
            System.out.println("1. Insert");
            System.out.println("2. Insert-Level-bise");
            System.out.println("3. Inorder Traversal");
            System.out.println("4. Preorder Traversal");
            System.out.println("5. Postorder Traversal");
            System.out.println("6. Search");
            System.out.println("7. Height");
            System.out.println("8. Minimum Node");
            System.out.println("9. Delete");
            System.out.println("10. Exit");
            System.out.print("Enter your choice: ");
            choice = sc.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("Enter value to insert: ");
                    value = sc.nextInt();
                    tree.root = tree.insert(tree.root, value);
                    break;

                case 2:
                    System.out.println("Enter the number of nodes to insert:");
                    int n = sc.nextInt();

                    for (int i = 0; i < n; i++) {
                        System.out.print("Enter value for node " + (i + 1) + ": ");
                        value = sc.nextInt();
                        tree.insert(value);
                    }
                    break;

                case 3:
                    System.out.println("Inorder Traversal:");
                    tree.inOrder(tree.root);
                    System.out.println();
                    break;

                case 4:
                    System.out.println("Preorder Traversal:");
                    tree.preOrder(tree.root);
                    System.out.println();
                    break;

                case 5:
                    System.out.println("Postorder Traversal:");
                    tree.postOrder(tree.root);
                    System.out.println();
                    break;

                case 6:
                    System.out.print("Enter the value to search: ");
                    value = sc.nextInt();
                    if (tree.search(tree.root, value)) {
                        System.out.println("Value " + value + " found in the tree.");
                    } else {
                        System.out.println("Value " + value + " not found in the tree.");
                    }
                    break;

                case 7:
                    System.out.println("Calculating height of the tree:");
                    tree.height(tree.root);
                    break;

                case 8:
                    System.out.println("Finding the minimum node:");
                    tree.findMin(tree.root);
                    break;

                case 9:
                    System.out.println("Enter the value to delete: ");
                    value = sc.nextInt();
                    tree.root = tree.delete(tree.root, value);
                    System.out.println("Value " + value + " deleted from the tree.");
                    break;

                case 10:
                    System.out.println("Exiting the program.");
                    break;

                default:
                    System.out.println("Invalid choice! Please try again.");
            }

        } while (choice != 9);

        sc.close();
    }
}