class Node {
    int data;
    Node next;

    Node(int data) {
        this.data = data;
        this.next = null;
    }
}

public class Day4_Qs4 {
    static Node head;
    static Node tail;

    // Insert at the end
    public static void addLast(int data) {
        Node newNode = new Node(data);
        if (head == null) {
            head = tail = newNode;
            return;
        }
        tail.next = newNode;
        tail = newNode;
    }

    // Reverse LinkedList using recursion and print the values
    public static void reversePrint(Node node) {
        if (node == null) {
            return;  // Base case: end of the list
        }
        reversePrint(node.next);  // Recursive call to the next node
        System.out.print(node.data + " ");  // Print after the recursive call (to reverse the order)
    }

    // Print reverse of the linked list
    public void printReverse() {
        reversePrint(head);  // Start reverse printing from the head
        System.out.println();  // Move to the next line after printing
    }

    public static void main(String[] args) {
        Day4_Qs4 list = new Day4_Qs4();
        list.addLast(10);
        list.addLast(20);
        list.addLast(30);
        list.addLast(40);
        list.addLast(50);

        System.out.println("The reversed linked list:");
        list.printReverse();  // Print the list in reverse order
    }
}