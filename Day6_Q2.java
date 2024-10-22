import java.util.Scanner;

// Class representing a node in the AVL tree
class Node {
    int data;
    Node left, right;
    int height;

    Node(int item) {
        data = item;
        left = right = null;
        height = 1; // New node is initially added at leaf
    }
}

// Class representing the AVL tree
public class Day6_Q2 {
    Node root;

    // Function to get the height of the tree
    int height(Node N) {
        if (N == null)
            return 0;
        return N.height;
    }

    // Function to get the maximum of two integers
    int max(int a, int b) {
        return (a > b) ? a : b;
    }

    // Right rotate
    Node rightRotate(Node y) {
        Node x = y.left;
        Node T2 = x.right;

        // Perform rotation
        x.right = y;
        y.left = T2;

        // Update heights
        y.height = max(height(y.left), height(y.right)) + 1;
        x.height = max(height(x.left), height(x.right)) + 1;

        // Return new root
        return x;
    }

    // Left rotate
    Node leftRotate(Node x) {
        Node y = x.right;
        Node T2 = y.left;

        // Perform rotation
        y.left = x;
        x.right = T2;

        // Update heights
        x.height = max(height(x.left), height(x.right)) + 1;
        y.height = max(height(y.left), height(y.right)) + 1;

        // Return new root
        return y;
    }

    // Get the balance factor of each node
    int getBalance(Node N) {
        if (N == null)
            return 0;
        return height(N.left) - height(N.right);
    }

    // Function to insert a new node
    Node insert(Node node, int data) {
        // 1. Perform the normal BST insertion
        if (node == null)
            return (new Node(data));

        if (data < node.data)
            node.left = insert(node.left, data);
        else if (data > node.data)
            node.right = insert(node.right, data);
        else // Duplicates are not allowed
            return node;

        // 2. Update the height of this ancestor node
        node.height = 1 + max(height(node.left), height(node.right));

        // 3. Get the balance factor
        int balance = getBalance(node);

        // If the node becomes unbalanced, then there are 4 cases

        // Left Left Case
        if (balance > 1 && data < node.left.data)
            return rightRotate(node);

        // Right Right Case
        if (balance < -1 && data > node.right.data)
            return leftRotate(node);

        // Left Right Case
        if (balance > 1 && data > node.left.data) {
            node.left = leftRotate(node.left);
            return rightRotate(node);
        }

        // Right Left Case
        if (balance < -1 && data < node.right.data) {
            node.right = rightRotate(node.right);
            return leftRotate(node);
        }

        // Return the (unchanged) node pointer
        return node;
    }

    // Function to perform inorder traversal
    void inorder(Node root) {
        if (root != null) {
            inorder(root.left);
            System.out.print(root.data + " ");
            inorder(root.right);
        }
    }

    // Function to perform preorder traversal
    void preorder(Node root) {
        if (root != null) {
            System.out.print(root.data + " ");
            preorder(root.left);
            preorder(root.right);
        }
    }

    // Function to perform postorder traversal
    void postorder(Node root) {
        if (root != null) {
            postorder(root.left);
            postorder(root.right);
            System.out.print(root.data + " ");
        }
    }

    // Function to delete a node
    Node delete(Node root, int data) {
        // STEP 1: PERFORM STANDARD BST DELETE
        if (root == null)
            return root;

        if (data < root.data)
            root.left = delete(root.left, data);
        else if (data > root.data)
            root.right = delete(root.right, data);
        else {
            // Node with only one child or no child
            if ((root.left == null) || (root.right == null)) {
                Node temp = root.left != null ? root.left : root.right;

                // No child case
                if (temp == null) {
                    return null;
                } else {
                    return temp; // One child case
                }
            } else {
                // Node with two children: Get the inorder successor (smallest in the right subtree)
                Node temp = minValueNode(root.right);

                // Copy the inorder successor's content to this node
                root.data = temp.data;

                // Delete the inorder successor
                root.right = delete(root.right, temp.data);
            }
        }

        // If the tree had only one node then return
        if (root == null)
            return root;

        // STEP 2: UPDATE THE HEIGHT OF THIS ANCESTOR NODE
        root.height = max(height(root.left), height(root.right)) + 1;

        // STEP 3: GET THE BALANCE FACTOR OF THIS ANCESTOR NODE TO CHECK WHETHER
        // THIS NODE BECAME UNBALANCED
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

    // Function to find the node with the smallest value greater than root
    Node minValueNode(Node node) {
        Node current = node;

        // Loop down to find the leftmost leaf
        while (current.left != null)
            current = current.left;

        return current;
    }

    public static void main(String[] args) {
        Day6_Q2 tree = new Day6_Q2();
        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\nAVL Tree Operations Menu:");
            System.out.println("1. Insert Node");
            System.out.println("2. Delete Node");
            System.out.println("3. Inorder Traversal");
            System.out.println("4. Preorder Traversal");
            System.out.println("5. Postorder Traversal");
            System.out.println("0. Exit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("Enter value for node to insert: ");
                    int valueToInsert = scanner.nextInt();
                    tree.root = tree.insert(tree.root, valueToInsert);
                    System.out.println("Node inserted.");
                    break;

                case 2:
                    System.out.print("Enter value for node to delete: ");
                    int valueToDelete = scanner.nextInt();
                    tree.root = tree.delete(tree.root, valueToDelete);
                    System.out.println("Node deleted.");
                    break;

                case 3:
                    System.out.println("Inorder traversal of the AVL tree:");
                    tree.inorder(tree.root);
                    System.out.println();
                    break;

                case 4:
                    System.out.println("Preorder traversal of the AVL tree:");
                    tree.preorder(tree.root);
                    System.out.println();
                    break;

                case 5:
                    System.out.println("Postorder traversal of the AVL tree:");
                    tree.postorder(tree.root);
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