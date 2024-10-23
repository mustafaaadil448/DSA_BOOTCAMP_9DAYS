import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

// Node class representing each node in the tree
class Node {
    int data;
    Node left, right;

    Node(int item) {
        data = item;
        left = right = null;
    }
}

// Class for performing BFS traversal on a binary tree
public class Day7_Q1 {
    Node root;

    // Function to insert a node in the binary tree in level order
    void insert(int data) {
        Node newNode = new Node(data);

        // If tree is empty, set root to new node
        if (root == null) {
            root = newNode;
            return;
        }

        // Use a queue to do level-order traversal to find the first empty spot
        Queue<Node> queue = new LinkedList<>();
        queue.add(root);

        while (!queue.isEmpty()) {
            Node temp = queue.poll();

            // If the left child is empty, insert there
            if (temp.left == null) {
                temp.left = newNode;
                break;
            } else {
                queue.add(temp.left);
            }

            // If the right child is empty, insert there
            if (temp.right == null) {
                temp.right = newNode;
                break;
            } else {
                queue.add(temp.right);
            }
        }
    }

    // Function to perform BFS (level-order) traversal
    void bfs() {
        if (root == null) {
            return; // If the tree is empty
        }

        // Create a queue for BFS
        Queue<Node> queue = new LinkedList<>();
        queue.add(root);

        // While the queue is not empty
        while (!queue.isEmpty()) {
            // Remove the front node from the queue and print its data
            Node currentNode = queue.poll();
            System.out.print(currentNode.data + " ");

            // Add the left child to the queue if it exists
            if (currentNode.left != null) {
                queue.add(currentNode.left);
            }

            // Add the right child to the queue if it exists
            if (currentNode.right != null) {
                queue.add(currentNode.right);
            }
        }
    }

    public static void main(String[] args) {
        Day7_Q1 tree = new Day7_Q1();
        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\nBinary Tree Operations Menu:");
            System.out.println("1. Insert Node");
            System.out.println("2. Perform BFS Traversal");
            System.out.println("0. Exit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("Enter value to insert: ");
                    int value = scanner.nextInt();
                    tree.insert(value);
                    System.out.println("Node inserted.");
                    break;

                case 2:
                    System.out.println("BFS (Level-order) traversal of the binary tree:");
                    tree.bfs();
                    System.out.println();
                    break;

                case 0:
                    System.out.println("Exiting...");
                    break;

                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
        } while (choice != 0);

        scanner.close();
    }
}