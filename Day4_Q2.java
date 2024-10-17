// Node class for LinkedList
class Node {
    int data;
    Node next;

    public Node(int data) {
        this.data = data;
        this.next = null;
    }
}

// Circular Queue using Linked List
class CircularQueue {
    private Node front, rear;

    // Constructor to initialize an empty queue
    public CircularQueue() {
        front = rear = null;
    }

    // Check if the queue is empty
    public boolean isEmpty() {
        return front == null;
    }

    // Enqueue operation
    public void enqueue(int value) {
        Node newNode = new Node(value);
        if (isEmpty()) {
            front = rear = newNode;
            rear.next = front;  // Circular linking
        } else {
            rear.next = newNode;
            rear = newNode;
            rear.next = front;  // Circular linking
        }
        System.out.println("Enqueued: " + value);
    }

    // Dequeue operation
    public int dequeue() {
        if (isEmpty()) {
            System.out.println("Queue is empty. Cannot dequeue.");
            return -1;
        }

        int value;
        if (front == rear) { // Single element in the queue
            value = front.data;
            front = rear = null;
        } else {
            value = front.data;
            front = front.next;
            rear.next = front;  // Maintain circular linking
        }
        return value;
    }

    // Display the elements of the circular queue
    public void display() {
        if (isEmpty()) {
            System.out.println("Queue is empty.");
            return;
        }

        Node temp = front;
        System.out.print("Queue: ");
        do {
            System.out.print(temp.data + " ");
            temp = temp.next;
        } while (temp != front);
        System.out.println();
    }
}

// Main Class to test Circular Queue
public class Day4_Q2 {
    public static void main(String[] args) {
        CircularQueue queue = new CircularQueue();
        queue.enqueue(10);
        queue.enqueue(20);
        queue.enqueue(30);

        queue.display();

        System.out.println("Dequeued: " + queue.dequeue());

        queue.display();
    }
}