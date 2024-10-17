// Node class for Singly Linked List
class Node {
    int data;
    Node next;  // Points to the next node

    // Constructor
    public Node(int data) {
        this.data = data;
        this.next = null;
    }
}

// Double-ended Queue (Deque) class using Singly Linked List
class CustomDeque {
    Node front;  // Points to the front of the deque
    Node rear;   // Points to the rear of the deque

    // Constructor
    public CustomDeque() {
        front = rear = null;
    }

    // Check if the deque is empty
    public boolean isEmpty() {
        return front == null;
    }

    // Insert element at the front of the deque
    public void insertFront(int value) {
        Node newNode = new Node(value);
        if (isEmpty()) {
            front = rear = newNode;
        } else {
            newNode.next = front;
            front = newNode;
        }
        System.out.println("Inserted " + value + " at the front.");
    }

    // Insert element at the rear of the deque
    public void insertRear(int value) {
        Node newNode = new Node(value);
        if (isEmpty()) {
            front = rear = newNode;
        } else {
            rear.next = newNode;
            rear = newNode;
        }
        System.out.println("Inserted " + value + " at the rear.");
    }

    // Remove element from the front of the deque
    public void removeFront() {
        if (isEmpty()) {
            System.out.println("Deque is empty. Cannot remove from front.");
        } else {
            System.out.println("Removed " + front.data + " from the front.");
            front = front.next;
            if (front == null) {
                rear = null;  // If the deque is now empty, set rear to null
            }
        }
    }

    // Remove element from the rear of the deque
    public void removeRear() {
        if (isEmpty()) {
            System.out.println("Deque is empty. Cannot remove from rear.");
        } else {
            System.out.println("Removed " + rear.data + " from the rear.");

            // If there's only one element, update both front and rear to null
            if (front == rear) {
                front = rear = null;
            } else {
                Node temp = front;
                // Traverse to the second last node
                while (temp.next != rear) {
                    temp = temp.next;
                }
                rear = temp;
                rear.next = null;
            }
        }
    }

    // Peek at the front element
    public int peekFront() {
        if (isEmpty()) {
            System.out.println("Deque is empty.");
            return -1;
        } else {
            return front.data;
        }
    }

    // Peek at the rear element
    public int peekRear() {
        if (isEmpty()) {
            System.out.println("Deque is empty.");
            return -1;
        } else {
            return rear.data;
        }
    }

    // Display the deque from front to rear
    public void display() {
        if (isEmpty()) {
            System.out.println("Deque is empty.");
        } else {
            Node temp = front;
            System.out.print("Deque: ");
            while (temp != null) {
                System.out.print(temp.data + " ");
                temp = temp.next;
            }
            System.out.println();
        }
    }
}

// Main class to test the Deque implementation
public class Day4_Q3 {
    public static void main(String[] args) {
        CustomDeque deque = new CustomDeque();

        // Test inserting elements
        deque.insertFront(10);
        deque.insertRear(20);
        deque.insertFront(5);
        deque.insertRear(25);

        // Display the deque
        deque.display();

        // Test removing elements
        deque.removeFront();
        deque.removeRear();

        // Display the deque after removals
        deque.display();

        // Peek front and rear
        System.out.println("Front element is: " + deque.peekFront());
        System.out.println("Rear element is: " + deque.peekRear());
    }
}