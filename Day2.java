package DSA_BOOTCAMP_9DAYS;

import java.util.Scanner;

class Queue {
    private int front, rear, capacity;
    private int[] queue;

    // Constructor to initialize the queue with a given size
    public Queue(int size) {
        capacity = size;
        queue = new int[capacity];
        front = 0;
        rear = -1;
    }

    // Check if the queue is full
    public boolean isFull() {
        return rear == capacity - 1;
    }

    // Check if the queue is empty
    public boolean isEmpty() {
        return front > rear;
    }

    // Enqueue operation to add an element to the queue
    public void enqueue(int value) {
        if (isFull()) {
            System.out.println("Queue is full. Cannot enqueue " + value);
        } else {
            rear++;
            queue[rear] = value; // Increment rear and insert element
            System.out.println("Enqueued " + value);
        }
    }

    // Dequeue operation to remove an element from the queue
    public int dequeue() {
        if (isEmpty()) {
            System.out.println("Queue is empty. Cannot dequeue.");
            return -1;
        } else {
            int removed = queue[front++];
            System.out.println("Dequeued " + removed);
            return removed;
        }
    }

    // Peek operation to get the front element without removing it
    public int peek() {
        if (isEmpty()) {
            System.out.println("Queue is empty.");
            return -1;
        } else {
            return queue[front];
        }
    }

    // Display the elements of the queue
    public void display() {
        if (isEmpty()) {
            System.out.println("Queue is empty.");
        } else {
            System.out.print("Queue: ");
            for (int i = front; i <= rear; i++) {
                System.out.print(queue[i] + " ");
            }
            System.out.println();
        }
    }
}

public class Day2 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        // Create a queue of size 5
        Queue queue = new Queue(5);

        int choice;
        do {
            System.out.println("\n--- Queue Operations Menu ---");
            System.out.println("1. Enqueue");
            System.out.println("2. Dequeue");
            System.out.println("3. Peek");
            System.out.println("4. Check if Empty");
            System.out.println("5. Check if Full");
            System.out.println("6. Display Queue");
            System.out.println("7. Exit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("Enter value to enqueue: ");
                    int value = scanner.nextInt();
                    queue.enqueue(value);
                    break;
                case 2:
                    queue.dequeue();
                    break;
                case 3:
                    System.out.println("Front element is: " + queue.peek());
                    break;
                case 4:
                    System.out.println("Queue is " + (queue.isEmpty() ? "empty." : "not empty."));
                    break;
                case 5:
                    System.out.println("Queue is " + (queue.isFull() ? "full." : "not full."));
                    break;
                case 6:
                    queue.display();
                    break;
                case 7:
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid choice! Please try again.");
            }
        } while (choice != 7);

        scanner.close();
    }
}
