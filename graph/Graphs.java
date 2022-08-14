package graph;

import java.io.BufferedReader;
import java.util.ArrayList;
import java.util.Objects;
import java.util.StringJoiner;

public class Graphs{
    static ArrayList<Edge>[] graph ;
    static class Edge {
        int src;
        int nbr;
        int wt;
  
        Edge(int src, int nbr, int wt){
           this.src = src;
           this.nbr = nbr;
           this.wt = wt;
        }
        @Override
        public String toString() {
            return nbr +"@"+ wt;
        }
     } 

     public static ArrayList<Edge>[] createGraph(BufferedReader br) throws Exception{

       
        int vtces = Integer.parseInt(br.readLine());
        graph = new ArrayList[vtces];
        for(int i = 0; i < vtces; i++){
           graph[i] = new ArrayList<>();
        }
  
        int edges = Integer.parseInt(br.readLine());
        for(int i = 0; i < edges; i++){
           String[] parts = br.readLine().split(" ");
           int v1 = Integer.parseInt(parts[0]);
           int v2 = Integer.parseInt(parts[1]);
           int wt = Integer.parseInt(parts[2]);
           graph[v1].add(new Edge(v1, v2, wt));
           graph[v2].add(new Edge(v2, v1, wt));
        }
  
        return graph;
     }
     public static void printGraph(){
         if(Objects.isNull(graph))
            throw new RuntimeException();
        System.out.println("\n====================");
        for(int i = 0 ; i< graph.length;i++){

            ArrayList<Edge> edges = graph[i];
            StringJoiner stringJoiner = new StringJoiner(",");
            System.out.print(i+" -> ");
            for(Edge edge : edges){
                stringJoiner.add(String.valueOf(edge));
            }
            System.out.println(stringJoiner.toString());
        }
     }

    
}