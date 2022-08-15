package graph;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;

import graph.Graphs.Edge;
/*
1. You are given a graph, a src vertex and a destination vertex.
2. You are give a number named "criteria" and a number "k".
3. You are required to find and print the values of 
3.1 Smallest path and it's weight separated by an "@"
3.2 Largest path and it's weight separated by an "@"
3.3 Just Larger path (than criteria in terms of weight) and it's weight separated by an "@"
3.4 Just smaller path (than criteria in terms of weight) and it's weight separated by an "@"
3.5 Kth largest path and it's weight separated by an "@"

https://nados.io/question/multisolver-smallest-longest-ceil-floor-kthlargest-path?zen=true

*/
public class Runner {
    static int smallestWeight = Integer.MAX_VALUE;
    static String pathWithSmallestWeight;
    static int largestWeight = Integer.MIN_VALUE;
    private static String pathWithLargesttWeight;
    static PriorityQueue<Pair> pq = null;
    static String pathJustLargest;
    static int weightJustLargest= Integer.MAX_VALUE;
    static String pathJustSmallest;
    static int weightJustSmallest= Integer.MIN_VALUE;

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
        int criteria = Integer.parseInt(br.readLine());
        int kForkLargest = Integer.parseInt(br.readLine());
        boolean[] visited = new boolean[graph.length];
        // System.out.println(hasPath(graph, src, dest, visited));
        // printAllPath(graph, src, dest, visited,""+src);

        //Multisolver Begin
        pq=new PriorityQueue<Pair>(kForkLargest);
        multisolver(graph, src, dest, visited, "" + src, 0,kForkLargest,criteria);
        System.out.println("Smallest Path = " + pathWithSmallestWeight + "@" + smallestWeight);
        System.out.println("Largest Path = " + pathWithLargesttWeight + "@" + largestWeight);
        System.out.println("Just Larger Path than " +criteria+" = " + pathJustLargest + "@" + weightJustLargest);
        System.out.println("Just Smaller Path than " +criteria+" = " + pathJustSmallest + "@" + weightJustSmallest);
        System.out.println(kForkLargest+"th largest path = " + pq.peek().psf+"@"+pq.peek().wsf);
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
            int weightSoFar, int kForkLargest, int criteria) {

        if (src == dest) {
            if (weightSoFar < smallestWeight) {
                smallestWeight = weightSoFar;
                pathWithSmallestWeight = pathSoFar;

            }
            if (weightSoFar > largestWeight) {
                largestWeight = weightSoFar;
                pathWithLargesttWeight = pathSoFar;

            }
            if(weightSoFar > criteria &&weightSoFar <weightJustLargest){
                weightJustLargest = weightSoFar;
                pathJustLargest=pathSoFar;
            }

            if(weightSoFar < criteria &&weightSoFar >weightJustSmallest){
                weightJustSmallest = weightSoFar;
                pathJustSmallest=pathSoFar;
            }

            if(pq.size()<kForkLargest){
                pq.add(new Pair(weightSoFar, pathSoFar));
            }else
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
                multisolver(graph, edge.nbr, dest, visited, pathSoFar + edge.nbr, weightSoFar + edge.wt,kForkLargest,criteria);

        }

        visited[src] = false;

    }
}