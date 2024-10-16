/**
 Implement stack using array
 */
package DSA_BOOTCAMP_9DAYS;
import java.util.Scanner;
class Stack {
    int[] stackArray;  // Array to store stack elements
    int top;           // Top of the stack
    int capacity;      // Maximum capacity of the stack

    // Constructor to initialize the stack
    public Stack(int size) {
        stackArray = new int[size];
        capacity = size;
        top = -1;  // Initial value of top is -1 indicating an empty stack
    }

    // (push operation)
    public void push(int value) {
        if (isFull()) {
            System.out.println("Stack Overflow! Cannot push " + value);
            return;
        }
        top++;
        stackArray[top] = value;
        System.out.println("Pushed " + value + " to the stack.");
    }

    // (pop operation)
    public int pop() {
        if (isEmpty()) {
            System.out.println("Stack Underflow! Cannot pop.");
            return -1;
        }
        return stackArray[top--];
    }

    // (peek operation)
    public int peek() {
        if (isEmpty()) {
            System.out.println("Stack is empty.");
            return -1;
        }
        return stackArray[top];
    }

    //  is empty
    public boolean isEmpty() {
        return top == -1;
    }

    // is full
    public boolean isFull() {
        return top == capacity - 1;
    }

    // current size of the stack
    public int size() {
        return top + 1;
    }
}

// Day1 class to test the Stack implementation
public class Day1 {
    public static void main(String[] args) {
        // Stack stack = new Stack(5);
        // stack.push(10);
        // stack.push(20);
        // stack.push(30);
        // stack.push(40);
        // stack.push(50);
        // System.out.println("Top element is: " + stack.peek());
        // System.out.println("Stack size is: " + stack.size());
        // stack.pop();
        // stack.pop();
        // System.out.println("Top element after pop operations: " + stack.peek());
        // System.out.println("Stack size after pop operations: " + stack.size());
        // stack.push(60);  // Trying to push an element when stack is not full

        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the size of the stack:");
        int size = scanner.nextInt();
        
        Stack stack = new Stack(size);
        
        int choice;
        do {
            System.out.println("\n--- Stack Operations Menu ---");
            System.out.println("1. Push");
            System.out.println("2. Pop");
            System.out.println("3. Peek");
            System.out.println("4. Check if Empty");
            System.out.println("5. Check if Full");
            System.out.println("6. Get Stack Size");
            System.out.println("7. Exit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("Enter value to push: ");
                    int value = scanner.nextInt();
                    stack.push(value);
                    break;
                case 2:
                    stack.pop();
                    System.out.println("Top is removed.");
                    break;
                case 3:
                    System.out.println("Top element is: " + stack.peek());
                    break;
                case 4:
                    System.out.println("Stack is " + (stack.isEmpty() ? "empty." : "not empty."));
                    break;
                case 5:
                    System.out.println("Stack is " + (stack.isFull() ? "full." : "not full."));
                    break;
                case 6:
                    System.out.println("Stack size is: " + stack.size());
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