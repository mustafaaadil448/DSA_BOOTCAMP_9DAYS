import java.util.Arrays;
import java.util.Scanner;

public class Day7_Q4 {

    // Linear Search
    public static int linearSearch(int[] arr, int key) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == key) {
                return i; // Element found at index i
            }
        }
        return -1; // Element not found
    }

    // Binary Search (Array must be sorted)
    public static int binarySearch(int[] arr, int key) {
        int left = 0, right = arr.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;

            // Check if key is present at mid
            if (arr[mid] == key) {
                return mid;
            }

            // If key is greater, ignore the left half
            if (arr[mid] < key) {
                left = mid + 1;
            }
            // If key is smaller, ignore the right half
            else {
                right = mid - 1;
            }
        }
        return -1; // Element not found
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Ask for array input
        System.out.print("Enter the number of elements in the array: ");
        int n = sc.nextInt();
        int[] arr = new int[n];

        System.out.println("Enter the elements of the array: ");
        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
        }

        // Ask the user for the search algorithm choice
        System.out.println("Choose the search algorithm: ");
        System.out.println("1. Linear Search");
        System.out.println("2. Binary Search (Array must be sorted)");
        System.out.print("Enter your choice: ");
        int choice = sc.nextInt();

        // Ask for the element to search
        System.out.print("Enter the element to search for: ");
        int key = sc.nextInt();

        // Search based on user's choice
        switch (choice) {
            case 1:
                // Perform Linear Search
                int linearResult = linearSearch(arr, key);
                if (linearResult != -1) {
                    System.out.println("Element found at index " + linearResult + " using Linear Search.");
                } else {
                    System.out.println("Element not found using Linear Search.");
                }
                break;
            case 2:
                // Perform Binary Search (Sort the array first)
                Arrays.sort(arr);
                System.out.println("Array after sorting: " + Arrays.toString(arr));
                int binaryResult = binarySearch(arr, key);
                if (binaryResult != -1) {
                    System.out.println("Element found at index " + binaryResult + " using Binary Search.");
                } else {
                    System.out.println("Element not found using Binary Search.");
                }
                break;
            default:
                System.out.println("Invalid choice.");
        }

        sc.close();
    }
}