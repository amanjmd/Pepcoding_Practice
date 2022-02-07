package Trees;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

public class Tree {

    public static void traversal(Node node) {
        // Pre Node
        System.out.println("Node Pre -> " + node.getData());

        for (Node child : node.getChildren()) {
            System.out.println("Edge Pre -> " + node.getData() + "-" + child.getData());
            traversal(child);
            System.out.println("Edge Post -> " + node.getData() + "-" + child.getData());
        }

        // Post Node
        System.out.println("Node Post -> " + node.getData());
    }

    public static Node construct(int[] arr) {
        Node root = null;

        Stack<Node> st = new Stack<>();
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == -1) {
                st.pop();
            } else {
                Node t = new Node();
                t.data = arr[i];

                if (st.size() > 0) {
                    st.peek().children.add(t);
                } else {
                    root = t;
                }

                st.push(t);
            }
        }

        return root;
    }

    public static void mirror(Node node) {
        for(Node child:node.children)
        mirror(child);

    //   for(int i = 0 ; i < node.children.size()/2 ;i++){
    //       swapNode(node.children, i, node.children.size()-i-1);
    //   }
    Collections.reverse(node.children);


    }

    public static void swapNode(List<Node> nodes, int source, int dest) {

        Node temp = nodes.get(source);
        nodes.set(source, nodes.get(dest));
        nodes.set(dest, temp);
    }

    public static void display(Node node) {

        System.out.print(node.data + " -> ");
        for (Node child : node.children)
            System.out.print(child.data + ", ");

        System.out.println(".");
        for (Node child : node.children)
            display(child);

    }


    public static void removeLeaves(Node node) {
        ArrayList<Node> nodesToRemove= new ArrayList<>();
        for(Node child : node.children){
            if(child.children.size() >0){
                removeLeaves(child);
            }
            else{
                nodesToRemove.add(child);
            }
        }
        node.children.removeAll(nodesToRemove);
      }
}
