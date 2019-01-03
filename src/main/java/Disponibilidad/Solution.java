package Disponibilidad;

import Json.Nodes;
import java.util.List;

public class Solution {
    private List<Nodes> path;
    private int totalcost;

    public List<Nodes> getPath() {
        return path;
    }

    public void setPath(List<Nodes> path) {
        this.path = path;
    }

    public int getTotalcost() {
        return totalcost;
    }

    public void setTotalcost(int totalcost) {
        this.totalcost = totalcost;
    }

    public void addNode(Nodes node){
        this.path.add(node);
    }

    public void removeNode(int index){
        this.path.remove(index);
    }
}
