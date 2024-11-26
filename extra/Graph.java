import java.util.*;

class Graph {
    private int V; // Number of vertices
    private LinkedList<Edge> adj[]; // Adjacency list

    class Edge {
        int dest, weight;
        Edge(int dest, int weight) {
            this.dest = dest;
            this.weight = weight;
        }
    }

    public Graph(int V) {
        this.V = V;
        adj = new LinkedList[V];
        for (int i = 0; i < V; i++) {
            adj[i] = new LinkedList<>();
        }
    }

    public void addEdge(int src, int dest, int weight) {
        adj[src].add(new Edge(dest, weight));
    }

    // Dijkstra's algorithm
    public void dijkstra(int src) {
        PriorityQueue<Node> pq = new PriorityQueue<>(V, new Node());
        int[] dist = new int[V];
        Arrays.fill(dist, Integer.MAX_VALUE);
        pq.add(new Node(src, 0));
        dist[src] = 0;

        while (!pq.isEmpty()) {
            Node node = pq.poll();
            int u = node.node;

            for (Edge edge : adj[u]) {
                int v = edge.dest;
                int weight = edge.weight;

                if (dist[u] + weight < dist[v]) {
                    dist[v] = dist[u] + weight;
                    pq.add(new Node(v, dist[v]));
                }
            }
        }

        printSolution(dist);
    }

    public void printSolution(int[] dist) {
        System.out.println("Vertex \t\t Distance from Source");
        for (int i = 0; i < V; i++) {
            System.out.println(i + " \t\t " + dist[i]);
        }
    }

    class Node implements Comparator<Node> {
        int node, cost;
        Node() {}

        Node(int node, int cost) {
            this.node = node;
            this.cost = cost;
        }

        public int compare(Node n1, Node n2) {
            if (n1.cost < n2.cost)
                return -1;
            if (n1.cost > n2.cost)
                return 1;
            return 0;
        }
    }

    public static void main(String[] args) {
        int V = 5; // Number of vertices
        Graph graph = new Graph(V);

        // Add edges
        graph.addEdge(0, 1, 9);
        graph.addEdge(0, 2, 6);
        graph.addEdge(0, 3, 5);
        graph.addEdge(0, 4, 3);
        graph.addEdge(2, 1, 2);
        graph.addEdge(2, 3, 4);

        graph.dijkstra(0); // Source vertex
    }
}
