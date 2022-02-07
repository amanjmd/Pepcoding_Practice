package Trees;

import java.util.Stack;

public class Tree {
    
    public static void traversal(Node node){
        //Pre Node 
        System.out.println("Node Pre -> " + node.getData());
        
        for(Node child: node.getChildren()){
            System.out.println("Edge Pre -> " + node.getData()+ "-" + child.getData());
            traversal(child);
            System.out.println("Edge Post -> " + node.getData()+ "-" + child.getData());
        }

        //Post Node
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
    

}
