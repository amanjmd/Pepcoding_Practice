package Trees;

import java.util.ArrayList;
import java.util.List;

public class Node {
    
    public  Integer data;
    public List<Node> children = new ArrayList<>();;

    public List<Node> getChildren() {
        return children;
    }
    public void setChildren(List<Node> children) {
        this.children = children;
    }

    public Integer getData() {
        return data;
    }
    public void setData(Integer data) {
        this.data = data;
    }
    
    @Override
    public String toString() {
        
        return super.toString();
    }
}
