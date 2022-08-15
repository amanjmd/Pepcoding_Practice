package graph;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.PriorityQueue;

import graph.Graphs.Edge;

public class Runner {
    static int smallestWeight = Integer.MAX_VALUE;
    static String pathWithSmallestWeight;
    static int largestWeight = Integer.MIN_VALUE;
    private static String pathWithLargesttWeight;
    static PriorityQueue<Pair> pq = null;
    static String pathJustLargest;
    static int weightJustLargest = Integer.MAX_VALUE;
    static String pathJustSmallest;
    static int weightJustSmallest = Integer.MIN_VALUE;
   
    static class Pair implements Comparable<Pair> {
        int wsf;
        String psf;

        Pair(int wsf, String psf) {
            this.wsf = wsf;
            this.psf = psf;
        }

        public int compareTo(Pair o) {
            return this.wsf - o.wsf;
        }
    }

    /*
10
11
0 3 10
3 2 10
2 1 10
0 1 10
1 6 10
6 4 10
6 5 10
4 5 10
8 7 10
7 9 10
9 8 10
    */

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        ArrayList<Edge>[] graph = Graphs.createGraph(br);
        Graphs.printGraph();

        // Has Path
        int src = Integer.parseInt(br.readLine());
        // int dest = Integer.parseInt(br.readLine());
        // int criteria = Integer.parseInt(br.readLine());
        // int kForkLargest = Integer.parseInt(br.readLine());
        boolean[] visited = new boolean[graph.length];
        // System.out.println(hasPath(graph, src, dest, visited));
        // printAllPath(graph, src, dest, visited,""+src);

        // Multisolver Begin
        /* pq = new PriorityQueue<Pair>(kForkLargest);
        multisolver(graph, src, dest, visited, "" + src, 0, kForkLargest, criteria);
        System.out.println("Smallest Path = " + pathWithSmallestWeight + "@" + smallestWeight);
        System.out.println("Largest Path = " + pathWithLargesttWeight + "@" + largestWeight);
        System.out.println("Just Larger Path than " + criteria + " = " + pathJustLargest + "@" + weightJustLargest);
        System.out.println("Just Smaller Path than " + criteria + " = " + pathJustSmallest + "@" + weightJustSmallest);
        System.out.println(kForkLargest + "th largest path = " + pq.peek().psf + "@" + pq.peek().wsf); */

        //GetConnected Components
        // ArrayList<ArrayList<Integer>> connectedComponents = getConnectedComponents(graph, visited) ;
        // System.out.println(connectedComponents);

        //tell if graph is completely connected , or there is a path from every vertex to every other vertex 
        //ArrayList<ArrayList<Integer>> connectedComponents = getConnectedComponents(graph, visited) ;
        //System.out.println(connectedComponents);

