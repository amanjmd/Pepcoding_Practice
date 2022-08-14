package graph;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

import graph.Graphs.Edge;

public class Runner {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        ArrayList<Edge>[] graph = Graphs.createGraph(br);
        Graphs.printGraph();

        // Has Path
        int src = Integer.parseInt(br.readLine());
        int dest = Integer.parseInt(br.readLine());
        boolean[] visited = new boolean[graph.length];
        //System.out.println(hasPath(graph, src, dest, visited));
        printAllPath(graph, src, dest, visited,""+src);

    }

    private static boolean hasPath(ArrayList<Edge>[] graph, int src, int dest, boolean[] visited) {

        if (src == dest)
            return true;
        visited[src] = true;
        ArrayList<Edge> edges = graph[src];
        for (Edge edge : edges) {
            //Do not visit the neighbours which are visited
            if (visited[edge.nbr] == false)
                if (hasPath(graph, edge.nbr, dest, visited))
                    return Boolean.TRUE;

        }
        return Boolean.FALSE;

    }

    private static void printAllPath(ArrayList<Edge>[] graph, int src, int dest, boolean[] visited, String pathSoFar){

        if(src == dest){
            System.out.println(pathSoFar);
        }
        visited[src] = true;
        ArrayList<Edge> edges = graph[src];

        for (Edge edge : edges) {
            //Do not visit the neighbours which are visited
            if(visited[edge.nbr]== false)
                printAllPath(graph, edge.nbr, dest, visited,pathSoFar + edge.nbr );      

        }
        // This below is required , when all the children are 
        // are parsed , we need to mar k it is as non visited as there an other paths as well  
        visited[src] = false;

    }
}