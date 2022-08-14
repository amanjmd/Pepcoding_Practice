package graph;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Objects;

import graph.Graphs.Edge;

public class Runner{

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        ArrayList<Edge>[] graph = Graphs.createGraph(br);
        Graphs.printGraph();

        //Has Path
        int src = Integer.parseInt(br.readLine());
        int dest = Integer.parseInt(br.readLine());
        Boolean[] visited = new Boolean[graph.length];
        System.out.println(hasPath(graph, src, dest, visited));
    }
    private static boolean hasPath(ArrayList<Edge>[] graph,int src, int dest, Boolean[] visited){
        if(Objects.isNull(graph) ||graph.length==0 )
            return false;
        if(src == dest)
            return true;
        if(visited[src]== Boolean.TRUE){
            return false;
        }
        visited[src]=true;
        ArrayList<Edge> edges = graph[src];
        for(Edge edge :edges){
            if(hasPath(graph, edge.nbr, dest, visited))
                return Boolean.TRUE;
            
        }

        return Boolean.FALSE;

    }

}