package DataStructures;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import Trees.*;

public class Runner {
    
    public static void main(String[] args) {
        int n = 12;
        int[] arr = {10 ,20 ,-1, 30, 50, -1, 60, -1, -1, 40, -1, -1};
        
        Node root = Tree.construct(arr);
        Tree.traversal(root);
    }
}
