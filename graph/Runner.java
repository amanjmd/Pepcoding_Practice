package graph;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

import graph.Graphs.Edge;

public class Runner{

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        ArrayList<Edge>[] graph = Graphs.createGraph(br);
        Graphs.printGraph();

    }

}