package graph;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;

import graph.Graphs.Edge;

public class Runner {
    static int smallestWeight = Integer.MAX_VALUE;
    static String pathWithSmallestWeight;
    static int largestWeight = Integer.MIN_VALUE;
    private static String pathWithLargesttWeight;
    static PriorityQueue<Pair> pq = null;
    static class Pair implements Comparable<Pair> {
        int wsf;
        String psf;
  
        Pair(int wsf, String psf){
           this.wsf = wsf;
           this.psf = psf;
        }
  
        public int compareTo(Pair o){
           return this.wsf - o.wsf;
        }
     }
  
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        ArrayList<Edge>[] graph = Graphs.createGraph(br);
        Graphs.printGraph();
        
        // Has Path
        int src = Integer.parseInt(br.readLine());
        int dest = Integer.parseInt(br.readLine());
        int kForkLargest = Integer.parseInt(br.readLine());
        boolean[] visited = new boolean[graph.length];
        // System.out.println(hasPath(graph, src, dest, visited));
        // printAllPath(graph, src, dest, visited,""+src);
        pq=new PriorityQueue<Pair>(kForkLargest);
        multisolver(graph, src, dest, visited, "" + src, 0);
        System.out.println("SmallestPath " + pathWithSmallestWeight + "@" + smallestWeight);
        System.out.println("LargestPath " + pathWithLargesttWeight + "@" + largestWeight);
        System.out.println("K Largest " + pq.peek().psf+"@"+pq.peek().wsf);
    }

    private static boolean hasPath(ArrayList<Edge>[] graph, int src, int dest, boolean[] visited) {

        if (src == dest)
            return true;
        visited[src] = true;
        ArrayList<Edge> edges = graph[src];
        for (Edge edge : edges) {
            // Do not visit the neighbours which are visited
            if (visited[edge.nbr] == false)
                if (hasPath(graph, edge.nbr, dest, visited))
                    return Boolean.TRUE;

        }
        return Boolean.FALSE;

    }

    private static void multisolver(ArrayList<Edge>[] graph, int src, int dest, boolean[] visited, String pathSoFar,
            int weightSoFar) {

        if (src == dest) {
            if (weightSoFar < smallestWeight) {
                smallestWeight = weightSoFar;
                pathWithSmallestWeight = pathSoFar;

            }
            if (weightSoFar > largestWeight) {
                largestWeight = weightSoFar;
                pathWithLargesttWeight = pathSoFar;

            }
            if(weightSoFar > pq.peek().wsf)
                {
                    pq.remove();
                    pq.add(new Pair(weightSoFar, pathSoFar));
                }
        }
        visited[src] = true;
        ArrayList<Edge> edges = graph[src];

        for (Edge edge : edges) {
            // Do not visit the neighbours which are visited
            if (visited[edge.nbr] == false)
                multisolver(graph, edge.nbr, dest, visited, pathSoFar + edge.nbr, weightSoFar + edge.wt);

        }

        visited[src] = false;

    }
}