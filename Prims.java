/*A spanning tree of a graph G is a Subgraph that includes all vertices of the original graph and no cycle. */
/*Start from a vertex, select the shortest edge emanating from that vertex which will connect it to rest of the graph, select the vertex connected to that edge and keep repeating. */

/*O(V^2) using adjacency matrix and O((V+E) Log V) using adjacency list. */

import java.util.Scanner;

class Prims
{

    static int V;

    static int min_vertex(int[] key , boolean[] visited)
    {
        int mini = Integer.MAX_VALUE;
        int vertex = -1;
        
        for(int i = 0;i< V;i++)
        {
            if(visited[i] == false && key[i]<mini)
            {
                mini = key[i];
                vertex = i;
            }
        }

        return vertex;

    }

    void con_mst(int[][] graph)
    {
        this.V = graph.length;

        int[] mst= new int[V];
        boolean[] visited= new boolean[V];
        int[] key = new int[V];

        for(int i = 0; i<V;i++)
        {
            visited[i] = false;
            key[i] = Integer.MAX_VALUE;
        }

        key[0] = 0;
        mst[0] = -1;

        for(int i = 0;i<V-1;i++)
        {
            int u = min_vertex(key,visited);
            visited[u] = true;

            for(int j =0; j<V ;j++)
            {
                if(graph[u][j] !=0)
                {
                    if(visited[j] == false)
                    {
                        if(graph[u][j] < key[j])
                        {
                            mst[j] = u;
                            key[j] = graph[u][j];
                        }
                    }
                }
            }

        }

        print_mst(mst,graph);
    }

    static void print_mst(int mst[], int[][]graph)
    {
        int cost = 0;

        System.out.println("MST of the graph is :");
        for(int i =1;i<V;i++)
        {   
            System.out.println(mst[i]+" - "+i+" = "+graph[mst[i]][i]);
            cost = cost+graph[mst[i]][i];
        }

        System.out.println("Cost of MST is = "+cost);
    }



    public static void main(String[] args) {
        
        Prims obj = new Prims();

        int[][] graph = {{0,2,3,4,0},
                        {2,0,0,6,5},
                        {3,0,0,2,4},
                        {4,6,2,0,5},
                        {0,5,4,5,0}};

        obj.con_mst(graph);

    }

}