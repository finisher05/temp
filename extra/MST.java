import java.util.*;

class MST {
    class Edge implements Comparable<Edge> {
        int src, dest, weight;

        // Comparator function to sort edges by weight
        public int compareTo(Edge compareEdge) {
            return this.weight - compareEdge.weight;
        }
    }

    class Subset {
        int parent, rank;
    }

    int V, E;    // V: Number of vertices, E: Number of edges
    Edge edge[]; // Collection of all edges

    // Constructor to create a graph with V vertices and E edges
    MST(int v, int e) {
        V = v;
        E = e;
        edge = new Edge[E];
        for (int i = 0; i < e; ++i)
            edge[i] = new Edge();
    }

    // A utility function to find set of an element i (uses path compression technique)
    int find(Subset subsets[], int i) {
        if (subsets[i].parent != i)
            subsets[i].parent = find(subsets, subsets[i].parent);
        return subsets[i].parent;
    }

    // A function that does union of two sets of x and y (uses union by rank)
    void union(Subset subsets[], int x, int y) {
        int xroot = find(subsets, x);
        int yroot = find(subsets, y);

        if (subsets[xroot].rank < subsets[yroot].rank)
            subsets[xroot].parent = yroot;
        else if (subsets[xroot].rank > subsets[yroot].rank)
            subsets[yroot].parent = xroot;
        else {
            subsets[yroot].parent = xroot;
            subsets[xroot].rank++;
        }
    }

    void KruskalMST() {
        Edge result[] = new Edge[V];
        int e = 0;
        for (int i = 0; i < V; ++i)
            result[i] = new Edge();

        Arrays.sort(edge);

        Subset subsets[] = new Subset[V];
        for (int v = 0; v < V; ++v) {
            subsets[v] = new Subset();
            subsets[v].parent = v;
            subsets[v].rank = 0;
        }

        int i = 0;
        while (e < V - 1) {
            Edge next_edge = edge[i++];

            int x = find(subsets, next_edge.src);
            int y = find(subsets, next_edge.dest);

            if (x != y) {
                result[e++] = next_edge;
                union(subsets, x, y);
            }
        }

        System.out.println("Following are the edges in the constructed MST:");
        for (i = 0; i < e; ++i)
            System.out.println(result[i].src + " -- " + result[i].dest + " == " + result[i].weight);
    }

    public static void main(String[] args) {
        int V = 5;
        int E = 7; // A connected graph with 7 edges
        MST graph = new MST(V, E);

        // Add edges
        graph.edge[0].src = 0;
        graph.edge[0].dest = 1;
        graph.edge[0].weight = 2;

        graph.edge[1].src = 0;
        graph.edge[1].dest = 3;
        graph.edge[1].weight = 6;

        graph.edge[2].src = 1;
        graph.edge[2].dest = 3;
        graph.edge[2].weight = 8;

        graph.edge[3].src = 1;
        graph.edge[3].dest = 4;
        graph.edge[3].weight = 5;

        graph.edge[4].src = 1;
        graph.edge[4].dest = 2;
        graph.edge[4].weight = 3;

        graph.edge[5].src = 2;
        graph.edge[5].dest = 4;
        graph.edge[5].weight = 7;

        graph.edge[6].src = 3;
        graph.edge[6].dest = 4;
        graph.edge[6].weight = 9;

        graph.KruskalMST();
    }
}
