import java.util.Arrays;
import java.util.LinkedList;
import java.util.PriorityQueue;

// Class representing an edge in the graph
class Edge {
    int src, dest, wt;

    Edge(int src, int dest, int wt) {
        this.src = src;
        this.dest = dest;
        this.wt = wt;
    }
}

// Class representing the Dijkstra’s Algorithm
public class Day7_Q2 {

    // Pair class used for priority queue (for vertex and its current path distance)
    static class Pair implements Comparable<Pair> {
        int node, path;

        Pair(int node, int path) {
            this.node = node;
            this.path = path;
        }

        @Override
        public int compareTo(Pair p2) {
            return this.path - p2.path;
        }
    }

    // Dijkstra's Algorithm
    public static void dijkstra(LinkedList<Edge> graph[], int src) {
        int dist[] = new int[graph.length];
        boolean vis[] = new boolean[graph.length];

        // Initialize distances with infinity, except the source
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[src] = 0;

        // Priority queue to process the next closest node
        PriorityQueue<Pair> pq = new PriorityQueue<>();
        pq.add(new Pair(src, 0));

        // BFS with priority queue (Dijkstra's logic)
        while (!pq.isEmpty()) {
            Pair curr = pq.remove();

            if (!vis[curr.node]) {
                vis[curr.node] = true;

                // Process neighbors of the current node
                for (Edge edge : graph[curr.node]) {
                    int u = edge.src;
                    int v = edge.dest;
                    int wt = edge.wt;

                    // Relaxation step: if the path through u is shorter
                    if (dist[u] + wt < dist[v]) {
                        dist[v] = dist[u] + wt;
                        pq.add(new Pair(v, dist[v]));
                    }
                }
            }
        }

        // Print the shortest distances from the source to all vertices
        for (int i = 0; i < dist.length; i++) {
            System.out.println("Vertex " + i + "\t Distance " + dist[i]);
        }
    }

    // Function to add an edge to the graph
    public static void addEdge(LinkedList<Edge> graph[], int src, int dest, int wt) {
        graph[src].add(new Edge(src, dest, wt));
        graph[dest].add(new Edge(dest, src, wt)); // Uncomment for undirected graph
    }

    public static void main(String[] args) {
        int V = 5; // Number of vertices
        LinkedList<Edge> graph[] = new LinkedList[V];
        for (int i = 0; i < V; i++) {
            graph[i] = new LinkedList<>();
        }

        // Adding edges to the graph
        addEdge(graph, 0, 1, 2);
        addEdge(graph, 0, 2, 4);
        addEdge(graph, 1, 2, 1);
        addEdge(graph, 1, 3, 7);
        addEdge(graph, 2, 4, 3);
        addEdge(graph, 3, 4, 2);

        // Source vertex for Dijkstra's Algorithm
        int source = 0;
        System.out.println("Dijkstra's Algorithm (Shortest Path from vertex " + source + "):");
        dijkstra(graph, source);
    }
}