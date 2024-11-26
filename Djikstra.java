//You are tasked with optimizing delivery routes for a courier service in a city with multiple pickup and delivery locations. 
// The courier service wants to minimize the total distance travelled while ensuring timely deliveries to all destinations. Find the shortest paths between these locations using the DIJKSTRA algorithm.

import javax.swing.text.StyledEditorKit.BoldAction;

class Djikstra
{   
    static int min_distance(int[] distance , boolean[] visited)
    {
        int mini = Integer.MAX_VALUE;
        int vertex = -1;
        int vertices = visited.length;

        for(int i = 0; i<vertices;i++)
        {
            if(visited[i] == false)
            {
                if(distance[i]<mini)
                {
                    mini = distance[i];
                    vertex = i;
                }
            }
        }

        return vertex;
    }
    
    
    void djikstra(int[][] graph)
    {
       
       int vertices = graph.length;

        int[] distance = new int[vertices]; //Will store the shortest distance from source to each respective vertex
        
        boolean[] visited = new boolean[vertices];
        
        for(int i =0 ; i<vertices ; i++)
        {   
            distance[i] = Integer.MAX_VALUE;
            visited[i] = false;
        }

        int src = 0;
        distance[src] = 0;

        for(int i = 0; i<vertices-1;i++ )
        {
            int vertex = min_distance(distance, visited);
            
            visited[vertex] = true;

            for(int j = 0;j<vertices;j++)
            {
                if(visited[j] == false)
                {   
                    if(graph[vertex][j] !=0)
                    {

                    if(distance[vertex] != Integer.MAX_VALUE)
                    {
                        if( distance[vertex] + graph[vertex][j] < distance[j])
                        {
                            distance[j] = distance[vertex] + graph[vertex][j];
                        }
                    }
                    
                    }
                }

            }   
        }

        print(distance);

    }

    static void print(int[] distance)
    {   
        System.out.println("Distance of vertices from source which is vertex 0: ");

        for(int i = 0; i<distance.length;i++)
        {
            System.out.println(i+" - "+distance[i]);    
        }
    }


    public static void main(String[] args) 
    {
        Djikstra obj = new Djikstra();
        
        int[][] graph = {{0,2,3,4,0},
                        {2,0,0,6,5},
                        {3,0,0,2,4},
                        {4,6,2,0,5},
                        {0,5,4,5,0}};

        obj.djikstra(graph);
        
    }
}