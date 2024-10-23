import java.util.Arrays;
import java.util.Scanner;

public class Day7_Q5 {

    // Class to represent an Edge with source, destination, and weight
    static class Edge implements Comparable<Edge> {
        int src, dest, weight;

        // Comparator function to sort edges based on their weights
        public int compareTo(Edge compareEdge) {
            return this.weight - compareEdge.weight;
        }
    }

    // Class to represent a Subset for Union-Find
    static class Subset {
        int parent, rank;
    }

    int V, E; // V = Number of vertices, E = Number of edges
    Edge[] edges; // Array of edges

    // Constructor for Kruskal's Algorithm
    Day7_Q5(int v, int e) {
        V = v;
        E = e;
        edges = new Edge[E];
        for (int i = 0; i < e; ++i)
            edges[i] = new Edge();
    }

    // Find set of an element i (uses path compression)
    int find(Subset[] subsets, int i) {
        if (subsets[i].parent != i) {
            subsets[i].parent = find(subsets, subsets[i].parent);
        }
        return subsets[i].parent;
    }

    // Union of two sets (x and y) by rank
    void union(Subset[] subsets, int x, int y) {
        int xroot = find(subsets, x);
        int yroot = find(subsets, y);

        if (subsets[xroot].rank < subsets[yroot].rank) {
            subsets[xroot].parent = yroot;
        } else if (subsets[xroot].rank > subsets[yroot].rank) {
            subsets[yroot].parent = xroot;
        } else {
            subsets[yroot].parent = xroot;
            subsets[xroot].rank++;
        }
    }

    // Main Kruskal's Algorithm function to find MST
    void kruskalMST() {
        Edge[] result = new Edge[V]; // This will store the resulting MST
        int e = 0; // Index variable for the result[]
        int i = 0; // Index variable for sorted edges
        for (i = 0; i < V; ++i) {
            result[i] = new Edge();
        }

        // Step 1: Sort all the edges in non-decreasing order of their weight
        Arrays.sort(edges);

        // Create V subsets with single elements
        Subset[] subsets = new Subset[V];
        for (i = 0; i < V; ++i) {
            subsets[i] = new Subset();
            subsets[i].parent = i;
            subsets[i].rank = 0;
        }

        i = 0; // Initial index of sorted edges

        // Number of edges in the MST will be V-1
        while (e < V - 1) {
            // Step 2: Pick the smallest edge and increment the index
            if (i >= edges.length) {
                System.out.println("Error: Not enough edges to form a Minimum Spanning Tree.");
                return;
            }

            Edge nextEdge = edges[i++];

            // Validation to ensure vertices are within bounds
            if (nextEdge.src < 0 || nextEdge.src >= V || nextEdge.dest < 0 || nextEdge.dest >= V) {
                System.out.println("Error: Edge contains invalid vertices.");
                return;
            }

            int x = find(subsets, nextEdge.src);
            int y = find(subsets, nextEdge.dest);

            // If including this edge doesn't cause a cycle, include it in the result
            if (x != y) {
                result[e++] = nextEdge;
                union(subsets, x, y);
            }
        }

        // Print the constructed MST
        System.out.println("Following are the edges in the constructed Minimum Spanning Tree:");
        int minimumCost = 0;
        for (i = 0; i < e; ++i) {
            System.out.println(result[i].src + " -- " + result[i].dest + " == " + result[i].weight);
            minimumCost += result[i].weight;
        }
        System.out.println("Minimum Cost Spanning Tree: " + minimumCost);
    }

    // Main function to take input and run Kruskal's Algorithm
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Input number of vertices and edges
        System.out.print("Enter the number of vertices: ");
        int V = sc.nextInt();
        System.out.print("Enter the number of edges: ");
        int E = sc.nextInt();

        if (V <= 0 || E <= 0) {
            System.out.println("Error: Invalid number of vertices or edges.");
            return;
        }

        Day7_Q5 graph = new Day7_Q5(V, E);

        // Input all edges
        for (int i = 0; i < E; i++) {
            System.out.print("Enter edge " + (i + 1) + " (source, destination, weight): ");
            graph.edges[i].src = sc.nextInt();
            graph.edges[i].dest = sc.nextInt();
            graph.edges[i].weight = sc.nextInt();

            // Check for valid vertex indices
            if (graph.edges[i].src < 0 || graph.edges[i].src >= V || graph.edges[i].dest < 0 || graph.edges[i].dest >= V) {
                System.out.println("Error: Invalid vertex index.");
                return;
            }
        }

        // Run Kruskal's Algorithm
        graph.kruskalMST();
    }
}