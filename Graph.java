/*A spanning tree of a graph G is a Subgraph that includes all vertices of the original graph and no cycle. */
/* Sort the edges in increasing order of weights and Select the shortest edge in a network and then select the next shortest edge which does not create a cycle */
//O(E log(E)) 

/*The Union-Find algorithm is a data structure that efficiently handles the union and find operations on sets. 
It is used to manage and merge disjoint sets and is particularly useful in graph-related algorithms.
Find:
    Determines which subset a particular element belongs to.
    This is used to check if two elements are in the same subset or different subsets.
    The find function takes an element and follows the chain of parent pointers until it reaches the root of the set to which the element belongs.
    It uses path compression to flatten the structure, making future find operations faster.

Union:
    Merges two subsets into a single subset.
    This operation is performed when we need to combine two sets into one.
    The union function takes two elements and merges the sets containing those elements.
    It uses union by rank to ensure that the smaller tree is always added under the root of the larger tree, keeping the structure balanced.
*/

import java.util.Arrays;

class Graph
{   
    int vertices;
    int edges;
    Edge edge[];

    class Edge implements Comparable<Edge> //Implements a common edge type among graphs and compare edges based on labels
    //implements is used to implement an interface(Interface is a abstract class( hides complex implementation details and exposes only the essential features of an object))
    {
        int source;
        int destination;
        int weight;

        // Comparator function to sort edges by weight
        public int compareTo(Edge compareEdge)
        {
            return this.weight - compareEdge.weight;
        }

    }

    
    public Graph(int vertices , int edges)
    {
        this.vertices = vertices;
        this.edges = edges;
        edge = new Edge[edges];

        for(int i = 0;i<edges;i++)
        {
            edge[i] = new Edge();
        }
    }


    class Subset
    {   
        int parent;
        int rank;
    }

    // Find set of an element using path compression.
    static int find(Subset[] subset , int i)
    {
        if(subset[i].parent != i)
        {
            subset[i].parent = find(subset , subset[i].parent);
        }

        return subset[i].parent;

    }

    // Perform Union of two sets by rank
    void union(Subset[] subset , int x , int y)
    {
        int x_root = find(subset, x);
        int y_root = find(subset,y);

        if(subset[x_root].rank < subset[y_root].rank)
        {
            subset[x_root].parent = y_root;
        }

        else if(subset[x_root].rank > subset[y_root].rank)
        {
            subset[y_root].parent = x_root;
        }

        else
        {
            subset[y_root].parent = x_root;
            subset[x_root].rank++;
        }

    }



    void kruskalAlgorithm()
    {
       
        int cost = 0;
        
        Edge[] result = new Edge[vertices]; // Result array to store MST
        for(int i = 0 ; i<vertices; i++)
        {
            result[i] = new Edge();
        }

        
        Subset[] subset = new Subset[vertices];
        for(int i = 0 ;i < vertices;i++)
        {
            subset[i] = new Subset();
            subset[i].parent = i;
            subset[i].rank = 0;
        }

        //Step 1: Sort all edges by weight
        Arrays.sort(edge);

        //Step2: Pick the edges one-by-one from the sorted array.
        int i = 0;
        int j = 0;

        while(i< vertices-1)
        {
            Edge nextEdge =  edge[j++];

            int x = find(subset , nextEdge.source);
            int y = find(subset, nextEdge.destination);

            // If including this edge doesn't cause a cycle, include it
            if( x != y )
            {
                result[i++] = nextEdge;
                union(subset , x , y);
            }
        }


        System.out.println("Minimum spanning tree is :");
        for(j = 0; j< i ;j++ )
        {
            System.out.println(result[j].source+" - "+result[j].destination+" = "+result[j].weight);
            cost = cost + result[j].weight;
        }

        System.out.println("Cost of MST is : "+cost);

    }
    
    public static void main(String[] args) 
    {
        int vertices = 5;
        int edges = 8;
        Graph graph = new Graph(vertices,edges);    

        graph.edge[0].source = 0;
        graph.edge[0].destination = 1;
        graph.edge[0].weight = 2;

        graph.edge[1].source = 0;
        graph.edge[1].destination = 3;
        graph.edge[1].weight = 4;

        graph.edge[2].source = 0;
        graph.edge[2].destination = 2;
        graph.edge[2].weight = 3;

        graph.edge[3].source = 1;
        graph.edge[3].destination = 3;
        graph.edge[3].weight = 6;

        graph.edge[4].source = 1;
        graph.edge[4].destination = 4;
        graph.edge[4].weight = 5;
        
        graph.edge[5].source = 2;
        graph.edge[5].destination = 3;
        graph.edge[5].weight = 2;

        graph.edge[6].source = 2;
        graph.edge[6].destination = 4;
        graph.edge[6].weight = 4;

        graph.edge[7].source = 3;
        graph.edge[7].destination = 4;
        graph.edge[7].weight = 5;

        graph.kruskalAlgorithm();
    }
}