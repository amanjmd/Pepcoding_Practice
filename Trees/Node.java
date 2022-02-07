package Trees;

import java.util.List;

public class Node {
    
    private Integer data;
    private List<Node> children;

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