         breadthFirstTraversal(graph, visited,src) ;
        
    }

    /*
     * 1. You are given a graph, a src vertex and a d estination vertex.
     * 2. You are required to find if a path exists between src and dest. If it
     * does, print true
     * otherwise print false.
     * Constraints
     * None
     * Format
     * Input
     * Input has been managed for you
     * Output
     * true if path exists, false otherwise
     * 
     */
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

    /*
     * 1. You are given a graph, a src vertex and a destination vertex.
     * 2. You are give a number named "criteria" and a number "k".
     * 3. You are required to find and print the values of
     * 3.1 Smallest path and it's weight separated by an "@"
     * 3.2 Largest path and it's weight separated by an "@"
     * 3.3 Just Larger path (than criteria in terms of weight) and it's weight
     * separated by an "@"
     * 3.4 Just smaller path (than criteria in terms of weight) and it's weight
     * separated by an "@"
     * 3.5 Kth largest path and it's weight separated by an "@"
     * 
     * https://nados.io/question/multisolver-smallest-longest-ceil-floor-kthlargest-
     * path?zen=true
     * 
     */
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
            if (weightSoFar > criteria && weightSoFar < weightJustLargest) {
                weightJustLargest = weightSoFar;
                pathJustLargest = pathSoFar;
            }

            if (weightSoFar < criteria && weightSoFar > weightJustSmallest) {
                weightJustSmallest = weightSoFar;
                pathJustSmallest = pathSoFar;
            }

            if (pq.size() < kForkLargest) {
                pq.add(new Pair(weightSoFar, pathSoFar));
            } else if (weightSoFar > pq.peek().wsf) {
                pq.remove();
                pq.add(new Pair(weightSoFar, pathSoFar));
            }
        }
        visited[src] = true;
        ArrayList<Edge> edges = graph[src];

        for (Edge edge : edges) {
            // Do not visit the neighbours which are visited
            if (visited[edge.nbr] == false)
                multisolver(graph, edge.nbr, dest, visited, pathSoFar + edge.nbr, weightSoFar + edge.wt, kForkLargest,
                        criteria);

        }

        visited[src] = false;

    }

    /*
     * 1. You are given a graph, a source vertex and a destination vertex. 2. You
     * are required to find and print all paths between source and destination.
     * Print them in lexicographical order.
     * E.g. Check the following paths 012546 01256 032546 03256 The
     * lexicographically smaller path is printed first.
     * 
     * https://nados.io/question/print-all-paths
     */
    private static void printAllPath(ArrayList<Edge>[] graph, int src, int dest, boolean[] visited, String pathSoFar) {

        if (src == dest) {
            System.out.println(pathSoFar);
        }
        visited[src] = true;
        ArrayList<Edge> edges = graph[src];

        for (Edge edge : edges) {
            // Do not visit the neighbours which are visited
            if (visited[edge.nbr] == false)
                printAllPath(graph, edge.nbr, dest, visited, pathSoFar + edge.nbr);

        }

        visited[src] = false;

    }

     // You are given a graph. 2. You are required to find and print all connected
      // components of the graph.
      public static ArrayList<ArrayList<Integer>> getConnectedComponents(ArrayList<Edge>[] graph, boolean[] visited) {

        ArrayList<ArrayList<Integer>> comps = new ArrayList<>();
        for (int i = 0; i < graph.length; i++) {

           if (visited[i] == false) {
              ArrayList<Integer> verticesOfComponent = new ArrayList<>();
              getComponent(graph, i, visited, verticesOfComponent);
              comps.add(verticesOfComponent);
           }
        }

        return comps;
     }

     private static void getComponent(ArrayList<Edge>[] graph, int src, boolean[] visited,
           ArrayList<Integer> verticesOfComponent) {
        visited[src] = true;
        ArrayList<Edge> edges = graph[src];
        verticesOfComponent.add(src);
        for (Edge edge : edges) {
           if (visited[edge.nbr] == false) {
            getComponent(graph, edge.nbr, visited, verticesOfComponent);  
           }
        }

     }

     // Level Order traversal or BFT
     private static void breadthFirstTraversal(ArrayList<Edge>[] graph, boolean[] visited, int src) {

         //for (int i = 0; i < graph.length; i++) {
             //if (visited[i] = false) {
                 ArrayDeque<BfsPair> childrenArrayDeque = new ArrayDeque<>();
                 childrenArrayDeque.add(new BfsPair(src, src+""));
                 traverse(graph, visited, childrenArrayDeque);
             //}
         //}

     }

     static class BfsPair {
         private int vtx;
         private String path;

         public BfsPair(int vtx, String path) {
             this.vtx = vtx;
             this.path = path;
         }

     }

     public static void traverse(ArrayList<Edge>[] graph, boolean[] visited, ArrayDeque<BfsPair> childrenArrayDeque) {
        
         while (childrenArrayDeque.size() > 0) {
             BfsPair src = childrenArrayDeque.remove();
             
             if(visited[src.vtx] == true)
                continue;
             visited[src.vtx] = true;
             ArrayList<Edge> edges = graph[src.vtx];
             
             System.out.print(src.vtx + "@" + src.path );
             for (Edge edge : edges) {
                 if(visited[edge.nbr]==false )
                    childrenArrayDeque.add(new BfsPair(edge.nbr, src.path+edge.nbr));
             }
             System.out.println();
         }
     }
}