// Node class for Singly Linked List
class Node {
    int data;
    Node next;

    // Constructor for the Node
    public Node(int data) {
        this.data = data;
        this.next = null;
    }
}

// Stack class using Singly Linked List
class CustomStack {
    private Node top;

    // Constructor for the Stack class
    public CustomStack() {
        this.top = null;  // Stack is initially empty
    }

    // Push element onto the stack
    public void push(int value) {
        Node newNode = new Node(value);
        newNode.next = top;
        top = newNode;
        System.out.println("Pushed " + value + " onto the stack.");
    }

    // Pop element from the stack
    public int pop() {
        if (isEmpty()) {
            System.out.println("Stack is empty. Cannot pop.");
            return -1;  // Return -1 to indicate stack underflow
        } else {
            int poppedValue = top.data;
            top = top.next;  // Move the top pointer to the next node
            System.out.println("Popped " + poppedValue + " from the stack.");
            return poppedValue;
        }
    }

    // Peek at the top element
    public int peek() {
        if (isEmpty()) {
            System.out.println("Stack is empty.");
            return -1;
        } else {
            return top.data;
        }
    }

    // Check if the stack is empty
    public boolean isEmpty() {
        return top == null;
    }

    // Display the stack elements
    public void displayStack() {
        if (isEmpty()) {
            System.out.println("Stack is empty.");
        } else {
            Node temp = top;
            System.out.print("Stack: ");
            while (temp != null) {
                System.out.print(temp.data + " ");
                temp = temp.next;
            }
            System.out.println();
        }
    }
}

// Main class to test the Stack implementation
public class Day4_Q1 {
    public static void main(String[] args) {
        // Create a Stack object
        CustomStack stack = new CustomStack();  // Ensure this constructor exists and is defined correctly

        // Push elements onto the stack
        stack.push(10);
        stack.push(20);
        stack.push(30);

        // Display the stack
        stack.displayStack();

        // Pop elements from the stack
        stack.pop();
        stack.pop();

        // Display the stack after popping
        stack.displayStack();

        // Peek the top element
        System.out.println("Top element is: " + stack.peek());

        // Pop the last element
        stack.pop();

        // Display the stack after popping the last element
        stack.displayStack();
    }
}